package com.sist.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.action.DeleteBoardAction;
import com.sist.action.DeleteBoardOKAction;
import com.sist.action.DetailBoardAction;
import com.sist.action.InsertBoardAction;
import com.sist.action.InsertBoardOKAction;
import com.sist.action.ListBoardAction;
import com.sist.action.SistAction;
import com.sist.action.UpdateBoardAction;
import com.sist.action.UpdateBoardOKAction;

/**
 * Servlet implementation class SistController
 */
//@WebServlet("*.do")
public class SistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HashMap<String, SistAction> map;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public SistController() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		map=new HashMap<>();
		
		
		String path=config.getServletContext().getRealPath("WEB-INF");
		try {
			FileReader fr=new FileReader(path+"/sist.properties");
			Properties prop=new Properties();
			prop.load(fr);
			Iterator iter= prop.keySet().iterator();
			while(iter.hasNext()) {
				String cmd=(String)iter.next();
				String clsName=(String)prop.get(cmd);
//				System.out.println("서비스명: "+cmd);
//				System.out.println("클래스명: "+clsName);
//				System.out.println("-----------------------");
				
				map.put(cmd, (SistAction)Class.forName(clsName).newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("day1031 SistController에서 예외 "+e.getMessage());
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		System.out.println("uri: "+uri);
		String cmd=uri.substring(uri.lastIndexOf("/")+1);
		System.out.println("cmd: "+cmd);
		
		SistAction action=null;
		String view="";
		/*
		if(cmd.equals("listBoard.do")) {
			action=new ListBoardAction();
		}else if(cmd.equals("insertBoard.do")) {
			action=new InsertBoardAction();
		}else if(cmd.equals("insertBoardOK.do")) {
			action=new InsertBoardOKAction();
		}else if(cmd.equals("detailBoard.do")) {
			action=new DetailBoardAction();
		}else if(cmd.equals("updateBoard.do")) {
			action=new UpdateBoardAction();
		}else if(cmd.equals("updateBoardOK.do")) {
			action=new UpdateBoardOKAction();
		}else if(cmd.equals("deleteBoard.do")) {
			action=new DeleteBoardAction();
		}else if(cmd.equals("deleteBoardOK.do")) {
			action=new DeleteBoardOKAction();
		}*/
		
		action=map.get(cmd);
		
		view=action.pro(request, response);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
