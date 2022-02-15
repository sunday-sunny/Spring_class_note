package com.test.spring;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;

	@Override
	public int add(BoardDTO dto, HttpSession session, HttpServletRequest req) {
		
		/* id 추가 : session 로그인 id값을 받아온다. */
		dto.setId(session.getAttribute("id").toString());
		
		/* 첨부파일 추가 : req로 넘어온 값을 받아온다. */
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;
		
		MultipartFile file = multi.getFile("attach");
		String path = req.getRealPath("/resources/file");
		System.out.println(path);

		// 첨부파일이 있는지 체크
		if(!file.isEmpty()) {
			
			// origin name
			String filename = file.getOriginalFilename();
			dto.setOrgfilename(filename);
			
			// numbering name
			filename = getFileName(path, filename);
			dto.setFilename(filename);
			
			// Transfer file to hope path from temp folder
			try {
				File mfile = new File(path+"\\"+filename);
				file.transferTo(mfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dao.add(dto);
	}
	
	
	/**
	 * 올라온 첨부파일의 이름을 넘버링하여 반환
	 * @param path 저장 희망경로
	 * @param filename original 파일이름
	 * @return 넘버링된 파일이름
	 */
	private String getFileName(String path, String filename) {
		
		// path = "/resources/file"
		// filename = "제품_이미지.png" > "제품_이미지_1.png"
		
		
		/* 확장자 기준으로 parsing */
		int n = 1;
		int index = filename.lastIndexOf(".");
		String tempName = filename.substring(0, index); // "제품_이미지"
		String tempExt = filename.substring(index);		// ".png"
		
		
		/* 파일 이름 중복 검사 */
		while(true) {
			
			// 파일 객체 참조
			File file = new File(path + "\\" + filename); // "resources/file/제품_이미지.png"
			
			if(file.exists()) {
				// 넘버링
				filename = tempName + "_" + n + tempExt; // "제품_이미지_1.png"
				n++;
			}
			else
				return filename;
		}
	}

	
	@Override
	public List<BoardDTO> list() {
		return dao.list();
	}


	@Override
	public BoardDTO get(String seq) {
		return dao.get(seq);
	}


	@Override
	public int edit(BoardDTO dto, HttpSession session, HttpServletRequest req, String del) {
		
		/* id 추가 : session 로그인 id값을 받아온다. */
		dto.setId(session.getAttribute("id").toString());
		
		/* 첨부파일 추가 : req로 넘어온 값을 받아온다. */
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;
		
		MultipartFile file = multi.getFile("attach");
		String path = req.getRealPath("/resources/file");
		System.out.println(path);
		
		
		/* 해당 게시물의 첨부파일 정보 가져와서 넘어온 수정 게시물 dto에 세팅 */
		BoardDTO mdto = dao.get(dto.getSeq());
		dto.setOrgfilename(mdto.getOrgfilename());
		dto.setFilename(mdto.getFilename());
		
		
		/* 수정 게시물에 첨부파일을 추가한 경우 */
		if (!file.isEmpty()) {
			
			// 기존 등록된 첨부파일이 있는 경우, 기존 파일 삭제
			if (mdto.getFilename() != null) {
				File delfile = new File(path + "\\" + mdto.getFilename());
				delfile.delete();
			}
			
			// 새로 첨부한 첨부파일 세팅
			String filename = file.getOriginalFilename();
			dto.setOrgfilename(filename);
			
			filename = getFileName(path, filename);
			dto.setFilename(filename);
			
			try {
				File mfile = new File(path + "\\" + filename);
				file.transferTo(mfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		/* 수정 게시물에 첨부파일을 추가하지 X한 경우 */
		else {
			
			// 기존 등록된 첨부파일을 [삭제]로 수정한 경우
			if (del.equals("on")) {
				
				// 기존 첨부파일 삭제 처리
				File delfile = new File(path + "\\" + mdto.getFilename());
				delfile.delete();
				
				dto.setOrgfilename(null);
				dto.setFilename(null);
			}
		}
		
		return dao.edit(dto);
	}
	
	
	@Override
	public int del(String seq, HttpServletRequest req) {
		
		// 첨부파일 삭제 선행
		BoardDTO dto = dao.get(seq);
		
		String path = req.getRealPath("/resources/file");
		File file = new File(path + "\\" + dto.getFilename());
		file.delete();
		
		return dao.del(seq);
	}
	
	
}
