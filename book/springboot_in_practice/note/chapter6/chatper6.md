# 6장 스프링부트 시큐리티 응용
madeBy hoding  
reference [SpringBoot In parctice](https://product.kyobobook.co.kr/detail/S000208713876)  
Date 23.01.02

## 스프링부트 Https 활성화
- application.properties 에서 키스토어를 지정합니다.
```yaml
# The format used for the keystore. It could be set to JKS in case it is a JKS file
server.ssl.key-store-type=PKCS12
# The path to the keystore containing the certificate
server.ssl.key-store=classpath:keystore/sbip.p12
# The password used to generate the certificate
server.ssl.key-store-password=p@ssw0rd
# The alias mapped to the certificate
server.ssl.key-alias=sbip
# HTTPS Port
server.port=8443
```
- spring security configure 에서 모든 요청에 HTTPS를 강제합니다.
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().anyRequest().requiresSecure()
```
- 모든 요청에 대해 제약사항을 적용하고 8080을 8443으로 리다이렉트 시킵니다.
```java

@SpringBootApplication
public class CourseTrackerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerSpringBootApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setRedirectPort(8443);
        return connector;
    }
}
```

## 스프링 Voalt
- application.properties에 비밀 정보를 기록하면 노출 위험이 크고 공유가 어렵다.
- 스프링 클라우드 볼트를 사용해서 비밀정보를 더 안전하게 관리할 수 있다.
- 스프링 볼트를 다운받고 valut.conf를 작성합니다.
```java
//vault.conf
    backend "inmem"{
        }
        
    listener "tcp"{
        address = "0.0.0.0:8200"
        tls_disable = 1
        }
        disable_mlock
```
- 볼트 서버를 실행합니다.
- 다른 터미널 창을 열고 export VAULT_ADDR=http://localhost:8200 명령으로 환경변수를 설정합니다.
- 볼트는 기본적으로 sealed돼 있기 때문에 해제하려면 비밀키가 필요하기 떄문에 비밀키를 생성합니다.
- ./vault operator init
- ./vault operator unseal {key} X3 으로 봉인을 해제합니다.
- export VAULT_TOKEN={ROOT TOKEN} 초기 루트토큰을 환경변수로 등록합니다.
- kv 비밀 정보 엔진을 활성화합니다. ./vault secrets enable -path=secret kv
- 비밀 정보를 볼트에 저장합니다. ./vault write secret/coursetracker keystore=P@ss0rd
- 볼트를 사용할 수 있도록 스프링부트에 의존관계를 설정해줍니다.
- application.properties 에 볼트 설정을 추가합니다.
```yaml
spring.cloud.vault.token=s.zI6LI5IMyquIh2SPCPqBiNXg
spring.cloud.vault.authentication=token
spring.cloud.vault.host=localhost
spring.cloud.vault.port=8200
spring.cloud.vault.scheme=http
spring.config.import=vault://secret/coursetracker
spring.application.name=coursetracker
```
- 저장된 값을 아래 처럼 사용할 수 있습니다.
```yaml
server.ssl.key-store-password=${keystore}
```
- 예제에서는 vault operator init 명령으로 생성한 초기 루트 토큰을 생성하고 aplication.properties 파일에 저장 했는데, 실제 서비스 환경에서는 초기 루트 토큰값을 환경 변수나 다른 방법으로 공급 받도록 구성해야 합니다.

#### email 확인 기능 :사용자 등록과 이메일 발송을 이벤트로 구성하여 행위를 분리하자
- 실습 위주의 내용임으로 정리는 생략합니다.
#### 로그인 시도 횟수 제한 :캐시를 이용한 실패횟수 카운팅
- 실습 위주의 내용임으로 정리는 생략합니다.
#### 리멤버 미 기능 : 스프링 시큐리티는 리멤버 미 기능을 구현하는테 필요한 기능을 제공한다. 
- 실습 위주의 내용임으로 정리는 생략합니다.
#### 구글 리캡차 기능  
- 실습 위주의 내용임으로 정리는 생략합니다.
#### 구글 Authenticator 
- 실습 위주의 내용임으로 정리는 생략합니다.
#### Oauth2 인증 기능  
- 실습 위주의 내용임으로 정리는 생략합니다.
