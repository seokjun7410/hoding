package com.example.springboot_in_practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

/**
 * @SpringBootApplication
 *  - @SpringBootConfiguration : 빈 관리 설정 클래스에 붙히고 설정과정에 참여한다.
 *  - @EnableAutoConfiguration : 클래스패스에 JAR바탕으로 APP을 자동구성한다.
 *  - @ComponentScan : 스프링 빈으로 관리된다.
 */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class) //@ConfigurationProperties 스캔, @ConfigurationPropertiesScan -> 패키지 하위 자동스캔
public class SpringbootInPracticeApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringbootInPracticeApplication.class);

    public static void main(String[] args)
    {
//        SpringApplication.run(SpringbootInPracticeApplication.class, args);

//        SpringApplication 직접생성, reactive dependency를 설정해야 정상부팅된다.
        SpringApplication springApplication = new SpringApplication(SpringbootInPracticeApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.addListeners(new ApplicationStartingEventListener());

        //SpringApplication 에서 property 설정
        Properties properties = new Properties();
        properties.setProperty("spring.config.on-not-found","ignore"); //설정파일이 존재하지 않아도 예외를 발생시키지 않는다.
        springApplication.setDefaultProperties(properties);

        ConfigurableApplicationContext context = springApplication.run(args);

        //customProperty 접근
        AppService appService = context.getBean(AppService.class);
        AppProperties appProperties = appService.getAppProperties();

        log.info(appProperties.toString());

    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("hello spring boot");
    }
}
