package com.shiva.license.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shiva.license.controller.Action;
import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.vo.LicenseBoardVO;




public class BoardAddService implements Action {

	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao = new LicenseDAO();
		LicenseBoardVO boarddata = new LicenseBoardVO();
		ActionForward forward=new ActionForward();
		String boardname=(String)request.getAttribute("board");
		
		boolean result=false;
		try {
			
			//보드세팅
			boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
			boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			
			
			//dao에서 인서트 처리
			result=boarddao.boardInsert(boarddata,boardname);
			
			//실패시 펄스
			if(result==false) {
				System.out.println("게시물 작성실패");
				return null;
			}
			System.out.println("게시물 작성성공");
			
			//�� ����� �Ϸ�Ǹ� �� ����� �ܼ��� �����ֱ⸸ �� ���̹Ƿ�
			// Redirect���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			//�̵��� ��θ� �����մϴ�.
			forward.setPath("./List.lo?board_type="+request.getParameter("board_type")+"");
			return forward;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
