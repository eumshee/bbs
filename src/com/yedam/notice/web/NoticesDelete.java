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
		String[] chkArr = request.getParameterValues("chk");
		//Enumeration<String> chk = chkArr;
		
		if(chkArr != null) {
			for(int i = 0; i<chkArr.length ; i++) {
				NoticeVO vo = new NoticeVO();
				vo.setId(Integer.parseInt(chkArr[i]));
				NoticeService service = new NoticeServiceImpl();
				service.deleteNotice(vo);
			}			
		} else {
			
		}
		
		
		return "/noticeListPaging.do";
	}

}
