package com.shiva.used.controller;

import javax.servlet.http.*;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.VO.used_sale_replyVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class ReplySaleController implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("replysalecontroller 실행");
		usedDAO useddao = new usedDAO();
		used_sale_replyVO VO_R = new used_sale_replyVO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		boolean result = false;
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("reply_content");
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		
		
		System.out.println("content : "+content+"     num : "+num);
		VO_R.setCONTENT_S(content);
		VO_R.setNAME_S(ID);
		VO_R.setNUM_S(num);
		
		
		result = useddao.setReplySaleUpdate(VO_R);
		System.out.println(result);
		if(result == true) {
			forward.setRedirect(true);
			forward.setPath("/ShivaProject/DetailSaleService.uo?num="+num);
			return forward;
		}else {
			forward.setRedirect(true);
			forward.setPath("/ShivaProject/DetailSaleService.uo?num="+num);
			return forward;
		}
	}
}
