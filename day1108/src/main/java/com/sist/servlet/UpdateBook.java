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
 * Servlet implementation class UpdateBook
 */
@WebServlet("/UpdateBook")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		String bookname=request.getParameter("bookname");
		String publisher=request.getParameter("publisher");
		int price=Integer.parseInt(request.getParameter("price"));
		
		BookVO b=new BookVO();
		b.setBookid(bookid);
		b.setBookname(bookname);
		b.setPublisher(publisher);
		b.setPrice(price);
		
		BookDAO dao=BookDAO.getInstance();
		int re=dao.updateBook(b);
		
		response.setContentType("text/plain");
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
