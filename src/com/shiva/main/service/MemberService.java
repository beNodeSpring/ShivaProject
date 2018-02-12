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
	
	// 2. 회원정보 수정
	public boolean memberUpdate(MemberVO member, HttpServletRequest request) {
		return dao.memberUpdate(member, request);
	}
	
	
	// 3. 회원정보 삭제(회원 탈퇴)
	
}
