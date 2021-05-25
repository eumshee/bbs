package com.yedam.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.common.Paging;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeListPaging implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page"); // (원하는) 페이지번호.
		if(page == null) {
			page="1";
		}
		int pageCnt = Integer.parseInt(page);
		
		// 전체 건수를 위해 실행.
		NoticeServiceImpl service = new NoticeServiceImpl();
		List<NoticeVO> total = service.noticeSelectList(); // totalCnt
		
		// 현재 페이지 리스트를 위해 실행.
		service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeListPaging(pageCnt);
		
		Paging paging = new Paging();
        paging.setPageNo(pageCnt);	// 해당 페이지 이동
        paging.setPageSize(10);		// 페이지 크기. 10건.
        paging.setTotalCount(total.size());

        request.setAttribute("noticeList", list);	 
        request.setAttribute("paging", paging);	
		
		return "notice/noticeListPaging.tiles";
	}

}
