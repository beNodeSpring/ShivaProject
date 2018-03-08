package com.shiva.license.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.controller.Action;
import com.shiva.license.vo.LicenseBoardVO;
import com.shiva.license.vo.LicenseReplyVO;

public class BoardViewService implements Action {

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

			
			LicenseDAO boarddao=new LicenseDAO();
			LicenseBoardVO boarddata= new LicenseBoardVO();
			List<LicenseReplyVO> replylist = new ArrayList<LicenseReplyVO>();
			int listcount;
			int num= Integer.parseInt(request.getParameter("num"));
			String boardname=(String)request.getAttribute("board");
			String replyname=(String)request.getAttribute("reply");
			//조회수올리기
			boarddao.setReadCount(num,boardname);
			
			
			//보드내용 가져오기
			boarddata=boarddao.getDetail(num,boardname);
			
			//리플리스트 가져오기
			if(!(boardname.equals("IT_NOTICE_BOARD"))) //공지 게시판에는 리플이엄서요
			{replylist=boarddao.getReplylist(num,replyname); }
			
			
			
			
			if(boarddata==null) {
				System.out.println("보드읽기 에러");
				return null;
			}
			System.out.println("보드읽기 성공");
			
			//보드데이터전달
			request.setAttribute("boarddata", boarddata);
			
			//리플리스트 전달
			request.setAttribute("replylist", replylist);
			
			//������
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./license/mainpage2.jsp?board_type="+request.getParameter("board_type")+"&action=view");
			
			return forward;
		}

}
