package com.shiva.used.controller;

import javax.servlet.http.*;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.VO.used_buy_replyVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class ReplyBuyController implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("replyBuycontroller 실행");
		usedDAO useddao = new usedDAO();
		used_buy_replyVO VO_R = new used_buy_replyVO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		boolean result = false;
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("reply_content");
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		
		
		System.out.println("content : "+content+"     num : "+num);
		VO_R.setCONTENT_B(content);
		VO_R.setNAME_B(ID);
		VO_R.setNUM_B(num);
		
		
		result = useddao.setReplyBuyUpdate(VO_R);
		System.out.println(result);
		if(result == true) {
			forward.setRedirect(true);
			forward.setPath("/ShivaProject/DetailBuyService.uo?num="+num);
			return forward;
		}else {
			forward.setRedirect(true);
			forward.setPath("/ShivaProject/DetailBuyService.uo?num="+num);
			return forward;
		}
	}
}
