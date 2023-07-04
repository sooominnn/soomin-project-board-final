package com.soomin.projectboardfinal.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * fileName     : StatusCode
 * author       : lia
 * date         : 2023/07/04
 * description  : 상태코드
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/04       lia          최초 생성
 */
@Getter
public enum StatusCode {

    /* 200 OK : 요청성공 */
    REQUEST_SUCCESS			(HttpStatus.OK,						"요청을 성공했습니다."),
    UPDATE_SUCCESS			(HttpStatus.OK,                     "업데이트를 성공했습니다."),
    LOGIN_SUCCESS			(HttpStatus.OK,						"로그인을 성공했습니다."),
    LOGOUT_SUCCESS          (HttpStatus.OK,                     "로그아웃 되었습니다."),
    SEARCH_SUCCESS			(HttpStatus.OK,						"조회를 성공했습니다."),
    DELETE_SUCCESS			(HttpStatus.OK,						"삭제를 성공했습니다."),

    /* 201 CREATED : 요청성공 & 새로운 리소스 생성 */
    REGISTER_SUCCESS		(HttpStatus.CREATED , 				"등록을 성공했습니다."),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REQUEST	        (HttpStatus.BAD_REQUEST,            "유효하지 않은 요청입니다."),
    INVALID_PARAMETER		(HttpStatus.BAD_REQUEST,			"잘못된 입력값입니다."),
    MISSING_PARAMETER		(HttpStatus.BAD_REQUEST,			"입력값이 누락됐습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    UNAUTHORIZED		    (HttpStatus.UNAUTHORIZED,			"인증되지 않은 사용자입니다."),

    /* 403 FORBIDDEN : 권한없음 */
    FORBIDDEN		        (HttpStatus.FORBIDDEN,				"권한이 없습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    NOT_FOUND               (HttpStatus.NOT_FOUND,              "해당 정보를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND		(HttpStatus.NOT_FOUND,				"해당 유저정보를 찾을 수 없습니다."),

    /* 408 REQUEST_TIMEOUT */
    REQUEST_TIMEOUT         (HttpStatus.REQUEST_TIMEOUT,        "요청시간이 만료되었습니다."),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATED_RESOURCE		(HttpStatus.CONFLICT, 				"데이터가 이미 존재합니다."),

    /* 500 INTERNAL_SERVER_ERROR : 에러 */
    ERROR					(HttpStatus.INTERNAL_SERVER_ERROR, 	"오류발생.");

    private final HttpStatus httpStatus;
    private final String detail;


    StatusCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail		= detail;
    }
}
