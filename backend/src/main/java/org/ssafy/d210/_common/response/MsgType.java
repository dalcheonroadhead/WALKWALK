package org.ssafy.d210._common.response;

import lombok.Getter;

@Getter
public enum MsgType {

    // ====================== MEMBERS ============================
    SIGNUP_SUCCESSFULLY("회원가입이 완료되었습니다!"),
    ADD_INFO_SUCCESSFULLY("추가 정보 제대로 들어갔습니다!"),
    SEARCH_SUCCESSFULLY("데이터 조회 성공했습니다!"),
    GENERATE_TOKEN_SUCCESSFULLY("토큰 생성 완료"),
    LAST_LOGIN_UPDATED_AT("마지막 접속일 조회 성공했습니다."),
    GET_MY_PAGE_SUCCESSFULLY("마이 페이지를 성공적으로 조회했습니다."),

    // ====================== File Upload =====================
    UPLOAD_FILE_SUCCESSFULLY("파일 업로드를 성공적으로 했습니다."),

    // ====================== WALLETS ============================
    GET_EGG_MONEY_SUCCESSFULLY("아이템 조회에 성공하였습니다."),

    // ====================== ITEMS ============================
    GET_ITEM_LIST_SUCCESSFULLY("아이템 조회에 성공하였습니다."),

    // ====================== HALLEY_GALLEY ======================
    GET_HALLEY_LIST_SUCCESSFULLY("할리 목록 조회에 성공하였습니다."),
    GET_GALLEY_LIST_SUCCESSFULLY("갈리 목록 조회에 성공하였습니다."),
    POST_GALLEY_REQUEST_SUCCESSFULLY("갈리 요청 전송에 성공하였습니다."),
    PUT_GALLEY_RESPONSE_SUCCESSFULLY("갈리 요청 처리에 성공하였습니다."),
    GET_HALLEY_REQUEST_LIST_SUCCESSFULLY("할리 요청 목록 조회에 성공하였습니다."),
    GET_GALLEY_SUCCESSFULLY("갈리 정보 조회에 성공하였습니다."),
    GET_HALLEY_SUCCESSFULLY("할리 정보 조회에 성공하였습니다."),

    // ====================== WALK ============================
    GET_WEEKLY_EXERCISE_DATA_SUCCESSFULLY("이번 주 운동 데이터 조회에 성공하였습니다."),
    GET_LAST_SAVED_DATE("마지막으로 저장된 날짜 조회에 성공하였습니다."),
    SET_DEFAULT_CRITERIA("연령대 맞춤 운동 기준 등록을 성공하였습니다."),
    SET_CUSTOM_CRITERIA("사용자 맞춤 운동 기준 등록을 성공하였습니다."),
    GET_EXERCISE_CRITERIA("운동 기준 조회에 성공하였습니다."),
    INIT_CRITERIA("연령대 맞춤 운동 기준으로 초기화에 성공하였습니다."),

    // ====================== MISSION ==========================
    POST_MISSION_SUCCESSFULLY("미션 등록에 성공하였습니다."),

    // ====================== FRIENDS ==========================
    GET_MEMBER_INFO_SUCCESSFULLY("프로필 조회에 성공하였습니다."),
    GET_FRIEND_LIST_SUCCESSFULLY("친구 목록 조회에 성공하였습니다."),
    POST_FRIEND_REQUEST_SUCCESSFULLY("친구 요청 전송에 성공하였습니다."),
    PUT_FRIEND_REQUEST_SUCCESSFULLY("친구 요청 수락/거절에 성공하였습니다."),

    ;

    private final String msg;

    MsgType(String msg) {
        this.msg =msg;
    }
}
