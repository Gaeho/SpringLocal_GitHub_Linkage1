package com.oracle.sBootApi01.repository;

import java.util.List;

import com.oracle.sBootApi01.domain.Member;

public interface MemberRepository {

	List<Member> findAll();
	
}
