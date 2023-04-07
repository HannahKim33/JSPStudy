package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.DeptDAO;

public class DeleteDeptAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dno=Integer.parseInt(request.getParameter("dno"));
		DeptDAO dao=DeptDAO.getInstance();
		int re=dao.deleteDept(dno);
		request.setAttribute("re", re);
		return "deleteDept.jsp";
	}

}
