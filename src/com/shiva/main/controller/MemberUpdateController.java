package com.shiva.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.service.MemberService;
import com.shiva.main.vo.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 파라미터로 수정할 회원정보 추출
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		
		// 유효성 체크 - 스크립트단에서 처리할꺼임
		
		
		// VO객체 member에 데이터 바인딩
		MemberVO member = new MemberVO();

		member.setName(name);
		member.setPasswd(passwd);
		member.setGender(gender);
		member.setMail(mail);
		member.setPhone(phone);	
		
		// Service 객체를 추출한뒤 의 회원정보 수정 서비스처리 담당 memberUpdate()를 호출
		MemberService service = MemberService.getInstance();
		boolean isUpdate = service.memberUpdate(member, request);
		System.out.println("service.memberUpdate() 완료 => MemberService 인스턴스 주소값: "+service);

		if(isUpdate) {
			System.out.println("회원정보 수정완료");
			out.print("<script>");
			out.print("alert('회원정보 수정이 완료되었습니다');");
			out.print("location.href='/ShivaProject/memberModify.shiva'");
			out.print("</script>");
		} else {
			System.out.println("회원정보 수정실패");
			out.print("<script>");
			out.print("alert('회원정보 수정이 실패했습니다. 관리자에게 문의하세요');");
			out.print("</script>");
		}
		
		// view 페이지로 이동	
		//HttpUtil.forward(request, response, "/main/memberModify.jsp");
		//HttpUtil.redirect(response, "/ShivaProject/main/memberModify.jsp");
		
	}

}
