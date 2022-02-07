package com.test.memo;

import java.sql.Connection;
import java.sql.DriverManager;

// 라이브러리 만들 때는 document 주석 꼭 달아주기!

/**
 * Oracle 연결 클래스입니다.
 * @author Sunny
 *
 */


public class DBUtil {
	
	/**
	 * 데이터베이스에 접속합니다.
	 * @return 연결된 Connection 클래스
	 */
	public static Connection open() {
		
		// 1. DB 연결
		Connection conn = null; //interface로 가져오기
		
		// 연결 문자열(Connection String)
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Developer에서 설정한 정보
	    String id = "hr";
	    String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB 접속한 정보를 가지고 있는 Connection 객체 생성
			// conn = new Connection(); -> (X)		
			// 객체 인터페이스를 DriverManager를 통해 만들어야함.
			conn = DriverManager.getConnection(url, id, pw); // DB객체 + Connection 객체 생성
			
			return conn;
			
		} catch(Exception e) {
			System.out.println("오류!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 데이터베이스에 접속합니다.
	 * @param host 연결할 데이터베이스 주소
	 * @param id 연결할 계정명
	 * @param pw 연결할 계정암호
	 * @return 연결된 Connection 클래스
	 */
	public static Connection open(String host, String id, String pw) {
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@" + host + "localhost:1521:xe";
		
		try {
			
			// Oracle 쓸 때는 class 네임 거의 고정.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch(Exception e) {
			System.out.println("오류!");
			e.printStackTrace();
		}
		
		return null;
	}

}
