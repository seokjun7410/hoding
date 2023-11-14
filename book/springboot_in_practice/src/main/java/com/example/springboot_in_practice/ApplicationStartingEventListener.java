package com.example.springboot_in_practice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *  앱 스타트업 극초기에 발행되는 이벤트
 */
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(final ApplicationStartingEvent event) {
        WebApplicationType webApplicationType = event.getSpringApplication().getWebApplicationType();
        System.out.println("webApplicationType is "+webApplicationType);
        System.out.println("Application Starting Event logged at "+ LocalDateTime.now());
    }
}
