package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="docuNo")
public class DocumentVO {
	//private int rnum;

	////insert랑 update만 extends default.class함 kr.
	//@NotNull : Null만 허용하지 않음   / "" 이나 " " 은 허용
	//@NotEmpty : null 과 "" 허용하지 않음 / " " 은 혀용 
	//@NotBlank :  null "" " " 모두 허용하지 않음
	private String docuNo;
	//신청자
	@NotBlank
	private String docuReq;
	//결재인1
	private String docuAp1;
	//결재인1 결재여부
	private String docuAp1yn;	
	//결재인2
	private String docuAp2;
	//결재인2 결재여부
	private String docuAp2yn;	
	//신청일시 
	private String docuReqdate;
	//최종결재여부 
	private String docuState;
	//최종결재자 
	@NotBlank
	private String docuApf;
	//최종결재일시  
	private String docuFdate;
	private String docuCancle;
	//서류종류
	private String docuCode;
	//제목
	private String docuFilename;
	//내용
	private String docuCont;
	//필요결재횟수 디폴트 = 2(기안자, 최종결재자) 
	private int docuReqcnt;
	//누적결재횟수 디폴트 = 1(기안자 본인)
	private int docuReqnow; 

	private List<MemberVO>  signMemberList; //has a 관계  	//prod와 buyer 1:1관계 

	private MemberVO member1;
	private CategoryVO category1;
	private MajorVO major1;
	
	public void setMemName(String memName) {
		this.docuReq = memName; 
	}
	
	//학생이 신청자일경우 학번과 이름 둘다 출력해야 해서 추가 
	private String studentName; 
	//트랙이름 추가 
	private String trackName;  
	//트랙번호 추가 
	private String trackNo;
	
	public void setCateName1(String cateName1) {
		this.docuCode = cateName1;	
	}

	public void setDocuContent(ClassVO myClass) {
	  StringBuffer sb = new StringBuffer();
	  sb.append("강의구분 :" + myClass.getClassCode());
	  sb.append("대학구분 :" + myClass.getCollegeCode());
	  sb.append("학과코드 :" + myClass.getMajorCode());
	  sb.append("강의실코드 :" + myClass.getFacNo());
	  sb.append("신규개설여부 :" + myClass.getClassExtend());
	  sb.append("강의명 :" + myClass.getClassName());
	  sb.append("학점 :" + myClass.getClassPoint());
	  sb.append("강의정원 :" + myClass.getClassMax());
	  sb.append("대면여부 :" + myClass.getClassOn());
	  sb.append("분반 :" + myClass.getClassDivide());
	  sb.append("분반 :" + myClass.getClassSemester());
	  sb.append("분반 :" + myClass.getClassTime());
	  this.docuCont = sb.toString();
	}
	
	private TrackVO track;
}
