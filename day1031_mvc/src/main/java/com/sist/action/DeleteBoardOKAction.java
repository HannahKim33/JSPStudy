package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;

public class DeleteBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("no"));
		String pwd=request.getParameter("pwd");
		String path=request.getRealPath("upload");
		BoardDAO dao=new BoardDAO();
		String fname=dao.findByNo(no).getFname();
		
		int re=dao.deleteBoard(no, pwd);
		if(re>0 && fname!=null&& !fname.equals("")) {
			File file=new File(path+"/"+fname);
			file.delete();
		}
		
		request.setAttribute("re", re);
		return "deleteBoardOK.jsp";
	}

}
