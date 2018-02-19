package com.shiva.license.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseCalendar_ConVO;


public class CalendarConAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		LicenseCalendar_ConVO boarddata = new LicenseCalendar_ConVO();
		ActionForward forward=new ActionForward();
		String boardname=(String)request.getAttribute("board");
		
		boolean result=false;
		try {
			
			//보드세팅
			boarddata.setITC_STRINGDATE(request.getParameter("ITC_DATE"));
			boarddata.setITC_NAME(request.getParameter("ITC_NAME"));
			boarddata.setITC_LINK(request.getParameter("ITC_LINK"));
			
			
			//dao에서 인서트 처리
			result=boarddao.WorkdayInsert(boarddata, boardname);
			
			//실패시 펄스
			if(result==false) {
				System.out.println("일정 입력실패");
				return null;
			}
			System.out.println("일정 입력성공");
			
		
			forward.setRedirect(true);
			//이동
			forward.setPath("./AdminPage.lo?action=calendar");
			return forward;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
