package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.DeptDAO;
import com.sist.vo.DeptVO;

public class UpdateDeptOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptVO d=new DeptVO();
		d.setDno(Integer.parseInt(request.getParameter("dno")));
		d.setDname(request.getParameter("dname"));
		d.setDloc(request.getParameter("dloc"));
		
		DeptDAO dao=DeptDAO.getInstance();
		int re=dao.updateDept(d);
		
		request.setAttribute("re", re);
		return "updateDeptOK.jsp";
	}

}
