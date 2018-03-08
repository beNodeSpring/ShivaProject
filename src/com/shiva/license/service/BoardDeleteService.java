package com.shiva.license.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;



public class BoardDeleteService implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao=new LicenseDAO();
		ActionForward forward = new ActionForward();
		
		int num= Integer.parseInt(request.getParameter("num"));
		boolean result=false;
		boolean result2=false;
		String boardname=(String)request.getAttribute("board");
		String replyname=(String)request.getAttribute("reply");
		result =boarddao.boardDelete(num,boardname);
		result2 =boarddao.ReplyDelete(num,replyname);
		System.out.println(num+","+boardname+","+replyname);
		
				if(!result) {
					System.out.println("보드삭제 실패");
					return null;
				}
				System.out.println("보드삭제 성공");
				if(!result2) {
					System.out.println("리플이가 없어요");
					
				}
				System.out.println("리플삭제 성공");
				
				forward.setRedirect(false);
				forward.setPath("List.lo?board_type="+request.getParameter("board_type"));
				return forward;
	}

}
