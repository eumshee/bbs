package com.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.product.serviceImpl.ProductServiceImpl;

//import com.yedam.product.serviceImpl.ProductServiceImpl;

public class IndexPage implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ProductServiceImpl service = new ProductServiceImpl();
		int cartCnt = service.getCountCart(id);
		
		//request.setAttribute("cartCnt", cartCnt);
		session.setAttribute("cartCnt", cartCnt);
		
		return "common/section.tiles";
	}

}
