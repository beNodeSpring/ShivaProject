package com.shiva.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shiva.main.service.MemberService;
import com.shiva.main.vo.MemberVO;

public class MemberDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");		
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 세션 아이디를 가져온다
		MemberVO member = new MemberVO();
		MemberService service = MemberService.getInstance();
		boolean isDelete = service.memberDelete(member, request);
		System.out.println("service.memberUpdate() 완료 => MemberService 인스턴스 주소값: "+service);

		if(isDelete) {
			System.out.println("회원탈퇴완료");
			session.invalidate();
			out.print("<script>");
			out.print("alert('회원정보탈퇴가 완료되었습니다');");
			out.print("location.href='/ShivaProject/'");
			out.print("</script>");
		} else {
			System.out.println("회원탈퇴실패");
			out.print("<script>");
			out.print("alert('회원정보탈퇴가 실패했습니다. 관리자에게 문의하세요');");
			out.print("</script>");
		}
		
	}

}
