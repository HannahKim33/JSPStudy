package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.GoodsVO;

public class GoodsDAO {
	
	private static GoodsDAO dao;

	public static GoodsDAO getInstance() {
		if(dao==null) {
			dao=new GoodsDAO();
		}
		return dao;
	}
		
	private GoodsDAO() {
		
	}
	public ArrayList<GoodsVO> findAll(){
		ArrayList<GoodsVO> list=new ArrayList<>();
		String sql="select * from goods";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				GoodsVO g=new GoodsVO();
				g.setNo(rs.getInt("no"));
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setQty(rs.getInt("qty"));
				g.setFname(rs.getString("fname"));
				list.add(g);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}} 
			if(stmt!=null) {try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return list;
	}
	
}