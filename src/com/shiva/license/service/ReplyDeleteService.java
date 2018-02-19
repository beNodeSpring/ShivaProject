package com.shiva.license.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseReplyVO;

public class ReplyDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		ActionForward forward=new ActionForward();
		String replyname=(String)request.getAttribute("reply");
		
		int num= Integer.parseInt(request.getParameter("REPLY_NUM"));
		int level= Integer.parseInt(request.getParameter("REPLY_LEVEL"));
		boolean result=false;
		
		
		result =boarddao.ReplyDelete(num,level,replyname);
		
		
				if(!result) {
					System.out.println("리플삭제 실패");
					return null;
				}
				System.out.println("리플삭제 성공");
				
				forward.setRedirect(true);
				//이동
				forward.setPath("View.lo?num="+num+"&board_type="+request.getParameter("board_type"));
				return forward;
	
	}

}
