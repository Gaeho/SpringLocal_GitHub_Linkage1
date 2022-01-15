package com.oracle.sBootApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(
		name = "team_seq_gen",// 시퀀스 이름.자바에서 쓰는 객체 이름이다. 
		sequenceName = "team_seq_generator",   //DB에서의 시퀀스 이름
		initialValue = 1,
		allocationSize = 1
		)

public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "team_seq_gen" //위에서 적었던 시퀀스의 이름
			)
	private Long team_id;
	@Column(name="teamname")
	private String name;
	
}
