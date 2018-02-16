package com.shiva.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.main.controller.HttpUtil;

public class BoardWriteFormService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpUtil.forward(request, response, "/main/boardWrite.jsp");
	}

}
