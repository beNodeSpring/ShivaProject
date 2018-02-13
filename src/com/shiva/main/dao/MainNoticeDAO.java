package com.shiva.main.dao;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.shiva.main.vo.MainNoticeVO;
import com.shiva.main.vo.MemberVO;

// 회원에 관한 모든 데이터베이스 처리 기능 담당 (싱글톤 패턴으로 생성)
public class MainNoticeDAO {
	DataSource ds;           // context.xml에서 주소를 찾기위한 데이터타입
	PreparedStatement pstmt; // ? 사용위해 필요
	ResultSet rs;            // 쿼리문 결과를 담음	
	Connection conn;
	
	private static MainNoticeDAO dao = new MainNoticeDAO();
	private MainNoticeDAO() { }
	public static MainNoticeDAO getInstance() {
		if(dao==null) {
			dao= new MainNoticeDAO();
		}
		return dao;
	}
	
	// DB에 접속해서 Connection객체를 얻어옴
	public Connection connect() {
		try {
			// DB접속정보는 XML처리(META-INF/context.xml)
			Context initCon = new InitialContext();
			ds=	(DataSource) initCon.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			
			// connection 가져오는 예전 방식
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "TIGER");
		} catch(Exception ex) {
			System.out.println("connect() 오류 발생 => DB 연결 실패 : " + ex);
		}
		return conn;
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
	
	// [select] 게시물 보기
	public List<MainNoticeVO> selectAllBoard() {
		String sql = "select * from MainNotice order by bdNum desc";
		List<MainNoticeVO> list = new ArrayList<MainNoticeVO>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; 
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			
			//
			while (rs.next()) {
				MainNoticeVO noticeVO = new MainNoticeVO();
				noticeVO.setBdNum(rs.getInt("bdNum"));
				noticeVO.setBdId(rs.getString("bdId"));
				noticeVO.setBdSubject(rs.getString("bdSubject"));
				noticeVO.setBdContent(rs.getString("bdContent"));
				noticeVO.setBdFile(rs.getString("bdFile"));
				noticeVO.setBdHits(rs.getString("bdHits"));
				noticeVO.setBdDate(rs.getDate("bdDate"));
				
				list.add(noticeVO);
			}
			close(conn, pstmt, rs);
		} catch (Exception ex) {
			System.out.println("selectAllBoard() 오류 발생 => DB 연결 실패 : " + ex);
		}
		
		return list;
	}
	//
}
