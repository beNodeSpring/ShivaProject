package com.shiva.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shiva.main.vo.MemberVO;

// 회원에 관한 모든 데이터베이스 처리 기능 담당 (싱글톤 패턴으로 생성)
public class MemberDAO {
	DataSource ds;           // context.xml에서 주소를 찾기위한 데이터타입
	PreparedStatement pstmt; // ? 사용위해 필요
	ResultSet rs;            // 쿼리문 결과를 담음	
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() { }
	public static MemberDAO getInstance() {
		if(dao==null) {
			dao= new MemberDAO();
		}
		return dao;
	}
	
	// DB에 접속해서 Connection객체를 얻어옴
	public Connection connect() {
		Connection conn = null;
		try {
			// DB접속정보는 XML처리(META-INF/context.xml)
			//Context init = new InitialContext();
			//ds=	(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			
			// xml로 하는 방식 어떻게 함??
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "TIGER");
		} catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
		return conn;
	}
	
	// 자원 해제 : Connection, PreparedStatement, ResultSet 
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				System.out.println("오류 발생 : " + e);
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
				System.out.println("오류 발생 : " + e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch(Exception e) {
				System.out.println("오류 발생 : " + e);
			}
		}
	}
	
	// member테이블에 회원정보 삽입
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		try {
			conn = connect();
			//conn = ds.getConnection();
			System.out.println("con의 값"+conn);
			pstmt = conn.prepareStatement("insert into member values(?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getMail());
			pstmt.setString(6, member.getPhone());
			pstmt.executeUpdate(); // executeUpdate : DML실행(select 제외)
		} catch (SQLException e) {
			System.out.println("오류발생" + e);
		} finally {
			close(conn, pstmt);
		}
		
	}
}
