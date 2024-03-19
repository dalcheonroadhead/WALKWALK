package org.ssafy.d210._common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.ssafy.d210._common.handler.JwtAccessDeniedHandler;
import org.ssafy.d210._common.handler.JwtAuthenticationEntryPoint;
import org.ssafy.d210._common.handler.OAuth2LoginFailureHandler;
import org.ssafy.d210._common.handler.OAuth2LoginSuccessHandler;
import org.ssafy.d210._common.jwt.JwtFilter;
import org.ssafy.d210._common.jwt.TokenProvider;
import org.ssafy.d210._common.respository.HttpCookieOAuth2AuthorizationRequestRepository;
import org.ssafy.d210._common.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    // * Security용
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    // * OAuth2 페이지용
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    // 1. 비밀번호를 안전하게 저장할 수 있도록 비밀번호의 단방향 암호화를 지원하는 인터페이스
    // 우리는 해당 인터페이스의 구현체 중 하나인 BCryptPasswordEncoder를 사용한다.
    // 해당 함수는 Bcrypt라는 해시 함수를 사용해 비밀번호를 암호화 한다.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    /* 2.  SpringSecurity에서 제공하는 인증, 인가를 위한 필터들의 모음
     *     가장 핵심이 되는 기능을 제공, 거의 대부분의 Security 서비스는 이 Filter Chain에서 실행됨
     *     개발 취지에 따라 기본 제공되는 필터들 사이에 커스텀 필터를 넣어도 된다.
     */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable) // 1)
                .httpBasic(AbstractHttpConfigurer::disable) // 2)
                .csrf(AbstractHttpConfigurer::disable)      // 3)
                .cors((request) -> request.configurationSource(corsConfigurationSource()))
                .headers((headers) -> headers.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                ))                                          // 4)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )                                           // 5)
                .exceptionHandling(authentication ->        // 6)
                        authentication.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeHttpRequests(                     // 7)
                        (authorizeRequest) ->
                                authorizeRequest
                                        .requestMatchers("/api/Oauth/callback/google/token/**").permitAll()
                                        .requestMatchers("/api/Oauth/authorize").permitAll()
                                        .requestMatchers("/oauth2//**").permitAll() //모든 소셜 로그인 후 인가코드 Redirect URL는 다음과 같이 설정
                                        .requestMatchers("/hello").permitAll()
                                        .anyRequest().authenticated()

                )
                .oauth2Login(                               // 9)
                        (oauth2Login) -> oauth2Login
                                        .authorizationEndpoint(
                                                authorizationEndpointConfig ->
                                                        authorizationEndpointConfig
                                                                .baseUri("/oauth2/authorization")
                                                                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
                                        )
                                .successHandler(oAuth2LoginSuccessHandler)
                                .failureHandler(oAuth2LoginFailureHandler)
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig.userService(customOAuth2UserService))
                )
                                                            // 10)
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.getOrBuild();


    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");    // 인증용 AccessToken 가지고 오는 경우도 허용
        configuration.addExposedHeader("Authorization-refresh");
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // 모든 경로에 대해 CORS 설정 적용

        return source;
    }


    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository(){
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

}
