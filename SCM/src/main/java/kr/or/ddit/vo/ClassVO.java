package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of={"classNo"})
@ToString
public class ClassVO implements Serializable{

	//강의 관리 번호
	private String classNo;
	
	//사용자등록번호
	private String memNo;
	
	//시설구분번호
	private String facNo;
	
	//강의구분코드
	private String classCode;
	
	//단과대코드
	private String collegeCode;
	
	//강의실명
	private String classRoom;
	
	//강의명
	private String className;
	
	//강의시간
	private String classTime;
	
	//학점
	@Min(1)
	@Max(4)
	private Integer classPoint;
	
	//수강인원현황
	private Integer classPerson;
	
	//강의최대인원
	private Integer classMax;
	
	//대면강의여부
	private String classOn;
	
	//폐강여부
	private String classDelete;
	
	//강의관리번호
	private String classExtend;
	
	//학과구분코드
	private String majorCode;
	
	//학사과정
	private String classSemester;
	
	//분반
	private String classDivide;
	
	//등록일
	private String classDate;
	
	//출석점수 비율
	private Integer classAttpoint = 0 ;
	//과제점수비율
	private Integer classTaskpoint = 0 ;
	//중간고사 점수비율
	private Integer classMidpoint = 0 ;
	//기말고사 점수비율
	private Integer classFinpoint = 0;
	
	
	
	private String classProName;
	
	public String getClassCodeName() {
		String classCodeName = null;
		
		if(this.classCode != null) {
			switch (this.classCode) {
			case "CL01":
				classCodeName = "전공선택";
				break;
			case "CL02":
				classCodeName = "전공필수";
				break;
			case "CL03":
				classCodeName = "교양선택";
				break;
			case "CL04":
				classCodeName = "교양필수";
				break;
			case "CL05":
				classCodeName = "복수선택";
				break;
			case "CL06":
				classCodeName = "복수필수";
				break;
			case "CL07":
				classCodeName = "자유선택";
				break;
			case "CL08":
				classCodeName = "부전공";
				break;
			}
		}else {
			classCodeName = "";
		}
		
		return classCodeName;
	}
	
	private String trackSatisfied; // 학생기준 : 현재 강의가 트랙졸업요건 강의일 경우 수강완료인지 표현하는 변수 
}
