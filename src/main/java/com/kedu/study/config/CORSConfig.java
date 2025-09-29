package com.kedu.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CORSConfig implements WebMvcConfigurer{
   
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      //나중에는 보안 관련 설정 더 필요
      registry.addMapping("/**") //서버의 어떤 url로 요청을 하든지
            .allowedOrigins("*") // 모든 출처 -> 이부분은 내 아이피에 대해서만 으로 나중에 수정해야함
            .allowedMethods("*") // get, post 방식 상관 없이 전부
            .allowedHeaders("*"); // 보든 요청 헤더에 대해 전부 허가하겠음
      		 // 보든 요청 헤더에 대해 전부 허가하겠음
   }
   
}