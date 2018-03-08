package com.shiva.used.dao;
import java.sql.*;
import javax.sql.*;

import org.apache.catalina.Session;

import com.shiva.used.VO.used_saleVO;
import com.shiva.used.VO.used_sale_replyVO;

import com.shiva.used.VO.used_buyVO;
import com.shiva.used.VO.used_buy_replyVO;

import javax.naming.*;
import java.util.*;
public class usedDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public usedDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			
		}catch(Exception ex) {
			return;
		}
	}
	
	public int maxNum() {
		String sql = "select max(NUM_S) from used_sale";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
				
			}
			System.out.println("result 1 : "+result);
			return result;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		System.out.println("result 2 : "+result);
		return result;
	}
	public boolean boardInsert(used_saleVO boarddata) {
		String sql = "";
		int result = 0;
		int max = maxNum();
		System.out.println("max : "+max);
		try {
			con = ds.getConnection();
			sql = "insert into used_sale ( "
					+ "NUM_S,NAME_S,SUBJECT_S,CONTENT_S,FILE_S,READCOUNT_S,DATE_S) "
					+ "VALUES (?,?,?,?,?,?,SYSDATE)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, max+1);
			pstmt.setString(2, boarddata.getNAME_S());
			pstmt.setString(3, boarddata.getSUBJECT_S());
			pstmt.setString(4, boarddata.getCONTENT_S());
			pstmt.setString(5, boarddata.getFILE_S());
			pstmt.setInt(6, 0);
			result = pstmt.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
	}
	public int getListCount() {
		int x = 0;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from used_sale");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return x;
	}
	public List<used_saleVO> getBoardList(int page, int limit) {
		String board_list_sql = "select * from (select rownum "
				+ " rnum,NUM_S,NAME_S,SUBJECT_S,CONTENT_S,FILE_S,READCOUNT_S,DATE_S "
				+ " from (SELECT * FROM USED_SALE ORDER BY NUM_S DESC))"
				+ " where rnum >= ? and rnum <= ?";
		List<used_saleVO> list = new ArrayList<used_saleVO>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			int result = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				used_saleVO board = new used_saleVO();
				board.setNUM_S(rs.getInt("NUM_S"));
				board.setNAME_S(rs.getString("NAME_S"));
				board.setSUBJECT_S(rs.getString("SUBJECT_S"));
				board.setCONTENT_S(rs.getString("CONTENT_S"));
				board.setFILE_S(rs.getString("FILE_S"));
				board.setREADCOUNT_S(rs.getInt("READCOUNT_S"));
				board.setDATE_S(rs.getDate("DATE_S"));
				list.add(board);
			}
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
		
	}
	public void setReadCountUpdate(int num) {
		String sql = "update used_sale set READCOUNT_S = READCOUNT_S+1 where NUM_S = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
	}
	public used_saleVO getDetail(int num) {
		used_saleVO VO = null;
		String sql = "select * from used_sale where NUM_S = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				VO = new used_saleVO();
				VO.setNUM_S(rs.getInt(1));
				VO.setNAME_S(rs.getString (2));
				VO.setSUBJECT_S(rs.getString(3));
				VO.setCONTENT_S(rs.getString(4));
				VO.setFILE_S(rs.getString(5));
				VO.setREADCOUNT_S(rs.getInt(6));
				VO.setDATE_S(rs.getDate(7));
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		
		
		return VO;
	}
	public boolean isBoardWriter(int num, String name) {
		
		String board_sql="select NAME_S from used_sale where NUM_S=?";
		boolean check = false;
		System.out.println("checkpoint1 : "+check);
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()){
				if(name.equals(rs.getString(1))) {
					check = true;
					System.out.println("checkpoint2 : "+check);
					return check;
				}
				else {
					check = false;
					return check;
				}
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter() 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		System.out.println("checkpoint3 : "+check);
		return check;
		
	}
	public boolean isBoardReplyWriter(int num, String name) {
		
		String board_sql="select NAME_S from used_sale_reply where NUM_S=?";
		boolean check = false;
		System.out.println("checkpoint1 : "+check);
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()){
				if(name.equals(rs.getString(1))) {
					check = true;
					System.out.println("checkpoint2 : "+check);
					return check;
				}
				else {
					check = false;
					return check;
				}
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter() 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		System.out.println("checkpoint3 : "+check);
		return check;
		
	}
	public boolean boardModify(used_saleVO VO) {
		
		String sql = "update used_sale set SUBJECT_S = ? , CONTENT_S = ? where NUM_S = ?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, VO.getSUBJECT_S());
			pstmt.setString(2, VO.getCONTENT_S());
			pstmt.setInt(3, VO.getNUM_S());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify() 에러: " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	public boolean boardDelete(int num){
		
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		
		String board_delete_sql
		  ="delete from used_sale where NUM_S=?";
		String board_delete_sql2
		="delete from used_sale_reply where NUM_S=?";
		int result1=0;
		int result2=0;
		try{
			con = ds.getConnection();
			pstmt1=con.prepareStatement(board_delete_sql);
			pstmt2=con.prepareStatement(board_delete_sql2);
			pstmt1.setInt(1, num);
			pstmt2.setInt(1, num);
			
			//쿼리 실행 후 삭제된 로우(레코드)갯수가 반환됩니다.
			result1=pstmt1.executeUpdate();
			result2=pstmt2.executeUpdate();
			//삭제가 안된 경우에는 false를 반환합니다.
			if(result1==0&&result2 == 0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete() 에러: "+ex);
		}finally{
		         try{
				       if(pstmt!=null)pstmt.close();
				       if(con!=null) con.close();
				      }
				   catch(Exception ex){}
		}
		
		return false;
	}
	public List<used_sale_replyVO> getSaleReplylist(int num) {
		String board_list_sql = "select NAME_S,CONTENT_S,DATE_S from used_sale_reply where NUM_S = ? order by DATE_S desc";
		List<used_sale_replyVO> list = new ArrayList<used_sale_replyVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			System.out.println("excute 완료");
			while(rs.next()) {
				used_sale_replyVO board = new used_sale_replyVO();
				board.setNAME_S(rs.getString("NAME_S"));
				board.setCONTENT_S(rs.getString("CONTENT_S"));
				board.setDATE_S(rs.getDate("DATE_S"));
				list.add(board);
			}
			System.out.println("list : " + list);
			System.out.println("select문 완료");
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
		
	}
	public boolean setReplySaleUpdate(used_sale_replyVO VO_R) {
		
		String sql = "";
		int result = 0;
		try {
			con = ds.getConnection();
			sql = "insert into used_sale_reply ( "
					+ "NUM_S,NAME_S,CONTENT_S,DATE_S) "
					+ "VALUES (?,?,?,SYSDATE)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, VO_R.getNUM_S());
			pstmt.setString(2, VO_R.getNAME_S());
			pstmt.setString(3, VO_R.getCONTENT_S());
			pstmt.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
		
	}

	public boolean UpdateSuccess(int num, String subject) {
		String sql = "update used_sale set SUBJECT_S = '"+subject+"[판매완료]' where NUM_S = "+num;
		if(subject.contains("[판매완료]")) {
			return false;
		}
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
			
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
	}

	public boolean boardReplyDelete(int num, String content) {
		String replyDel = "delete from used_sale_reply where NUM_S = "+num+" and CONTENT_S = '"+content+"'";
		System.out.println(replyDel);
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(replyDel);
			result = pstmt.executeUpdate();
			System.out.println("result = "+result);
			if(result == 0) {
				
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return true;
	}

	public List<used_saleVO> SearchList(int page, int limit, String searchkey) {
		System.out.println("DAO 에있는 searchkey"+searchkey);
		
		String search_list_sql = "select * from (select rownum "
				+ " rnum,NUM_S,NAME_S,SUBJECT_S,CONTENT_S,FILE_S,READCOUNT_S,DATE_S "
				+ " from (SELECT * FROM USED_SALE where NAME_S like '%" + searchkey + "%'"
						+ " or SUBJECT_S like '%"+searchkey+"%' ORDER BY NUM_S DESC))"
				+ " where rnum >= ? and rnum <= ?";
		List<used_saleVO> list = new ArrayList<used_saleVO>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(search_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				used_saleVO board = new used_saleVO();
				board.setNUM_S(rs.getInt("NUM_S"));
				board.setNAME_S(rs.getString("NAME_S"));
				board.setSUBJECT_S(rs.getString("SUBJECT_S"));
				board.setCONTENT_S(rs.getString("CONTENT_S"));
				board.setFILE_S(rs.getString("FILE_S"));
				board.setREADCOUNT_S(rs.getInt("READCOUNT_S"));
				board.setDATE_S(rs.getDate("DATE_S"));
				list.add(board);
			}
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
	
	}
	
	
	
	
	
	//sale, buy 구분
	
	
	
	
	
	public int buyMaxNum() {
		String sql = "select max(NUM_B) from used_buy";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
				
			}
			System.out.println("result 1 : "+result);
			return result;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		System.out.println("result 2 : "+result);
		return result;
	}
	public boolean buyBoardInsert(used_buyVO boarddata) {
		String sql = "";
		int result = 0;
		int max = buyMaxNum();
		System.out.println("max : "+max);
		try {
			con = ds.getConnection();
			sql = "insert into used_buy ( "
					+ "NUM_B,NAME_B,SUBJECT_B,CONTENT_B,FILE_B,READCOUNT_B,DATE_B) "
					+ "VALUES (?,?,?,?,?,?,SYSDATE)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, max+1);
			pstmt.setString(2, boarddata.getNAME_B());
			pstmt.setString(3, boarddata.getSUBJECT_B());
			pstmt.setString(4, boarddata.getCONTENT_B());
			pstmt.setString(5, boarddata.getFILE_B());
			pstmt.setInt(6, 0);
			result = pstmt.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
	}
	public int getBuyListCount() {
		int x = 0;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from used_buy");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return x;
	}
	public List<used_buyVO> getBuyBoardList(int page, int limit) {
		String board_list_sql = "select * from (select rownum "
				+ " rnum,NUM_B,NAME_B,SUBJECT_B,CONTENT_B,FILE_B,READCOUNT_B,DATE_B "
				+ " from (SELECT * FROM used_buy ORDER BY NUM_B DESC))"
				+ " where rnum >= ? and rnum <= ?";
		List<used_buyVO> list = new ArrayList<used_buyVO>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			int result = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				used_buyVO board = new used_buyVO();
				board.setNUM_B(rs.getInt("NUM_B"));
				board.setNAME_B(rs.getString("NAME_B"));
				board.setSUBJECT_B(rs.getString("SUBJECT_B"));
				board.setCONTENT_B(rs.getString("CONTENT_B"));
				board.setFILE_B(rs.getString("FILE_B"));
				board.setREADCOUNT_B(rs.getInt("READCOUNT_B"));
				board.setDATE_B(rs.getDate("DATE_B"));
				list.add(board);
			}
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
		
		
	}
	public void setBuyReadCountUpdate(int num) {
		String sql = "update used_buy set READCOUNT_B = READCOUNT_B+1 where NUM_B = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
	}
	public used_buyVO getBuyDetail(int num) {
		used_buyVO VO = null;
		String sql = "select * from used_buy where NUM_B = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				VO = new used_buyVO();
				VO.setNUM_B(rs.getInt(1));
				VO.setNAME_B(rs.getString (2));
				VO.setSUBJECT_B(rs.getString(3));
				VO.setCONTENT_B(rs.getString(4));
				VO.setFILE_B(rs.getString(5));
				VO.setREADCOUNT_B(rs.getInt(6));
				VO.setDATE_B(rs.getDate(7));
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		
		
		return VO;
	}
	public boolean isBuyBoardWriter(int num, String name) {
		
		String board_sql="select NAME_B from used_buy where NUM_B=?";
		boolean check = false;
		System.out.println("checkpoint1 : "+check);
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()){
				if(name.equals(rs.getString(1))) {
					check = true;
					System.out.println("checkpoint2 : "+check);
					return check;
				}
				else {
					check = false;
					return check;
				}
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter() 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		System.out.println("checkpoint3 : "+check);
		return check;
		
	}
	public boolean isBuyBoardReplyWriter(int num, String name) {
		
		String board_sql="select NAME_B from used_sale_reply where NUM_B=?";
		boolean check = false;
		System.out.println("checkpoint1 : "+check);
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()){
				if(name.equals(rs.getString(1))) {
					check = true;
					System.out.println("checkpoint2 : "+check);
					return check;
				}
				else {
					check = false;
					return check;
				}
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter() 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		System.out.println("checkpoint3 : "+check);
		return check;
		
	}
	public boolean buyBoardModify(used_buyVO VO) {
		
		String sql = "update used_buy set SUBJECT_B = ? , CONTENT_B = ? where NUM_B = ?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, VO.getSUBJECT_B());
			pstmt.setString(2, VO.getCONTENT_B());
			pstmt.setInt(3, VO.getNUM_B());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify() 에러: " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	public boolean buyBoardDelete(int num){
		
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		
		String board_delete_sql
		  ="delete from used_buy where NUM_B=?";
		String board_delete_sql2
		="delete from used_buy_reply where NUM_B=?";
		int result1=0;
		int result2=0;
		try{
			con = ds.getConnection();
			pstmt1=con.prepareStatement(board_delete_sql);
			pstmt2=con.prepareStatement(board_delete_sql2);
			pstmt1.setInt(1, num);
			pstmt2.setInt(1, num);
			
			//쿼리 실행 후 삭제된 로우(레코드)갯수가 반환됩니다.
			result1=pstmt1.executeUpdate();
			result2=pstmt2.executeUpdate();
			//삭제가 안된 경우에는 false를 반환합니다.
			if(result1==0&&result2 == 0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete() 에러: "+ex);
		}finally{
		         try{
				       if(pstmt!=null)pstmt.close();
				       if(con!=null) con.close();
				      }
				   catch(Exception ex){}
		}
		
		return false;
	}
	public List<used_buy_replyVO> getBuyReplylist(int num) {
		String board_list_sql = "select NAME_B,CONTENT_B,DATE_B from used_buy_reply where NUM_B = ? order by DATE_B desc";
		List<used_buy_replyVO> list = new ArrayList<used_buy_replyVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			System.out.println("excute 완료");
			while(rs.next()) {
				used_buy_replyVO board = new used_buy_replyVO();
				board.setNAME_B(rs.getString("NAME_B"));
				board.setCONTENT_B(rs.getString("CONTENT_B"));
				board.setDATE_B(rs.getDate("DATE_B"));
				list.add(board);
			}
			System.out.println("list : " + list);
			System.out.println("select문 완료");
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
		
	}
	public boolean setReplyBuyUpdate(used_buy_replyVO VO_R) {
		
		String sql = "";
		int result = 0;
		try {
			con = ds.getConnection();
			sql = "insert into used_buy_reply ( "
					+ "NUM_B,NAME_B,CONTENT_B,DATE_B) "
					+ "VALUES (?,?,?,SYSDATE)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, VO_R.getNUM_B());
			pstmt.setString(2, VO_R.getNAME_B());
			pstmt.setString(3, VO_R.getCONTENT_B());
			pstmt.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
		
	}

	public boolean BuyUpdateSuccess(int num, String subject) {
		String sql = "update used_buy set SUBJECT_B = '"+subject+"[판매완료]' where NUM_B = "+num;
		if(subject.contains("[판매완료]")) {
			return false;
		}
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
			
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
	}

	public boolean buyBoardReplyDelete(int num, String content) {
		String replyDel = "delete from used_buy_reply where NUM_B = "+num+" and CONTENT_B = '"+content+"'";
		System.out.println(replyDel);
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(replyDel);
			result = pstmt.executeUpdate();
			System.out.println("result = "+result);
			if(result == 0) {
				
				return false;
			}
			return true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return true;
	}

	public List<used_buyVO> buySearchList(int page, int limit, String searchkey) {
		System.out.println("DAO 에있는 searchkey"+searchkey);
		
		String search_list_sql = "select * from (select rownum "
				+ " rnum,NUM_B,NAME_B,SUBJECT_B,CONTENT_B,FILE_B,READCOUNT_B,DATE_B "
				+ " from (SELECT * FROM used_buy where NAME_B like '%" + searchkey + "%'"
						+ " or SUBJECT_B like '%"+searchkey+"%' ORDER BY NUM_B DESC))"
				+ " where rnum >= ? and rnum <= ?";
		List<used_buyVO> list = new ArrayList<used_buyVO>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(search_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				used_buyVO board = new used_buyVO();
				board.setNUM_B(rs.getInt("NUM_B"));
				board.setNAME_B(rs.getString("NAME_B"));
				board.setSUBJECT_B(rs.getString("SUBJECT_B"));
				board.setCONTENT_B(rs.getString("CONTENT_B"));
				board.setFILE_B(rs.getString("FILE_B"));
				board.setREADCOUNT_B(rs.getInt("READCOUNT_B"));
				board.setDATE_B(rs.getDate("DATE_B"));
				list.add(board);
			}
			return list;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return null;
	
	}

	public boolean UpdateBuySuccess(int num, String subject) {
		String sql = "update used_buy set SUBJECT_B = '"+subject+"[구매완료]' where NUM_B = "+num;
		if(subject.contains("[구매완료]")) {
			return false;
		}
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
			
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(pstmt != null)
			try {pstmt.close();}catch(Exception e1) {}
			if(con != null)
			try {con.close();}catch(Exception e1) {}
			if(rs != null)
			try {rs.close();}catch(Exception e1) {}
		}
		return false;
	}
}
