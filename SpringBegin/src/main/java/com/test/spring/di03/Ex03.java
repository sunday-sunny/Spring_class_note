package com.test.spring.di03;

public class Ex03 {
	
	public static void main(String[] args) {
		
		// Ex03.java
		// AAA -> (의존) -> BBB -> (의존) -> CCC -> (의존) -> DDD
		
		/* < com.test.spring.di03 ~ di05 한 예제>
		 * 		- di03 : 고전 방식
		 * 		- di04 : 의존 주입 (스프링 사용X)
		 * 		- di05 : 의존 주입 (스프링 사용O) + di03.xml
		 */
		
		
		
		/* 고전 방식 */
		AAA a = new AAA();
		a.run();
		
	}

}
