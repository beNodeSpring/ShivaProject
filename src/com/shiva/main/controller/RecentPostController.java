package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.MemberDAO;
import com.shiva.main.service.MemberService;

public class RecentPostController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = MemberService.getInstance();
		String[] result = service.viewRecentPost();	
		// 3가지 게시글 합치고 "/"로 구분해서 응답함
		response.getWriter().print(result[0]+"/");
		response.getWriter().print(result[1]+"/");
		response.getWriter().print(result[2]);	
	}

}
