package com.sist.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.BookDAO;

public class ListBookAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) {
		BookDAO dao=BookDAO.getInstance();
		request.setAttribute("list", dao.findAll());
		return "listBook.jsp";
	}

}
