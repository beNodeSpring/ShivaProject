package com.shiva.main.service;

import javax.servlet.http.HttpServletRequest;

import com.shiva.main.dao.MemberDAO;
import com.shiva.main.vo.MemberVO;

// 회원에 관한 모든 서비스 기능 처리 담당 (싱글톤 패턴으로 생성)
public class MemberService {
	private static MemberService service;
	public MemberDAO dao = MemberDAO.getInstance();

	private MemberService(){ }
	public static MemberService getInstance() {
		if(service==null) {
			service= new MemberService();
		}
		return service;
	}
	
	// 1. 회원정보 생성
	public void memberInsert(MemberVO member) {
		dao.memberInsert(member);
	}
	
	// 2. 회원아이디 중복검사
	public boolean memberIdCheck(String desiredId) {
		return dao.memberIdCheck(desiredId);
	}

	// 4. 회원 검색
	public MemberVO memberSearch(HttpServletRequest request) {
		return dao.memberSearch(request);
	}	
	
	// 5. 회원정보 수정
	public boolean memberUpdate(MemberVO member, HttpServletRequest request) {
		return dao.memberUpdate(member, request);
	}
		
	// 6. 회원정보 삭제(회원 탈퇴)
	public boolean memberDelete(MemberVO member, HttpServletRequest request) {
		return dao.memberDelete(member, request);
	}
	
	// 7. 최근 게시물 비동기로 불러오기
	public String[] viewRecentPost() {
		return dao.viewRecentPost();
	}
	
	// 8. 인기 게시물 비동기로 불러오기
	public String[] viewHotPost() {
		return dao.viewHotPost();
	}
	

}
