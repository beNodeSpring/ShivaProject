package com.shiva.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
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
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
				
		try {
			conn = ds.getConnection();
			String sql = "select * from MainNotice order by ref desc, step asc";
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
	
	// 게시판의 페이징 처리를 위한 기능 수행
	public int boardPageCnt() {
		int pageCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
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
}
