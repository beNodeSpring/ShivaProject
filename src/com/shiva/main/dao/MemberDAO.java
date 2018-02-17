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

	private void close(Connection conn, PreparedStatement[] psArr, ResultSet[] rsArr) {
		if (rsArr != null) {
			try {
				for (int i = 0; i < rsArr.length; i++) {
					rsArr[i].close();					
				}
			} catch(Exception e) {
				System.out.println("rsArr.close() 오류 발생 : " + e);
			}
		}		
		if (psArr != null) {
			try {
				for (int i = 0; i < psArr.length; i++) {
					psArr[i].close();					
				}
			} catch(Exception e) {
				System.out.println("psArr.close() 오류 발생 : " + e);
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
		//System.out.println(idPw[0] + ", " + idPw[1]);
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idPw[0]);
			pstmt.setString(2, idPw[1]);
			rs = pstmt.executeQuery(); // executeQuery() : select 실행
			if (rs.next()) {
				sessionId = rs.getString("id");
				System.out.println("returnId()에서 대입된 sessionId : " + sessionId );
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
		
		System.out.println("세션아이디 : "+sessionId);
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
	
	// [검사] 회원아이디 중복 검사 
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

	// [ajax] 메인 상단 최신글 불러오기 
	public String[] viewRecentPost() {
		Connection conn = null;
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		PreparedStatement pstmt3;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		String bd1Sql = " select BOARD_SUBJECT from (select rownum rnum, BOARD_SUBJECT "
				+ "from (SELECT * FROM IT_NOTICE_BOARD ORDER BY BOARD_NUM DESC)) "
				+ "where rnum = 1";
		String bd2Sql = " select RESUME_ID from (select rownum rnum, RESUME_ID "
				+ "from (SELECT * FROM resume ORDER BY RESUME_DATE DESC)) "
				+ "where rnum = 1";
		String bd3Sql = " select SUBJECT_S from (select rownum rnum,NUM_S,NAME_S,SUBJECT_S "
				+ "from (SELECT * FROM USED_SALE ORDER BY NUM_S DESC)) "
				+ "where rnum = 1";
		String[] resultText = new String[3];
		try {
			conn = connect();
			pstmt1 = conn.prepareStatement(bd1Sql);
			pstmt2 = conn.prepareStatement(bd2Sql);
			pstmt3 = conn.prepareStatement(bd3Sql);
			rs1 = pstmt1.executeQuery(); // executeQuery() : select 실행
			rs2 = pstmt2.executeQuery(); // executeQuery() : select 실행
			rs3 = pstmt3.executeQuery(); // executeQuery() : select 실행
			if (rs1.next() && rs2.next() && rs3.next()) {
				//System.out.println("IT 자격증 최근글 : "+rs1.getString("BOARD_SUBJECT"));
				//System.out.println("IT 이력서 최근글 : "+rs2.getString("RESUME_ID"));
				//System.out.println("IT 중고 최근글 : "+rs3.getString("SUBJECT_S"));
				resultText[0] = rs1.getString("BOARD_SUBJECT");
				resultText[1] = rs2.getString("RESUME_ID");
				resultText[2] = rs3.getString("SUBJECT_S");
				return resultText;
			} else {
				System.out.println("viewRecentPost()의 쿼리문 실행 에러");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("viewRecentPost() 오류발생" + e);
		} finally {
			close(conn, pstmt, rs);
		}		
		return null;
	}
	
	// [ajax] 인기 게시글 불러오기 
	public String[] viewHotPost() {
		Connection conn = null;
		int postLen = 6;
		PreparedStatement[] pstmtArr = new PreparedStatement[postLen];
		ResultSet[] rsArr = new ResultSet[postLen];
		String[] resultText = new String[postLen];
		String sqlArr[] = new String[postLen];
		
		sqlArr[0] = " select BOARD_SUBJECT from (select rownum rnum, BOARD_SUBJECT "
				+ "from (SELECT * FROM IT_NOTICE_BOARD ORDER BY BOARD_COUNT DESC)) "
				+ "where rnum = 1";
		sqlArr[1] = " select BOARD_SUBJECT from (select rownum rnum, BOARD_SUBJECT "
				+ "from (SELECT * FROM IT_NOTICE_BOARD ORDER BY BOARD_COUNT DESC)) "
				+ "where rnum = 2";
		sqlArr[2] = " select RESUME_ID from (select rownum rnum, RESUME_ID "
				+ "from (SELECT * FROM resume ORDER BY RESUME_DATE DESC)) "
				+ "where rnum = 1";
		sqlArr[3] = " select RESUME_ID from (select rownum rnum, RESUME_ID "
				+ "from (SELECT * FROM resume ORDER BY RESUME_DATE DESC)) "
				+ "where rnum = 2";
		sqlArr[4] = " select SUBJECT_S from (select rownum rnum,NUM_S,NAME_S,SUBJECT_S "
				+ "from (SELECT * FROM USED_SALE ORDER BY READCOUNT_S DESC)) "
				+ "where rnum = 1";
		sqlArr[5] = " select SUBJECT_S from (select rownum rnum,NUM_S,NAME_S,SUBJECT_S "
				+ "from (SELECT * FROM USED_SALE ORDER BY READCOUNT_S DESC)) "
				+ "where rnum = 2";
		
		try {
			
			conn = connect();
			for (int i = 0; i < postLen; i++) {
				pstmtArr[i] = conn.prepareStatement(sqlArr[i]);
				rsArr[i] = pstmtArr[i].executeQuery();
			}

			if(rsArr[0].next()&&rsArr[1].next()&&rsArr[2].next()&&rsArr[3].next()&&rsArr[4].next()&&rsArr[5].next()){
				resultText[0] = rsArr[0].getString("BOARD_SUBJECT");
				resultText[1] = rsArr[1].getString("BOARD_SUBJECT");
				resultText[2] = rsArr[2].getString("RESUME_ID");
				resultText[3] = rsArr[3].getString("RESUME_ID");
				resultText[4] = rsArr[4].getString("SUBJECT_S");				
				resultText[5] = rsArr[5].getString("SUBJECT_S");						
			} else {
				System.out.println("sqlArr중에서 쿼리문 select가 안된게 있음");
			}
			// System.out.println("쿼리문 하나 확인 : " + rsArr[0].getString("BOARD_SUBJECT"));
			
			return resultText;

		} catch (SQLException e) {
			System.out.println("viewHotPost() 오류발생" + e);
		} finally {
			close(conn, pstmtArr, rsArr);
		}		
		return null;
	}
	
	// [ajax] 공지사항 최신글 불러오기
	public String viewRecentNotice() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		try {		
			conn = connect();
			sql = " select SUBJECT from (select rownum rnum, SUBJECT "
					+ "from (SELECT * FROM MainNotice ORDER BY NUM DESC)) "
					+ "where rnum = 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("SUBJECT");				
			} else {
				result = "공지사항이 없습니다";
				System.out.println("MainNotice에서 불러올 DB 내용이 없음");
			}

		} catch (SQLException e) {
			System.out.println("viewRecentNotice() 오류발생" + e);
		} finally {
			close(conn, pstmt, rs);
		}			
		
		return result;
	}
	
}
