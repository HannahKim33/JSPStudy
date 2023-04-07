package com.sist.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.DeptDAO;
import com.sist.vo.DeptVO;

public class ListDeptAction implements SistAction{

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DeptDAO dao=DeptDAO.getInstance();
		ArrayList<DeptVO> list= dao.findAll();
		//System.out.println("list in Action: "+list);
		
		request.setAttribute("list", list);
		
		return "listDept.jsp";
	}

}
