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
