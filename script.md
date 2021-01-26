# 스프링 부트 프로젝트(Admin Page)

## 1. HTTP Method

### @RestController

- 이 곳은 컨트롤러로 이용할 거야 라는 의미의 어노테이션
### @RequestMapping("/api")
- Localhost:808/api 까지 주소가 매핑된다.

### @RequestMapping(method = RequestMethod.GET,path="/getMethod")
- Localhost:8080/api/getMethod 까지 주소가 매핑된다.

### @GetMapping("/getParameter")
- Get 방식으로 주소를 매핑한다.

### @RequestParam String id
- id 파라미터를 수신하기 위한 어노테이션

### 객체를 통해 파라미터를 수신하고 리턴하면 Json형태로 리턴 가능

~~~
@GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){ //객체를 통해서 파라미터를 받는다
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam; //json 형태로 출력된다 {"account":"". "email":"", "page":0}
    }
~~~

Code - generate 에서 Getter, Setter 자동 생성 가능