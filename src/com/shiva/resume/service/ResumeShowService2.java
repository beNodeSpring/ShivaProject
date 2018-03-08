package com.shiva.resume.service;

import java.util.*;
import javax.servlet.http.*;
import com.shiva.resume.controller.*;
import com.shiva.resume.dao.*;
import com.shiva.resume.vo.*;

public class ResumeShowService2 implements Service{
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ResumeDAO resumedao = new ResumeDAO();
		ResumeBean resume = new ResumeBean();
		ServiceForward forward = new ServiceForward();
		String visitorid = (String) request.getSession(false).getAttribute("id");
				
		//시작
		System.out.println("ResumeShowService2 시작합니다.");
		
		//db에서 해당 레주메를 가져옵니다.
		System.out.println("가져올 레주메의 id는 : " + request.getParameter("want"));
		resume = resumedao.getResume(request.getParameter("want"));
		System.out.println("resume를 받아왔습니다 : " + resume);
		String visitorpic = resumedao.getPic(visitorid);
		resumedao.visitResume(visitorid, visitorpic, request.getParameter("want"));
		System.out.println("resume방문기록완료");
		
		//받아온 resume의 정보를 request에 저장
		request.setAttribute("RESUME_ID", resume.getRESUME_ID());
		request.setAttribute("RESUME_DATE", resume.getRESUME_DATE());
		request.setAttribute("TODAYFEELING", resume.getTODAYFEELING());
		request.setAttribute("PROJECT1", resume.getPROJECT1());
		request.setAttribute("PROJECT2", resume.getPROJECT2());
		request.setAttribute("PROJECT3", resume.getPROJECT3());
		request.setAttribute("PROJECT4", resume.getPROJECT4());
		request.setAttribute("PROJECT5", resume.getPROJECT5());
		request.setAttribute("PROJECT6", resume.getPROJECT6());
		request.setAttribute("PROJECT7", resume.getPROJECT7());
		request.setAttribute("PROJECT8", resume.getPROJECT8());
		request.setAttribute("PROJECT9", resume.getPROJECT9());
		request.setAttribute("DURATION1", resume.getDURATION1());
		request.setAttribute("DURATION2", resume.getDURATION2());
		request.setAttribute("DURATION3", resume.getDURATION3());
		request.setAttribute("DURATION4", resume.getDURATION4());
		request.setAttribute("DURATION5", resume.getDURATION5());
		request.setAttribute("DURATION6", resume.getDURATION6());
		request.setAttribute("DURATION7", resume.getDURATION7());
		request.setAttribute("DURATION8", resume.getDURATION8());
		request.setAttribute("DURATION9", resume.getDURATION9());
		request.setAttribute("TEXT1", resume.getTEXT1());
		request.setAttribute("TEXT2", resume.getTEXT2());
		request.setAttribute("TEXT3", resume.getTEXT3());
		request.setAttribute("TEXT4", resume.getTEXT4());
		request.setAttribute("TEXT5", resume.getTEXT5());
		request.setAttribute("TEXT6", resume.getTEXT6());
		request.setAttribute("TEXT7", resume.getTEXT7());
		request.setAttribute("TEXT8", resume.getTEXT8());
		request.setAttribute("TEXT9", resume.getTEXT9());
		request.setAttribute("PYTHONVAL", resume.getPYTHONVAL());
		request.setAttribute("CVAL", resume.getCVAL());
		request.setAttribute("RUBYVAL", resume.getRUBYVAL());
		request.setAttribute("JAVASCRIPTVAL", resume.getJAVASCRIPTVAL());
		request.setAttribute("CSHAPVAL", resume.getCSHAPVAL());
		request.setAttribute("PHPVAL", resume.getPHPVAL());
		request.setAttribute("OBJECTIVECVAL", resume.getOBJECTIVECVAL());
		request.setAttribute("SQLVAL", resume.getSQLVAL());
		request.setAttribute("CPLUSVAL", resume.getCPLUSVAL());
		request.setAttribute("PIC", resume.getPIC());
		request.setAttribute("VISITORPIC", resume.getVISITORPIC());
		request.setAttribute("VISITOR_ID", resume.getVISITOR_ID());
		forward.setRedirect(false);
		forward.setPath("./resume/resume2.jsp");
		return forward;
	}
}
