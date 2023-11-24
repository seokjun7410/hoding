package com.example.springboot_in_practice;

import com.example.springboot_in_practice.part1.ApplicationStartingEventListener;
import com.example.springboot_in_practice.part2.AppProperties;
import com.example.springboot_in_practice.part2.AppService;
import com.example.springboot_in_practice.part2.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;
import java.util.Set;

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
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        User user1 = new User("sbip01", "sbip");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        log.error("Password for user1 do not adhere to the password policy");
        violations.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user2 = new User("sbip02", "Sbip01$4UDfg");
        violations = validator.validate(user2);
        if(violations.isEmpty()) {
            log.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("sbip03", "Sbip01$4UDfgggg");
        violations = validator.validate(user3);
        log.error("Password for user3 violates maximum repetitive rule");
        violations.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user4 = new User("sbip04", "Sbip014UDfgggg");
        violations = validator.validate(user4);
        log.error("Password for user4 violates special character rule");
        violations.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

    }
}
