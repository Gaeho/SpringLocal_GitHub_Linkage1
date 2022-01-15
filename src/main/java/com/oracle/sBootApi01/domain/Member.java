package com.oracle.sBootApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter//게터
@Setter//세터
@Entity//테이블화
@SequenceGenerator(name="member5_seq_gen", //자바에서 사용되는 이름 자바 내에서 부를 땐 이 이름 쓴다.
					sequenceName = "member5_seq_generator", //db에 들어갈 이름
					initialValue = 1, //시퀀스 시작 값
					allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
		)
@Table(name = "member5")//내가 이것을 엔티티를 만들 때는 이 이름으로 만들겠다.
public class Member {

	@Id //이것을 pk로 설정하겠다.
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member5_seq_gen")
	@Column(name = "member_id")
	private Long id;
	@Column(name="username",length = 100) //default=255byte
	private String name;
	
	//회원 1에 여러 맴버가 포함되는 형태이다. 1:다 형태임
	//fk를 갖고 있는 테이블에 many to one을 걸어둔다. 나(현재클래스 member)는 many이고 one인 해당 테이블이 있다
	@ManyToOne
	@JoinColumn(name = "team_id") 
	private Team team;
	
	//수정페이지인 memberModify에서 값을 저장하려면 도메인에 getter&setter가 있어야 함.
	//@Transient는 도메인을 만드는 목적이 아니라는 것을 알리는 어노테이션이다. 
	//Team ID
	@Transient
	private Long teamid;
	
	@Transient//테이블에 임시 저장하는 역할을 하되, table column으로 만들지 않는다.
	private String teamname;
	//카디널리티, 옵셔널리티 개념을 잡을 수 없으면 혼자서 할 수 없겠다.
}
