package com.yedam.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.product.service.ProductService;
import com.yedam.product.vo.ProductVO;

public class ProductServiceImpl extends DAO implements ProductService{
	PreparedStatement psmt;
	ResultSet rs;
	
	ProductVO rvo = null;
	String sql = null;
	int n = 0;
	
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
	
	// 카트테이블에 상품정보 추가
	public void addCart(String id, String item, int qty) {
		sql = "insert into cart values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, item);
			psmt.setInt(3, qty);
			n = psmt.executeUpdate();
			System.out.println(id+"회원"+item+"주문");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 회원별 장바구니 상품수
	public int getCountCart(String id) {
		sql = "select count(*) from cart where user_id = ? ";
		int rCnt = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				rCnt = rs.getInt(1); // 첫번째 칼럼 명시; alias(alias지정시)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rCnt;
	}
	
	// 장바구니 전체조회
	public List<ProductVO> cartSelectList(String uid) {
		sql = "select c.user_id, c.item_code, p.item_name, p.price, p.sale, p.sale_price, \r\n"
				+ "sum(p.price) as sum, sum(p.sale_price) as psum, count(*) as item_qty\r\n"
				+ "from cart c\r\n"
				+ "left outer join product p\r\n"
				+ "on (c.item_code=p.item_code)\r\n"
				+ "group by rollup(c.user_id,c.item_code,p.item_name, p.price, p.sale, p.sale_price)\r\n"
				+ "having c.user_id=?";
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new ProductVO();
				rvo.setUserId(rs.getString("user_id"));
				rvo.setItemCode(rs.getString("item_code"));
				rvo.setItemName(rs.getString("item_name"));
				rvo.setItemQty(rs.getInt("item_qty"));
				rvo.setPrice(rs.getInt("price"));
				rvo.setSale(rs.getString("sale"));
				rvo.setSalePrice(rs.getInt("sale_price"));
				rvo.setSum(rs.getInt("sum"));
				rvo.setPsum(rs.getInt("psum"));
				list.add(rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 전체조회
	@Override
	public List<ProductVO> productSelectList() {
		sql = "select * from product order by 1";
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new ProductVO();
				rvo.setItemCode(rs.getString("item_code"));
				rvo.setItemName(rs.getString("item_name"));
				rvo.setItemImage(rs.getString("item_image"));
				rvo.setPrice(rs.getInt("price"));
				rvo.setItemDesc(rs.getString("item_desc"));
				rvo.setLikeIt(rs.getInt("like_it"));
				rvo.setSale(rs.getString("sale"));
				rvo.setSalePrice(rs.getInt("sale_price"));
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
	public ProductVO productSelect(ProductVO vo) {
		sql = "select * from product where item_code = ? order by 1";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			rs = psmt.executeQuery();
			if(rs.next()) {
				rvo = new ProductVO();
				rvo.setItemCode(rs.getString("item_code"));
				rvo.setItemName(rs.getString("item_name"));
				rvo.setItemImage(rs.getString("item_image"));
				rvo.setPrice(rs.getInt("price"));
				rvo.setItemDesc(rs.getString("item_desc"));
				rvo.setLikeIt(rs.getInt("like_it"));
				rvo.setSale(rs.getString("sale"));
				rvo.setSalePrice(rs.getInt("sale_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rvo;
	}

	@Override
	public int insertProduct(ProductVO vo) {
		return n;
	}

	@Override
	public int updateProduct(ProductVO vo) {
		return n;
	}

	@Override
	public int deleteProduct(ProductVO vo) {
		return n;
	}

}
