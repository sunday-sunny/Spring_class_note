package com.test.memo;

import java.util.List;


public interface IMemoService {
	
	/* memo 목록을 가져오는 service 메소드 */
	List<MemoDTO> list();
	
	/* memo를 추가하는 service 메소드 */
	int add(MemoDTO dto);

	/* memo를 하나 가져오는 메소드 */
	MemoDTO get(String seq);
	
	/* memo를 수정하는 메소드 */
	int edit(MemoDTO dto);
	
	/* memo를 삭제하는 메소드 */
	int del(String seq);


}
