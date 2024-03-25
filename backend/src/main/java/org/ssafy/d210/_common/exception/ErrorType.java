package org.ssafy.d210._common.exception;

import lombok.Getter;


// 자바에서 열거형(Enum)은 클래스이다. 따라서 생성자와 매서드를 안에서 쓸 수가 있다.
@Getter
public enum ErrorType {
    //-------------------로그인 & 회원 가입-----------------------------
    TOKEN_DOESNT_EXIST(401, "[HEADER]에 [TOKEN]이 존재하지 않습니다."),
    NOT_VALID_TOKEN(401, "[Token]이 유효하지 않습니다."),
    EXPIRED_TOKEN(401, "[TOKEN]의 유효기간이 끝났습니다."),

    //----------------------------MEMBERS----------------------------
    NOT_FOUND_MEMBER(401, "해당 멤버가 존재하지 않습니다."),
    CANT_PASS_SECURITY(401, "[Security Config]를 통과할 수 없습니다. 접두사로 [/api]를 붙였는지 확인해주세요"),

    //-------------------------파일 업로드 부분-------------------------
    CANT_UPLOAD_FILE(415, "해당 파일을 업로드할 수가 없습니다."),
    CANT_DELETE_FILE(500, "임시파일 삭제에 실패했습니다."),

    //----------------------------WALLETS-----------------------------
    NOT_FOUND_MEMBER_ACCOUNT(401, "해당 멤버 계좌가 존재하지 않습니다."),

    //----------------------------HALLEY_GALLEY-----------------------
    NOT_FOUND_HALLEY(401, "할리가 존재하지 않습니다."),
    NOT_FOUND_GALLEY(401, "갈리가 존재하지 않습니다."),
    NOT_FOUND_HALLEY_GALLEY(401, "할리/갈리 관계가 존재하지 않습니다."),
    ALREADY_SEND_REQUEST(401, "이미 요청을 보냈습니다."),
    NOT_FOUND_HALLEY_REQUEST_LIST(401, "요청받은 할리 목록이 존재하지 않습니다."),

    //----------------------------MISSION-----------------------------
    CANT_ADD_MISSION(401, "미션을 등록할 수 없습니다."),
    NOT_ENOUGH_MONEY(401, "머니가 부족합니다."),
    NOT_FOUND_MISSION(401, "미션이 존재하지 않습니다."),

    //----------------------------FRIENDS-----------------------------
    ALREADY_SEND_FRIEND_REQUEST(401, "이미 요청을 보냈습니다."),
    NOT_FOUND_FRIEND(401, "친구가 존재하지 않습니다."),
    ;

    private int code;
    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg =msg;
    }
}


