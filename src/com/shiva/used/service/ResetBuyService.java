package com.shiva.used.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.VO.used_buy_replyVO;
import com.shiva.used.dao.usedDAO;

public class ResetBuyService implements Service{

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

System.out.println("resetbuyservice 실행");
		usedDAO useddao= new usedDAO();
		used_buyVO VO = new used_buyVO();
		List<used_buy_replyVO> VO_R = new ArrayList<used_buy_replyVO>();
		int num = Integer.parseInt(request.getParameter("num"));
		
		useddao.setReadCountUpdate(num);
		
		VO = useddao.getBuyDetail(num);
		VO_R = useddao.getBuyReplylist(num);
		if(VO == null) {
			System.out.println("상세보기 실패 reset");
			return null;
		}
		System.out.println("상세보기 성공 reset");
		
		request.setAttribute("VO", VO);
		ServiceForward forward = new ServiceForward();
		forward.setRedirect(false);
		
		forward.setPath("/used/buy/buy_board_detail.jsp");
		return forward;
	}
	}


