package com.soomin.projectboardfinal.service;

import com.soomin.projectboardfinal.dto.req.ReqJoinDto;
import com.soomin.projectboardfinal.dto.req.ReqLoginDto;
import com.soomin.projectboardfinal.entity.Member;
import com.soomin.projectboardfinal.service.serviceinterface.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * fileName     : MemberServiceImpl
 * author       : lia
 * date         : 2023/07/03
 * description  : 회원 ServiceImpl
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    @Override
    @Transactional(readOnly = true)
    public Member getLoginMember(long memberId) {
        return null;
    }

    @Override
    @Transactional
    public boolean checkLoginIdDuplicate(String email) {
        return false;
    }

    @Override
    @Transactional
    public boolean checkNicknameDuplicate(String nickname) {
        return false;
    }

    @Override
    @Transactional
    public void join(ReqJoinDto reqJoinDto) {

    }

    @Override
    @Transactional
    public Member login(ReqLoginDto reqLoginDto) {
        return null;
    }


}
