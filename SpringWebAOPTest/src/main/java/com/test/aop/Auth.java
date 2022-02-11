package com.test.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Auth {

	/* 주업무 지정 */
	// test.do와 edit.do는 회원 전용 기능이라 가정
	@Pointcut("execution(public String AOPController.test(..)) || execution(public String AOPController.edit(..))")
	public void pc1() {}
	
	
	/* 보조업무 */
	@Before("pc1()")
	public void check(JoinPoint joinPoint) {
		
		HttpServletResponse resp = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		
		/* 세션값 검사 */
		if(session.getAttribute("id") == null) {
			try {
				resp.sendRedirect("/aop/login.do");
			} catch (IOException e) {
				System.out.println("Auth.check()");
				e.printStackTrace();
			}
		}
	}
	
}
