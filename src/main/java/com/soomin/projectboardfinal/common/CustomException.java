package com.soomin.projectboardfinal.common;

import lombok.Getter;

/**
 * fileName     : CustomException
 * author       : lia
 * date         : 2023/07/05
 * description  : CustomException
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/05       lia          최초 생성
 */
@Getter
public class CustomException extends RuntimeException{

    private final StatusCode statusCode;
    private String msg;

    public CustomException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
    public CustomException(StatusCode statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg		= msg;
    }
}
