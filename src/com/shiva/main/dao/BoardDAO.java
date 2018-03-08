package com.shiva.main.dao;

import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.shiva.main.vo.BoardVO;

// 회원에 관한 모든 데이터베이스 처리 기능 담당 (싱글톤 패턴으로 생성)
public class BoardDAO {
	DataSource ds;           // context.xml에서 주소를 찾기위한 데이터타입
	public static final int WRITING_PER_PAGE = 10;
	
	// 생성자에서 DB에 접속해서 Connection객체를 얻어옴
	public BoardDAO() {
		try {
			// DB접속정보는 XML처리(META-INF/context.xml)
			Context initCon = new InitialContext();
			ds=	(DataSource) initCon.lookup("java:comp/env/jdbc/OracleDB");
		} catch(Exception ex) {
			System.out.println("BoardDAO() 오류 발생 => DB 연결 실패 : " + ex);
		}		
	}

	// 자원 해제 : Connection, PreparedStatement, ResultSet 
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				System.out.println("rs.close() 오류 발생 : " + e);
			}
		}
		close(conn, ps);
	}
	
	// 자원 해제 : Connection, PreparedStatement
	private void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch(Exception e) {
				System.out.println("ps.close() 오류 발생 : " + e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch(Exception e) {
				System.out.println("conn.close() 오류 발생 : " + e);
			}
		}
	}
	
	// [select] 게시물 목록 조회
	public List<BoardVO> boardList(String curPage) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = ds.getConnection();

			String sql = "select * from "
            + "(select rownum rnum, NUM, ID, SUBJECT, CONTENT, WRITEDATE, REF, STEP, LEV, READCNT, CHILDCNT "
            + "from (select * from MainNotice order by REF DESC, STEP asc)) "
            + "where rnum >= ? and rnum <= ? ";		
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, WRITING_PER_PAGE *(Integer.parseInt(curPage)-1));  // 시작점
			pstmt.setInt(2, WRITING_PER_PAGE); // 가져오는 갯수
			rs = pstmt.executeQuery();
			 				

			while (rs.next()) {
				BoardVO boardVO = new BoardVO();
				
				boardVO.setNum(rs.getInt("num"));
				boardVO.setId(rs.getString("id"));
				boardVO.setSubject(rs.getString("subject"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setWriteDate(rs.getDate("writeDate"));
				boardVO.setRef(rs.getInt("ref"));
				boardVO.setStep(rs.getInt("step"));
				boardVO.setLev(rs.getInt("lev"));
				boardVO.setReadCnt(rs.getInt("readCnt"));
				boardVO.setChildCnt(rs.getInt("childCnt"));

				list.add(boardVO);
			}
		} catch (Exception e) {
			System.out.println("boardList() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);			
		}
		
		return list;
	}
	
	// [select] 게시판의 페이징 처리를 위한 기능 수행
	public int boardPageCnt() {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		int pageCnt = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) as num from MainNotice"; // 게시판에 등록 된 글의 갯수
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pageCnt = rs.getInt("num") / WRITING_PER_PAGE + 1; // 제작되어야 할 페이지의 수
			}
		} catch (Exception e) {
			System.out.println("boardPageCnt() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return pageCnt;
	}
	
	// [insert] 게시글 등록 기능 수행
	public void boardWrite(String id, String subject, String ckVal) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		int num = 1;
		
		// 등록된 글의 번호를 구한후 테이블에 insert
		try {
			conn = ds.getConnection();
			
			// 게시판의 가장 높은 글 번호를 max()를 이용해서 찾은 뒤 1을 더해준다 
			// 만약 최초의 게시글일 경우 글 번호 최대값이 null이므로 null벨류를 체크하는  nvl()을 이용하여  0으로 변환하여 글 번호를 1로 만듬
			String sql = "select nvl(max(num),0)+1 as num from MainNotice"; // 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("num");
			}

			// writedate : 현재 날짜를 구하는 sysdate를 사용해서 지정
			// ref : 현재 작성된 글이 새글이므로 먼저 구했던 글 번호 num으로 지정
			// step, lev, read_cnt, child-cnt : 새글 작성의 경우 0으로 지정			
			sql = "insert into MainNotice values (?,?,?,?,sysdate,?,0,0,0,0)";
			//     insert into MainNotice values (번호,아이디,글제목,sysdate,번호,0,0,0,0);
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2,id);
			pstmt.setString(3, subject);
			pstmt.setString(4, ckVal);
			pstmt.setInt(5, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardWrite() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}		
		
	}
	
	// [select] 게시물 열람 기능
	public BoardVO boardRead(String inputNum) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;		
		
		BoardVO writing = new BoardVO();
		
		try {
			conn = ds.getConnection();
			// 조회수 증가시키는 쿼리문
			String sql = "update MainNotice set readCnt=readCnt+1 where num=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			pstmt.executeUpdate();
			
			// 전달받은 num을 조건으로 조회하는 쿼리문
			sql = "select * from MainNotice where num=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				writing.setNum(rs.getInt("num"));
				writing.setId(rs.getString("id"));
				writing.setSubject(rs.getString("subject"));
				writing.setContent(rs.getString("content"));
				writing.setWriteDate(rs.getDate("writeDate"));
				writing.setRef(rs.getInt("ref"));
				writing.setStep(rs.getInt("step"));
				writing.setLev(rs.getInt("lev"));
				writing.setReadCnt(rs.getInt("readCnt"));
				writing.setChildCnt(rs.getInt("childCnt"));			
			}
			
		} catch (Exception e) {
			System.out.println("boardRead() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return writing;
	}
	
	// [update] 게시글 수정 기능 수행
	public void boardUpdate(String pSubject, String pCkVal, String pNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			String sql = "update MainNotice set subject=?, content=? where num=?";
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, pSubject);
			pstmt.setString(2, pCkVal);
			pstmt.setInt(3, Integer.parseInt(pNum));
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println("boardUpdate() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		
	}
	
	// [update] 게시글 수정에 필요한 원본글 데이터 조회 기능 수행
	public BoardVO boardUpdateForm(String pNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BoardVO writing = new BoardVO();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from MainNotice where num=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pNum));
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				writing.setNum(rs.getInt("num"));
				writing.setId(rs.getString("id"));
				writing.setSubject(rs.getString("subject"));
				writing.setContent(rs.getString("content"));
				writing.setWriteDate(rs.getDate("writeDate"));
				writing.setRef(rs.getInt("ref"));
				writing.setStep(rs.getInt("step"));
				writing.setLev(rs.getInt("lev"));
				writing.setReadCnt(rs.getInt("readCnt"));
				writing.setChildCnt(rs.getInt("childCnt"));			
			}
		} catch (Exception e) {
			System.out.println("boardUpdateForm() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 
		
		return writing;
	}
	
	// [delete] 게시글 삭제
	public void boardDelete(String pNum) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select ref, lev, step from MainNotice where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pNum));
			rs = pstmt.executeQuery();
		
			// 삭제를 수행하기 전에 대상들들의 답글 칼럼(childCnt)의 값을 1줄여주기 위한 boardDeleteChildCntUpdate를 호출한다
			if(rs.next()) {
				int ref = rs.getInt(1);
				int lev = rs.getInt(2);
				int step = rs.getInt(3);
				boardDeleteChildCntUpdate(ref, lev, step);
			}
			
			// 파라미터로 받은 글번호를 조건으로 delete한다
			sql = "delete from MainNotice where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pNum));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardDelete() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 		
		
	}
	
	// [댓글 만들고 제대로 작동하나 테스트 할 것] 삭제하려는 글에 답글이 달려있는지 여부를 검사
	public boolean boardReplyCheck(String pNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean replyCheck = false;
		int replyCnt = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select childCnt as reply_check from MainNotice where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pNum));
			rs = pstmt.executeQuery();
		
			// replyCnt에 쿼리문을 실행해서 나온 childCnt 값을 대입
			if(rs.next()) replyCnt = rs.getInt("reply_check");
			// replyCnt가 0이라면 댓글이 없다는거  
			if(replyCnt==0) replyCheck = true;
			
		} catch (Exception e) {
			System.out.println("boardUpdateForm() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 		
				
		return replyCheck;	
	}
	
	// [댓글 만들고 제대로 작동하나 테스트 할 것] 게시글이 답글일 경우, 원글들의 답글 갯수를 줄이는 기능 수행
	public void boardDeleteChildCntUpdate(int ref, int lev, int step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = null;
		
			for (int updateLev = lev-1; updateLev >= 0; updateLev--) {
				sql = "select max(step) from MainNotice where ref = ? and lev =? and step < ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, step);
				rs = pstmt.executeQuery();
				
				int maxStep = 0;
				if(rs.next()) maxStep = rs.getInt(1);
				
				sql = "update MainNotice set childCnt = childCnt-1 where ref=? and lev=? and step=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, maxStep);
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			System.out.println("boardDeleteChildCntUpdate() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 		
	}
	
	// 게시글 검색 기능
	public List<BoardVO> boardSearch(String searchOpt, String searchWord) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from MainNotice ";

			// 검색 옵션에 따른 쿼리문 분기처리
			if(searchOpt.equals("both")) {
				sql += "where subject like ? or content like ? ";
				//sql += "order by ref desc, step asc ";
				sql += "order by num ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchWord+"%");
				pstmt.setString(2, "%"+searchWord+"%");
			} else {
				sql += "where "+searchOpt+" like ? ";
				//sql += "order by ref desc, step asc ";
				sql += "order by num ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchWord+"%");
			}

			rs = pstmt.executeQuery();
			
			// 쿼리문(검색결과)를 ArrayList에 추가함
			while(rs.next()) {
				BoardVO writing = new BoardVO();
				
				writing.setNum(rs.getInt("num"));
				writing.setId(rs.getString("id"));
				writing.setSubject(rs.getString("subject"));
				writing.setContent(rs.getString("content"));
				writing.setWriteDate(rs.getDate("writeDate"));
				writing.setRef(rs.getInt("ref"));
				writing.setStep(rs.getInt("step"));
				writing.setLev(rs.getInt("lev"));
				writing.setReadCnt(rs.getInt("readCnt"));
				writing.setChildCnt(rs.getInt("childCnt"));
				
				list.add(writing);
			}
/*
			for(BoardVO value : list){
			    System.out.println("검색 쿼리문 결과 list에 담긴 글의 제목 :"+value.getSubject());
			}
*/			
		} catch (Exception e) {
			System.out.println("boardSearch() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 			

		return list;
	}
	
	// [답글] 작성에 필요한 원글 데이터 조회 기능
	public BoardVO boardReplyForm(String pNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO writing = new BoardVO();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from MainNotice where num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pNum));
			rs = pstmt.executeQuery();
			
			// 쿼리문(검색결과)를 ArrayList에 추가함
			if(rs.next()) {
				writing.setNum(rs.getInt("num"));
				writing.setId(rs.getString("id"));
				writing.setSubject(rs.getString("subject"));
				writing.setContent(rs.getString("content"));
				writing.setWriteDate(rs.getDate("writeDate"));
				writing.setRef(rs.getInt("ref"));
				writing.setStep(rs.getInt("step"));
				writing.setLev(rs.getInt("lev"));
				writing.setReadCnt(rs.getInt("readCnt"));
				writing.setChildCnt(rs.getInt("childCnt"));
			}
	
		} catch (Exception e) {
			System.out.println("boardReplyForm() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		} 			

		return writing;		
	}
	
	// [답글] 등록 기능 수행
	public void boardReply(String id, String subject, String content, String ref, String lev, String step) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		int replyNum = 0;
		int replyStep = 0;
		String sql = null;
		
		// 등록된 글의 번호를 구한후 테이블에 insert
		try {
			conn = ds.getConnection();
			
			// 답글이 위치할 step 값을 가져옴
			replyStep = boardReplySearchStep(ref, lev, step);

			if(replyStep > 0) {
				sql = "update MainNotice set step = step+1 where ref=? and step >= ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ref));
				pstmt.setInt(2, replyStep);
				pstmt.executeUpdate();
			} else {
				sql = "select max(step) from MainNotice where ref=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ref));
				rs = pstmt.executeQuery();
				if(rs.next()) replyStep = rs.getInt(1) + 1;
			}
			
			sql = "select max(num)+1 as num from MainNotice ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) replyNum = rs.getInt("num");
			
			sql = "insert into MainNotice values (?,?,?,?,sysdate,?,?,?,0,0)";
			//     insert into MainNotice values (번호,아이디,글제목,sysdate,ref,step,lev,readCnt,childCnt);
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, replyNum);
			pstmt.setString(2,id);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setInt(5, Integer.parseInt(ref));
			pstmt.setInt(6, replyStep);
			pstmt.setInt(7, Integer.parseInt(lev)+1);
			pstmt.executeUpdate();
			
			// 답글 출력 위치 선정 기능 수행
			boardReplyChildCntUpdate(ref, lev, replyStep);
			
		} catch (Exception e) {
			System.out.println("boardReply() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}		
		
	}

	// [답글] 출력 위치(step) 선정 기능 수행
	private int boardReplySearchStep(String ref, String lev, String step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int replyStep=0;
		
		try {
			conn = ds.getConnection();
			String sql="select nvl(min(step), 0) from MainNotice where ref=? and lev <= ? and step > ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(ref));
			pstmt.setInt(2, Integer.parseInt(lev));
			pstmt.setInt(3, Integer.parseInt(step));
			rs = pstmt.executeQuery();
			
			if(rs.next()) replyStep = rs.getInt(1);
						
		} catch (Exception e) {
			System.out.println("boardReplySearchStep() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}		
		return replyStep;
	}
	
	// [답글] 작성 후 원글들의 답글 갯수를 늘려주는 기능 수행
	private void boardReplyChildCntUpdate(String ref, String lev, int replyStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		
		try {
			conn = ds.getConnection();

			for (int updateLev = Integer.parseInt(lev); updateLev >= 0; updateLev--) {
				sql = "select max(step) from MainNotice where ref = ? and lev =? and step < ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ref));
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, replyStep);
				rs = pstmt.executeQuery();
				
				int maxStep = 0;
				if(rs.next()) maxStep = rs.getInt(1);
				
				sql = "update MainNotice set childCnt = childCnt+1 where ref=? and lev=? and step=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ref));
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, maxStep);
				pstmt.executeUpdate();
			}			
						
		} catch (Exception e) {
			System.out.println("boardReplySearchStep() 오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}		
	}

}
