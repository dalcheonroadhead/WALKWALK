package org.ssafy.d210;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaAuditing  // (1) 엔티티에 생성, 수정 시각을 Insert된 날짜로 자동으로 등록하는 것을 가능케 하는 어노테이션
@EnableScheduling   // (2) 스케줄러를 추가할 수 있게 해주는 어노테이션
@EnableAsync        // (3) Multi Threading이 가능하도록 해주는 어노테이션
public class BackendApplication {

    @GetMapping("/api/hello")
    String hello() {
        return "Hello World!";
    }
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
