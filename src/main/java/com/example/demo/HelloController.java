package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

// new 되서 메모리에 떠서 자료형에 담김. 자료형이 ioc . 컨퍼넌트 스캔의 기준. com.example.demo 이하의 모든 클래스를 스캔. @Restcontrroler가 있는걸 스캔
// 스캔해서 자기가 만든 자료형에 담음. 그게 ioc = inversion of controller
// ioc 제어의 역전. 원래는 개발자가 new 를 하지만 ioc 자료형은 스프링이 new 를 해서 new 의 주도권이 넘어감. new 는 내가 할테니까 너는 @만 붙여라
// 위임했을 때 뭐가 좋은지 알아보자
// 프론트 컨틀롤러 (서블릿) 가 디스패쳐 서블릿을 때림 (리플렉션으로 만듬) - 주소 요청을 받으면 컨트롤러에 메서드를 때리는 것 까지ㅐ 구현되어있음 - 개발자는 컨트롤러만 만들면 됨.


@RestController
public class HelloController {  //new 되서 ioc 컨테이너에 저장됨. 싱글톤으로 뜸.
    public HelloController(){
        System.out.println("hello컨트롤러 컴포넌트 스캔됨");
    }
//    webservlet("/*") 서블릿 하나로 모든 요청을 받을땐 이렇게 되어있음. / 이하의 모든 주소를 받음. 그래서 라우터가 필요함.
//    서블릿을 계속 만들어지면 분기 처리는 쉬워짐. 메모리가 많아지기 때문에 메모리 부하가 생김.
//    하나의 서블릿으로 만들면 공통 로직을 처리하기 좋아짐. ex 헤더에 어떤 값을 입력하고 싶다면 하나만 하면 됨.
//

   @GetMapping(("/home"))
    public String home(){
        String name = "홍길동" ;
        return name ;
    }

    // 정적 페이지. 요청할 때마다 동일함. 동일한 값이기 떄문에 캐싱하는게 좋음. http 헤더에 캐싱하라고 표시함. 그러면 브라우저가 hello 를 요청 받으면 다운 받아놓고 캐싱함. 서버 비용이 작게 됨.
    // 프로토콜에 맞춰서 서버가 캐싱해야됨.
    // 데이터베이스에서 조회된 변수르 자바 변수랑 같이 사용
    @GetMapping("/hello")
    public String hello(){
        String name = "홍길동";
        return "<h1>hello" +name+"</h1>";  // 보이지 않지만 디스패쳐가 호출됨 , 디스패쳐가 데이터를 라우팅하고, 응답받은 데이터를 bw 에 담아서 보냄.
    }                                      // 스프링은 자기들이 디스패쳐 만듬. 컨트롤러 메서드만 구현. 근데 컨트롤러에 무슨 객체 무슨 메서드일지 알 수가 없음. 그래서 리플렉션으 사용. 컨포넌트 스캔= 클래스를 대신 new 해줌

    //동적 페이지. 실행할때마다 변함 . 동적 페이지는 헤더에 아무 정보가 없음. 그러면 캐싱하지 않음.
    @GetMapping("/random")
    public String random(){
        Random r = new Random() ;
        int num = r.nextInt(50)+1;
        return "<h1>random: " +num+"</h1>";
    }
}
