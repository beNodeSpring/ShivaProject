package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.service.MemberService;
import com.shiva.main.vo.MemberVO;

public class MemberInsertController implements Controller {

	// FrontController의 service()에서 호출 (회원정보 생성 서비스 담당)
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 추출
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		
		// 유효성 체크 - 스크립트단에서 처리할꺼임
		
		
		// VO객체 member에 데이터 바인딩
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setName(name);
		member.setPasswd(passwd);
		member.setGender(gender);
		member.setMail(mail);
		member.setPhone(phone);
		
		// Service 객체를 추출한뒤 의 회원정보 생성 서비스처리 담당 MemberInsert()를 호출
		MemberService service = MemberService.getInstance();
		service.MemberInsert(member);
		System.out.println("service.MemberInsert() 완료 => MemberService 인스턴스 주소값: "+service);
		
		// Output view 페이지로 이동
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/main/join_complete.jsp");
	}

}
