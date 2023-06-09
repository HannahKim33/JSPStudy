package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;
import com.sist.vo.BookVO;

/**
 * Servlet implementation class EditBooks
 */
@WebServlet("/EditBooks")
public class EditBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oper=request.getParameter("oper");
		System.out.println("oper:"+oper);

		int bookid=Integer.parseInt(request.getParameter("bookid"));
		BookDAO dao=BookDAO.getInstance();
		
		int re=-1;
		
		if(oper.equals("add")||oper.equals("edit")) {
			int price=Integer.parseInt(request.getParameter("price"));
			String publisher=request.getParameter("publisher");
			String bookname=request.getParameter("bookname");
			
			BookVO b=new BookVO();
			b.setBookid(bookid);
			b.setBookname(bookname);
			b.setPublisher(publisher);
			b.setPrice(price);
			
			if(oper.equals("add")) {
				re=dao.insertBook(b);
			}else if(oper.equals("edit")) {
				re=dao.updateBook(b);
			}
		}else if(oper.equals("del")) {
			re=dao.deleteBook(bookid);
		}
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(re);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
