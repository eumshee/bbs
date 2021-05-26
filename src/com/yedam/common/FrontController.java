package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.web.Bulletin;
import com.yedam.bulletin.web.BulletinDelete;
import com.yedam.bulletin.web.BulletinForm;
import com.yedam.bulletin.web.BulletinInsert;
import com.yedam.bulletin.web.BulletinList;
import com.yedam.bulletin.web.BulletinSearch;
import com.yedam.bulletin.web.BulletinUpdate;
import com.yedam.member.web.MemberJoin;
import com.yedam.member.web.MemberJoinForm;
import com.yedam.member.web.MemberLogin;
import com.yedam.member.web.MemberLoginForm;
import com.yedam.member.web.MemberLoginOut;
import com.yedam.notice.web.Notice;
import com.yedam.notice.web.NoticeDelete;
import com.yedam.notice.web.NoticeForm;
import com.yedam.notice.web.NoticeInsert;
import com.yedam.notice.web.NoticeList;
import com.yedam.notice.web.NoticeListPaging;
import com.yedam.notice.web.NoticeSearch;
import com.yedam.notice.web.NoticeUpdate;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {
	private HashMap<String, DbCommand> map = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException { // 요청페이지 - 실행컨트롤
		// 회원
		map.put("/main.do", new MainPage());
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLoginOut.do", new MemberLoginOut());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListPaging.do", new NoticeListPaging());
		map.put("/notice.do", new Notice());
		map.put("/noticeUpdate.do", new NoticeUpdate());
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/noticeInsert.do", new NoticeInsert());
		map.put("/noticeSearch.do", new NoticeSearch());
		map.put("/noticeDelete.do", new NoticeDelete());
		
		// 게시글
		map.put("/bulletinList.do", new BulletinList());
		map.put("/bulletinForm.do", new BulletinForm());
		map.put("/bulletinInsert.do", new BulletinInsert());
		map.put("/bulletin.do", new Bulletin());
		map.put("/bulletinSearch.do", new BulletinSearch());
		map.put("/bulletinUpdate.do", new BulletinUpdate());
		map.put("/bulletinDelete.do", new BulletinDelete());
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 페이지 정보 읽기
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		DbCommand command = map.get(path);
		String viewPage = command.execute(req, resp);

		// 보여줄 페이지
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}

}
