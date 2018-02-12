package com.shiva.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shiva.main.dao.MemberDAO;

public class MemberLoginController implements Controller {

	// 로그인한 id로 세션을 생성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("passwd");
		String[] idPw = {id, pwd};
		MemberDAO dao = MemberDAO.getInstance();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		// 아이디&비밀번호 맞나 확인
		String idCheck = dao.returnId(idPw);
		System.out.println(idCheck);
		if (id.equals(idCheck)) { // 아이디& 비밀번호 일치 
			session.setAttribute("id", idCheck); // 세션 생성
			
			System.out.println("MemberLoginController : 로그인 업무 완료");
			out.print("<script>");
			out.print("alert('로그인이 완료되었습니다');");
			out.print("location.href='/ShivaProject';");
			out.print("</script>");			
		} else { // 아이디& 비밀번호 불일치 
			out.print("<script>");
			out.print("alert('회원정보가 일치하지 않습니다');");
			out.print("history.back()");
			out.print("</script>");
		}
		
		

		
		//System.out.println("MemberLoginController.doPost()");
		
		// 아이디 or 비밀번호 틀릴때

		
		// 로그인 되었을 때
/*		
		if (session.isNew() || session.getAttribute("id") == null) {

			
		} else {
			System.out.println("MemberLoginController : 이미 로그인 상태");		
			out.print("<script>");
			out.print("alert('이미 로그인 상태입니다.');");
			out.print("location.href='/ShivaProject';");
			out.print("</script>");			
		}
 */	
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
