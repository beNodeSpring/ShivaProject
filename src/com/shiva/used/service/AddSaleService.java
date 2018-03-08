package com.shiva.used.service;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shiva.used.VO.used_saleVO;
import com.shiva.used.dao.usedDAO;
import com.shiva.used.service.Service;
import com.shiva.used.service.ServiceForward;

public class AddSaleService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		usedDAO useddao = new usedDAO();
		used_saleVO VO = new used_saleVO();
		ServiceForward forward = new ServiceForward();
		HttpSession session = request.getSession();
		
		String realFolder = "";
		
		
		String ID = "";
		ID = (String)session.getAttribute("id");//세션의 id값을 불러오는부분
		
		String saveFolder = "sale_picture";
	
		int fileSize = 5*1024*1024;
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		System.out.println("realFolder = "+realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			
			VO.setNAME_S(ID);
			VO.setSUBJECT_S(multi.getParameter("SUBJECT_S"));
			VO.setCONTENT_S(multi.getParameter("CONTENT_S"));
			
			VO.setFILE_S(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			System.out.println("multi.getFilesystemName((String)multi.getFileNames().nextElement()) : " + multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			result = useddao.boardInsert(VO);
			
			if(result == false) {
				return null;
			}
			forward.setRedirect(true);
			forward.setPath("./ListSaleController.uo");
			return forward;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
