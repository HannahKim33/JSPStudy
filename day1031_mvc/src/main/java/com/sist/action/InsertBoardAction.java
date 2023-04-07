package com.sist.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class InsertBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=0;
		String title="새 글 작성";
		if(request.getParameter("no")!=null) {
			no=Integer.parseInt(request.getParameter("no"));
			title="답글 작성";
		}
		request.setAttribute("no", no);
		request.setAttribute("title", title);
		
		return "insertBoard.jsp";
	}

}
