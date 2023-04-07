package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.EmpDAO;

public class DetailEmpAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno=Integer.parseInt(request.getParameter("eno"));
		EmpDAO dao=EmpDAO.getInstance();
		request.setAttribute("e", dao.findByEno(eno));
		return "detailEmp.jsp";
	}

}
