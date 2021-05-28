package com.yedam.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class CartList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("uid");
		  
		ProductServiceImpl service = new ProductServiceImpl();
		List<ProductVO> list = service.cartSelectList(id);
		
		request.setAttribute("clist", list);
		
		
		return "product/cartList.tiles";
	}

}
