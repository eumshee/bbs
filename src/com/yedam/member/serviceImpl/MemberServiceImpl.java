package com.yedam.member.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class MemberServiceImpl extends DAO implements MemberService {
	PreparedStatement psmt;
	ResultSet rs;

	// id 중복체크 메소드
	// 중복이면 true, 아니면 false.
	public boolean idCheck(String id) {
		boolean exist = false;
		String sql = "select id from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				exist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return exist;
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		return null;
	}

	@Override
	public MemberVO selectMember() {
		return null;
	}

	@Override
	public int insertMember(MemberVO vo) {
		String sql = "insert into member values(?,?,?,?)";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPasswd());
			psmt.setString(4, vo.getAddress());
			n = psmt.executeUpdate();
			if(n != 0) {
				System.out.println(n+"건 입력");
			} else {
				System.out.println("입력 실패..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int updateMember(MemberVO vo) {
		return 0;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		return 0;
	}

}
