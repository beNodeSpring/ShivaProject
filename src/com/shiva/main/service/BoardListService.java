package com.shiva.main.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;
import com.shiva.main.dao.BoardDAO;
import com.shiva.main.vo.BoardVO;

public class BoardListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list;
		int pageCnt=0;
		String curPage = request.getParameter("curPage");
		
		if (curPage==null) curPage="1";
		
		list = dao.boardList(curPage);
		request.setAttribute("boardList", list); // 페이지에 표시될 목록 저장
		
		pageCnt = dao.boardPageCnt();
		request.setAttribute("pageCnt", pageCnt); // 페이지 갯수 저장
		
		HttpUtil.forward(request, response, "/main/boardList.jsp");
	}

}
