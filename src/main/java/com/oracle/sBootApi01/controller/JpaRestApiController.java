package com.oracle.sBootApi01.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.sBootApi01.domain.Member;
import com.oracle.sBootApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RestController//Controller + ResponseBody(나를 요청한 쪽의 몸통 안으로)
// 1. API 2.Ajax
@RequiredArgsConstructor//지정된 속성들에 대해서 생성자를 만들어 줌
public class JpaRestApiController {

	private final MemberService memberService;
//	public JpaRestApiController (MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	// Bad API - 보안이 매우 취약 함  url만 알고 있으면 정보뿐만 아니라 layout까지 가져갈 수 있다.
	@GetMapping("/restApi/v1/members")
	public List<Member> membersV1(){
		System.out.println("JpaRestApiController의 /restApi/v1/members 시작되었습니다.");
		return memberService.getListAllMember();//아래 주석을 이 한 줄로 해결함  
		//List<Member> listMemberV1 = memberService.getListAllMember(); return listMemberV1;
		// *중요* ResponseBody속성이 있기 때문에 객체를 return하면 Json으로 컨버트해서 날린다.
		// Json 뷰어 사이트에 들어가서 Json으로 응답된 내용을 돌려서 확인한다.
	}
	
	// Good API - 필요한 데이터만 선택해서 보내주기
	@GetMapping("/restApi/v2/members")
	public Result membersV2(){
		System.out.println("JpaRestApiController의 /restApi/v2/members 시작되었습니다123.");
		List<Member> findMembers = memberService.getListAllMember(); 
		
										//  자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
		
		
		
		//member 객체에서 name에 해당하는 정보만 홀랑 갖다가 새로운 객체로 만든다.
											//findMembers 안에 1.김진구 1 2.진구 2 3.여진구3
	List<MemberRtnDto> memberCollect = findMembers.stream().map(m->new MemberRtnDto(m.getName())) //for개념이다  MemberRtnDto객체를 순차적으로 만든다.
																.collect(Collectors.toList());// 여러 생성자로 만들어진 인스턴트를 모은다.  
																
//		List<MemberRtnDto> memberCollect = null; 
//		  for (Member member : findMembers) {
//			  MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName());
//			  memberCollect.add(memberRtnDto);
//		  } //람다가 어려우면 이런식으로 for문으로 담아도 된다.

		
		return new Result(memberCollect);//라인 49에서 만들어진 것을 list형으로 조합해서 리턴한다.
		// return new Result(memberCollect.size(),memberCollect); 2개의 파라메터를 보낼 수 도 있다.
		//라인 60 -> Result result = new Result(memberCollect); return result; 와 같다.
	}
	
	//내부 클래스(클래스안의 클래스)를 만든다. - 다른곳에 공유하지 않고 해당 컨트롤러 안에서 종결하기 위해 내부 클래스 사용
	@Data // 게터&세터/toStirng/requiredArgsConstructor 등 다 합친 범용 어노테이션
	@AllArgsConstructor // 생성자를 자동으로 만들어주는 어노테이션
	class MemberRtnDto { //여기에서 보여줄 요소들을 필드로 지정해서 인스턴스로 만들어낸다.
		private String name; 
//		public MemberRtnDto(String name) {
//			this.name=name;
//		}
	
	}
	
	@Data	
	@AllArgsConstructor // 생성자를 자동으로 만들어주는 어노테이션
	class Result<T>{ //memberCollect객체를 집어 넣으면 알아서 MemberRtnDto 타입의 객체로 만든다.
//		private int totCount;
		private T data;
//		public Result(T) {
//		this.data=data;
//	}
	}
	
}
