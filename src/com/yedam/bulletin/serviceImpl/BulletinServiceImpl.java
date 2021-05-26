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

	// 페이징
	public List<BulletinVO> bulletinListPaging(int page) {
		String sql = "select b.*\r\n"
				+ "from(select rownum rn, a.*\r\n"//
				+ "        from (select * from bulletin n order by n.id ) a\r\n"
				+ ") b\r\n"
				+ "where b.rn between ? and ?";
		int firstCnt, lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1; // 1, 11
		lastCnt = (page * 10);			// 10, 20
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
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
	
	// 전체 리스트
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
	
	// 게시글 검색 리스트
	public List<BulletinVO> bulletinSearch(String title, String content, String writer){
		String sql = "select * from bulletin "
				+ "where title like ? "
				+ "or content like ? "
				+ "or writer like ?";
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%"+title+"%");
			psmt.setString(2, "%"+content+"%");
			psmt.setString(3, "%"+writer+"%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new BulletinVO();
				rvo.setId(rs.getInt("id"));
				rvo.setHit(rs.getInt("hit"));
				rvo.setTitle(rs.getString("title"));
				rvo.setContent(rs.getString("content"));
				rvo.setWriter(rs.getString("writer"));
				rvo.setRegDate(rs.getDate("reg_date"));
				list.add(rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	// 한건 조회
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
	
	// 게시글 입력
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

	// 게시글 수정
	@Override
	public int updateBulletin(BulletinVO vo) {
		String sql = "update bulletin set title = ?, content = ? where writer = ? and id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setInt(4, vo.getId());
			n = psmt.executeUpdate();
			if(n!=0) {
				System.out.println(n+"건 수정!");
			} else {
				System.out.println("수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 게시글 삭제
	@Override
	public int deleteBulletin(BulletinVO vo) {
		String sql = "delete from bulletin where writer = ? and id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setInt(2, vo.getId());
			n = psmt.executeUpdate();
			if(n!=0) {
				System.out.println(n+"건 삭제!");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
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
