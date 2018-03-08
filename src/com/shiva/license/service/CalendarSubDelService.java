package com.shiva.license.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseCalendar_SubVO;

public class CalendarSubDelService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		ActionForward forward=new ActionForward();
		String boardname=(String)request.getAttribute("board");
		String itc_name=request.getParameter("ITC_NAME");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		boolean result=false;
		try {
			
			
			
			//dao에서 인서트 처리
			result=boarddao.HolidayDelete(boardname,itc_name);
			
			//실패시 펄스
			if(result==false) {
				System.out.println("기념일 삭제실패");
				forward.setRedirect(false);
				//이동
				forward.setPath("AdminPage.lo?action=delfail&year="+year+"&month="+month);
				return forward;
				
			}
			System.out.println("기념일 삭제성공");
			
			
		
			forward.setRedirect(false);
			//이동
			forward.setPath("AdminPage.lo?action=delsuecess&year="+year+"&month="+month);
			
			return forward;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
