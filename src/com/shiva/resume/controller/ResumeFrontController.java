package com.shiva.resume.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.shiva.resume.service.*;

public class ResumeFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String RequestURI = request.getRequestURI(); //포트 번호 다음부터 마지막 문자열까지 반환 (http://localhost:8088/SemiProject/mainpage.shiva인 경우
		//"/SemiProject/mainpage.shiva"가 반환됨)
		System.out.println("RequestURI = " + RequestURI);
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		//초기화
		ServiceForward forward = null;
		Service service = null;
		
		//시작
		System.out.println("ResumeFrontController 시작합니다.");
		
		if(command.contentEquals("/myResume.ro")) {
			HttpSession session = request.getSession(false);
			if(session.getAttribute("id") == null) {
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("/ShivaProject"); //나중에 로그인 창으로 변경할 것.
			} else if ((request.getParameter("want")!=null)&&!(request.getParameter("want").equals(session.getAttribute("id")))) {
				System.out.println("want파라미터는 " + request.getParameter("want"));
				System.out.println("session아이디는 " + session.getAttribute("id"));
				service = new ResumeShowService2();
				try {
					forward =service.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				service = new ResumeShowService();
				try {
					forward =service.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if(command.equals("/ResumeUpdate.ro")) {
			service = new ResumeUpdateService();
			try {
				forward =service.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/ResumeList.ro")) {
			service = new ResumeListService();
			try {
				forward =service.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			System.out.println("ResumeFrontController 끝.");
			if(forward.isRedirect()) { //리다이렉트로 보냄
				response.sendRedirect(forward.getPath());
			} else { //포워딩으로 보냄
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
}
