package com.test.spring.cross;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 페이지 방문 기록을 남기는 logger
	 * @param dto 방문 log DTO
	 */
	public void add(LogDTO dto) {
		template.insert("log.add", dto);
	}
	
}
