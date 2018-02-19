package com.shiva.used.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class ModifyBuyController implements Service {

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		usedDAO useddao = new usedDAO();
		used_buyVO VO = new used_buyVO();
		ServiceForward forward = new ServiceForward();
		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		VO = useddao.getBuyDetail(num);
		if(VO == null) {
			return null;
		}
		request.setAttribute("VO", VO);
		forward.setRedirect(false);
				
		forward.setPath("/used/buy/buy_board_modify.jsp?num="+num);
		return forward;
	}
		
}


