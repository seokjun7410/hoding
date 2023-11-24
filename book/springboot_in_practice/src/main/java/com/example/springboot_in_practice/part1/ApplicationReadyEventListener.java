package com.example.springboot_in_practice.part1;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ApplicationReadyEventListener {

    //애플리케이션이 요청을 처리할 준비가 됐을때 발행된다. 초기화가 완료된 시점임으로 애플리케이션 내부상태를 변경하는 것은 권장되지 않는ㄷ.ㅏ
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent){
        WebApplicationType webApplicationType = applicationReadyEvent.getSpringApplication().getWebApplicationType();
        System.out.println("webApplicationType is "+webApplicationType);
        System.out.println("Application Ready Event generated at " + LocalDateTime.now());
    }
}
