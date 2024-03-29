package com.shiva.license.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.license.controller.ActionForward;
import com.shiva.license.dao.LicenseDAO;
import com.shiva.license.controller.Action;
import com.shiva.license.vo.LicenseBoardVO;

public class BoardSerachService implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LicenseDAO boarddao=new LicenseDAO();
		List<LicenseBoardVO> boardlist = new ArrayList<LicenseBoardVO>();
		int page=1;
		int limit=10;
		String option=request.getParameter("option");
		String serachname=request.getParameter("searchname");
		String boardname=(String)request.getAttribute("board");
		
		if(option.equals("subject"))
		{
			option="BOARD_SUBJECT";
		}
		else if(option.equals("name"))
		{
			option="BOARD_NAME";
		}
		System.out.println("옵션 = "+option);
		if(request.getParameter("page")!=null)
		{
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("페이지수 = "+page);
		
		int listcount =boarddao.getSerachListCount(serachname,option,boardname) ;
		System.out.println("listcount = "+listcount);
		boardlist = boarddao.getNoticeSerachBoardList(page, limit,listcount, serachname,option,boardname);
		System.out.println("가져온리스트숫자= "+boardlist.size());
		int maxpage=((listcount+limit-1)/limit);
		System.out.println("최대페이지= "+maxpage);
	
		int startpage =((page-1)/10)*10+1;
		System.out.println("시작페이지 = "+startpage);
		
		int endpage=startpage+10-1;
		
		if(endpage>maxpage)
			endpage=maxpage;
		System.out.println("끝페이지 = "+endpage);
		
		request.setAttribute("page",page ); 
		request.setAttribute("maxpage", maxpage ); 
		request.setAttribute("startpage", startpage );
		request.setAttribute("endpage", endpage ); 
		request.setAttribute("boardlist", boardlist );
		request.setAttribute("listcount", listcount );
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("license/mainpage2.jsp?board_type="+request.getParameter("board_type")+"&action=search");
		
		return forward;
	}

}
