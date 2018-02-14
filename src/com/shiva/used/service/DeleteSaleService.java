package com.shiva.used.service;

import java.io.PrintWriter;

import javax.servlet.http.*;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.dao.usedDAO;

public class DeleteSaleService implements Service {

	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		usedDAO useddao = new usedDAO();
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		
		boolean usercheck = useddao.isBoardWriter(num, ID);
		if(usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href = './ListSaleController.uo'");
			out.println("</script>");
			out.close();
			return null;
		}else{
			boolean boardDelete = useddao.boardDelete(num);
			if(boardDelete) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 되었습니다.');");
				out.println("location.href = './ListSaleController.uo'");
				out.println("</script>");
				out.close();
				System.out.println("여기 타냐??");
				return null;
			}
			else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제하지 못하였습니다..');");
				out.println("location.href = './ListSaleController.uo'");
				out.println("</script>");
				out.close();
				return null;
			}
		}
	}
}
