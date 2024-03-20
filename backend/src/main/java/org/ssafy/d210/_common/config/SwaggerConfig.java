package org.ssafy.d210._common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "WALK_WALK API 명세서",
        description = "효도하기 10조? OPEN API 명세서",
        version = "v1")
)

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi WalkWalkOpenApi() {
        // 1. "/v1/**" 경로에 매칭되는 API를 그룹화하여 문서화 한다.
        String [] paths = {"/v1/**"};

        return GroupedOpenApi.builder()
                .group("WALK_WALK API v1")
                .pathsToMatch(paths)
                .build();
    }
}