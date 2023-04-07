package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class UpdateBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path=request.getRealPath("upload");
		System.out.println("path: "+path);
		MultipartRequest multi=new MultipartRequest(request, path, 1024*1024*5, "utf-8", 
				new DefaultFileRenamePolicy());
		
		int no=Integer.parseInt(multi.getParameter("no"));
		
		BoardDAO dao=new BoardDAO();
		BoardVO b=new BoardVO();
		
		
		b.setNo(no);
		//b.setWriter(multi.getParameter("writer"));
		b.setPwd(multi.getParameter("pwd"));
		b.setTitle(multi.getParameter("title"));
		b.setContent(multi.getParameter("content"));
		
		String oldFname=multi.getParameter("oldFname");
		String fname="";
		File file=multi.getFile("uploadFile");
		if(file!=null) {
			fname=file.getName();
		}
		b.setFname(oldFname);
		if(fname!=null && !fname.equals("")) {
			b.setFname(fname);
		}
		
		System.out.println("b toString:"+b);
		

		int re=dao.updateBoard(b);
		
		if(re>0) {
			if(fname!=null && !fname.equals("")) {
				if(oldFname!=null && !oldFname.equals("")) {
					File file2=new File(path+"/"+oldFname);
					file2.delete();
				}
			}
		}
		
		request.setAttribute("re", re);
		
		return "updateBoardOK.jsp";
	}

}
