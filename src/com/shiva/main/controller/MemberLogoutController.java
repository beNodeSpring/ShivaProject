package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutController implements Controller {
	
	// id로 로그인한 세션을 제거(로그아웃)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("id") != null) {
			session.invalidate();
			System.out.println("MemberLogoutController : 로그아웃 업무 완료");
		} else {
			System.out.println("MemberLogoutController : 로그인 상태 아님");
		}
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
