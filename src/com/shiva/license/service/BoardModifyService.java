package com.shiva.license.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseBoardVO;



public class BoardModifyService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao=new LicenseDAO();
		LicenseBoardVO boarddata= new LicenseBoardVO();
		ActionForward forward = new ActionForward();
		String boardname=(String)request.getAttribute("board");
		boolean result=false;
		int num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		
	
		
		
			boarddata.setBOARD_NUM(num);
			boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		
			result =boarddao.boardModify(boarddata,boardname);
		
		//dao���� ���� ������ ���� ������ ��� null�� ��ȯ
		if(!result) {
			System.out.println("보드 불러오기 실패");
			return null;
		}
		System.out.println("보드 불러오기 성공");
		
		
		forward.setRedirect(false);
		forward.setPath("View.lo?num="+boarddata.getBOARD_NUM());
		return forward;
	}

}
