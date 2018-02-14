package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.MemberDAO;

public class RecentPostController implements Controller {

	// 마이페이지로 이동 담당
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");		
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = MemberDAO.getInstance();
		String[] result = dao.viewRecentPost();
		System.out.println(result[2]);
		response.getWriter().print(result[0]+"/");
		response.getWriter().print(result[1]+"/");
		response.getWriter().print(result[2]);

	}

}
