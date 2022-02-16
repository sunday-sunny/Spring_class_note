package com.test.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/* @RestController
 * - RESTful API 메소드 생성시 사용하는 어노테이션
 * - RESTful API 메소드는 모든 메소드에 @ResponseBody 어노테이션을 붙여야 하는데
 * 	 해당 어노테이션을 붙일 경우, 자동으로 모든 메소드에 @ResponseBody을 붙여버림
 * - 대신 이 친구는 jsp를 부를 수 없다.
 * 
 * @Controller를 붙이고, 매 메소드마다 @ResponseBody를 붙여도 되긴 함.
 * 
 */


@RestController
public class AddressController {
	
	@Autowired private AddressDAO dao;
	
	
	/* RESTful API 만들기 
	 * 1. @RestController					: 자동으로 @ResponseBody를 붙여주는 Controller 어노테이션
	 * 2. @ResponseBody(메소드 헤더 위)		: 이 어노테이션 때문에 JSON으로 받을 수 있음
	 * 3. @RequestBody(메소드 매개변수)		: 해당 어노테이션을 붙여야 RESTful API 메소드가 됨
	 * 4. 메소드마다 알맞은 함수 Mapping
	 */
	
	
	/**
	 * 요소 추가
	 * 1. http://localhost:8090/address
	 * 2. POST
	 * 3. return int
	 * @param dto 추가하려는 주소록DTO
	 * @return insert 결과값 
	 */
	@PostMapping("/address")
	public int add(@RequestBody AddressDTO dto) {
		return dao.add(dto);
	}
	
	
	/**
	 * 요소 가져오기(목록)
	 * 1. http://localhost:8090/address
	 * 2. GET
	 * 3. return JSON
	 * @return JSON 타입의 주소록 목록
	 */
	@GetMapping("/address")
	public List<AddressDTO> list(){
		return dao.list();
	}
	
	
	/**
	 * 요소 수정하기
	 * 1. http://localhost:8090/address/1
	 * 2. PUT
	 * 3. return int
	 * @param seq 수정할 주소록 seq
	 * @param dto 수정할 주소록 DTO
	 * @return update 결과값
	 */
	@PutMapping("/address/{seq}")
	public int edit(@PathVariable("seq") String seq, @RequestBody AddressDTO dto) {
		dto.setSeq(seq);
		return dao.edit(dto);
	}
	
	
	/**
	 * 요소 삭제하기
	 * 1. http://localhost:8090/address/1
	 * 2. DELETE
	 * 3. return int
	 * @param seq 삭제할 주소록 seq
	 * @return delete 결과값
	 */
	@DeleteMapping("/address/{seq}")
	public int del(@PathVariable("seq") String seq) {
		return dao.del(seq);
	}
	
	
	/**
	 * 요소 검색하기
	 * 1. http://localhost:8090/address/검색어
	 * 2. GET
	 * 3. return JSON
	 * @param word 검색하고자 하는 검색어
	 * @return JSON 타입의 주소록 목록
	 */
	@GetMapping("/address/{word}")
	public List<AddressDTO> search(@PathVariable("word") String word){
		
		/* 만약 n개 검색일 경우 */
		// 어노테이션	: @GetMapping("/address/{word}/{word2}")
		// 파라미터		: @PathVariable("word") String word, @PathVariable("word2") String word2
		
		return dao.search(word);
	}
	
	
}
