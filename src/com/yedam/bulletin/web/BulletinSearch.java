package com.yedam.bulletin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinSearch implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		BulletinServiceImpl service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinSearch(title, content, writer);
		
		request.setAttribute("bulletinList", list);
		
		return "bulletin/bulletinSearchList.tiles";
	}

}
