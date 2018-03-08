package com.shiva.license.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.service.BoardAddService;
import com.shiva.license.service.BoardDeleteService;
import com.shiva.license.service.BoardListService;
import com.shiva.license.service.BoardModifyService;
import com.shiva.license.service.BoardModifyViewService;
import com.shiva.license.service.BoardSerachService;
import com.shiva.license.service.BoardViewService;
import com.shiva.license.service.CalendarConAddService;
import com.shiva.license.service.CalendarConDelService;
import com.shiva.license.service.CalendarSubAddService;
import com.shiva.license.service.CalendarSubDelService;
import com.shiva.license.service.ReplyAddService;
import com.shiva.license.service.ReplyDeleteService;



/**
 * Servlet implementation class BoardFrontController
 */
// @WebServlet("/BoardFrontController")
public class LicenseFrontController 
	extends javax.servlet.http.HttpServlet {
	//private static final long serialVersionUID = 1L;
    protected void doProcess(HttpServletRequest request,
    		   				 HttpServletResponse response)
    throws ServletException, IOException {
    	
    	request.setCharacterEncoding("utf-8");
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	// �ʱ�ȭ
    	ActionForward forward = null;
    	Action action = null;
    	
    	
    	if(command.equals("/main.lo")) {
    		action = new BoardListService();
    		
    		try {
    			
    			request.setAttribute("board", "IT_NOTICE_BOARD");
    			forward = action.execute(request, response);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	} 
    	else if(command.equals("/List.lo")) {
    			action = new BoardListService();
        		
        		try {
        			setboardname(request.getParameter("board_type"),request);
        			forward = action.execute(request, response);
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
    		}
    	else if(command.equals("/View.lo")) {
    		action = new BoardViewService();
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/Search.lo")) {
    		action = new BoardSerachService(); //검색
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/Write.lo")) {
    		action = new BoardAddService(); //입력창보기
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/WriteView.lo")) {
    		
    		forward= new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("./license/mainpage2.jsp?action=write");
    	}
    	else if(command.equals("/ModifyView.lo")) {
    		
    		action = new BoardModifyViewService(); //수정창보기
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/Modify.lo")) {
    		action = new BoardModifyService(); //수정하기
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/Delete.lo")) {
    		action = new BoardDeleteService(); //삭제하기
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	else if(command.equals("/Calendar.lo")) {
    		//action = new BoardListService(); //출력
    		try {
    			String year=null;
    			String month=null;
    			if(request.getParameter("year")!=null)
    				year=request.getParameter("year");
    			
    			if(request.getParameter("month")!=null)
    				month=request.getParameter("month");
    			
    			forward= new ActionForward();
    			forward.setRedirect(false);
        		forward.setPath("./license/calendar.jsp?year="+year+"&month="+month);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/ReplyAdd.lo")) {
    		action = new ReplyAddService(); //출력
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/ReplyDelete.lo")) {
    		action = new ReplyDeleteService(); //출력
    		try {
    			setboardname(request.getParameter("board_type"),request);
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/AdminPage.lo")) { //관리자 페이지 이동
    		forward= new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("./license/adminpage.jsp");
    	}
    	else if(command.equals("/CalendarSubAdd.lo")) { //캘린더 삽입
    		action = new CalendarSubAddService(); //넣긔
    		try {
    			request.setAttribute("board", "IT_CALENDAR_SUBJECT");
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/CalendarConAdd.lo")) { //캘린더 삽입
    		action = new CalendarConAddService(); //넣긔
    		try {
    			request.setAttribute("board", "IT_CALENDAR_CONTENT");
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/CalendarSubDel.lo")) { //캘린더 삭제
    		action = new CalendarSubDelService(); //삭제
    		try {
    			request.setAttribute("board", "IT_CALENDAR_SUBJECT");
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else if(command.equals("/CalendarConDel.lo")) { //캘린더 삭제
    		action = new CalendarConDelService(); //삭제
    		try {
    			request.setAttribute("board", "IT_CALENDAR_CONTENT");
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) { // �����̷�Ʈ �ȴ�.
    			response.sendRedirect(forward.getPath());
    		} else { // ������ �ȴ�.
    			RequestDispatcher dispatcher = 
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    	
    	
    	
    	
    }
   
    public LicenseFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void setboardname(String name,HttpServletRequest request)
    {
    	if(request.getParameter("board_type").equals("notice")) {
			request.setAttribute("board", "IT_NOTICE_BOARD");
			request.setAttribute("reply", "IT_NOTICE_REPLY");
			}
			else if(request.getParameter("board_type").equals("free"))
			{
				request.setAttribute("board", "IT_COMUNITY_BOARD");
				request.setAttribute("reply", "IT_COMUNITY_REPLY");
				
			}
			else if(request.getParameter("board_type").equals("complete"))
			{
				request.setAttribute("board", "IT_COMPLETE_BOARD");
				request.setAttribute("reply", "IT_COMPLETE_REPLY");
			}
			else if(request.getParameter("board_type").equals("qna"))
			{
				request.setAttribute("board", "IT_QNA_BOARD");
				request.setAttribute("reply", "IT_QNA_REPLY");
			}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		    doProcess(request, response);
	}

}
