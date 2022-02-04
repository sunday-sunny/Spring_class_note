package com.test.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/* @Controller 어노테이션 
 *  - JSP Model2의 서블릿(컨트롤러) 역할
 *  - 컨트롤러 하나에 여러개의 가상주소를 붙여, 여러개의 JSP 호출 가능
 *  - 장점)
 *  	- 서블릿간의 공통으로 사용되던 자원들을 따로 만들 필요 없이, 멤버 변수로 만들어 사용 가능!!
 *  	- ex) private DAO dao = new DAO();
 * */


@Controller
public class Ex02Controller {
	
	@RequestMapping(value="/ex02.do")
	public String test() {
		
		/* @RequestMapping 
		 *  - doGet() or doPost()의 역할 > 요청 메소드
		 * 	- 가상 주소를 매핑할 메소드를 만든다.
		 *  - spring/ex02.do > 패키지의 마지막 단어(여기서는 spring)가 contextroot로 적용됨
		 *  - JSP처럼 알아서 매핑해주지 않으니 주소창에 잘 검색
		 * */
		
		
		/* JSP 페이지 호출하기! (== requestDispatcher)
		 * 	- servelt-context.xml의 ViewResolver가 아래 반환값만으로도 처리해줌
		 * 	- (src > main > webapp > WEB-INF > spring > appServlet > servlet-context.xml에 존재)
		 */
		return "ex02";
	
	}

	
	@RequestMapping(value="/ex02_1.do")
	public String test2() {
		return "ex02_1";
	}
	
	
	@RequestMapping(value="/ex02_2.do")
	public String test3() {
		//JSP가 있는 폴더가 다를 때는 잘보고 return 값 주기
		return "member/ex02_2";
	}
	
	
}