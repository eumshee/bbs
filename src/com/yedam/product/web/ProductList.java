package com.yedam.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.DbCommand;
import com.yedam.product.service.ProductService;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class ProductList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.productSelectList();
		request.setAttribute("list", list);
		  
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ProductServiceImpl service2 = new ProductServiceImpl();
		int cartCnt = service2.getCountCart(id);
		
		session.setAttribute("cartCnt", cartCnt);
		
		return "product/productList.tiles";
	}

}
