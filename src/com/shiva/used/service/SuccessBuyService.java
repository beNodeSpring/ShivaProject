package com.shiva.used.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class SuccessBuyService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		usedDAO useddao = new usedDAO();
		ServiceForward forward = new ServiceForward();
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = (String)request.getParameter("subject");
		useddao.UpdateBuySuccess(num,subject);
		
		forward.setRedirect(true);
		forward.setPath("./ListBuyController.uo");
		return forward;
	}
}
