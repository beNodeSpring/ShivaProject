package com.shiva.main.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	// 다른 페이지도 이동(포워드)
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			// path에 지정된 페이지의 경로를 가지는 RequestDispatcher 객체 생성
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("forward 오류 "+e);
		} 
	}
	
}
