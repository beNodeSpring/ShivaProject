package com.shiva.license.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseBoardVO;



public class BoardModifyViewService implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		LicenseBoardVO boarddata = new LicenseBoardVO();
		ActionForward forward=new ActionForward();
		String boardname=(String)request.getAttribute("board");
		
			//게시물 번호 가져오기
				int num=Integer.parseInt(request.getParameter("num"));
				
				//ㅇㅇ
				boarddata= boarddao.getDetail(num,boardname);
				
				if(boarddata==null) {
					System.out.println("보드가져오기 에러");
					return null;
				}
				System.out.println("보드 가져오기 성공");
				
				System.out.println(boarddata.getBOARD_SUBJECT());
				//
				request.setAttribute("boarddata", boarddata);
				
				forward.setRedirect(false);
				
				forward.setPath("./license/mainpage2.jsp?action=modify");
				
				return forward;
	}

}
