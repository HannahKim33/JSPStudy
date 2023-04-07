package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.BookDAO;
import com.sist.vo.BookVO;

public class InsertBookOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookVO b=new BookVO();
		b.setBookid(Integer.parseInt(request.getParameter("bookid")));
		b.setBookname(request.getParameter("bookname"));
		b.setPublisher(request.getParameter("publisher"));
		b.setPrice(Integer.parseInt(request.getParameter("price")));
		BookDAO dao=BookDAO.getInstance();
		int re=dao.insertBook(b);
		String msg="등록 성공";
		if(re<=0) {
			msg="등록 실패";
		}
		request.setAttribute("msg", msg);
		return "insertBookOK.jsp";
	}

}
