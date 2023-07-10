package com.soomin.projectboardfinal.controller;

import com.soomin.projectboardfinal.dto.req.ReqJoinDto;
import com.soomin.projectboardfinal.dto.req.ReqLoginDto;
import com.soomin.projectboardfinal.entity.Member;
import com.soomin.projectboardfinal.service.serviceinterface.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

/**
 * fileName     : MemberController
 * author       : lia
 * date         : 2023/07/03
 * description  : 회원 Controller
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 루트 페이지
     *
     * @param   model       model
     * @param   memberId    회원 고유번호
     * @return  로그인 결과
     */
    @GetMapping(value = {"", "/"})
    public String home(Model model, @SessionAttribute(name = "memberId", required = false) long memberId) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        Member loginMember = memberService.getLoginMember(memberId);

        if(loginMember != null) {
            model.addAttribute("nickname", loginMember.getNickname());
        }

        return "home";
    }

    /**
     * 회원가입 페이지
     *
     * @param   model       model
     * @return  회원가입 페이지
     */
    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        model.addAttribute("reqJoinDto", new ReqJoinDto());
        return "join";
    }

    /**
     * 회원가입
     *
     * @param   reqJoinDto      회원가입 정보
     * @param   bindingResult   bindingResult
     * @param   model           model
     * @return  로그인 결과
     */
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute ReqJoinDto reqJoinDto, BindingResult bindingResult, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        // loginId 중복 체크
        if(memberService.checkLoginIdDuplicate(reqJoinDto.getEmail())) {
            bindingResult.addError(new FieldError("reqJoinDto", "loginId", "로그인 아이디가 중복됩니다."));
        }
        // 닉네임 중복 체크
        if(memberService.checkNicknameDuplicate(reqJoinDto.getNickname())) {
            bindingResult.addError(new FieldError("reqJoinDto", "nickname", "닉네임이 중복됩니다."));
        }
        // password passwordCheck 같은지 체크
        if(!reqJoinDto.getPassword().equals(reqJoinDto.getPasswordCheck())) {
            bindingResult.addError(new FieldError("reqJoinDto", "passwordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "join";
        }

        memberService.join(reqJoinDto);
        return "redirect:/session-login";
    }

    /**
     * 로그인 페이지
     *
     * @param   model       model
     * @return  로그인 페이지
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        model.addAttribute("reqLoginDto", new ReqLoginDto());
        return "login";
    }

    /**
     * 로그인
     *
     * @param   reqLoginDto         로그인 정보
     * @param   bindingResult       bindingResult
     * @param   httpServletRequest  httpServletRequest
     * @param   model               model
     * @return  로그인 결과
     */
    @PostMapping("/login")
    public String login(@ModelAttribute ReqLoginDto reqLoginDto, BindingResult bindingResult,
                        HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        Member member = memberService.login(reqLoginDto);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(member == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        if(bindingResult.hasErrors()) {
            return "login";
        }

        // 로그인 성공 => 세션 생성

        // 세션을 생성 전에 기존 세션 파기
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);  // Session 없으면 생성
        // 세션에 userId를 넣어줌
        session.setAttribute("memberId", member.getId());
        session.setMaxInactiveInterval(1800); // Session 30분동안 유지

        return "redirect:/session-login";
    }

    /**
     * 로그아웃
     *
     * @param   request     httpServletRequest
     * @param   model       model
     * @return  로그아웃 결과
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        HttpSession session = request.getSession(false);  // Session 없으면 null return
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/session-login";
    }
}
