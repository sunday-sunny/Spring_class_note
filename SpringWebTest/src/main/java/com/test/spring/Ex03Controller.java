package com.test.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @Controller
 * 	- MVC 중 Controller 역할
 *  - 클라이언트의 요청을 받아 처리할 수 있는 자격을 가진 알바생이 된다.
 */

@Controller	
public class Ex03Controller {

	/* 클라이언트가 이 컨트롤러에게 요청을 하려면?
	 * 	- 1. 요청 메소드 > doGet()/doPost()		> @RequestMapping
	 *  - 2. 가상 주소	 > "/spring/ex03.do"	> value="/ex03.do"
	 *  
	 * @RequestMapping
	 *  - 클라이언트의 요청을 받아 처리할 수 있는 자격을 가진 알바생의 요청 메소드가 된다.
	 */
	

	
	/* GET/POST 설정
	 *  - method X) GET/POST 둘 다 받을 수 있음
	 *  - GET	) method= {RequestMethod.GET}
	 *  - POST  ) method= {RequestMethod.POST}
	 *  
	 *  *** POST의 경우 'web.xml' 파일에서 encoding 전역 설정 !! ***
	 *  
	 */
	
	@RequestMapping(value="/ex03.do", method= {RequestMethod.GET})
	public String call() {
		
		/* 뷰 호출 > ViewResolver 사용 (JSP 페이지의 경로를 단축)
		 *  - == WEB-INF/views/ex03.jsp
		 *  - RequestDispatcher + forward()
		 */
		return "ex03"; 
	}
	
	
	
	
	/* JSP에서 데이터 가져오기
	 *  - 요청 메소드의 매개변수 사용
	 * 		1) request, response, session 파라미터로 자동 받기 가능.
	 * 			- HttpServletRequest req
	 * 			- HttpServletResponse resp
	 * 			- HttpSession session
	 * 
	 * 		2) '일반 변수' 받기 가능.
	 * 			- String name, String age, String address
	 * 
	 * 		3) 'DTO' 받기 가능
	 * 		4) 'hidden'만 개별로 받기 가능
	 */
	
	
	
	/* 예제1) 일반변수 매개변수로 받기 */
	@RequestMapping(value="/ex03ok.do", method= {RequestMethod.POST})
	public String callok( HttpServletRequest req, String name, String age, String address) {
		
		/* 데이터 가져오기 */
		
		/* 기존방식 (사용 안함) */
		// String name = req.getParameter("name");
		// String age = req.getParameter("age");
		// String address = req.getParameter("address");
		
		// DTO dto = new DTO();
		// dto.setName(name);
		// dto.setAge(age);
		// dto.setAddress(address);
		
		
		/* 매개변수로 받아서 세팅 */
		DTO dto = new DTO();
		dto.setName(name);
		dto.setAge(age);
		dto.setAddress(address);
		
		/* Test print */
		System.out.println(name + " " + age + " " + address);
		
		return "ex03ok";
	}
	
	
	
	/* 예제2) DTO 및 hidden 매개변수로 받기 */
	@RequestMapping(value="/ex03ok2.do", method= {RequestMethod.POST})
	public String callok2(HttpServletRequest req, DTO dto, String color) {

		System.out.println(dto);
		System.out.println(color);
		
		return "ex03ok2";
	}
	
}
