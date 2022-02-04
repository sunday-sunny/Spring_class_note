package com.test.spring.aop;

public interface IMemo {
	
	/* <참고>
	 *  - AOP를 묶어서 설정하기 위해, auth가 필요한 메소드에 접두어를 붙임
	 */

	
	// 1. 주업무 > 메모쓰기
	void auth_add(String memo);
	
	// 2. 주업무 > 메모읽기
	void read(int seq) throws Exception;
	
	// 3. 주업무 > 메모수정
	void auth_edit(int seq, String memo);
	
	// 4. 주업무 > 메모삭제
	void auth_del(int seq);
	
	// 5. 주업무 > 메모검색
	int search(String word);
	
}
