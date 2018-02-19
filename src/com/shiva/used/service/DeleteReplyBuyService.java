package com.shiva.used.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;

public class DeleteReplyBuyService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		usedDAO useddao = new usedDAO();
		HttpSession session = request.getSession();
		
		String content = request.getParameter("content");
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		String ID ="";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		boolean usercheck = useddao.isBuyBoardReplyWriter(num, ID);
		if(!usercheck) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href = './DetailBuyService.uo?num="+num+"'");
			out.println("</script>");
			out.close();
			return null;
		}else{
			boolean boardDelete = useddao.buyBoardReplyDelete(num,content);
			if(boardDelete) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 되었습니다.');");
				out.println("location.href = './DetailBuyService.uo?num="+num+"'");
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
				out.println("location.href = './DetailBuyService.uo?num="+num+"'");
				out.println("</script>");
				out.close();
				return null;
			}
		}
	}
}
