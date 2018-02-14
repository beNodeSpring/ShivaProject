package com.shiva.used.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.VO.used_sale_replyVO;
import com.shiva.used.dao.usedDAO;

public class DetailSaleService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
request.setCharacterEncoding("UTF-8");

System.out.println("detailsaleservice 실행");
		
		usedDAO useddao= new usedDAO();
		used_saleVO VO = new used_saleVO();
		List<used_sale_replyVO> VO_R = new ArrayList<used_sale_replyVO>();
		int num = Integer.parseInt(request.getParameter("num"));
		
		useddao.setReadCountUpdate(num);
		
		VO = useddao.getDetail(num);
		VO_R = useddao.getSaleReplylist(num);
		if(VO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("VO", VO);
		request.setAttribute("VO_R", VO_R);
		request.setAttribute("num", num);
		ServiceForward forward = new ServiceForward();
		
		forward.setRedirect(false);
		forward.setPath("/ResetSaleService.uo");
		return forward;
	}
}
