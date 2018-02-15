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
		MemberService service = MemberService.getInstance();
		memberVO = service.memberSearch(request); // 회원정보를 수정하려면 먼저 아이디 검색이 필요
		request.setAttribute("memberVO", memberVO);
		
		HttpUtil.forward(request, response, "/main/memberModify.jsp");
	}

}
