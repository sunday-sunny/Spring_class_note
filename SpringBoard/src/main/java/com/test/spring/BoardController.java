package com.test.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	
	/* @GetMapping, @PostMapping
	 * - 스프링 4.0대에 나온 어노테이션 
	 * - 기존에 쓰던 @requestMapping과 같은 의미 
	 */
	
	
	/* ** 컨트롤러가 하는 3가지 일 ** 
	 * 1. 데이터 가져오기
	 * 2. 업무 위임
	 * 3. 뷰호출 
	 * 	- 이것만 함. 나머지는 서비스에 넘김 !!
	 */
	
	
	@GetMapping("/login")
	public String login(String id, HttpSession session, HttpServletRequest req) {
		session.setAttribute("id", id);
		return "redirect:/list";
	}
	
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BoardDTO> list = service.list();
		model.addAttribute("list", list);		// == req.setAttribute 
		
		return "board.list";
	}
	
	
	@GetMapping("/add")
	public String add() {
		return "board.add";
	}
	
	
	@PostMapping("/addok")
	public String addok(BoardDTO dto, HttpSession session, HttpServletRequest req) {
		
		/* ※ 원래 파라미터 원형 
		 * public String addok(@ModelAttribute("dto") BoardDTO dto, @RequestParam("seq") String seq)
		 */
		
		int result = service.add(dto, session, req);
		
		if(result == 1)
			return "redirect:/list";
		else
			return "redirect/add";
		
	}
	
	
}
