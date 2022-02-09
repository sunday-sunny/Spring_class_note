package com.test.mybatis;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisDAO {
	
	
	/* SQL 실행
	 * 
	 * - 기존
	 * 		- Connection conn
	 * 		- Statement stat
	 * 		- ResultSet rs
	 * 		- String sql > executeXXX() > return O, X
	 * 
	 * - Mybatis
	 * 		- SqlSessionTemplate 멤버 변수 사용 (root-context.xml에 설정했던거)
	 * 		- 해당 멤버 변수 선언 후 @Autowired
	 * 		- 이미 받아오는 객체에 의존 주입이 되어있어서 따로 @Component 등의 설정을 안해줘도 된다.
	 * 		- root-context.xml를 확인해보면, 내부적으로 sessionfactory > datasource 연쇄적으로 생성이 이루어짐
	 * 		
	 * 		// select 문
	 * 		기존	: stat.executeQuery()
	 * 		mybatis	: template.selectXXX("XML에 있는 쿼리 식별자")
	 * 				  
	 * 				 - 컬럼 수는 무관
	 * 
	 * 				 - template.selectOne();
	 * 					: 결과셋의 레코드 수가 1개일 때 > 주로 DTO 반환
	 * 				 - template.selectList(statement)
	 * 					: 결과셋의 레코드 수가 N개 일 때 > 주로 List<DTO> 반환
	 * 
	 * 		
	 *		// 그외 SQL 문
	 *		기존 	: stat.executeUpdate()
	 * 		mybatis	: template.insert()
	 * 				  template.update()
	 * 				  template.delete()
	 */

	
	
	@Autowired
	private SqlSessionTemplate template;

	
	/**
	 * 예제1) insert문 | 인자값 X, 반환값 X, 하드코딩 insert문
	 */
	public void m1() {
		template.insert("test.m1");
	}

	
	/**
	 * 예제2) insert문 | 인자값 O, 반환값 X
	 * @param dto insert할 Memo DTO
	 * @return insert 결과값
	 */
	public int m2(MemoDTO dto) {
		return template.insert("test.m2", dto);
	}


	/**
	 * 예제3) delete문 | 인자값 O, 반환값 X
	 * @param seq delete할 메모 seq
	 * @return delete 결과값
	 */
	public int m3(String seq) {
		return template.delete("test.m3", seq);
	}


	/**
	 * 예제4) insert문 | 복합값 전달
	 * @param map insert할 map
	 * @return insert 결과값
	 */
	public int m4(HashMap<String, String> map) {
		return template.insert("test.m4", map);
	}


	/**
	 * 예제5) select문 | 단일값 반환, count(*) 예제
	 * @return select된 DTO
	 */
	public int m5() {
		return template.selectOne("test.m5");
	}


	/**
	 * 예제6) select문 | 단일값 반환(복합값 다중 컬럼), DTO 반환
	 * @param seq select 하려는 memo seq
	 * @return select된 DTO
	 */
	public MemoDTO m6(String seq) {
		return template.selectOne("test.m6", seq);
	}


	/**
	 * 예제7) select문 | (단일 컬럼 + 다중 레코드) 반환, 메모 내용만 가져오기
	 * @return select된 List
	 */
	public List<String> m7() {
		// selectList > 이 메소드가 알아서 list로 만들어서 돌려줌
		return template.selectList("test.m7");
	}

	
	/**
	 * 예제8) select문 | (다중 컬럼 + 다중 레코드) 반환, 메모 리스트 전체 반환
	 * @return 전체 MemoDTO 타입 List
	 */
	public List<MemoDTO> m8() {
		return template.selectList("test.m8");
	}


	/**
	 * 예제9) select문 | 메모 검색, 동적쿼리 사용
	 * @param map 검색조건 map
	 * @return 검색된 MemoDTO 타입 List
	 */
	public List<MemoDTO> m9(HashMap<String, String> map) {
		return template.selectList("test.m9", map);
	}


	/**
	 * 예제10) select문 | 메모 검색, ${} 바인딩
	 * @param map map 검색조건 map
	 * @return 검색된 MemoDTO 타입 List
	 */
	public List<MemoDTO> m10(HashMap<String, String> map) {
		return template.selectList("test.m10", map);
	}
	
	
}
