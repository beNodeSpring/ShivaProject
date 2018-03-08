package com.shiva.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러 기능을 구현하는 메소드를 통일하기 위한 역할
public interface Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
