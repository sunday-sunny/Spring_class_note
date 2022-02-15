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

	
	/**
	 * 글 하나를 가져오는 서비스
	 * @param seq 불러올 게시물 seq
	 * @return 게시물 DTO
	 */
	BoardDTO get(String seq);

	
	/**
	 * 글을 수정하는 서비스
	 * @param dto 수정할 게시물 DTO
	 * @param session 회원 id값을 받기 위한 session
	 * @param req 첨부파일을 받기 위한 request 파라미터
	 * @param del 첨부파일 삭제여부 확인을 위해 넘어온 hidden input
	 * @return update 결과
	 */
	int edit(BoardDTO dto, HttpSession session, HttpServletRequest req, String del);
	
	
	/**
	 * 글을 삭제하는 서비스
	 * @param seq 삭제하려는 게시물 seq
	 * @return delete 결과값
	 */
	int del(String seq, HttpServletRequest req);


}
