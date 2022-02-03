package com.test.spring.di02;

public class Hong {
	
	private Pen pen;
	
	public Hong() {
		this.pen = null;
	}
	
	/* 의존 주입 도구(생성자) */
	public Hong(Pen pen) {
		this.pen = pen;
	}
	
	public void setPen(Pen pen) {
		this.pen = pen;
	}
	
	public void run() {
		
		// Pen > 의존 객체
		// 주입받은 pen 객체 사용!
		pen.draw();
		
	}
}
