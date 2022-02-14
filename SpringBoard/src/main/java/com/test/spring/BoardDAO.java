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

}
