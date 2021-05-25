package com.yedam.notice.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터 (id, title, content)
		// serviceImpl --> update 기능 작성.
		// 공지사항 리스트로 페이지 호출.
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(Integer.parseInt(id));

		NoticeService service = new NoticeServiceImpl();
		service.updateNotice(vo);
		
//		return "notice/noticeList.tiles";
		return "/noticeList.do";
	}

}
