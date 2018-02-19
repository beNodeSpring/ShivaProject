package com.shiva.used.service;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shiva.used.VO.used_buyVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class AddBuyService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		usedDAO useddao = new usedDAO();
		used_buyVO VO = new used_buyVO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		
		String realFolder = "";
		
		
		String ID = "";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		String saveFolder = "buy_picture";
	
		int fileSize = 5*1024*1024;
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		System.out.println("realFolder = "+realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			
			VO.setNAME_B(ID);
			VO.setSUBJECT_B(multi.getParameter("SUBJECT_B"));
			VO.setCONTENT_B(multi.getParameter("CONTENT_B"));
			
			VO.setFILE_B(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			result = useddao.buyBoardInsert(VO);
			
			if(result == false) {
				return null;
			}
			forward.setRedirect(true);
			forward.setPath("./ListBuyController.uo");
			return forward;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
