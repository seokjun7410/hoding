package com.example.springboot_in_practice;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.StringJoiner;

//@ConstructorBinding//springboot.3.0 변경사항 -> 생성자 1개일 경우 생략 가능

@ConfigurationProperties("app.sbip.ct") //prefix
public class AppProperties {
    private final String name;
    private final String ip;
    private final int port;
    private final Security security;


    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Security getSecurity() {
        return security;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AppProperties.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("ip='" + ip + "'")
                .add("port=" + port)
                .add("security=" + security)
                .toString();
    }

    public AppProperties(final String name, final String ip, final int port, final Security security) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }


    private static class Security {
        private boolean enabled;
        private final String token;
        private final List<String> roles;

        @Override
        public String toString() {
            return new StringJoiner(", ", Security.class.getSimpleName() + "[", "]")
                    .add("enabled=" + enabled)
                    .add("token='" + token + "'")
                    .add("roles=" + roles)
                    .toString();
        }

        public Security(final boolean enabled, final String token, final List<String> roles) {
            this.enabled = enabled;
            this.token = token;
            this.roles = roles;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getToken() {
            return token;
        }

        public List<String> getRoles() {
            return roles;
        }
    }
}
