package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sist.vo.GoodsVO;

public class GoodsDAO {
	public int insertGoods(GoodsVO g) {
		int re=-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into goods(no, name, qty, price, fname) values(?,?,?,?,?)";
		
		try {
			Context context= new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, g.getNo());
			pstmt.setString(2, g.getName());
			pstmt.setInt(3, g.getQty());
			pstmt.setInt(4, g.getPrice());
			pstmt.setString(5, g.getFname());
			re=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {
				pstmt.close();
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
		return re;
	}
	
	public ArrayList<GoodsVO> findAll(HashMap<String, String> map){
		String searchColumn=map.get("searchColumn");
		String searchWord=map.get("searchWord");
		String sortColumn=map.get("sortColumn");
		String op=map.get("op");
		
		ArrayList<GoodsVO> list=new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from goods";
		
		if(searchWord!=null && !searchWord.equals("")) {
			switch(searchColumn) {
			case "no": sql+=" where no="+searchWord; break;
			case "name": sql+=" where name like '%"+searchWord+"%'"; break;
			default : sql += " where " + searchColumn + " "+ op + " " + searchWord;
			}
		}
		if(sortColumn!=null && !sortColumn.equals("")) {
			sql+=" order by "+sortColumn;
		}
		
		
		System.out.println("sql:"+sql);
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new GoodsVO(rs.getInt("no"),rs.getString("name"), rs.getInt("qty"),
						rs.getInt("price"),rs.getString("fname")));
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
	
	public GoodsVO findByNo(int no) {
		GoodsVO g=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from goods where no=?";
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				g=new GoodsVO(no, rs.getString("name"),rs.getInt("qty"),rs.getInt("price"),
						rs.getString("fname"));
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
			if(pstmt!=null) {try {
				pstmt.close();
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

		return g;
	}
	
	public int updateGoods(GoodsVO g) {
		int re=-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update goods set name=?, qty=?, price=?, fname=? where no=?";
		
		try {
			Context context= new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getQty());
			pstmt.setInt(3, g.getPrice());
			pstmt.setString(4, g.getFname());
			pstmt.setInt(5, g.getNo());
			re=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {
				pstmt.close();
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
		return re;
	}
	
	public int deleteGoods(int no) {
		int re=-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete goods where no=?";
		
		try {
			Context context= new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {
				pstmt.close();
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
		return re;
	}

}
