package org.ssafy.d210._common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.ResponseUtils;

@Slf4j
@RestControllerAdvice // RestController를 위한 부가기능

/*
*   Controller 레벨에서 발생한 Error
*   (바인딩에서 바로 Error 가 났든지 혹은 서비스 단에서 Error 가 난 것이 종국에 Controller 레벨로 왔든지)
*   -> 이때 서버에서 에러를 내버리고 멈추는 게 아니라, 클라이언트에게는 정상적인 status를 보내고
*   -> 응답 메세지 내에서 실제 발생한 Error 와 그 내용을 보내주어, 클라이언트가 융통성 있게 처리할 수 있도록 해주는 게 핵심.
*   -> 추척하여 무엇이 문제인지 특정할 수 있게 만드는 것이 중요 -> Custom Exception의 세분화가 필요!
* */
public class GlobalExceptionHandler {

    // 1. request DTO에 맞지 않는, 유효하지 않은 데이터가 인수로 들어왔을 시의 에러를 다루는 Handler
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponseDto<Void>> methodValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()){
            sb.append(fieldError.getField()).append(":")
                    .append(fieldError.getDefaultMessage())
                    .append(",");
        }

        log.error("파라미터 유효성 검증에서 오류 발생:{}", e.getBindingResult());

        return ResponseEntity.badRequest().body(ResponseUtils.error(ErrorResponse.of(String.valueOf(sb))));
    }

    // 2. Custom Error가 발생 했을 시, 처리해주는 Handler
    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ApiResponseDto<Void>> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorType());
        log.error("에러가 발생했습니다. 에러 내역: {}", e.getErrorType());

        return ResponseEntity
                .status(e.getErrorType().getCode())
                .body(ResponseUtils.error(errorResponse));
    }
}
