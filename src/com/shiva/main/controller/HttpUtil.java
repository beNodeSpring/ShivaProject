package com.shiva.main.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 다른 페이지도 이동 시키는 2가지 방식
public class HttpUtil {
	// forward : request와  response를 가지고 이동(select등 단순업무)
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			// path에 지정된 페이지의 경로를 가지는 RequestDispatcher 객체 생성
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("HttpUtil.forward() 오류 "+e);
		} 
	}
	
	// redirect : DB,session 등 변화가 필요한 이동
	public static void redirect(HttpServletResponse response, String path) {
		try {
			// path에 지정된 페이지의 경로로 이동
			response.sendRedirect(path);
		} catch (Exception e) {
			System.out.println("HttpUtil.redirect() 오류 "+e);
		} 		
	}
	
}
