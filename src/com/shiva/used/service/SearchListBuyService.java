package com.shiva.used.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;

public class SearchListBuyService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		usedDAO useddao = new usedDAO();
		List<used_buyVO> ListVO = new ArrayList<used_buyVO>();
		String searchkey = request.getParameter("searchList");
		System.out.println("service에 있는 searchkey"+searchkey);
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = useddao.getBuyListCount();
		
		
		
		int maxpage = (listcount+limit-1)/limit;
		
		int startpage = ((page-1)/10) * 10 + 1;
		
		int endpage = startpage+10-1;
		
		
		
		
		if(endpage<maxpage) endpage = maxpage;
		
		ListVO = useddao.buySearchList(page,limit, searchkey);
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardlist", ListVO);
		
		
		request.setAttribute("listcount", listcount);
		
		ServiceForward forward = new ServiceForward();
		forward.setRedirect(false);
		forward.setPath("/used/buy/buy_board_list.jsp");
		
		return forward;
		
		
	}

}
