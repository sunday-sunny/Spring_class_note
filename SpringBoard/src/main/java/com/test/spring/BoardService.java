package com.test.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface BoardService {
	
	/**
	 * 글작성 서비스
	 * @param dto 글작성 목록
	 * @param session 로그인한 id값을 받기 위한 session
	 * @param req 첨부파일을 받기 위한 request 파라미터
	 * @return insert 결과
	 */
	int add(BoardDTO dto, HttpSession session, HttpServletRequest req);

	
	/**
	 * 글목록을 불러오는 서비스
	 * @return 글목록 리스트
	 */
	List<BoardDTO> list();


}
