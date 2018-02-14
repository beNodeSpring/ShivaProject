package com.shiva.used.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.dao.usedDAO;

public class ModifySaleService implements Service{

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		used_saleVO VO = new used_saleVO();
		usedDAO useddao = new usedDAO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		boolean usercheck = useddao.isBoardWriter(num, ID);
		if(usercheck == false) {
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href = './ListSaleController.uo'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		VO.setNUM_S(num);
		VO.setSUBJECT_S(request.getParameter("SUBJECT_S"));
		VO.setCONTENT_S(request.getParameter("CONTENT_S"));
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");
		
		result = useddao.boardModify(VO);
		if(result == false) {
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("./ListSaleController.uo");
		
		return forward;
	}
}
