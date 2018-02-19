package com.shiva.resume.service;

import java.util.*;
import javax.servlet.http.*;
import com.shiva.resume.controller.*;
import com.shiva.resume.dao.*;
import com.shiva.resume.vo.*;

public class ResumeListService implements Service{
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResumeDAO resumedao = new ResumeDAO();
		List<ResumeBean> resumelist = new ArrayList<ResumeBean>();
		
		int page = 1;
		int limit = 10;
		
		int resumecount = resumedao.getResumeCount();
		resumelist = resumedao.getResumeList(page, limit);
		System.out.println("resumelist를 받아왔습니다 : " + resumelist);
		
		int maxpage = (resumecount + limit - 1)/limit;
		int startpage = ((page-1)/10)*10+1;
		int endpage = startpage + 10 - 1;
		
		if(endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("resumecount", resumecount);
		request.setAttribute("resumelist", resumelist);
		ServiceForward forward = new ServiceForward();
		forward.setRedirect(false);
		forward.setPath("/resume/resumelist.jsp");
		return forward;
	}
}
