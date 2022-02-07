package com.test.spring;

import lombok.Data;

// 아래의 어노테이션을 사용해서 getter, setter, toString을 자동으로 만들어주는 기능
@Data
public class DTO {
	
	private String seq;
	private String id;
	private String subject;
	private String content;
	private String regdate;
	
	/* 2022-02-07(월), ex03 예제로 추가*/
	private String name;
	private String age;			
	private String address;

}
