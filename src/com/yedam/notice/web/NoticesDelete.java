package com.yedam.notice.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticesDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> chkArr = request.getParameterNames();

		while (chkArr.hasMoreElements()) {
			String chk = (String) chkArr.nextElement();
			String[] ch = request.getParameterValues(chk);
			for (int i = 0; i < ch.length; i++) {
				NoticeVO vo = new NoticeVO();
				vo.setId(Integer.parseInt(ch[i]));
				NoticeService service = new NoticeServiceImpl();
				service.deleteNotice(vo);
			}
		}

		return "/noticeListPaging.do";
	}
}