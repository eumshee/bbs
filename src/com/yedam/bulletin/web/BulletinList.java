package com.yedam.bulletin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;
import com.yedam.common.Paging;

public class BulletinList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page"); // (원하는) 페이지번호.
		if(page == null) {
			page="1";
		}
		int pageCnt = Integer.parseInt(page);
		
		// 게시글 리스트 --> bulletinList.jsp
		
		BulletinServiceImpl service = new BulletinServiceImpl();
		List<BulletinVO> total = service.bulletinSelectList();
		
		service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinListPaging(pageCnt);
		
		request.setAttribute("bulletinList", list);
		
		Paging paging = new Paging();
        paging.setPageNo(pageCnt);	// 해당 페이지 이동
        paging.setPageSize(10);		// 페이지 크기. 10건.
        paging.setTotalCount(total.size());

        request.setAttribute("paging", paging);
		return "bulletin/bulletinList.tiles";
	}

}
