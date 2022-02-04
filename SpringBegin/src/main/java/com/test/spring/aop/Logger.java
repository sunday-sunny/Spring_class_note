package com.test.spring.aop;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;

/* 보조 업무 객체 */
// - 주업무 완료 후 관리자 로그 기록을 생성한다.
public class Logger {
	
	/* 주업무의 어느 시점에서 보조업무를 실행할지 결정 
	 * - Advice > "memo.xml" 작성
	 */

	
	/* After Advice */
	public void log() {
		Calendar now = Calendar.getInstance();
		System.out.printf("[log %tF %tT] 보조업무가 실행됩니다.\n", now, now);
	}
	
	
	
	/* Around Advice */
	// 주업무가 실행하는데 소요되는 시간을 로그로 남기기
	public void logTime(ProceedingJoinPoint joinPoint){
		
		long begin = System.currentTimeMillis();
		System.out.println("[log] 시간을 기록합니다.");
		
		
		// 주업무 코드
		try {
			//joinPoint > Proxy 객체(대리자)
			joinPoint.proceed(); // 주업무 메소드의 호출을 대신하는 코드
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("[log] 시간 기록을 종료합니다.");
		System.out.printf("[log] 주업무 실행 소요 시간: %,dms\n", end-begin);
	}
	
	
	
	/* After Throwing Advice 구현 */
	// - 주업무 실행 중 예외가 발생하면 같이 실행되는 보조업무
	// - 예외처리가 아님!
	// - Exception 객체를 꼭 받는다!
	public void sendMail(Exception e) {
		
		// 에러 발생 > 관리자에게 메일 발송
		System.out.printf("'%s' 예외 발생으로 인해 관리자에게 메일을 발송합니다.\n", e.getMessage());
	
	}
	
	
	
	/* After Returning Advice */
	// - Object 객체를 파라미터로 받는다!
	public void logSearch(Object obj) {
		System.out.printf("[log] %s번 메모가 검색되었습니다.\n", obj);
		
	}
	
	
}
