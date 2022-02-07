package com.test.memo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemoDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * MemoDAO 생성자
	 * - DB연결 설정
	 */
	public MemoDAO() {
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("MemoDAO.MemoDAO()");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * memo 목록을 가져오는 메소드
	 * @return memo 리스트
	 */
	public List<MemoDTO> list() {
		
		try {
			String sql = "select * from tblMemo order by seq desc";
			rs = stat.executeQuery(sql);

			List<MemoDTO> list = new ArrayList<MemoDTO>();
			
			while(rs.next()) {
				MemoDTO dto = new MemoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("MemoDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * memo를 추가하는 메소드
	 * @param dto memo DTO
	 * @return INSERT 결과
	 */
	public int add(MemoDTO dto) {
		
		try {

			String sql = "insert into tblMemo(seq, name, memo, regdate) values (seqMemo.nextVal, ?, ?, default)"; 
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getMemo());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemoService.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
}
