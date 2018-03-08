package com.shiva.main.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.BoardDAO;

public class BoardReplyService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String content = request.getParameter("ckeditorReply");
		String ref = request.getParameter("ref");
		String lev = request.getParameter("lev");
		String step = request.getParameter("step");
		
		BoardDAO dao = new BoardDAO();
		// 답변 글의 insert 및 답변글과 관련된 다른 글들의 값을 조정하기 위한 기능 수행
		dao.boardReply(id, subject, content, ref, lev, step);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('답글 작성이 완료 되었습니다');");
			out.print("location.href='./boardList.bbs'");
			out.print("</script>");			
		} catch (IOException e) {
			System.out.println("BoardReplyService.execute() => 글 작성중 에러발생 : "+e);
		}		
	}

}
