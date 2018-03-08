package com.shiva.main.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.BoardDAO;

public class BoardDeleteService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		
		String pNum = request.getParameter("num");
		
		BoardDAO dao = new BoardDAO();
		boolean reply_check = dao.boardReplyCheck(pNum);
		System.out.println("reply_check : "+ reply_check);
		
		PrintWriter out;
		if(reply_check) {
			dao.boardDelete(pNum);	
			try {
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('글 삭제가 완료 되었습니다');");
				out.print("location.href='./boardList.bbs'");
				out.print("</script>");			
			} catch (IOException e) {
				System.out.println("BoardDeleteService.execute() => 글 삭제중 에러발생 : "+e);
			}			
		} else {
			try {
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('답글이 있어서 글 삭제를 할 수가 없습니다');");
				out.print("location.href='./boardList.bbs'");
				out.print("</script>");			
			} catch (IOException e) {
				System.out.println("BoardDeleteService.execute() => 글 삭제중 에러발생 : "+e);
			}			
		}
		
			
	}

}
