package com.soomin.projectboardfinal.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * fileName     : Response
 * author       : lia
 * date         : 2023/07/04
 * description  : Response
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/04       lia          최초 생성
 */
@Getter
public class Response {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final String detail;
    private final Object data;

    @Builder
    public Response(int status, String error, String code, String message, String detail, Object data) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.data = data;
    }

    /**
     * response 상태코드 & 데이터
     *
     * @param 	statusCode	상태코드
     * @param 	data		데이터
     * @return	response
     */
    public static ResponseEntity<Response> toResponseEntity(StatusCode statusCode, Object data){

        return ResponseEntity
                .status	(statusCode.getHttpStatus())
                .body	(Response.builder()
                        .status	(statusCode.getHttpStatus().value())
                        .error	(statusCode.getHttpStatus().name())
                        .code	(statusCode.name())
                        .message(statusCode.getDetail())
                        .data	(data)
                        .build());
    }
}
