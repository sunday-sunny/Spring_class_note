package com.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/* 보조 업무 객체 */
@Aspect		// 자격
@Component	// 의존 객체 주입 자격, 스프링이 알아서 가져다 씀
public class Cross {
	
	/* 주업무 지정 > 포인트컷 지정 */
	@Pointcut("execution(public int com.test.aop.BoardService.add(String, int))")
	public void pc1() {
		// 구현 x
		// 이 메소드는 하는 일이 없음, @Pointcut 지정을 위해 만든것
	}
	
	
	/* 보조업무 선언 + 주업무 결합 */
	@After("pc1()")
	public void m1(JoinPoint joinPoint) { 
		
		/* @After() 
		 * 	- 사용할 Advise 어노테이션 설정
		 * 	- 포인트컷과 연결을 위해 매개변수는 포인트컷 메소드 호출식으로 적어줌
		 * 
		 * JoinPoint joinPoint
		 *  - 프록시 객체
		 *  - 주업무를 참조하기 위한 객체
		 */
		
		System.out.println("보조 업무를 실행합니다.");

		
		/* 매개변수들 가져오기 가능 */
		Object[] args = joinPoint.getArgs();
		
		for(Object o : args) {
			System.out.println(o);
		}
		
	}

}
