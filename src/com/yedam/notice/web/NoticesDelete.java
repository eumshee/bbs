package com.yedam.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticesDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

			String[] chk = request.getParameterValues("chkArr");
			String[] arr=null;
			for (int i = 0; i < chk.length; i++) {
		           arr=chk[i].split(",");         
		    }
			
			for(int i = 0; i < arr.length; i++) {
				NoticeVO vo = new NoticeVO();
				vo.setId(Integer.parseInt(arr[i]));
				
				NoticeService service = new NoticeServiceImpl();
				service.deleteNotice(vo);
				System.out.println(Integer.parseInt(arr[i]));
			}

		return "/noticeListPaging.do";
	}
}