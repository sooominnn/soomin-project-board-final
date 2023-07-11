package com.soomin.projectboardfinal.service.serviceinterface;

import com.soomin.projectboardfinal.dto.req.ReqJoinDto;
import com.soomin.projectboardfinal.dto.req.ReqLoginDto;
import com.soomin.projectboardfinal.entity.Member;

/**
 * fileName     : MemberService
 * author       : lia
 * date         : 2023/07/03
 * description  : 회원 Service
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
public interface MemberService {
    Member getLoginMember(long memberId);

    boolean checkLoginIdDuplicate(String email);

    boolean checkNicknameDuplicate(String nickname);

    void join(ReqJoinDto reqJoinDto);

    Member login(ReqLoginDto reqLoginDto);
}
