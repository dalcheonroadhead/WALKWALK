package org.ssafy.d210._common.response;

import org.ssafy.d210._common.exception.ErrorResponse;

public class ResponseUtils {

    // 1. 돌려줄 데이터가 있는 반환 매소드
    // GenericMethod는 <T> 리턴타입 (인수 타입)으로 이루어져 있다. -> <T>는 제네릭 매소드임을 알리는 것으로 반환 타입 앞에 쓴다.
    // 뒤의 T는 APIResponseDto라는 클래스의 T == 해당 클래스는 제네릭 타입을 사용하고 있습니다.
    public static <T> ApiResponseDto<T> ok (T data, MsgType msg){
        return ApiResponseDto.<T>builder() //-> 제네릭 타입의 Builder 사용. 어떤 타입의 builder인지는 런타임 시 확정
                .data(data)
                .msg(msg.getMsg())
                .build();
    }

    // 2. 돌려줄 Data가 없는 경우를 위한 반환 매소드
    public static ApiResponseDto<Void> ok (MsgType msg) {
        return ApiResponseDto.<Void>builder()
                .msg(msg.getMsg())
                .build();
    }

    // 3. Error 반환 매소드
    public static ApiResponseDto<Void> error(ErrorResponse error) {
        return ApiResponseDto.<Void>builder()
                .error(error)
                .build();
    }
}
