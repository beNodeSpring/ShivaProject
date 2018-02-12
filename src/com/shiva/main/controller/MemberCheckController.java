package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.MemberDAO;

public class MemberCheckController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");		
		request.setCharacterEncoding("UTF-8");
		
		String desiredId = request.getParameter("desiredId");
		MemberDAO dao = MemberDAO.getInstance();
		if(dao.memberIdCheck(desiredId)) {
			System.out.println("중복 아이디 존재");
		} else {
			System.out.println("중복 아이디 없음");			
			response.getWriter().print(desiredId);
		}
	}

}
