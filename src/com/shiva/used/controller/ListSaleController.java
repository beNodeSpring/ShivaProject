package com.shiva.used.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class ListSaleController implements Service {

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		usedDAO boarddao = new usedDAO();
		List<used_saleVO> boardlist = new ArrayList<used_saleVO>();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = boarddao.getListCount();
		
		
		
		int maxpage = (listcount+limit-1)/limit;
		
		int startpage = ((page-1)/10) * 10 + 1;
		
		int endpage = startpage+10-1;
		
		
		
		
		if(endpage<maxpage) endpage = maxpage;
		
		boardlist = boarddao.getBoardList(page,limit);
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardlist", boardlist);
		
		
		request.setAttribute("listcount", listcount);
		
		ServiceForward forward = new ServiceForward();
		forward.setRedirect(false);
		forward.setPath("/used/sale/sale_board_list.jsp");
		
		return forward;
		
	}
}
