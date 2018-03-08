
package com.shiva.license.dao;

import java.sql.*; 
import java.util.*;
import java.sql.Date;

import javax.naming.*;
import javax.sql.DataSource;

import com.shiva.license.vo.LicenseBoardVO;
import com.shiva.license.vo.LicenseCalendar_ConVO;
import com.shiva.license.vo.LicenseCalendar_SubVO;
import com.shiva.license.vo.LicenseReplyVO;


public class LicenseDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//
	public LicenseDAO() {
		try {
			Context init = new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			
		}catch(Exception ex)
		{
			System.out.println("lDB접속실패"+ex);
			return;
		}
	}

	public boolean boardInsert(LicenseBoardVO board, String boardname) {
		int num=0;
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			
			pstmt=con.prepareStatement("select max(BOARD_NUM) from "+boardname);
			rs= pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1)+1; //게시물최대숫자
			else
				num=1; //처음작성
		sql= "insert into "+boardname
			+ "(BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
			+ "BOARD_CONTENT,"
			+ "BOARD_COUNT,"
			+ "BOARD_DATE)"
			+ "values(?,?,?,?,?,sysdate)";
		
		//정보넣긔
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, board.getBOARD_NAME());
		pstmt.setString(3, board.getBOARD_SUBJECT());
		pstmt.setString(4, board.getBOARD_CONTENT());
		pstmt.setInt(5, 0);//조회수
		
		
		result=pstmt.executeUpdate();
		if(result==0)//실패할시 false
			return false;
		//성공시
		return true; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("boardInsert() 실패: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return false;
	}
	public boolean HolidayInsert(LicenseCalendar_SubVO board, String boardname) {
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			
		sql= "insert into "+boardname
			+ "(ITC_DATE,ITC_NAME,ITC_TYPE)"
			+ "values(to_date('"+board.getITC_STRINGDATE()+"','yyyy-mm-dd'),?,?)";
		
		//정보넣긔
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, board.getITC_NAME());
		pstmt.setInt(2, board.getITC_TYPE());
		
		
		
		result=pstmt.executeUpdate();
		if(result==0)//실패할시 false
			return false;
		//성공시
		return true; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("HolidayInsert() 실패: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return false;
	}

	public boolean WorkdayInsert(LicenseCalendar_ConVO board, String boardname) {
		String sql="";
		int result=0;
		try {
			con= ds.getConnection();
			
		sql= "insert into "+boardname
			+ "(ITC_DATE,ITC_NAME,ITC_LINK)"
			+ "values(to_date('"+board.getITC_STRINGDATE()+"','yyyy-mm-dd'),?,?)";
		
		//정보넣긔
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, board.getITC_NAME());
		pstmt.setString(2, board.getITC_LINK());
		
		
		
		result=pstmt.executeUpdate();
		if(result==0)//실패할시 false
			return false;
		//성공시
		return true; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("WorkdayInsert() 실패: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return false;
	}
	
	public int getListCount(String boardname) {
		
		int num=0;
		try {
			con= ds.getConnection();
			//최대 숫자 출력
			pstmt=con.prepareStatement("select count(*) from "+boardname);
			rs= pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1); //있을경우 최대수
			else
				num=0; //없을시
			return num; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getListCount() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return 0;
	}
public int getSerachListCount(String serachname,String option, String boardname) {
		
		int num=0;
		try {
			con= ds.getConnection();
			//최대 숫자 출력
			System.out.println("검색명은 "+serachname);
			
			pstmt=con.prepareStatement("select count(*) from "+boardname+" where "+option+" like '%"+serachname+ "%' ");
			//pstmt.setString(1, option);
			rs= pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1); //있을경우 최대수
			else
				num=0; //없을시
			return num; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getSerachListCount() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return 0;
	}
	
	public List<LicenseBoardVO> getBoardList(int page, int limit, String boardname) {
		
		
		String board_list_sql=
				"select * from "
			+	"(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT," 
			+	"BOARD_CONTENT,"
			+   "BOARD_COUNT," 
			+   "BOARD_DATE from"
			+	"(select * from "+boardname
			+	" order by BOARD_NUM desc))"
			+ "where rnum>=? and rnum<=?";
		
		List<LicenseBoardVO> list= new ArrayList<LicenseBoardVO>();
		int startrow=(page-1)*limit+1;  //�б� ������ row
		int endrow=startrow+limit-1; 	//���� ������ row
		try {
			con= ds.getConnection();
			//board ���̺��� board_num �ʵ��� �ִ밪�� ���ؿͼ� ���� ���
			//�� ��ȣ�� ���������� �����ϱ� ����
			pstmt=con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				LicenseBoardVO board=new LicenseBoardVO();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_COUNT(rs.getInt("BOARD_COUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return null;
	}

public List<LicenseBoardVO> getNoticeSerachBoardList(int page, int limit,int listcount,String serachname,String option, String boardname) {
		
		
		String board_list_sql=
				"select * from "
			+	"(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT," 
			+	"BOARD_CONTENT,"
			+   "BOARD_COUNT," 
			+   "BOARD_DATE from"
			+	"(select * from "+boardname
			+	" where "+option+" LIKE '%"+serachname+"%' "
			+	" order by BOARD_NUM desc))"
			+ "where rnum>=? and rnum<=?";
		
		List<LicenseBoardVO> list= new ArrayList<LicenseBoardVO>();
		int startrow=(page-1)*limit+1;  //�б� ������ row
		int endrow=startrow+limit-1; 	//���� ������ row
		
		
		try {
			con= ds.getConnection();
			//board ���̺��� board_num �ʵ��� �ִ밪�� ���ؿͼ� ���� ���
			//�� ��ȣ�� ���������� �����ϱ� ����
			pstmt=con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			System.out.println("있어요~"+startrow+","+endrow);
			
			rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				
				LicenseBoardVO board=new LicenseBoardVO();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_COUNT(rs.getInt("BOARD_COUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			System.out.println("list ="+list.size());
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getNoticeSerachBoardList() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
		return null;
	}
	public void setReadCount(int num, String boardname) {
		
		String sql="update "+boardname+" set BOARD_COUNT=BOARD_COUNT+1 where BOARD_NUM = ?";
		try {
			con= ds.getConnection();
			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("setCount() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
	
	}
	
	//�� ����
		public boolean boardDelete(int num, String boardname){
			String board_delete_sql
			  ="delete from "+boardname+" where BOARD_NUM=?";
	
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_delete_sql);
				pstmt.setInt(1, num);
				
				//보드삭제 ㄱ
				result=pstmt.executeUpdate();
				
				
				//실패시
				if(result==0)return false;
				
				return true;
			}catch(Exception ex){
				System.out.println("boardDelete() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return false;
		}//boardDelete()�޼��� end
		
		public boolean ReplyDelete(int num,String replyname){
			String reply_delete_sql
			="delete from "+replyname+" where REPLY_NUM=?";
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(reply_delete_sql);
				pstmt.setInt(1, num);
				
				//보드삭제 ㄱ
				result=pstmt.executeUpdate();
				
				
				//실패시
				if(result==0)return false;
				
				return true;
			}catch(Exception ex){
				System.out.println("ReplyDelete() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return false;
		}//boardDelete()�޼��� end
		
	public LicenseBoardVO getDetail(int num, String boardname) {
		String sql= "select * from "+boardname+" where BOARD_NUM = ?";
		
		try {
			con= ds.getConnection();
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery();
			
			if(rs.next())
			{
				LicenseBoardVO board=new LicenseBoardVO();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_COUNT(rs.getInt("BOARD_COUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				return board;
			}
				
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getDetail() 에러: "+e);
		}
		finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
			if(con!=null) try {con.close();}catch(SQLException ex){}
			
		}
				
		return null;
	}
	
	
		//고치긔
		public boolean boardModify(LicenseBoardVO modifyboard, String boardname) 
				throws Exception{
			String sql="";
			try{
				sql= "update "+boardname
					     + " set BOARD_SUBJECT=? , BOARD_CONTENT=? "
					     + "where BOARD_NUM = ? ";
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
				pstmt.setString(2, modifyboard.getBOARD_CONTENT());
				pstmt.setInt(3, modifyboard.getBOARD_NUM());
				pstmt.executeUpdate();
				return true;
			}catch(Exception ex){
				System.out.println("boardModify() 실패 " + ex);
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
				}
			return false;
		}//boardModify() end
		
		public boolean isBoardWriter(int num, String pass){
			String board_sql="select * from board where BOARD_NUM=?";
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				rs.next();
				
				if(pass.equals(rs.getString("BOARD_PASS"))){
					return true;
				}
			}catch(SQLException ex){
				System.out.println("isBoardWriter() ���� : "+ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}
				                catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return false;
		}//isBoardWriter end

		public List<LicenseReplyVO> getReplylist(int num, String reply) {
			
			String reply_list_sql=
					"select * from "+reply
					+" where REPLY_NUM= "+num
					+" order by REPLY_LEVEL asc";
				
			List<LicenseReplyVO> list= new ArrayList<LicenseReplyVO>();
			
			try {
				con= ds.getConnection();
				
				pstmt=con.prepareStatement(reply_list_sql);
				
				
				rs= pstmt.executeQuery();
				
				while(rs.next())
				{
					LicenseReplyVO board=new LicenseReplyVO();
					board.setREPLY_NUM(rs.getInt("REPLY_NUM"));
					board.setREPLY_NAME(rs.getString("REPLY_NAME"));
					board.setREPLY_LEVEL(rs.getInt("REPLY_LEVEL"));
					board.setREPLY_CONTENT(rs.getString("REPLY_CONTENT"));
					list.add(board);
				}
				
				return list;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("getReplylist() 에러: "+e);
			}
			finally {
				if(rs!=null) try {rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
				if(con!=null) try {con.close();}catch(SQLException ex){}
				
			}
			return null;
		}

		public boolean ReplyInsert(LicenseReplyVO replydata, String replyname) {
			int level=0;
			String sql="";
			int result=0;
			try {
				con= ds.getConnection();
				
				pstmt=con.prepareStatement("select count(REPLY_NUM) from "+replyname+" where REPLY_NUM= ?");
				pstmt.setInt(1, replydata.getREPLY_NUM());
				rs= pstmt.executeQuery();
				
				if(rs.next())
					level=rs.getInt(1)+1; //게시물최대숫자
				else
					level=1; //처음작성
				System.out.println("여기까지 실행되요");
			sql= "insert into "+replyname
				+ "(REPLY_NUM,REPLY_LEVEL,REPLY_NAME,"
				+ "REPLY_CONTENT)"
				+ "values(?,?,?,?)";
			
			//정보넣긔
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replydata.getREPLY_NUM());
			pstmt.setInt(2, level);
			pstmt.setString(3, replydata.getREPLY_NAME());
			pstmt.setString(4, replydata.getREPLY_CONTENT());
			
			
			result=pstmt.executeUpdate();
			if(result==0)//실패할시 false
				return false;
			//성공시
			return true; 
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("ReplyInsert() 실패: "+e);
			}
			finally {
				if(rs!=null) try {rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try {pstmt.close();}catch(SQLException ex){}
				if(con!=null) try {con.close();}catch(SQLException ex){}
				
			}
			return false;
		}

		public boolean ReplyDelete(int num, int level, String replyname) {
			String board_delete_sql
			  ="delete from "+replyname+" where REPLY_NUM=? and REPLY_LEVEL=?";
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_delete_sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, level);
				
				//삭제 ㄱ
				result=pstmt.executeUpdate();
				//실패시
				if(result==0)return false;
				
				return true;
			}catch(Exception ex){
				System.out.println("ReplyDelete() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return false;
		}
		
		public int getReplyCount(int num, String replyname) {
			
			String board_sql
			  ="select count(*) from "+replyname+" where REPLY_NUM=?";
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				pstmt.setInt(1, num);
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReplyCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return 0;
		}
		
		public String getCalendarDayname(int year,int month ,int day) {
			String dayname="";
			
			
			String calendar_search_sql
			  ="select * from IT_CALENDAR_SUBJECT where ITC_DATE=to_date('"+year+"-"+month+"-"+day+"','yyyy-mm-dd')";
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(calendar_search_sql);
				
				
				//제목가져오기
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					dayname=rs.getString(2);
				
				return dayname;
			}catch(Exception ex){
				System.out.println("getCalendarDayname() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return dayname;
		}
		public int getCalendarDaytype(int year,int month ,int day) {
			int daytype=0;
			
			String calendar_search_sql
			  ="select * from IT_CALENDAR_SUBJECT where ITC_DATE=to_date('"+year+"-"+month+"-"+day+"','yyyy-mm-dd')";
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(calendar_search_sql);
				
				
				//제목가져오기
				rs= pstmt.executeQuery();
				//타입넣긔 1은 휴일 
				if(rs.next())
					daytype=rs.getInt(3);
			
				return daytype;
				
			}catch(Exception ex){
				System.out.println("getCalendarDayname() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return daytype;
		}
		public LicenseCalendar_ConVO getCalendarContent(int year,int month ,int day) {
			int daytype=0;
			LicenseCalendar_ConVO calendarvo= new LicenseCalendar_ConVO();
			String calendar_search_sql
			  ="select * from IT_CALENDAR_CONTENT where ITC_DATE=to_date('"+year+"-"+month+"-"+day+"','yyyy-mm-dd')";
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(calendar_search_sql);
				calendarvo.setITC_DATE(new Date(9999,12,12));
				calendarvo.setITC_NAME("");
				calendarvo.setITC_LINK("none");
				//쿼리 ㄱ
				rs= pstmt.executeQuery();
				//성공시
				if(rs.next()) {
				calendarvo.setITC_DATE(rs.getDate(1));
				calendarvo.setITC_NAME(rs.getString(2));
				calendarvo.setITC_LINK(rs.getString(3));
				}
			
				return calendarvo;
				
			}catch(Exception ex){
				System.out.println("getCalendarContent() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			
			return calendarvo;
		}
		public int getboardMaxCount(String boardname) {
			String board_sql
			  ="select max(BOARD_NUM) from "+boardname;
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getboardMaxCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}
		
		public int getReadSumCount(String boardname) {
			String board_sql
			  ="select sum(BOARD_COUNT) from "+boardname;
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReadSumCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}
		
		public double getReadAvgCount(String boardname) {
			String board_sql
			  ="select avg(BOARD_COUNT) from "+boardname;
			double result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getDouble(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReadAvgCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}
		
		public int getReadMaxCount(String boardname) {
			String board_sql
			  ="select max(BOARD_COUNT) from "+boardname;
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReadMaxCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}
		
		public int getReadZeroCount(String boardname) {
			String board_sql
			  ="select count(BOARD_COUNT) from "+boardname+" where BOARD_COUNT=0";
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//실패시
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReadZeroCount() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}
		
		public int getReadMaxBoardNum(String boardname) {
			String board_sql
			  ="select max(BOARD_COUNT) from "+boardname;
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				
				
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				
				//값받아오긔
				if(rs.next())
					result=rs.getInt(1);
				
				board_sql="select BOARD_NUM from "+boardname+ " where BOARD_COUNT="+result;
				pstmt=con.prepareStatement(board_sql);
				
				//카운트 ㄱ
				rs= pstmt.executeQuery();
				//값받아오긔
				
				if(rs.next())
					result=rs.getInt(1);
				
				return result;
			}catch(Exception ex){
				System.out.println("getReadMaxBoardNum() 실패: "+ex);
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return 0;
		}

		public boolean HolidayDelete(String boardname,String ITC_NAME) {
			String board_sql
			  ="select count(*) from "+boardname+" where ITC_NAME='"+ITC_NAME+"'";
			int result=0;
			try{
				con = ds.getConnection();
				
				pstmt=con.prepareStatement(board_sql);
				rs=pstmt.executeQuery();
				
				board_sql="delete "+boardname+ " where ITC_NAME='"+ITC_NAME+"'";
				pstmt=con.prepareStatement(board_sql);
				
				
			   pstmt.executeUpdate();
				
				
			   if(rs.next())
				   result=rs.getInt(1);
				
			   if(result==0)
				   return false;
			   else
				return true;
				
				
			}catch(Exception ex){
				System.out.println("HolidayDelete() 실패: "+ex);
				
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return false;
		}
		
		public boolean WorkdayDelete(String boardname,String ITC_NAME) {
			String board_sql
			  ="select count(*) from "+boardname+" where ITC_NAME='"+ITC_NAME+"'";
			int result=0;
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_sql);
				rs=pstmt.executeQuery();
				
				board_sql="delete "+boardname+ " where ITC_NAME='"+ITC_NAME+"'";
				pstmt=con.prepareStatement(board_sql);
				
				
			   pstmt.executeUpdate();
				
				
			   if(rs.next())
				   result=rs.getInt(1);
				
			   if(result==0)
				   return false;
			   else
				return true;
			}catch(Exception ex){
				System.out.println("WorkdayDelete() 실패: "+ex);
				
			}finally{
			         try{
					       if(pstmt!=null)pstmt.close();
					       if(con!=null) con.close();
					      }
					   catch(Exception ex){}
			}
			return false;
		}
		
}
