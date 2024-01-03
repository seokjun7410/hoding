# 6장 스프링부트 시큐리티 응용
madeBy hoding  
reference [SpringBoot In parctice](https://product.kyobobook.co.kr/detail/S000208713876)  
Date 23.01.03

## 스프링 부트 RESTful 웹서비스 설계 및 구축

## RESTful API 예외 처리


- CustomException을 정의해줍니다.
```java
public class CourseNotFoundException extends RuntimeException {
    //serialVersionUID에 관한 이야기 : https://hjjungdev.tistory.com/187
	private static final long serialVersionUID = 5071646428281007896L; 

	public CourseNotFoundException(String message) {
		super(message);
	}
}
```
- service단에서 exception을 사용해 예외처리를 진행합니다.
```java
Course existingCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException(String.format("No course with id %s is available", courseId)));
```

- @ControllerAdvice를 이용하여 공통처리
```java
@ControllerAdvice
public class CourseTrackerGlobalExceptionHandler extends ResponseEntityExceptionHandler -- 1️⃣ {

	@ExceptionHandler(value = {CourseNotFoundException.class})	return handleExceptionInternal(courseNotFoundException, 
				courseNotFoundException.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request) -- 2️⃣;
	}
}
```
1️⃣. 다양한 예외에 대한 기본 처리 로직이 포함되어 있어 필요한 경우 재정의하여 예외를 처리할 수 있다.
2️⃣. 헤더 및 HTTP 상태코드를 재정의 한다.
- API를 설계할 때는 애플리케이션에서 발생할 수 있는 예외 시나리오를 식별하는 것이 우선이다.

## RESTful API 테스트
#### @SpringBootTest
- 모든 스프링 부트 빈을 생성하고, 구성된 애플리케이션 컨텍스트를 포함하는 스프링 부트 환경을 사용해서 테스트가 실행된다.
#### @AutoConfigureMockMvc
- MockMVC 프레임워크가 자동 구성되어 편리하게 MockMvc 인스턴스를 주입받아서 테스트에 사용할 수 있다.
#### @ExtendWith(SpringExtension.class)
- JUnit5의 주피터 프로그래밍 모델과 스프링 테스트 컨텍스트 프레임워크를 함께 서용할 수 있다.
## 문서화 
- 블로그 포스팅으로 대체됩니다.
- [[SpringBoot] 문서 자동화 제대로 해보기(with. Restdocs)](https://hoding-cloud.tistory.com/)
## API 버저닝 
- API 버저닝 기법 4가지
#### URL 버저닝 : URL에 버전 번호를 붙인다.
- ex: `@RequestMapping("/courses/v1")`
#### 요청 파라미터 버저닝 : 버전 번호를 나타내는 요청 파라미터 추가
```java
@GetMapping(params = "version=v1")
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<Course> getAllLegacyCourses() {
		return courseService.getCourses();
	}
```
#### 커스텀 HTTP 헤더 버저닝 : 버전을 구분할 수 있는 요청헤더 추가
```java
	@GetMapping(headers = "X-API-VERSION=v1")
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<Course> getAllLegacyCourses() {
		return courseService.getCourses();
	}
```
#### 미디어 타입 버저닝 : Accept헤더를 사용해서 버전구분
```java
@GetMapping(produces = "application/vnd.sbip.app-v1+json")
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<Course> getAllLegacyCourses() {
		return courseService.getCourses();
	}
```
## 어떤 방식을 사용해야 할까?
- 어떠한 방식도 완벽하지 않다.
- URL 기법 : 엔드포인트에 버전번호를 포함하는 것은 URI의 일부가 아니기떄문에 URL를 오염시킨다
- Accept 헤더 : Accept헤더는 원론적으로 버저닝 목적으로 사용하는 헤더가 아니다.
  - 파라미터,HTTP헤더 위 이유와 유사
- API 설계자나 조직차원에서 장단점을 고려해서 적절한 방법을 선택하는 것이 중요하다.
#### 기업의 버저닝 기법 채택 예시
- 아마존 : HTTP 요청 파라미터 방식
- 깃허브 : 미디어 타입 방식
- 마이크로소프트 - 커스텀HTTP 헤더 방식
- 트위터 : URI 방식
## 보안기법
- 베어러 토큰을 사용해서 엔드포인트에 접근 제한 조치를 통해 보안을 강화활 수 있다.
#### 키클록을 사용해서 인가서버 구성하기
- 사용자 모델에 인가와 관련된 정보를 제거할 수 있다.
- application.preoperties에 spring.sequrity가 인가서버를 통해 JWT를 발급받는다는 설정을 해주어야 한다.
- 키클록 인가서버에서 설정한 커스텀 클레임을 통해 JWT로부터 유저정보(강사이름)을 획득할 수 있따.
```java
    @GetMapping
	public Iterable<Course> getAllCourses(@AuthenticationPrincipal Jwt jwt) {
		String author = jwt.getClaim("user_name");
		return courseRepository.findByAuthor(author);
	}
```
#### 본인의 리소스에만 접근할 수 있도록 제한하기
```java
@PostAuthorize("@getAuthor.apply(returnObject, principal.claims['user_name'])") -- 1️⃣
public Optional<Course> getCourseById(@PathVariable("id") long courseId) {
        return courseRepository.findById(courseId);
        }
```
- 1️⃣. 토큰에 포함되어있는 user_name 클레임 값과 반환되는 객체의 author값을 비교하여 PostAuthrize를 진행한다.(SpEL 표현식)
#### 스코프를 활용하여 접근제어하기
- 예를 들어 서브파티 클라이언트 애플리케이션에게 특정 가상가 개설한 모든 과정을 조회는 허용하나 저장은 제한하고 싶을 때
```java
@PreAuthorize("hasAuthority('SCOPE_course:read')")
```

#### 보안과 관련된 더 자세한 내용은 아래 도서 참조
- OAuth2 in ACtion
- OpenID Connect in Action