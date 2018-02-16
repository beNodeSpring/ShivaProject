package com.shiva.main.dao;

import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
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
			String sql = "select * from MainNotice order by num";
			pstmt = conn.prepareStatement(sql); 
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
	
}
