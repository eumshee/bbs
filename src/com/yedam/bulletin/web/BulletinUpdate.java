package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터 (id, title, content, writer)
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String id = request.getParameter("id");

		BulletinVO vo = new BulletinVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);		
		vo.setId(Integer.parseInt(id));

		BulletinServiceImpl service = new BulletinServiceImpl();
		service.updateBulletin(vo);
		
		return "/bulletinList.do";
	}

}
