package com.shiva.used.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiva.used.service.*;



public class UoFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
    	
    	
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = "+RequestURI);
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = "+contextPath);
    	
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = "+command);
    	
    	ServiceForward forward = null;
    	Service service = null;
    	
    
    	if(command.equals("/SaleBoardWrite.uo")){
    		forward = new ServiceForward();
    		forward.setRedirect(false);
    		forward.setPath("/used/sale/sale_board_write.jsp");
    	}else if(command.equals("/addservice_sale.uo")) {
    		service = new AddSaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/ListSaleController.uo")) {
    		service = new ListSaleController();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/DetailSaleService.uo")) {
    		service = new DetailSaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/ModifySaleController.uo")) {
    		service = new ModifySaleController();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/ModifySaleService.uo")) {
    		service = new ModifySaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/DeleteSaleController.uo")) {
    		forward = new ServiceForward();
    		forward.setRedirect(false);
    		forward.setPath("./used/sale/sale_board_delete.jsp");
    	}else if(command.equals("/DeleteSaleService.uo")) {
    		service = new DeleteSaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    	}
    	}else if(command.equals("/ReplySaleController.uo")) {
    		service = new ReplySaleController();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/ResetSaleService.uo")) {
    		service = new ResetSaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/SuccessSaleController.uo")) {
    		service = new SuccessSaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/DeleteReplySaleService.uo")) {
    		service = new DeleteReplySaleService();
    		try {
    			forward = service.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request,response);
    		}
    	}
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
