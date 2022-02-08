package com.test.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemoController {

	/* 기존 생성자를 이용한 DI 방법 */
	//public MemoController(IMemoService service) {
	//	this.service = new MemoService();
	//}

	
	/* @Autowired
	 *  - Spring DI 발생 (XML에 설정해 줄 필요가 없다)
	 *  - 의존 객체 변수에 선언
	 *  - 여러 메소드에서 사용하고 있는 공용 객체를 멤버 변수로 설정 후 설정.
	 */
	
	@Autowired
	private IMemoService service;
	
	
	/* List */
	@RequestMapping(value="/list.do", method= {RequestMethod.GET})
	public String list(HttpServletRequest req) {
		
		// Controller -> Service -> DAO -> DTO
		List<MemoDTO> list = service.list();
		req.setAttribute("list", list);
		
		return "list";
	}
	
	
	/* Add */
	@RequestMapping(value="/add.do", method= {RequestMethod.GET})
	public String add() {
		
		return "add";
	}
	
	@RequestMapping(value="/addok.do", method= {RequestMethod.POST})
	public void addok(HttpServletResponse resp, MemoDTO dto) {
	
		// 1. 데이터 가져오기
		// 2. DB작업 > insert > DAO(X), Service(O)
		
		int result = service.add(dto);
		
		try {
			if(result == 1)
				resp.sendRedirect("/memo/list.do");
			else
				resp.sendRedirect("/memo/add.do");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/* Edit */
	@RequestMapping(value="/edit.do", method= {RequestMethod.GET})
	public String edit(HttpServletRequest req, String seq) {
		
		// 1. 데이터 가져오기
		// 2. DB작업 > select > Service 위임
		// 3. 반환값 전달 + JSP 호출
		
		MemoDTO dto = service.get(seq);
		
		req.setAttribute("dto", dto);
		
		
		return "edit";
	}
	
	@RequestMapping(value="/editok.do", method= {RequestMethod.POST})
	public void editok(HttpServletResponse resp, MemoDTO dto) {
		
		// 1. 데이터 가져오기
		// 2. DB작업 > select > Service 위임
		// 3. 반환값 전달 + JSP 호출
		
		int result = service.edit(dto);
		
		try {
			if(result == 1)
				resp.sendRedirect("/memo/list.do");
			else
				resp.sendRedirect("/memo/edit.do?seq=" + dto.getSeq());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/* Del */
   @RequestMapping(value="/delok.do", method={RequestMethod.GET})
   public void delook(HttpServletResponse resp, String seq) {
      
	   // *** location.href은 GET 요청
	   
	   // 1. 데이터 가져오기(seq)
	   // 2. DB 작업 > delete > Service 위임
	   // 3. 피드백
	   
	   int result = service.del(seq);
	   
	   try {
			resp.sendRedirect("/memo/list.do");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	   
   }
	
	
}
