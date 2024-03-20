package org.ssafy.d210._common.exception;

import lombok.Getter;


// 자바에서 열거형(Enum)은 클래스이다. 따라서 생성자와 매서드를 안에서 쓸 수가 있다.
@Getter
public enum ErrorType {
    //-------------------로그인 & 회원 가입-----------------------------
    TOKEN_DOESNT_EXIST(401, "[HEADER]에 [TOKEN]이 존재하지 않습니다."),
    NOT_VALID_TOKEN(401, "[Token]이 유효하지 않습니다."),

    //----------------------------MEMBERS----------------------------
    MEMBER_NOT_FOUND(400, "해당 멤버가 존재하지 않습니다."),

    //-------------------------파일 업로드 부분-------------------------
    CANT_UPLOAD_FILE(415, "해당 파일을 업로드할 수가 없습니다."),

    //----------------------------WALLETS-----------------------------
    MEMBER_ACCOUNT_NOT_FOUND(400, "해당 멤버가 존재하지 않습니다.")
    ;

    private int code;
    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg =msg;
    }
}


