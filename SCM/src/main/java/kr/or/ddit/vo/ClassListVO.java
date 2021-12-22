package kr.or.ddit.vo;

import javax.validation.constraints.Max;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="classNo")
public class ClassListVO {

	private String classCode;
	private String classlistNo;
	private String classNo;
	private String memNo;
	private String classDate;
	private String classSemester; // 정규학기
	private String classState;
	@Max(100)
	private Integer classMid;
	@Max(100)
	private Integer classFin;
	private Integer classScore;	// 백분위 점수
	private String classRetake;
	private String classCont;
	
	private String classGrade;			// 등급 A~F
	private double classGradePoint;		// 평점
	
	private ClassVO classInfo;	// 1:1
	
	private String classYear;	// 수강연도
	
	
	//점수계산을 위해  추가  
	private Integer atdcPointsum ;//출석누적원점수 
	private Integer atdcChangePointsum; //출석환산점수 
	private Integer avgTaskScore ;//과제평균원점수 
	private Integer avgTaskChangeScore;  //과제환산점수 
	private Integer classFinChange; //기말환산점ㅈ수 
	private Integer classMidChange; //중간환산점수
	//이름 뽑으려고 추가함 
	private String memName; 
	
	public String getClassCodeName() {
		String classCodeName = null;
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
		
		return classCodeName;
	}
	
	public void setClassScore(Integer classScore) {
		this.classScore = classScore;
		
		if(classScore >= 60) {
			this.classGradePoint = (double)(classScore - 54)/10;
			
			switch (classScore/10) {
			case 10:
				classGrade = "A+";
				this.classGradePoint = 4.5;
				break;
			case 9:
				classGrade = "A";
				break;
			case 8:
				classGrade = "B";
				break;
			case 7:
				classGrade = "C";
				break;
			case 6:
				classGrade = "D";
				break;
			
			}
			if(classScore%10 >= 5) {
				classGrade += "+";
			}
		}else {
			this.classGrade = "F";
		}
	}
	
	
}
