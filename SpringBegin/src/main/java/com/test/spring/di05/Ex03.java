package com.test.spring.di05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {
	
	public static void main(String[] args) {
		
		// Ex03.java
		// AAA -> (의존) -> BBB -> (의존) -> CCC -> (의존) -> DDD

		/* < com.test.spring.di03 ~ di05 한 예제>
		 * 		- di03 : 고전 방식
		 * 		- di04 : 의존 주입 (스프링 사용X)
		 * 		- di05 : 의존 주입 (스프링 사용O) + di03.xml
		 * 
		 * < Spring DI의 이유>
		 * 	* 관리의 주체 *가 어디있는가의 문제
		 * 		- 고전 방식 : 개별 관리 방식
		 * 		- 스프링 DI : 중앙 집중 관리
		 */
		
		
		/* Spring DI */
		// XML에서 설정한 의존 주입 관계를 적용하려면 스프링 DI를 사용해서 객체를 생성.
		ApplicationContext context = new ClassPathXmlApplicationContext("di03.xml");
		AAA a = (AAA)context.getBean("a");
		a.run();
		
		
	}

}
