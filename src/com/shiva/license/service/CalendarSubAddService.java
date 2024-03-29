package com.shiva.license.service;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseBoardVO;
import com.shiva.license.vo.LicenseCalendar_SubVO;




public class CalendarSubAddService implements Action {

	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		LicenseCalendar_SubVO boarddata = new LicenseCalendar_SubVO();
		ActionForward forward=new ActionForward();
		String boardname=(String)request.getAttribute("board");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		boolean result=false;
		try {
			
			//보드세팅
			boarddata.setITC_STRINGDATE(request.getParameter("ITC_DATE"));
			boarddata.setITC_NAME(request.getParameter("ITC_NAME"));
			boarddata.setITC_TYPE(Integer.parseInt(request.getParameter("ITC_TYPE")));
			
			
			//dao에서 인서트 처리
			result=boarddao.HolidayInsert(boarddata, boardname);
			
			//실패시 펄스
			if(result==false) {
				System.out.println("기념일 입력실패");
				forward.setPath("AdminPage.lo?action=addfail&year="+year+"&month="+month);
				return forward;
			}
			System.out.println("기념일 입력성공");
			
		
			forward.setRedirect(true);
			//이동
			forward.setPath("./AdminPage.lo?action=addsuecess&year="+year+"&month="+month);
			return forward;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
