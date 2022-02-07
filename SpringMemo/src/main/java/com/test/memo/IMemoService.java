package com.test.memo;

import java.util.List;


public interface IMemoService {
	
	/* memo 목록을 가져오는 service 메소드 */
	List<MemoDTO> list();
	
	/* memo를 추가하는 service 메소드 */
	int add(MemoDTO dto);

}
