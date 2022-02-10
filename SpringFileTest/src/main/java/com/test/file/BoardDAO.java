package com.test.file;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	/**
	 * mybatis를 사용하기 위한 멤버 변수
	 */
	@Autowired
	private SqlSessionTemplate template;
	
	
	/**
	 * 파일업로드(1개) | 첨부파일 insert
	 * @param dto 저장하려는 첨부파일 Board DTO
	 * @return insert 결과값
	 */
	public int add(BoardDTO dto) {
		return template.insert("file.add", dto);
	}

	
	/**
	 * 파일업로드(1개) | 파일 목록 select
	 * @return BoardDTO 리스트
	 */
	public List<BoardDTO> list() {
		return template.selectList("file.list");
	}

	
	/**
	 * 파일업로드(N개) | 게시물 insert
	 * @param dto 저장하려는 MBoard DTO
	 * @return insert 결과값
	 */
	public int madd(MBoardDTO dto) {
		return template.insert("file.madd", dto);
	}


	/**
	 * 파일업로드(N개) | 첨부파일된 게시물 번호 select 
	 * @return 첨부파일 관련 게시물 번호
	 */
	public String getSeq() {
		return template.selectOne("file.getseq");
	}
	
	
	/**
	 * 파일업로드(N개) | 첨부파일 insert
	 * @param fdto 저장하려는 FileDto
	 * @return insert 결과값
	 */
	public int maddFile(FileDTO fdto) {
		return template.insert("file.maddfile", fdto);
	}
	

	/**
	 * 파일업로드(N개) | 파일 게시판 목록 select
	 * @return MBoardDTO 리스트
	 */
	public List<MBoardDTO> mlist() {
		return template.selectList("file.mlist");
	}

	
	/**
	 * 파일업로드(N개) | 첨부파일 목록 select
	 * @param seq 가져오려는 첨부파일의 게시물 seq
	 * @return 해당 게시물 seq에 맞는 FileDTO 리스트
	 */
	public List<FileDTO> getFileList(String seq) {
		return template.selectList("file.mfilelist", seq);
	}
	
	
}
