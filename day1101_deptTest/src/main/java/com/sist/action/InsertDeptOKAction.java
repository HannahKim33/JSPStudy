package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.DeptDAO;
import com.sist.vo.DeptVO;

public class InsertDeptOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DeptDAO dao=DeptDAO.getInstance();
		DeptVO d=new DeptVO();
		d.setDno(Integer.parseInt(request.getParameter("dno")));
		d.setDname(request.getParameter("dname"));
		d.setDloc(request.getParameter("dloc"));
		int re=dao.insertDept(d);
		request.setAttribute("re", re);
		return "insertDeptOK.jsp";
	}

}
