package com.example.springboot_in_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootInPracticeApplication {

    public static void main(String[] args)
    {
//        SpringApplication.run(SpringbootInPracticeApplication.class, args);

        //SpringApplication 직접생성
        SpringApplication springApplication = new SpringApplication(SpringbootInPracticeApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
        springApplication.run(args);
    }

}
