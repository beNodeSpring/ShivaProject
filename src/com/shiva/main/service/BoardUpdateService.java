package com.shiva.main.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;

public class BoardUpdateService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pSubject = request.getParameter("subject");
		String pCkVal = request.getParameter("ckeditorNoti");
		String pNum = request.getParameter("num");
		//System.out.println(pSubject + ", " + pCkVal + ", " + pNum);
		BoardDAO dao = new BoardDAO();
		dao.boardUpdate(pSubject, pCkVal, pNum);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('글 수정이 완료 되었습니다');");
			out.print("location.href='./boardList.bbs'");
			out.print("</script>");			
		} catch (IOException e) {
			System.out.println("BoardUpdateService.execute() => 글 수정중 에러발생 : "+e);
		}		
		
		//HttpUtil.forward(request, response, "/main/boardList.jsp");
	}

}
