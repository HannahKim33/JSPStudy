package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class DetailBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		//System.out.println("no: "+no);
		BoardDAO dao=new BoardDAO();
		dao.updateHit(no);
		BoardVO b= dao.findByNo(no);
		request.setAttribute("b", b);
		
		return "detailBoard.jsp";
	}

}
