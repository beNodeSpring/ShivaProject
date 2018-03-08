package com.shiva.resume.controller;

import javax.servlet.http.*;

//특정 비즈니스 요청으로 수행하고 결과 값을 ActionForward 타입으로 변환하는 메서드 정의
//Action : 인터페이스 명
//ActionForward : 반환형
public interface Service {
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
