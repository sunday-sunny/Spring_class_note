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
	
	
}
