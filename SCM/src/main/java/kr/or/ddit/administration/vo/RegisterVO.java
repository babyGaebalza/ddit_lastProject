package kr.or.ddit.administration.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;

@Data
public class RegisterVO {

	private String regNo;
	private String regStudent;
	
	@NotBlank(message="변경된 학적을 입력해주세요.", groups= {InsertGroup.class} )
	private String regCode;
	
	private String regCngdate;
	
	private String regLeavedate;
	
	@NotBlank(message="변경사유를 입력해주세요.", groups= {InsertGroup.class} )
	private String regReason;
	
	private String regWriter;
	private String regWdate;
	private String regFwriter;
	private String regFdate;
	private String regState;
}
