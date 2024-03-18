package org.ssafy.d210._common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Getter
public class ErrorResponse {

    private int status;
    private String msg;

    // 1. Builder 만들기 용 생성자
    @Builder
    private ErrorResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    // 2. ErrorType을 받아서 Error Response를 만들 때,
    public static ErrorResponse of (ErrorType errorType){
        return ErrorResponse.builder()
                .status(errorType.getCode())
                .msg(errorType.getMsg())
                .build();
    }

    // 3. 메세지만 인수로 받았을 때는 Error Status를 무조건 400으로 통일! (응 클라이언트 잘못~)
    public static ErrorResponse of (String msg) {
        return ErrorResponse.builder()
                .status(400)
                .msg(msg)
                .build();
    }


    // 4. ErrorType이 아닌 멤버변수를 그대로 받았을 경우,
    public static ErrorResponse of (int status, String msg) {
        return ErrorResponse.builder()
                .status(status)
                .msg(msg)
                .build();
    }

    /* 5. Spring Validation 을 사용해, Controller 바인딩 단계에서 들어온 값의 유효성을 체크할 경우,
          Controller 로직에 닿기도 전에 Error 가 난다.
          위와 같이 바인딩 과정에서 검증 오류가 발생할 경우, 그 오류 내용을 보관하는 객체가 바로 BindingResult 이다.
          BindingResult 에 담긴 Error 또한 ErrorResponse 형태에 맞추어 반환한다.
    */
    public static ErrorResponse of(BindingResult bindingResult) {
        String msg = "";

        if(bindingResult.hasErrors()){
            msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(msg)
                .build();
    }

}
