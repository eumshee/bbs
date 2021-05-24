package com.yedam.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.DbCommand;
import com.yedam.member.service.MemberService;
import com.yedam.member.serviceImpl.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class MemberJoin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// DB 저장, main.do
		HttpSession session = request.getSession();
		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		String name = request.getParameter("memberName");
		String addr = request.getParameter("memberAddr");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPasswd(pwd);
		vo.setName(name);
		vo.setAddress(addr);
		
		MemberService service = new MemberServiceImpl();
		service.insertMember(vo);
		
		// 다른 페이지에서 값 유무 확인
		session.setAttribute("id", id);
		session.setAttribute("member", vo);
		
		return "main/main.tiles";
	}

}
