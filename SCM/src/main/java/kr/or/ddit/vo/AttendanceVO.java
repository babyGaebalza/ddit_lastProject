package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceVO implements Serializable {
	
	private String classNo;  //입력안받음
	
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", groups= {UpdateGroup.class})
	private String atdcDate; //수정할때만/ 입력할때는 sysdate로 검증 불필요 
	
	private String classlistNo; //해당 강의의 수강생에게 부여되는 번호 

	@NotBlank
	private String classStudent;  //수강생 학번 
	
	@NotNull
	private Integer atdcPoint;
	
	private String atdcNote;  //비고부분 
	
	//이름 뽑으려고 추가함. 
	private String memName; 
	
	
	
	
	//추가
	private String date;
	private String memMajor;
	private String memCollege;
	
}
