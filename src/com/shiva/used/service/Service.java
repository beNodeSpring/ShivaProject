package com.shiva.used.service;

import javax.servlet.http.*;


public interface Service {
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response)  throws Exception;

}
