package org.ssafy.d210._common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.d210._common.entity.RefreshTokenInRedis;
import org.ssafy.d210._common.repository.RefreshTokenInRedisRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenInRedisService {

    private final RefreshTokenInRedisRepository refreshTokenInRedisRepository;


    // A. 토큰 생성 시, 유저ID, RT, AT 순으로 저장
    @Transactional
    public void saveTokenInfo(Long memberId, String refreshToken, String accessToken){
        refreshTokenInRedisRepository.save(new RefreshTokenInRedis(String.valueOf(memberId), refreshToken, accessToken));
    }

    // B. RT를 삭제 -> 주어진 AccessToken을 통해 그것이 포함된 레코드를 찾고
    //                만약에 레코드가 존재한다면 삭제
    @Transactional
    public void removeRefreshToken(String accessToken) {
        refreshTokenInRedisRepository.findByAccessToken(accessToken)
                .ifPresent(refreshTokenInRedisRepository::delete);
    }


}
