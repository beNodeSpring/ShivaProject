
package com.shiva.car.dao;

//SQL과 관련된 객체와 List객체, JNDI 관련 객체를 사용하기 위해 import 합니다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shiva.car.vo.BoardVO;

public class BoardDAO {
	//데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public BoardDAO() {
		try{
			Context init = new InitialContext();
	  	    ds = 
	  	 (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}	
	
	//글의 갯수 구하기
	public int getListCount() {
		int x= 0;
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(
					"select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount() 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return x;
	}//getListCount() end
	
	//글 목록 보기
	public List<BoardVO> getBoardList(int page,int limit){
	    //page : 페이지 
		//limit : 페이지 당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을 
		//조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.		
		String board_list_sql= 
		  "select * from "
		+ 		  "(select rownum rnum,BOARD_NO,BOARD_NAME,"
		+         "BOARD_SUBJECT, BOARD_CONTENT,"
		+         "BOARD_COUNT,BOARD_DATE from board  order by BOARD_NO desc) "
  	    + "where rnum>=? and rnum<=?";
	
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		                      //한 페이지당 10개씩 목록인 경우         1페이지, 2페이지, 3페이지,  4페이지   ...
		int startrow=(page-1)* limit + 1; //읽기 시작할 row 번호(1      11      21     31  ... 
		int endrow= startrow + limit - 1; //읽을 마지막 row 번호(10	    20      30     40  ...
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()){
				BoardVO board = new BoardVO();
				board.setBOARD_NO(rs.getInt("BOARD_NO"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_COUNT(rs.getInt("BOARD_COUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board); //값을 담은 객체를 리스트에 저장합니다. 
			}
			return list; //값을 담은 객체를 저장한 리스트를 호출한 곳으로 가져갑니다.
		}catch(Exception ex){
			System.out.println("getBoardList() 에러 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}//getBoardList()메서드  end
	
	//글 내용 보기
	public BoardVO getDetail(int num){
		BoardVO board = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(
				"select * from board where BOARD_NO = ?");
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery();
			if(rs.next()){
				board = new BoardVO();
				board.setBOARD_NO(rs.getInt("BOARD_NO"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_COUNT(rs.getInt("BOARD_COUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		}catch(Exception ex){
			System.out.println("getDetail() 에러: " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}//getDetail()메서드 end
	
	//글 등록하기
	public boolean boardInsert(BoardVO board){
		int num =0;
		String sql="";
		int result=0;
		try{
			con = ds.getConnection();
			//board 테이블의 board_num 필드의 최대값을 구해와서 글을
			//등록할 때 글 번호를 순차적으로 지정하기 위함입니다.
			String max_sql = "select max(board_no) from board";
			pstmt=con.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num =rs.getInt(1)+1; //최대값보다 1만큼 큰값을 지정합니다.
			else  
				num=1; //처음 데이터를 등록하는 경우입니다. 
			
			sql=   "insert into board "
				 + "(BOARD_NO,BOARD_NAME,BOARD_SUBJECT,"
			     + " BOARD_CONTENT,BOARD_COUNT,"
				 //+ " BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
				 + " BOARD_DATE) "
				 + " values(?,?,?,?,?,sysdate)";
			
			//새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			
			
			
			pstmt.setInt(5, 0);   //BOARD_READCOUNT 필드
			
			result=pstmt.executeUpdate();
			if(result==0)  
				return false;			
			return true;
		}catch(Exception ex){
			System.out.println("boardInsert() 에러: "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
		
	}//boardInsert()메서드 end
	
	
	
/*	//글 답변 
	public int boardReply(BoardBean board){
		//board 테이블의 board_num 필드의 최대값을 구해와서 글을 등록할 때 
		//글 번호를 순차적으로 지정하기 위함입니다.
		String board_max_sql
		     ="select max(board_num) from board";
		String sql="";
		int num=0;
		
		 
		    답변을 할 원문 글 그룹 번호입니다.
		    답변을 달게 되면 답변 글은 이 번호와 같은 관련글 번호를 갖게 처리되면서 
		    같은 그룹에 속하게 됩니다.
		    글 목록에서 보여줄때 하나의 그룹으로 묶여서 출력됩니다.
		    
		int re_ref=board.getBOARD_RE_REF();
		
		
		    답글의 깊이를 의미합니다. 
		    원문에 대한 답글이 출력될 때 한 번 들여쓰기 처리가 
		    되고 답글에 대한 답글은 들여쓰기가 두 번 처리되게 합니다.
		    원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
		   
		int re_lev=board.getBOARD_RE_LEV();
		
		//같은 관련 글 중에서 해당 글이 출력되는 순서입니다.		
		int re_seq=board.getBOARD_RE_SEQ();
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				 num =rs.getInt(1)+1;
			else 
				num=1;
			
			//BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 다른 답글이 있으면 
			//다른 답글들의 BOARD_RE_SEQ값을 1씩 증가시킵니다.
			//현재 글을 다른 답글보다 앞에 출력되게 하기 위해서 입니다.
			sql="update board "
			  + "set BOARD_RE_SEQ=BOARD_RE_SEQ + 1 "
			  + "where BOARD_RE_REF = ? "
			  + "and BOARD_RE_SEQ > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			pstmt.executeUpdate();
			
			//등록할 답변 글의  BOARD_RE_LEV, BOARD_RE_SEQ 값을 원문 글보다 1씩 증가시킵니다.
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			
			sql="insert into board "
			 + "(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
			 + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
			 + " BOARD_RE_LEV, BOARD_RE_SEQ,"
			 + " BOARD_READCOUNT,BOARD_DATE) "
			 + "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, ""); //답변에는 파일을 업로드하지 않습니다.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);  //BOARD_READCOUNT(조회수)는 0
			pstmt.executeUpdate();
			return num;//글번호
		}catch(SQLException ex){
			System.out.println("boardReply() 에러 : "+ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return 0;
	}//boardReply()메서드 end
	*/
	//글 수정
	public boolean boardModify(BoardVO modifyboard) 
			{
		String sql="update board "
				 + "set BOARD_SUBJECT= ?, "
				 + "BOARD_CONTENT=? "
				 + "where BOARD_NO=? ";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NO());
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
	}//boardModify()메서드 end
	
	//글 삭제
	public boolean boardDelete(int num){
		String board_delete_sql
		  ="delete from board where BOARD_NO=?";
		int result=0;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			
			//쿼리 실행 후 삭제된 로우(레코드)갯수가 반환됩니다.
			result=pstmt.executeUpdate();
			//삭제가 안된 경우에는 false를 반환합니다.
			if(result==0)return false;
			
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
	}//boardDelete()메서드 end
	
	//조회수 업데이트 - 글번호에 해당하는 조회수를 1 증가합니다.	
	public void setReadCountUpdate(int num) {
		
		String sql="update board "
				+  "set BOARD_COUNT=BOARD_COUNT+1 "
				+  "where BOARD_NO = ?";
		try{
			con = ds.getConnection();			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate() 에러: "+ex);
		}finally{
			try{
		    	if(pstmt!=null)pstmt.close();
			    if(con!=null) con.close();
			}
			catch(Exception ex){
		        System.out.println(ex.getMessage());
			}
	   }
	}//setReadCountUpdate()메서드  end
	
	
	//글쓴이인지 확인 - 비밀번호로 확인합니다.
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
			System.out.println("isBoardWriter() 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}
			                catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}//isBoardWriter end
	
}//class end
	