package com.shiva.resume.service;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import com.shiva.resume.controller.*;
import com.shiva.resume.dao.*;
import com.shiva.resume.vo.*;

public class ResumeUpdateService implements Service {
	public ServiceForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ResumeDAO resumedao = new ResumeDAO();
		ResumeBean resumebean = new ResumeBean();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");
		System.out.println("업데이트에서" + id);
		int result = 0;
		
		//파일 업로드 준비
		String realFolder = "";
		String saveFolder = "profilepic";
		int fileSize = 5*1024*1024;
		
		//저장경로설정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		//시작
		System.out.println("ResumeUpdateService 시작합니다.");
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			//파라미터로 넘어온 값들을 resumebean 객체에 저장합니다.
			resumebean.setRESUME_ID(id);
			resumebean.setTODAYFEELING(multi.getParameter("TODAYFEELING"));
			resumebean.setPROJECT1(multi.getParameter("PROJECT1"));
			resumebean.setPROJECT2(multi.getParameter("PROJECT2"));
			resumebean.setPROJECT3(multi.getParameter("PROJECT3"));
			resumebean.setPROJECT4(multi.getParameter("PROJECT4"));
			resumebean.setPROJECT5(multi.getParameter("PROJECT5"));
			resumebean.setPROJECT6(multi.getParameter("PROJECT6"));
			resumebean.setPROJECT7(multi.getParameter("PROJECT7"));
			resumebean.setPROJECT8(multi.getParameter("PROJECT8"));
			resumebean.setPROJECT9(multi.getParameter("PROJECT9"));
			resumebean.setDURATION1(multi.getParameter("DURATION1"));
			resumebean.setDURATION2(multi.getParameter("DURATION2"));
			resumebean.setDURATION3(multi.getParameter("DURATION3"));
			resumebean.setDURATION4(multi.getParameter("DURATION4"));
			resumebean.setDURATION5(multi.getParameter("DURATION5"));
			resumebean.setDURATION6(multi.getParameter("DURATION6"));
			resumebean.setDURATION7(multi.getParameter("DURATION7"));
			resumebean.setDURATION8(multi.getParameter("DURATION8"));
			resumebean.setDURATION9(multi.getParameter("DURATION9"));		
			resumebean.setTEXT1(multi.getParameter("TEXT1"));
			resumebean.setTEXT2(multi.getParameter("TEXT2"));
			resumebean.setTEXT3(multi.getParameter("TEXT3"));
			resumebean.setTEXT4(multi.getParameter("TEXT4"));
			resumebean.setTEXT5(multi.getParameter("TEXT5"));
			resumebean.setTEXT6(multi.getParameter("TEXT6"));
			resumebean.setTEXT7(multi.getParameter("TEXT7"));
			resumebean.setTEXT8(multi.getParameter("TEXT8"));
			resumebean.setTEXT9(multi.getParameter("TEXT9"));
			resumebean.setJAVAVAL(multi.getParameter("JAVAVAL"));
			resumebean.setPYTHONVAL(multi.getParameter("PYTHONVAL"));
			resumebean.setCVAL(multi.getParameter("CVAL"));
			resumebean.setRUBYVAL(multi.getParameter("RUBYVAL"));
			resumebean.setJAVASCRIPTVAL(multi.getParameter("JAVASCRIPTVAL"));
			resumebean.setCSHAPVAL(multi.getParameter("CSHAPVAL"));
			resumebean.setPHPVAL(multi.getParameter("PHPVAL"));
			resumebean.setOBJECTIVECVAL(multi.getParameter("OBJECTIVECVAL"));
			resumebean.setSQLVAL(multi.getParameter("SQLVAL"));
			resumebean.setCPLUSVAL(multi.getParameter("CPLUSVAL"));
			
			//사진 저장
			resumebean.setPIC(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			
			//DB에 저장
			if (resumebean.getPIC() != null) {
				resumedao.resumeDelete(resumebean);
			}
			result = resumedao.resumeUpdate(resumebean);
			
			//업데이트 실패
			if(result==0) {
				System.out.println("업데이트 실패");
				return null;
			}
			
			//업데이트 성공
			System.out.println("업데이트 성공");
			forward.setPath("myResume.ro");
			forward.setRedirect(true);
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
