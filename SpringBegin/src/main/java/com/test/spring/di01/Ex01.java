package com.test.spring.di01;

public class Ex01 {
	
	public static void main(String[] args) {
		
		/* 기존 방법 */
		Hong hong = new Hong();
		hong.run();
		
		
		/* 의존성 주입 방법*/
		// Lee의 의존 객체인 pen을 Lee에게 사용할 수 있도록 전달!
		// 1. 생성자 사용
		// 2. setter 사용
		
		Pen pen = new Pen();
		
		// ** ↓ 의존 주입(Dependency Injection) **
		Lee lee = new Lee(pen); 
		lee.run();
	}

}
