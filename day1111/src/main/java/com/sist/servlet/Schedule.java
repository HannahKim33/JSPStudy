package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.ScheduleDAO;
import com.sist.vo.ScheduleVO;

/**
 * Servlet implementation class Schedule
 */
@WebServlet("/Schedule")
public class Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleDAO dao=ScheduleDAO.getInstance();
		ArrayList<ScheduleVO> list=dao.findAll();
		Gson gson=new Gson();
		String str=gson.toJson(list);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(str);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ScheduleVO s=new ScheduleVO();
		s.setName(request.getParameter("name"));
		s.setSdate(request.getParameter("sdate"));
		ScheduleDAO dao=ScheduleDAO.getInstance();
		int re=dao.insertSchedule(s);
		response.setContentType("text/plain");
		PrintWriter out=response.getWriter();
		out.print(re);
		out.close();
	}

}
