package com.yedam.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeSearch implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		NoticeVO vo = new NoticeVO();
		vo.setTitle(title);
		vo.setContent(content);
		
		NoticeServiceImpl service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeSearch(vo);
		
		request.setAttribute("noticeList", list);
		
		return "/noticeList.do";
	}

}
