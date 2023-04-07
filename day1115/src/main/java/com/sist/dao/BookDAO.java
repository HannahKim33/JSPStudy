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

import com.sist.vo.BookVO;

public class BookDAO {
	
	public static int totalRecord=0;
	public static int totalPage=0;	
	
	public static BookDAO dao;
	
	public static BookDAO getInstance() {
		if (dao==null) {
			dao=new BookDAO();
		}
		return dao;
	}
	
	private BookDAO() {}
	
	public int getTotalRecord() {
		int n=0;
		String sql="select count(*) from book";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				n=(rs.getInt(1));
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
		
		return n;
	}
	
	public int getNextBookid() {
		String sql="select nvl(max(bookid)+1,0) from book";
		int bookid=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bookid=rs.getInt(1);
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
		return bookid;
	}
	
	public ArrayList<BookVO> findAll(int rows, int page, String searchField, String searchOper, String searchString){
		totalRecord = getTotalRecord();
		totalPage = (int)Math.ceil(totalRecord / (double)rows);
		int start = (page-1)*rows + 1;
		int end = start + rows;
		if(end >  totalRecord) {
			end = totalRecord;
		}
		
		System.out.println("totalRecord:"+totalRecord);
		System.out.println("totalPage:"+totalPage);
		
		//검색컬럼이 bookid이면 = 연산을 하고 
		//나머지는 like연산을 수행하도록 합니다.      완성하면 "1"
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql = "select bookid, bookname, publisher, price "
				+ "from( "
				+ "select rownum n, a.* "
				+ "from "
				+ "(";
		
		
		sql += "select * from book ";
		if(searchString !=null && !searchString.equals("")) {
			if(searchField.equals("bookid") || searchField.equals("price")) {
				sql += " where "+searchField+searchOper+searchString;
			}else {
				sql += " where " + searchField + " like '%"+searchString+"%'" ;
			}
		}
		
		sql += " order by bookid) a) "
				+ "where n between ? and ?";
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);;
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				list.add(b);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) { try{rs.close();}catch(Exception e) {} }
			if(pstmt != null) { try{pstmt.close();}catch(Exception e) {} }
			if(conn != null) { try{conn.close();}catch(Exception e) {} }
		}
		
		return list;
	}
	
	public BookVO findByBookId(int bookid) {
		BookVO b=null;
		String sql="select * from book where bookid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
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
		return b;
	}
	
	public int updateBook(BookVO b) {
		String sql="update book set bookname=?, publisher=?, price=? where bookid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int re=-1;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookname());
			pstmt.setString(2, b.getPublisher());
			pstmt.setInt(3, b.getPrice());
			pstmt.setInt(4, b.getBookid());
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

	public int deleteBook(int bookid) {
		String sql="delete book where bookid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int re=-1;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
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
	
	public int insertBook(BookVO b) {
		String sql="insert into book(bookid, bookname, publisher, price) values(?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int re=-1;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBookid());
			pstmt.setString(2, b.getBookname());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
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
