package com.test.mybatis;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyBatisController {

	/* Mybatis 예제 10개 */

	
	@Autowired
	private MyBatisDAO dao;

	
	/* 예제1) insert문 | 인자값 X, 반환값 X, 하드코딩 insert문 */
	@RequestMapping(value = "/m1.do", method = { RequestMethod.GET })
	public String m1(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		dao.m1();
		return "result";
	}
	
	
	
	/* 예제2) insert문 | 인자값 O, 반환값 X */
	@RequestMapping(value = "/m2.do", method = { RequestMethod.GET })
	public String m2(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		MemoDTO dto = new MemoDTO();
		dto.setName("아무개");
		dto.setMemo("인자값이 있는 쿼리 실행");

		int result = dao.m2(dto);
		req.setAttribute("result", result);
		
		return "result";
	}
	
	
	
	/* 예제3) delete문 | 인자값 O, 반환값 X */
	@RequestMapping(value = "/m3.do", method = { RequestMethod.GET })
	public String m3(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		String seq = "6";
		
		int result = dao.m3(seq);
		req.setAttribute("result", result);
		
		return "result";
	}
	
	
	
	/* 예제4) insert문 | 복합값 전달 */
	@RequestMapping(value = "/m4.do", method = { RequestMethod.GET })
	public String m4(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		// 1. DTO (O)
		// 2. Map (O)
		// 3. List (X)
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "홍길동");
		map.put("memo", "해시맵을 전달합니다.");
		
		int result = dao.m4(map);
		
		return "result";
	}
	
	
	
	/* 예제5) select문 | 단일값 반환, count(*) 예제 */
	@RequestMapping(value = "/m5.do", method = { RequestMethod.GET })
	public String m5(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		int count = dao.m5();
		req.setAttribute("count", count);

		return "result";
	}
	
	
	
	/* 예제6) select문 | 단일값 반환(복합값 다중 컬럼), DTO 반환 */
	@RequestMapping(value = "/m6.do", method = { RequestMethod.GET })
	public String m6(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		String seq = "1";
		MemoDTO dto = dao.m6(seq);
		
		req.setAttribute("dto", dto);

		return "result";
	}
	
	
	
	/* 예제7) select문 | (단일 컬럼 + 다중 레코드) 반환, 메모 내용만 가져오기 */
	@RequestMapping(value = "/m7.do", method = { RequestMethod.GET })
	public String m7(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		/* mybatis가 ArrayList가 아니라 상위 타입인 List로 돌려줌
		 * - 보통 계층과 계층이 데이터를 주고 받을 때는 인터페이스로 주고받음
		 */
		
		List<String> memo = dao.m7();
		req.setAttribute("memo", memo);

		return "result";
	}
	
	
	
	/* 예제8) select문 | (다중 컬럼 + 다중 레코드) 반환, 메모 리스트 전체 반환 */
	@RequestMapping(value = "/m8.do", method = { RequestMethod.GET })
	public String m8(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		List<MemoDTO> list = dao.m8();
		req.setAttribute("list", list);

		return "result";
	}
	
	
	
	/* 예제9) select문 | 메모 검색, 동적쿼리 사용 */
	@RequestMapping(value = "/m9.do", method = { RequestMethod.GET })
	public String m9(HttpServletRequest req, HttpServletResponse resp, HttpSession session
						, String column, String word, String order) {
		
		/* 메모 검색
		 * 	1. 메모 내용 검색 memo like '%검색어%'
		 * 	2. 작성자 검색 > name = '홍길동'
		 * 
		 * 	m9.do?column=memo&word=메모&order=asc
		 *  m9.do?column=name&word=길동&order=desc
		 */

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("word", word);
		map.put("order", order);
		
		List<MemoDTO> list = dao.m9(map);
		req.setAttribute("list", list);

		return "result";
	}
	
	
	
	/* 예제10) select문 | 메모 검색, ${} 바인딩 */
	@RequestMapping(value = "/m10.do", method = { RequestMethod.GET })
	public String m10(HttpServletRequest req, HttpServletResponse resp, HttpSession session
						, String column, String word, String order) {
		
		/* 메모 검색
		 *  1. 메모 검색 > memo like '%검색어%'
		 *  2. 작성자 검색 > name like '%검색어%'
		 *  3. 정렬
		 *  
		 *  m10.do?column=seq&word=메모&order=asc
		 *  m10.do?column=name&word=홍길동&order=desc
		 */
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("word", word);
		map.put("order", order);
		
		List<MemoDTO> list = dao.m10(map);
		req.setAttribute("list", list);

		return "result";
	}
	
	
}
