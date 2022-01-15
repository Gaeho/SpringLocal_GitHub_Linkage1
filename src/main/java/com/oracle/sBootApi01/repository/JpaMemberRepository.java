package com.oracle.sBootApi01.repository;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.sBootApi01.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;
	//생성자 오토와이어드
	public JpaMemberRepository(EntityManager em) {
		this.em=em;
	}
	
	@Override
	public List<Member> findAll() {//셀렉트문을 엔티티메니저 안에 클래스의 형태로 가지고 있겠다. 인스턴스가 여러개 이므로 리스트화 해서 갖고 있겠다. 
		List<Member> memberList = em.createQuery("select m from Member m",Member.class).getResultList();
		System.out.println("JpaMemberRepository의 findAll()실행  memberList.size->"+  memberList.size());
		return memberList; //그리고 리스트화 한 객체를 리턴하겠다.
	}

}
