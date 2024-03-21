package org.ssafy.d210._common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210._common.request.Base64URI;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.S3Base64Uploader;
import org.ssafy.d210._common.service.S3MultiPartUploader;

import java.io.IOException;
import java.util.Base64;

// 이건 테스트용, 다른 곳에서 구현이 다 된다면 지웁니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final S3MultiPartUploader s3MultiPartUploader;
    private final S3Base64Uploader s3Base64Uploader;

    // consume == 들어오는 데이터의 타입을 정한다.
    // consume를 쓰는 컨트롤러로 클라이언트가 요청을 보낸다면, 클라이언트는 무조건 consume가 제시한 데이터 타입 중 적어도 하나 이상을
    // 헤더에 명시해야한다. (이 경우 보내는 data가 JSON 혹은 MultipartValue임을 명시)

    // @RequestPart -> MultipartFile 형식의 데이터를 온전하게 바인딩 시켜주는 어노테이션
    // @RequestPart(value = "file") 이면 프론트에서 formData로 key-value 형태로 적은 MultiPartFile 안에서
    // key가 file에 해당하는 값을 해당 변수에 바인딩 시킨다.
    @PostMapping(value = "/uploadFile", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponseDto<String> uploadFile(@RequestPart(value = "file")MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            throw new CustomException(ErrorType.CANT_UPLOAD_FILE);
        }

        String imgUrl = s3MultiPartUploader.upload(multipartFile);

        return ResponseUtils.ok(imgUrl, MsgType.UPLOAD_FILE_SUCCESSFULLY);
    }


    @PostMapping(value = "/uploadBase64")
    public ApiResponseDto<String> uploadBase64(@RequestBody Base64URI base64Data) throws IOException{

        return ResponseUtils.ok(s3Base64Uploader.Base64ToHttp(base64Data.getFile()), MsgType.UPLOAD_FILE_SUCCESSFULLY);

    }

}
