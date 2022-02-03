package com.test.spring.di01;

public class Lee {
	
	private Pen pen;
	
	/* 의존주입 방법1) 생성자 사용 */
	public Lee(Pen pen) {
		this.pen = pen;
	}
	
	/* 의존주입 방법2) Setter 사용 */
	public void setPen(Pen pen) {
		this.pen = pen;
	}

	/* Pen을 생성한 뒤 그림 그리기 */
	public void run() {
	
		/* 기존 방법 */
		//의존 객체를 직접 생성 > 사용
		//Pen pen = new Pen(); 
		
		
		/* 의존성 주입 방법*/
		// 의존 객체를 외부로부터 공급 > 사용
		pen.draw();
	}

}
