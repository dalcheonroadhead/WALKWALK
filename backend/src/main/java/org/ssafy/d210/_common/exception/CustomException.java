package org.ssafy.d210._common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


// 1. 백앤드 팀원들이 직접 디자인 한 ErrorType 을 진짜로 사용하는 CustomError
@Getter
@RequiredArgsConstructor //멤버 변수 전부 넣은 생성자 만들어주기
public class CustomException extends RuntimeException{
    private final ErrorType errorType;

    @Override
    public String getMessage() {
        return errorType.getMsg();
    }

    public CustomException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
