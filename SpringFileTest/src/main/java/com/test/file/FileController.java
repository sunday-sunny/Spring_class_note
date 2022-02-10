package com.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {

	@Autowired
	private BoardDAO dao;
	
	/**
	 * "파일 다운로드" 기능을 위한 멤버 변수
	 */
	@Autowired 
	private ServletContext context;
	
	
	
	/* 파일업로드(1개) | add 페이지 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET })
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		return "add";
	}
	
	
	
	/* 파일업로드(1개) | addok, 첨부파일 저장 */
	@RequestMapping(value = "/addok.do", method = { RequestMethod.POST })
	public String addok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, BoardDTO dto) {
		
		
		/* ****** 파일 저장하는 과정 ****** */
		
		
		/* 첨부파일 받아오기 */
		// - enctype="multipart/form-data" 이걸로 요청할 때만, 
		// - "MultipartHttpServletRequest" 이걸로 받아옴
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;

		
		
		/* 첨부 파일 참조 객체 */ 
		// <input type="file" name="attach">
		MultipartFile attach = multi.getFile("attach"); 
		System.out.println(attach.getOriginalFilename()); 	// 첨부파일명
		System.out.println(attach.getSize());				// 첨부파일 크기(바이트)
		System.out.println(attach.getContentType());		// 첨부파일 형식
		
		
		
		/* 원하는 폴더로 파일 이동 
		 *  - 첨부 파일 > 일단 임시 폴더에 저장됨 > 원하는 폴더로 파일 이동
		 *  - webapp > resources > file에 저장할 예정, 폴더 만들기
		 *  - req.getRealPath("resources/file") : 희망경로, 업로드하고 싶은 실제 위치 지정
		 */
		
		
		/* 파일 저장 희망경로 확인*/
		// D:\class\spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringFileTest\resources\file
		System.out.println("파일 저장 희망경로 찍어보기 : " + req.getRealPath("resources/file"));
		
		// origin 파일이름
		String filename = attach.getOriginalFilename();
		dto.setOrgfilename(filename);

		// 넘버링한 파일이름
		filename = getFileName(req.getRealPath("resources/file"), filename);
		dto.setFilename(filename);
		
		// 희망경로에 파일 참조 객체 생성
		File file = new File(req.getRealPath("resources/file") + "\\" + filename);
		
		
		
		/* 임시 폴더에 있는 첨부파일을 희망경로로 이동 */
		try {
			attach.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		/* 게시판 글쓰기 */
		int result = dao.add(dto);
		
		try {
			// 결과에 상관없이 list.do로 redirect
			resp.sendRedirect("/file/list.do");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "add";
	
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

	
	
	/* 파일업로드(1개) | 목록 가져오기 */
	@RequestMapping(value = "/list.do", method = { RequestMethod.GET })
	public String list(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		List<BoardDTO> list = dao.list();
		req.setAttribute("list", list);

		return "list";
	}
	

	
	/* 파일 다운로드 메소드
	 * 	- 링크로 들어왔으니 GET 메소드 
	 * 	- 선생님이 주신 소스 복붙
	 *  - 재사용시, "savePath"(희망저장경로) 변경 사용
	 *  - 멤버변수로, "ServletContext context" 추가하여 사용 
	 *  - JSP 파일에 다운 원하는 파일 <a> 태그 걸어놓기
	 *  	- EX) <a href="/file/download.do?orgfilename=${dto.orgfilename}&filename=${dto.filename}">
	 */
	@RequestMapping(value = "/download.do", method = { RequestMethod.GET })
	public void download(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception {

		String fileName = req.getParameter("filename");
		String orgfileName = req.getParameter("orgfilename");

		String savePath = "resources/file"; // webapp > files

		String sDownloadPath = context.getRealPath(savePath);

		String sFilePath = sDownloadPath + "/" + fileName;
		byte b[] = new byte[4096];
		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);

		if (sMimeType == null)
			sMimeType = "application/octet-stream";// 브라우저 반환 MIME
		// text/html
		// image/gif
		// application/zip

		resp.setContentType(sMimeType);
		String agent = req.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);

		if (ieBrowser) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("/+", "%20");
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		}

		orgfileName = new String(orgfileName.getBytes("UTF-8"), "ISO-8859-1");

		resp.setHeader("Content-Disposition", "attachment; filename= " + orgfileName);

		ServletOutputStream out2 = resp.getOutputStream();
		int numRead;

		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out2.write(b, 0, numRead);
		}
		out2.flush();
		out2.close();
		in.close();

	}
	
	
	
	/* 파일업로드(N개) | add 페이지 */
	@RequestMapping(value = "/madd.do", method = { RequestMethod.GET })
	public String madd(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		return "madd";
	}
	
	
	
	/* 파일업로드(N개) | 첨부파일 저장 */
	@RequestMapping(value = "/maddok.do", method = { RequestMethod.POST })
	public void maddok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, MBoardDTO dto) {
		
		/* 첨부파일 받아오기 */
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;
		
		/* 첨부파일 N개 */
		Iterator<String> iter = multi.getFileNames();
		List<FileDTO> flist = new ArrayList<FileDTO>();
		
		/* while문을 돌면서 flist에 저장 */
		while(iter.hasNext()) {
			
			// 첨부 파일 태그명
			String item = iter.next(); 
			System.out.println(item);
			
			
			 // 첨부 파일명
			MultipartFile attach = multi.getFile(item);
			System.out.println(attach.getOriginalFilename());

			// fdto에 저장
			FileDTO fdto = new FileDTO();
			String filename = attach.getOriginalFilename();
			fdto.setOrgfilename(filename);
			
			// 파일이름 넘버링
			filename = getFileName(req.getRealPath("resources/file"), filename);
			fdto.setFilename(filename);
			
			// 희망경로에 파일 참조 객체 생성
			File file = new File(req.getRealPath("resources/file") + "\\" + filename);
			
			// 임시 폴더에 있는 첨부파일을 희망경로로 이동
			try {
				attach.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			flist.add(fdto);
			
		}
		
		
		/* DB 작업 */
		
		// 게시물 insert
		int result = dao.madd(dto);
		
		// 게시물 번호 select
		String bseq = dao.getSeq();
		
		// 첨부파일 insert
		for(FileDTO fdto : flist) {
			fdto.setBseq(bseq);
			result = dao.maddFile(fdto);
		}
		
		// 게시판 글쓰기
		try {
			resp.sendRedirect("/file/mlist.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/* 파일업로드(N개) | 목록 가져오기 */
	@RequestMapping(value = "/mlist.do", method = { RequestMethod.GET })
	public String mlist(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		List<MBoardDTO> list = dao.mlist();
		
		for(MBoardDTO dto : list) {
			List<FileDTO> flist = dao.getFileList(dto.getSeq());
			dto.setFlist(flist);
		}
		
		req.setAttribute("list", list);

		return "mlist";
	}
	
	
}
