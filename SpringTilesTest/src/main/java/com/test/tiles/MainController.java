package com.test.tiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/* 메인화면 */
	@RequestMapping(value = "/main.do", method = { RequestMethod.GET })
	public String main(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		/* 기존 return문 */
		// - JSP 페이지의 경로 + 기존 viewResolver = main.jsp

		/* 현재 return문 */
		// - tiles.xml의 <definition>을 검색하는 키워드
		// - 설정된 <definition>의 name을 검색하여 반환
		
		return "main";
	}
	
	
	/* 회원 관련 */
	@RequestMapping(value = "/member/info.do", method = { RequestMethod.GET })
	public String info(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		return "member.info";
	}
	
	@RequestMapping(value = "/member/log.do", method = { RequestMethod.GET })
	public String log(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		return "member.log";
	}
	
	
	/* 관리자 관련 */
	@RequestMapping(value = "/admin/chart.do", method = { RequestMethod.GET })
	public String chart(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		return "admin.chart";
	}
	
	
	@RequestMapping(value = "/admin/point.do", method = { RequestMethod.GET })
	public String point(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		return "admin.point";
	}
	
	
}
