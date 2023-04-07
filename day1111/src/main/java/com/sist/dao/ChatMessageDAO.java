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

import com.sist.vo.ChatMessageVO;

public class ChatMessageDAO {
	
	private static ChatMessageDAO dao;

	public static ChatMessageDAO getInstance() {
		if(dao==null) {
			dao=new ChatMessageDAO();
		}
		return dao;
	}
		
	private ChatMessageDAO() {
		
	}
	public ArrayList<ChatMessageVO> findAll(){
		ArrayList<ChatMessageVO> list=new ArrayList<>();
		String sql="select * from chatmessage order by id";
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
				ChatMessageVO c=new ChatMessageVO();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setMessage(rs.getString("message"));
				list.add(c);
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
	
	public int insertChatMessage(ChatMessageVO c) {
		int re=-1;
		String sql="insert into chatmessage(id, name, message) values(seq_chatmessage.nextval,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getMessage());
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