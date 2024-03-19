package org.ssafy.d210._common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210._common.exception.ErrorResponse;

/*================ 모든 API 응답에 대한 DTO ====================*/
@Getter
/*
 * JsonInclude
 * -> Dto를 JSON으로 직렬화할 때, 다양한 제약사항을 걸어, 직렬화할 데이터를 필터링 할 수 있다.
 *    Jackson에서 제공하는 직렬화 기능인 ObjectMapper를 사용해도 되지만,
 *    ObjectMapper는 Default로는  "", null도 다 JSON에 넣어 반환해서 응답 data에 필요없는 게 많이 들어간다.
 *    이러한 문제를 @JsonInclude 어노테이션의 필터링 기능으로 해결할 수 있다.
 *
 *    밑에 NON_NULL은 값이 NULL인 데이터는 직렬화에 포함시키지 않는 것이다.
 *    ""이나 []같은 빈 String 빈 배열은 포함된다.
 * */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> { // Generic class 사용 -> 런타임에 타입 확정
    private T data;
    private String msg;
    private ErrorResponse error;

    @Builder
    public ApiResponseDto(T data, String msg, ErrorResponse error) {
        this.data = data;
        this.msg = msg;
        this.error = error;
    }

    public static <T> ApiResponseDto<T> of(MsgType msgType, T result) {
        return ApiResponseDto.<T>builder()
                .msg(msgType.getMsg())
                .data(result)
                .build();
    }

    public static <T> ApiResponseDto<T> of(ErrorResponse errorResponse, MsgType msgType, T result) {
        return ApiResponseDto.<T>builder()
                .error(errorResponse)
                .msg(msgType.getMsg())
                .data(result)
                .build();
    }
}