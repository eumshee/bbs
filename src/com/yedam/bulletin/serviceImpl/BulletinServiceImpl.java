package com.yedam.bulletin.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DAO;

public class BulletinServiceImpl extends DAO implements BulletinService {
	PreparedStatement psmt;
	ResultSet rs;
	BulletinVO rvo = null;
	int n = 0;

	@Override
	public List<BulletinVO> bulletinSelectList() {
		String sql = "select * from bulletin order by id";
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new BulletinVO();
				rvo.setId(rs.getInt("id"));
				rvo.setTitle(rs.getString("title"));
				rvo.setContent(rs.getString("content"));
				rvo.setWriter(rs.getString("writer"));
				rvo.setRegDate(rs.getDate("reg_date"));
				rvo.setHit(rs.getInt("hit"));
				list.add(rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BulletinVO bulletinSelect(BulletinVO vo) {
		String sql = "select * from bulletin where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new BulletinVO();
				hitCount(vo.getId());
				rvo.setId(rs.getInt("id"));
				rvo.setTitle(rs.getString("title"));
				rvo.setContent(rs.getString("content"));
				rvo.setWriter(rs.getString("writer"));
				rvo.setRegDate(rs.getDate("reg_date"));
				rvo.setHit(rs.getInt("hit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rvo;
	}

	@Override
	public int insertBulletin(BulletinVO vo) {
		String sql = "insert into bulletin values(bulletin_seq.nextval, ?, ?, ?, sysdate, 0)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			n = psmt.executeUpdate();
			if(n!=0) {
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
	public int updateBulletin(BulletinVO vo) {
		return 0;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {
		return 0;
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
	
	public void hitCount(int id) { // 호출하는 값의 cnt 증가.
		String sql = "update bulletin set hit = hit+1 where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			int n = psmt.executeUpdate();
			if(n!=0) {
				System.out.println(id+"번글 조회수 증가!");				
			} else {
				System.out.println("실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
