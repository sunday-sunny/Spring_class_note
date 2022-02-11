package com.test.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AOPController {
	
	@Autowired
	private BoardService service;

	
	/* 글쓰기 */
	@RequestMapping(value = "/test.do", method = { RequestMethod.GET })
	public String test(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		// 게시판 글쓰기
		int result = service.add("홍길동", 10);
		System.out.println();
		
		result = service.add("아무개", 20);
		System.out.println();

		return "result";
	}
	
	
	/* 리스트 화면 */
	@RequestMapping(value = "/list.do", method = { RequestMethod.GET })
	public String list(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		return "result";
	}
	
	
	/* 수정 화면 */
	@RequestMapping(value = "/edit.do", method = { RequestMethod.GET })
	public String edit(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		return "result";
	}

	
	/* 로그인 기능 */
	@RequestMapping(value = "/login.do", method = { RequestMethod.GET })
	public String login(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String id) {
	
		session.setAttribute("id", id);
		return "result";
	}
	
	
}
