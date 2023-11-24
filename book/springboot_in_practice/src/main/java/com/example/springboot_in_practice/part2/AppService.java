package com.example.springboot_in_practice.part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final AppProperties appProperties;
    @Autowired
    public AppService(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public AppProperties getAppProperties(){
        return this.appProperties;
    }
}
