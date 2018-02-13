package com.shiva.main.dao;

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

import com.shiva.main.vo.MemberVO;

// 회원에 관한 모든 데이터베이스 처리 기능 담당 (싱글톤 패턴으로 생성)
public class MemberDAO {
	DataSource ds;           // context.xml에서 주소를 찾기위한 데이터타입
	PreparedStatement pstmt; // ? 사용위해 필요
	ResultSet rs;            // 쿼리문 결과를 담음	
	Connection conn;
	
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
	
	// [삽입] 회원정보 추가
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		
		try {
			conn = connect();
			//conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into member values(?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getMail());
			pstmt.setString(6, member.getMailDomain());
			pstmt.setString(7, member.getPhone());
			pstmt.executeUpdate(); // executeUpdate() : DML실행(select 제외)
		} catch (SQLException e) {
			System.out.println("memberInsert() 오류발생" + e);
		} finally {
			close(conn, pstmt);
		}
	}// memberInsert()
	
	// [추출] DB에서 아이디,비번을 매칭해서 세션 아이디를 꺼냄
	public String returnId(String[] idPw) {
		Connection conn = null;
		ResultSet rs = null;
		String sessionId = null;
		String sql = "SELECT id FROM member WHERE id=? AND passwd=? ";
		System.out.println(idPw[0] + ", " + idPw[1]);
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idPw[0]);
			pstmt.setString(2, idPw[1]);
			rs = pstmt.executeQuery(); // executeQuery() : select 실행
			if (rs.next()) {
				sessionId = rs.getString("id");
				System.out.println("sessionId : " + sessionId );
				return sessionId;
			} else {	
				return null;
			}
		} catch (SQLException e) {
			System.out.println("memberInsert() 오류발생" + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return sessionId;
	}

	// [검색] 회원정보 검색
	public MemberVO memberSearch(HttpServletRequest request) {
		Connection conn = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("id");
		String sql = "SELECT * FROM member WHERE id='"+ sessionId +"'";
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // executeQuery() : select 실행
			
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setPasswd(rs.getString(2));
				memberVO.setName(rs.getString(3));
				memberVO.setGender(rs.getString(4));
				memberVO.setMail(rs.getString(5));
				memberVO.setMailDomain(rs.getString(6));
				memberVO.setPhone(rs.getString(7));
				return memberVO;
			}
			
		} catch (SQLException e) {
			System.out.println("memberSearch() 오류발생" + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return null;		
	}
	
	// 짜는중 : [검사] 회원아이디 중복 검사 
	public boolean memberIdCheck(String desiredId) {
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM member WHERE id=? ";
		System.out.println("ajax로 넘어온 파라미터 : "+desiredId);
		try {
			conn = connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, desiredId);	
			rs = pstmt.executeQuery(); // executeQuery() : select 실행
			if (rs.next()) {
				return true; // 중복아이디 존재
			} else {
				return false; // 중복 아이디 없음
			}

		} catch (SQLException e) {
			System.out.println("memberSearch() 오류발생" + e);
		} finally {
			close(conn, pstmt, rs);
		}		
		return false;
	}
	
	// [수정] 회원정보 변경
	public boolean memberUpdate(MemberVO member, HttpServletRequest request) {
		Connection conn = null;
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("id");
		System.out.println("memberUpdate의 세션 아이디"+sessionId);
		
		String sql =  " UPDATE member SET passwd=?, name=?, gender=?, mail=?, mailDomain=?, phone=?";
		       sql += " where id = '";
		       sql += sessionId+"' ";
		       
		try {
			conn = connect();
			//conn = ds.getConnection();
			System.out.println("memberUpdate의 connection의 주소값"+conn);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getMail());
			pstmt.setString(5, member.getMailDomain());
			pstmt.setString(6, member.getPhone());
			pstmt.executeUpdate(); // executeUpdate() : DML실행(select 제외)
			return true;
		} catch (SQLException e) {
			System.out.println("memberUpdate() 오류발생" + e);
		} finally {
			close(conn, pstmt);
		}
		return false;
	}// memberUpdate()	
	
	// [삭제] 회원탈퇴
	public boolean memberDelete(MemberVO member, HttpServletRequest request) {
		Connection conn = null;
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("id");
		System.out.println("memberDelete의 세션 아이디"+sessionId);		
		String sql =  "DELETE FROM member WHERE id = '";
	           sql += sessionId+"' ";
	    System.out.println(sql);
	    try {       
		    conn = connect();
		    pstmt = conn.prepareStatement(sql);
		    pstmt.executeUpdate(); // memberDelete() : DML실행(select 제외)
		    return true;
		} catch (SQLException e) {
			System.out.println("memberUpdate() 오류발생" + e);
		} finally {
			close(conn, pstmt);
		}
		return false;
	}// memberDelete()
	
}
