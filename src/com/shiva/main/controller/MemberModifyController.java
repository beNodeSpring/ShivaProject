package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.MemberDAO;
import com.shiva.main.service.MemberService;
import com.shiva.main.vo.MemberVO;

public class MemberModifyController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberVO = memberDAO.memberSearch(request);
		request.setAttribute("memberVO", memberVO);
		
		HttpUtil.forward(request, response, "/main/memberModify.jsp");
	}

}
