package com.shiva.resume.dao;

/*
	DAO(Data Access Object) 클래스
	-데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
	-어떤 Service 클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동 처리는 DAO 클래스에서 이루어지게 됩니다.
*/
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;
import com.shiva.resume.vo.ResumeBean;

public class ResumeDAO {
	//데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public ResumeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println("DB 연결 성공");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	//데이터베이스의 한 레주메 가져오기
	public ResumeBean getResume(String id) {
		String sql = "select * from resume where RESUME_ID = ?";
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ResumeBean resume = new ResumeBean();
				resume.setRESUME_ID(rs.getString("RESUME_ID"));
				resume.setRESUME_DATE(rs.getDate("RESUME_DATE"));
				resume.setTODAYFEELING(rs.getString("TODAYFEELING"));
				resume.setDURATION1(rs.getString("DURATION1"));
				resume.setDURATION2(rs.getString("DURATION2"));
				resume.setDURATION3(rs.getString("DURATION3"));
				resume.setDURATION4(rs.getString("DURATION4"));
				resume.setDURATION5(rs.getString("DURATION5"));
				resume.setDURATION6(rs.getString("DURATION6"));
				resume.setDURATION7(rs.getString("DURATION7"));
				resume.setDURATION8(rs.getString("DURATION8"));
				resume.setDURATION9(rs.getString("DURATION9"));
				resume.setPROJECT1(rs.getString("PROJECT1"));
				resume.setPROJECT2(rs.getString("PROJECT2"));
				resume.setPROJECT3(rs.getString("PROJECT3"));
				resume.setPROJECT4(rs.getString("PROJECT4"));
				resume.setPROJECT5(rs.getString("PROJECT5"));
				resume.setPROJECT6(rs.getString("PROJECT6"));
				resume.setPROJECT7(rs.getString("PROJECT7"));
				resume.setPROJECT8(rs.getString("PROJECT8"));
				resume.setPROJECT9(rs.getString("PROJECT9"));
				resume.setTEXT1(rs.getString("TEXT1"));
				resume.setTEXT2(rs.getString("TEXT2"));
				resume.setTEXT3(rs.getString("TEXT3"));
				resume.setTEXT4(rs.getString("TEXT4"));
				resume.setTEXT5(rs.getString("TEXT5"));
				resume.setTEXT6(rs.getString("TEXT6"));
				resume.setTEXT7(rs.getString("TEXT7"));
				resume.setTEXT8(rs.getString("TEXT8"));
				resume.setTEXT9(rs.getString("TEXT9"));
				resume.setJAVAVAL(rs.getString("JAVAVAL"));
				resume.setPYTHONVAL(rs.getString("PYTHONVAL"));
				resume.setCVAL(rs.getString("CVAL"));
				resume.setRUBYVAL(rs.getString("RUBYVAL"));
				resume.setJAVASCRIPTVAL(rs.getString("JAVASCRIPTVAL"));
				resume.setCSHAPVAL(rs.getString("CSHAPVAL"));
				resume.setPHPVAL(rs.getString("PHPVAL"));
				resume.setOBJECTIVECVAL(rs.getString("OBJECTIVECVAL"));
				resume.setSQLVAL(rs.getString("SQLVAL"));
				resume.setCPLUSVAL(rs.getString("CPLUSVAL"));
				resume.setPIC(rs.getString("PIC"));
				resume.setVISITORPIC(rs.getString("VISITORPIC"));
				resume.setVISITOR_ID(rs.getString("VISITOR_ID"));
				return resume;
			} else {
				ResumeBean resume = new ResumeBean();
				resume.setRESUME_ID("아이디를 넣어주세요");
				resume.setRESUME_DATE(new Date(0));
				resume.setTODAYFEELING("오늘의한마디적어주세요");
				resume.setDURATION1("없음");
				resume.setDURATION2("없음");
				resume.setDURATION3("없음");
				resume.setDURATION4("없음");
				resume.setDURATION5("없음");
				resume.setDURATION6("없음");
				resume.setDURATION7("없음");
				resume.setDURATION8("없음");
				resume.setDURATION9("없음");
				resume.setPROJECT1("프로젝트 명");
				resume.setPROJECT2("프로젝트 명");
				resume.setPROJECT3("프로젝트 명");
				resume.setPROJECT4("프로젝트 명");
				resume.setPROJECT5("프로젝트 명");
				resume.setPROJECT6("프로젝트 명");
				resume.setPROJECT7("프로젝트 명");
				resume.setPROJECT8("프로젝트 명");
				resume.setPROJECT9("프로젝트 명");
				resume.setTEXT1("프로젝트 부가설명");
				resume.setTEXT2("프로젝트 부가설명");
				resume.setTEXT3("프로젝트 부가설명");
				resume.setTEXT4("프로젝트 부가설명");
				resume.setTEXT5("프로젝트 부가설명");
				resume.setTEXT6("프로젝트 부가설명");
				resume.setTEXT7("프로젝트 부가설명");
				resume.setTEXT8("프로젝트 부가설명");
				resume.setTEXT9("프로젝트 부가설명");
				resume.setJAVAVAL("false");
				resume.setPYTHONVAL("false");
				resume.setCVAL("false");
				resume.setRUBYVAL("false");
				resume.setJAVASCRIPTVAL("false");
				resume.setCSHAPVAL("false");
				resume.setPHPVAL("false");
				resume.setOBJECTIVECVAL("false");
				resume.setSQLVAL("false");
				resume.setCPLUSVAL("false");
				resume.setPIC("");
				resume.setVISITORPIC("");
				resume.setVISITOR_ID("방문자가없습니다");
				return resume;
			}
		} catch (Exception ex) {
			System.out.println("ResumeDAO -> getResume() 에러 : " + ex);
		} finally {
			if (rs!=null) try {rs.close();} catch(SQLException ex) {}
			if (pstmt!=null) try {pstmt.close();} catch(SQLException ex) {}
			if (con!=null) try {con.close();} catch(SQLException ex) {}
		}
		return null;
	}
	
	//데이터베이스의 모든 레주메 가져오기
	public List<ResumeBean> getResumeList(int page, int limit){
		System.out.println("ResumeDAO -> getResumeList 시작");
		String resume_list_sql = "select * from "
				+ "(select rownum rnum, RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1 from "
				+ "(select * from resume order by RESUME_DATE desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		List<ResumeBean> list = new ArrayList<ResumeBean>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(resume_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ResumeBean resume = new ResumeBean();
				resume.setRESUME_ID(rs.getString("RESUME_ID"));
				resume.setRESUME_DATE(rs.getDate("RESUME_DATE"));
				resume.setTODAYFEELING(rs.getString("TODAYFEELING"));
				resume.setPROJECT1(rs.getString("PROJECT1"));
				list.add(resume);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("ResumeDAO -> getResumeList 에러 : " + ex);
		} finally {
			if (rs!=null) try {rs.close();} catch (SQLException ex) {}
			if (pstmt!=null) try {pstmt.close();} catch (SQLException ex) {}
			if (con!=null) try {con.close();} catch (SQLException ex) {}
		}
		return null;
	}
	
	//데이터베이스 내 레주메 개수 구하기
	public int getResumeCount() {
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from resume");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("ResumeDAO -> getResumeCount 에러 : " + ex);
		} finally {
			if (rs!=null) try {rs.close();} catch (SQLException ex) {}
			if (pstmt!=null) try {pstmt.close();} catch (SQLException ex) {}
			if (con!=null) try {con.close();} catch (SQLException ex) {}
		}
		return x;
	}
	
	//데이터베이스 업데이트
	public int resumeUpdate(ResumeBean resumebean) {
		System.out.println("resumeUpdate 시작합니다.");
		String sql = "";
		int updated = 1;
		
		try {
			con = ds.getConnection();
			if (resumebean.getPIC() != null) {
			sql = "insert into resume "
					+ "(RESUME_ID, RESUME_DATE, TODAYFEELING, PROJECT1, PROJECT2, "
					+ "PROJECT3, PROJECT4, PROJECT5, PROJECT6, PROJECT7, "
					+ "PROJECT8, PROJECT9, DURATION1, DURATION2, DURATION3, "
					+ "DURATION4, DURATION5, DURATION6, DURATION7, DURATION8, "
					+ "DURATION9, TEXT1, TEXT2, TEXT3, TEXT4, TEXT5, TEXT6, TEXT7, "
					+ "TEXT8, TEXT9, JAVAVAL, PYTHONVAL, CVAL, RUBYVAL,"
					+ "JAVASCRIPTVAL, CSHAPVAL, PHPVAL, OBJECTIVECVAL,"
					+ "SQLVAL, CPLUSVAL, PIC)"
					+ "values(?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resumebean.getRESUME_ID());
			pstmt.setString(2, resumebean.getTODAYFEELING());
			pstmt.setString(3, resumebean.getPROJECT1());
			pstmt.setString(4, resumebean.getPROJECT2());
			pstmt.setString(5, resumebean.getPROJECT3());
			pstmt.setString(6, resumebean.getPROJECT4());
			pstmt.setString(7, resumebean.getPROJECT5());
			pstmt.setString(8, resumebean.getPROJECT6());
			pstmt.setString(9, resumebean.getPROJECT7());
			pstmt.setString(10, resumebean.getPROJECT8());
			pstmt.setString(11, resumebean.getPROJECT9());
			pstmt.setString(12, resumebean.getDURATION1());
			pstmt.setString(13, resumebean.getDURATION2());
			pstmt.setString(14, resumebean.getDURATION3());
			pstmt.setString(15, resumebean.getDURATION4());
			pstmt.setString(16, resumebean.getDURATION5());
			pstmt.setString(17, resumebean.getDURATION6());
			pstmt.setString(18, resumebean.getDURATION7());
			pstmt.setString(19, resumebean.getDURATION8());
			pstmt.setString(20, resumebean.getDURATION9());
			pstmt.setString(21, resumebean.getTEXT1());
			pstmt.setString(22, resumebean.getTEXT2());
			pstmt.setString(23, resumebean.getTEXT3());
			pstmt.setString(24, resumebean.getTEXT4());
			pstmt.setString(25, resumebean.getTEXT5());
			pstmt.setString(26, resumebean.getTEXT6());
			pstmt.setString(27, resumebean.getTEXT7());
			pstmt.setString(28, resumebean.getTEXT8());
			pstmt.setString(29, resumebean.getTEXT9());
			pstmt.setString(30, resumebean.getJAVAVAL());
			pstmt.setString(31, resumebean.getPYTHONVAL());
			pstmt.setString(32, resumebean.getCVAL());
			pstmt.setString(33, resumebean.getRUBYVAL());
			pstmt.setString(34, resumebean.getJAVASCRIPTVAL());
			pstmt.setString(35, resumebean.getCSHAPVAL());
			pstmt.setString(36, resumebean.getPHPVAL());
			pstmt.setString(37, resumebean.getOBJECTIVECVAL());
			pstmt.setString(38, resumebean.getSQLVAL());
			pstmt.setString(39, resumebean.getCPLUSVAL());
			pstmt.setString(40, resumebean.getPIC());
			} else {
				sql = "update resume "
						+ "set RESUME_DATE = sysdate, TODAYFEELING = ?, PROJECT1 = ?, PROJECT2 = ?, "
						+ "PROJECT3 = ?, PROJECT4 = ?, PROJECT5 = ?, PROJECT6 = ?, PROJECT7 = ?, "
						+ "PROJECT8 = ?, PROJECT9 = ?, DURATION1 = ?, DURATION2 = ?, DURATION3 = ?, "
						+ "DURATION4 = ?, DURATION5 = ?, DURATION6 = ?, DURATION7 = ?, DURATION8 = ?, "
						+ "DURATION9 = ?, TEXT1 = ?, TEXT2 = ?, TEXT3 = ?, TEXT4 = ?, TEXT5 = ?, TEXT6 = ?, TEXT7 = ?, "
						+ "TEXT8 = ?, TEXT9 = ?, JAVAVAL = ?, PYTHONVAL = ?, CVAL = ?, RUBYVAL = ?,"
						+ "JAVASCRIPTVAL = ?, CSHAPVAL = ?, PHPVAL = ?, OBJECTIVECVAL = ?,"
						+ "SQLVAL = ?, CPLUSVAL = ? "
						+ "where RESUME_ID = ?";
					
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, resumebean.getTODAYFEELING());
				pstmt.setString(2, resumebean.getPROJECT1());
				pstmt.setString(3, resumebean.getPROJECT2());
				pstmt.setString(4, resumebean.getPROJECT3());
				pstmt.setString(5, resumebean.getPROJECT4());
				pstmt.setString(6, resumebean.getPROJECT5());
				pstmt.setString(7, resumebean.getPROJECT6());
				pstmt.setString(8, resumebean.getPROJECT7());
				pstmt.setString(9, resumebean.getPROJECT8());
				pstmt.setString(10, resumebean.getPROJECT9());
				pstmt.setString(11, resumebean.getDURATION1());
				pstmt.setString(12, resumebean.getDURATION2());
				pstmt.setString(13, resumebean.getDURATION3());
				pstmt.setString(14, resumebean.getDURATION4());
				pstmt.setString(15, resumebean.getDURATION5());
				pstmt.setString(16, resumebean.getDURATION6());
				pstmt.setString(17, resumebean.getDURATION7());
				pstmt.setString(18, resumebean.getDURATION8());
				pstmt.setString(19, resumebean.getDURATION9());
				pstmt.setString(20, resumebean.getTEXT1());
				pstmt.setString(21, resumebean.getTEXT2());
				pstmt.setString(22, resumebean.getTEXT3());
				pstmt.setString(23, resumebean.getTEXT4());
				pstmt.setString(24, resumebean.getTEXT5());
				pstmt.setString(25, resumebean.getTEXT6());
				pstmt.setString(26, resumebean.getTEXT7());
				pstmt.setString(27, resumebean.getTEXT8());
				pstmt.setString(28, resumebean.getTEXT9());
				pstmt.setString(29, resumebean.getJAVAVAL());
				pstmt.setString(30, resumebean.getPYTHONVAL());
				pstmt.setString(31, resumebean.getCVAL());
				pstmt.setString(32, resumebean.getRUBYVAL());
				pstmt.setString(33, resumebean.getJAVASCRIPTVAL());
				pstmt.setString(34, resumebean.getCSHAPVAL());
				pstmt.setString(35, resumebean.getPHPVAL());
				pstmt.setString(36, resumebean.getOBJECTIVECVAL());
				pstmt.setString(37, resumebean.getSQLVAL());
				pstmt.setString(38, resumebean.getCPLUSVAL());
				pstmt.setString(39, resumebean.getRESUME_ID());
			}
			pstmt.executeUpdate();
			return updated;
		} catch (SQLException ex) {
			updated = 0;
			System.out.println("ResumeDAO -> resumeUpdate 에러 : " + ex);
		} finally {
			if(rs!=null)try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null)try {con.close();}catch(SQLException ex) {}
		}
		return updated;
	}
	
	//데이터베이스 업데이트
	public void resumeDelete(ResumeBean resumebean) {
		System.out.println("resumeDelete 시작합니다.");
		String sql = "";
		
		try {
			con = ds.getConnection();
			sql = "delete from resume where RESUME_ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resumebean.getRESUME_ID());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("ResumeDAO -> resumeDelete 에러 : " + ex);
		} finally {
			if(rs!=null)try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null)try {con.close();}catch(SQLException ex) {}
		}
	}		
	
	//프로필 사진 가져오기
	public String getPic(String id) {
		System.out.println("getPic 시작합니다.");
		String sql = "";
		
		try {
			String pic = "";
			con = ds.getConnection();
			sql = "select PIC from resume where RESUME_ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pic = rs.getString(1);
			}
			return pic;
		} catch (SQLException ex) {
			System.out.println("ResumeDAO -> getPic 에러 : " + ex);
		} finally {
			if(rs!=null)try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null)try {con.close();}catch(SQLException ex) {}
		}
		return null;
	}
	
	//방문기록
	public void visitResume(String visitorid, String visitorpic, String want) {
		System.out.println("visitResume 시작합니다.");
		String sql = "";
		
		try {
			con = ds.getConnection();
			sql = "update resume set VISITORPIC = ?, VISITOR_ID = ? where RESUME_ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, visitorpic);
			pstmt.setString(2, visitorid);
			pstmt.setString(3, want);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("ResumeDAO -> visitResume 에러 : " + ex);
		} finally {
			if(rs!=null)try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null)try {con.close();}catch(SQLException ex) {}
		}
	}
}
