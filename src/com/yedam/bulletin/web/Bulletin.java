package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class Bulletin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 한건 조회 --> notice.jsp (상세화면)
		// ServiceImpl 구현.
		String id = request.getParameter("id");		

		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
		
		BulletinService service = new BulletinServiceImpl();
		BulletinVO rvo = service.bulletinSelect(vo);
		
		request.setAttribute("bulletin", rvo);
		
		return "bulletin/bulletin.tiles";
	}

}
