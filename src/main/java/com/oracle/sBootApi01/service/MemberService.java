package com.oracle.sBootApi01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.sBootApi01.domain.Member;
import com.oracle.sBootApi01.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	
	public MemberService (MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}
	
	//전체회원 조회
	public List<Member> getListAllMember(){
		List<Member> listMember = memberRepository.findAll(); // 레포지토리에서 리스트화 한 객체를 리턴으로 받았다.
		System.out.println("MemberService getListAllMember 사이즈"+listMember.size());
		return listMember; //리스트 객체를 리턴하면서 트랜잭션 및 커밋 된다.
	}
}
