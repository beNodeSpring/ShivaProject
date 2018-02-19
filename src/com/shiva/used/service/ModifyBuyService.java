package com.shiva.used.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;

public class ModifyBuyService implements Service{

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		used_buyVO VO = new used_buyVO();
		usedDAO useddao = new usedDAO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		boolean usercheck = useddao.isBuyBoardWriter(num, ID);
		if(usercheck == false) {
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href = './ListBuyController.uo'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		VO.setNUM_B(num);
		VO.setSUBJECT_B(request.getParameter("SUBJECT_B"));
		VO.setCONTENT_B(request.getParameter("CONTENT_B"));
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");
		
		result = useddao.buyBoardModify(VO);
		if(result == false) {
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("./ListBuyController.uo");
		
		return forward;
	}
}
