package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.MemberDAO;
import com.shiva.main.service.MemberService;

public class HotPostController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = MemberService.getInstance();
		String[] result = service.viewHotPost();	
		System.out.println("핫게시물 갯수 : "+result.length);
		// 구분자가 &기 때문에 게시물 제목에 &가 있으면 안됨
		for (int i = 0; i < result.length; i++) {
			if(i==result.length-1) {
				response.getWriter().print(result[i]);
			} else {
				response.getWriter().print(result[i]+"&");
			}
		}
	}

}
