package com.test.spring.di02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex02 {
	
	
	public static void main(String[] args) {
		
		// Ex02.java
		// (Ex01)직접 DI 구현 > (Ex02)Spring DI 구현
		
		
		/* 직접 DI 구현 */
		Pen pen = new Pen();
		Hong hong = new Hong(pen);
		hong.run();
		
		
		
		/* Spring DI */
		
		/* 1. XML 파일에 Bean 세팅
		 * 2. XML 설정 읽기
		 * 3. Bean 객체 읽기
		 * 		- 스프링 프레임워크를 사용해서 객체 사용
		 * 		- context.getBean("bean id값")
		 * 		- Object로 반환하기에 다운캐스팅 필요
		 * 		- 이 Object는 스프링이 만들어준 것! 
		 */

		
		/* 예제1) Spring DI (contructor-arg 설정 X) */
		
		// 1. XML 설정 읽기
		// - 매개변수 = 해당 XML의 경로
		ApplicationContext context = new ClassPathXmlApplicationContext("di02.xml");
		
		// 2. Bean 객체 읽기
		// <bean id="pen" name="dd ee" class="com.test.spring.di02.Pen"></bean>
		Pen pen2 = (Pen)context.getBean("pen"); // new Pen() 실행 후 객체 반환
		pen2.draw();
		
		// <bean id="hong" name="aa bb cc" class="com.test.spring.di02.Hong"></bean>
		Hong hong2 = (Hong)context.getBean("hong"); // new Hong() 기본 생성자 호출
		hong2.setPen(pen2);
		hong2.run();
		
		
		
		/* 예제2) Spring DI (contructor-arg 설정 O) */
		//ApplicationContext context = new ClassPathXmlApplicationContext("di02.xml");

		Hong hong3 = (Hong)context.getBean("hong");
		hong3.run();
		
		
	}
	
}
