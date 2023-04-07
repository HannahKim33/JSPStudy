package com.sist.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class ListBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		
		String searchColumn="";
		String keyword="";
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("keyword")!=null) {
			searchColumn=(String)session.getAttribute("searchColumn");
			keyword=(String)session.getAttribute("keyword");
		}
		
		
		if(request.getParameter("keyword")!=null) {
			searchColumn=request.getParameter("searchColumn");
			keyword=request.getParameter("keyword");
		}
		
		
		
		
		
		BoardDAO dao= new BoardDAO();
		int pageTo=1;
		if(request.getParameter("pageTo")!=null) {
			pageTo=Integer.parseInt(request.getParameter("pageTo"));
		}
		
		
		HashMap<String, String> map=new HashMap<>();
		map.put("pageTo", pageTo+"");
		map.put("searchColumn", searchColumn);
		map.put("keyword", keyword);
		
		ArrayList<BoardVO> list=dao.listAll(map);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", BoardDAO.totalPage);
		
		session.setAttribute("searchColumn", searchColumn);
		session.setAttribute("keyword", keyword);
		
		return "listBoard.jsp";
	}

}
