package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.BookDAO;
import com.sist.vo.BookVO;

public class DetailBookAction implements SistAction{
	
	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		System.out.println("상세보기 : "+ bookid);
		BookDAO dao=BookDAO.getInstance();
		BookVO b=dao.findByBookId(bookid);
		request.setAttribute("b", b);
		return "detailBook.jsp";
	}
	
}
