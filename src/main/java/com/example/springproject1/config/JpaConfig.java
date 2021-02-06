package com.example.springproject1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //여기는 설정파일이 들어갑니다. 라는 의미의 어노테이션
@EnableJpaAuditing //Jpa에 대한 감시를 활성화시키겠다.
public class JpaConfig {

}
