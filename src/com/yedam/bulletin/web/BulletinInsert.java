package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinInsert implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// DB insert --> bulletinList.jsp
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("id");
		
		BulletinVO vo = new BulletinVO();
		vo.setContent(content);
		vo.setTitle(title);
		vo.setWriter(writer);
		
		BulletinService service = new BulletinServiceImpl();
		service.insertBulletin(vo);
		
		return "/bulletinList.do";
	}

}
