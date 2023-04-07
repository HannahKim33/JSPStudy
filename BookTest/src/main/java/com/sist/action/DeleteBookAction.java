package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.BookDAO;

public class DeleteBookAction implements SistAction{

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		BookDAO dao=BookDAO.getInstance();
		int re=dao.deleteBook(bookid);
		String msg="삭제 성공";
		if(re<=0) {
			msg="삭제 실패";
		}
		request.setAttribute("msg", msg);
		return "deleteBook.jsp";
	}

}
