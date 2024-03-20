package org.ssafy.d210._common.response;

import lombok.Getter;

@Getter
public enum MsgType {

    // ====================== MEMBERS ============================
    SIGNUP_SUCCESSFULLY("회원가입이 완료되었습니다!"),
    SEARCH_SUCCESSFULLY("데이터 조회 성공했습니다!"),
    GENERATE_TOKEN_SUCCESSFULLY("토큰 생성 완료"),
    // ====================== File Upload =====================
    UPLOAD_FILE_SUCCESSFULLY("파일 업로드를 성공적으로 했습니다."),

    // ====================== ITEMS ============================
    GET_ITEM_LIST_SUCCESSFULLY("아이템 조회에 성공하였습니다.")

    ;

    private final String msg;

    MsgType(String msg) {
        this.msg =msg;
    }
}
