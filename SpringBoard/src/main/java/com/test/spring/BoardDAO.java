package com.test.spring;

import java.util.List;

public interface BoardDAO {

	/**
	 * 글작성 DAO
	 * @param dto 저장하려는 게시물 DTO
	 * @return insert 결과값
	 */
	int add(BoardDTO dto);
	
	
	/**
	 * 글목록 불러오는 DAO
	 * @return 글목록 리스트
	 */
	List<BoardDTO> list();

	
	/**
	 * 글 하나를 불러오는 DAO
	 * @param seq 불러올 게시물 seq
	 * @return 게시물 DTO
	 */
	BoardDTO get(String seq);

	
	/**
	 * 글을 수정하는 DAO
	 * @param dto 수정하려는 게시글 DTO
	 * @return update 결과값
	 */
	int edit(BoardDTO dto);

	
	/**
	 * 글을 삭제하는 DAO
	 * @param seq 삭제하려는 게시물 seq
	 * @return delete 결과값
	 */
	int del(String seq);

}
