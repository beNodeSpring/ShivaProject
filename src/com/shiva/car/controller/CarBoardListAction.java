package com.shiva.car.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.car.dao.BoardDAO;
import com.shiva.car.vo.BoardVO;

public class CarBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDAO boarddao=new BoardDAO();
		List<BoardVO> boardlist=new ArrayList<BoardVO>();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));			
		}
		
		int listcount=boarddao.getListCount(); //총 리스트 수를 받아옵니다.
		
		boardlist = boarddao.getBoardList(page,limit); //리스트를 받아옵니다.
		
		int maxpage = (listcount+limit-1)/limit;
			
		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//endpage: 현재 페이지 그룹에서 보여줄 마지막 페이지 수([10], [20], [30] 등...)
		int endpage = startpage + 10 -1;
		
		/* 
		 * 마지막 그룹의 마지막 페이지 값은  최대 페이지 값입니다.
		     예로  마지막 페이지 그룹이 [21]~[30]인 경우 
		     시작페이지(startpage>=21)와 마지막페이지(endpage>=30)
		     이지만 최대 페이지(maxpage)가 25라면 [21]~[25]까지만 표시되도록 합니다.
		 */
 		if (endpage> maxpage) endpage= maxpage;
 		
 		request.setAttribute("page", page); //현재 페이지 수
 		request.setAttribute("maxpage", maxpage); //최대 페이지 수
 		
 		//현재 페이지에 표시할 첫 페이지 수
 		request.setAttribute("startpage", startpage);
 		
 		//현재 페이지에 표시할 끝 페이지 수
 		request.setAttribute("endpage", endpage); 
 		
		request.setAttribute("listcount",listcount); //총 글의 수
		
		//해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardlist", boardlist);		
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);	 
	 	
	 	//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
 		forward.setPath("./car/boardList.jsp");
 		return forward; //BoardFrontController.java로 리턴됩니다.
	}

}
