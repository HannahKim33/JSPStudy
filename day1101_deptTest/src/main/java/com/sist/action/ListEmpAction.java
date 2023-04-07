package com.sist.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.EmpDAO;
import com.sist.vo.EmpVO;

public class ListEmpAction implements SistAction{

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao=EmpDAO.getInstance();
		ArrayList<EmpVO> list= dao.findAll();
		request.setAttribute("list", list);
		
		return "listEmp.jsp";
	}

}
