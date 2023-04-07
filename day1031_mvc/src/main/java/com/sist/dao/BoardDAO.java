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

import com.sist.vo.BoardVO;

public class BoardDAO {
	
	//한 화면에 보여줄 레코드 수
	public static int pageSIZE=10;
	
	//전체 레코드 수
	public static int totalRecord=0;
	
	//전체 페이지 수
	public static int totalPage=0;
	
	public int insertBoard(BoardVO b) {
		int re=-1;
		String sql="insert into board(no,writer,pwd,title,content,fname,b_ref,b_step,b_level) values(?,?,?,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds= (DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			pstmt.setString(2, b.getWriter());
			pstmt.setString(3, b.getPwd());
			pstmt.setString(4, b.getTitle());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getFname());
			pstmt.setInt(7, b.getB_ref());
			pstmt.setInt(8, b.getB_step());
			pstmt.setInt(9, b.getB_level());
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
	
	public static int getNextNo() {
		int no=0;
		String sql="select nvl(max(no),0)+1 from board";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds= (DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				no=rs.getInt(1);
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
		
		return no;
	}
	
	public ArrayList<BoardVO> listAll(HashMap<String, String> map) {
		int pageTo=Integer.parseInt(map.get("pageTo"));
		String searchColumn=map.get("searchColumn");
		String keyword=map.get("keyword");
		
		totalRecord=getTotalRecord(searchColumn, keyword);
		
		/*
		totalPage=totalRecord/pageSIZE;
		if(totalRecord%pageSIZE!=0) {
			totalPage++;
		}*/
		
		totalPage=(int)Math.ceil((double)totalRecord/pageSIZE);
		
		System.out.println("searchColumn: "+searchColumn);
		System.out.println("keyword: "+keyword);
		
		int start=(pageTo-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		if(end>totalRecord) {
			end=totalRecord;
		}
		
		

		ArrayList<BoardVO> list=new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum n, no, writer, pwd, title, content,"
				+ " regdate, hit, fname, b_ref, b_step, b_level from "
				+ "(select * from board ";
				
				if(keyword!=null && !keyword.equals("")) {
					sql+="where "+searchColumn+" like '%"+keyword+"%'";
				}
				
				sql+=" order by b_ref desc, b_step)) "
				+ "where n between "+start+" and "+end;
	
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO b=new BoardVO();
				b.setNo(rs.getInt("no"));
				b.setWriter(rs.getString("writer"));
				b.setPwd(rs.getString("pwd"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setRegdate(rs.getDate("regdate"));
				b.setB_ref(rs.getInt("b_ref"));
				b.setHit(rs.getInt("hit"));
				b.setB_step(rs.getInt("b_step"));
				b.setB_level(rs.getInt("b_level"));
				list.add(b);
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
	
	public BoardVO findByNo(int no) {
		BoardVO b=null;
		String sql="select * from board where no=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new BoardVO();
				b.setNo(no);
				b.setWriter(rs.getString("writer"));
				b.setPwd(rs.getString("pwd"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setRegdate(rs.getDate("regdate"));
				b.setHit(rs.getInt("hit"));
				b.setFname(rs.getString("fname"));
				b.setB_ref(rs.getInt("b_ref"));
				b.setB_step(rs.getInt("b_step"));
				b.setB_level(rs.getInt("b_level"));
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
	
	public int updateBoard(BoardVO b) {
		int re=-1;
		String sql="update board set title=?, content=?, fname=? where no=? and pwd=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		Context context;
		try {
			context = new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFname());
			pstmt.setInt(4, b.getNo());
			pstmt.setString(5, b.getPwd());
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
	
	public void updateHit(int no) {
		String sql="update board set hit=hit+1 where no=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		
		Context context;
		try {
			context = new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
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
	}
	
	public int deleteBoard(int no, String pwd) {
		System.out.println("dao-----------------------");
		System.out.println("no:"+no);
		System.out.println("pwd:"+pwd);
		int re=-1;
		String sql="delete board where no=? and pwd=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, pwd);
			re=pstmt.executeUpdate();
			System.out.println("re: "+re);
			System.out.println("dao-----------------------");
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
	
	public void updateStep(int b_ref, int b_step) {
		String sql="update board set b_step=b_step+1 where b_ref=? and b_step>?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		Context context;
		try {
			context = new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,b_ref);
			pstmt.setInt(2, b_step);
			pstmt.executeUpdate();
			
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
	}
	
	public int getTotalRecord(String searchColumn, String keyword) {
		int count=0;
		String sql="select count(*) from board";
		if(keyword!=null && !keyword.equals("")) {
			sql+=" where "+searchColumn+" like '%"+keyword+"%'";
		}
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/mydb");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
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
		return count;
	}
}
