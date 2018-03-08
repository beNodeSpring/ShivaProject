package com.shiva.main.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.dao.BoardDAO;

public class BoardWriteService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		// ck에디터로 교체 => String content = request.getParameter("content"); 
		String ckVal = request.getParameter("ckeditorNoti");

		BoardDAO dao = new BoardDAO();
		
		// form submit로 전달받은 id, subject, content로 boardWrite() 호출
		dao.boardWrite(id, subject, ckVal);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('글 작성이 완료 되었습니다');");
			out.print("location.href='./boardList.bbs'");
			out.print("</script>");			
		} catch (IOException e) {
			System.out.println("BoardWriteService.execute() => 글 작성중 에러발생 : "+e);
		}
		
		//HttpUtil.forward(request, response, "/main/boardList.jsp");
	}

}
