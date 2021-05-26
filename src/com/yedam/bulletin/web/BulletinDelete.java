package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String id = request.getParameter("id");
		
		BulletinVO vo = new BulletinVO();
		vo.setWriter(writer);
		vo.setId(Integer.parseInt(id));
		
		BulletinService service = new BulletinServiceImpl();
		service.deleteBulletin(vo);
		
		return "/bulletinList.do";
	}

}
