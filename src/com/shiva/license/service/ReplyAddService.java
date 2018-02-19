package com.shiva.license.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseBoardVO;
import com.shiva.license.vo.LicenseReplyVO;

public class ReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		LicenseReplyVO replydata = new LicenseReplyVO();
		ActionForward forward=new ActionForward();
		String replyname=(String)request.getAttribute("reply");
		
		boolean result=false;
		try {
			
			//보드세팅
			replydata.setREPLY_NAME(request.getParameter("REPLY_NAME"));
			replydata.setREPLY_NUM(Integer.parseInt(request.getParameter("REPLY_NUM")));
			replydata.setREPLY_CONTENT(request.getParameter("REPLY_CONTENT"));
			
			System.out.println("리플테이블= "+replyname);
			
			//dao에서 인서트 처리
			result=boarddao.ReplyInsert(replydata,replyname);
			
			//실패시 펄스
			if(result==false) {
				System.out.println("리플 작성실패");
				return null;
			}
			System.out.println("리플 작성성공");
			
			
			// Redirect���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			//�̵��� ��θ� �����մϴ�.
			forward.setPath("View.lo?num="+request.getParameter("REPLY_NUM")+"&board_type="+request.getParameter("board_type"));
			return forward;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
