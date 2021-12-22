package kr.or.ddit.vo;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

@Data
public class MajorVO implements Serializable {
	
	//학과구분코드
	@NotBlank(message="학과코드를 입력해주세요", groups = {InsertGroup.class})
	private String majorCode;
	
	//전공명
	@NotBlank(message="전공명를 입력해주세요", groups = {InsertGroup.class})
	private String majorName;
	
	//단과대코드
	@NotBlank(message="단과대코드를 입력해주세요", groups = {InsertGroup.class})
	private String collegeCode;
	
	//단과대명
	private String collegeName;
	
	//작성자
	@NotBlank(message="작성자를 입력해주세요", groups = {InsertGroup.class})
	private String majorWriter;
	
	//작성일자
	private String majorDate;
	
	//등록금
	@NotNull(message="등록금을 입력해주세요", groups = {InsertGroup.class, UpdateGroup.class})
	private int majorPay;
	
	//학과정원
	@NotNull(message="학과정원 입력해주세요", groups = {InsertGroup.class, UpdateGroup.class})
	private int majorCount;
	
	//최종수정자
	@NotBlank(message="최종수정자를 입력해주세요", groups = {InsertGroup.class, UpdateGroup.class})
	private String majorFWriter;
	
	//최종변경일
	private String majorFDate;
	
	//삭제여부
	private String majorDelete;
	
	//졸업충족학점
	@NotNull(message="졸업충족학점 입력해주세요", groups = {InsertGroup.class, UpdateGroup.class})
	private int majorPoint;
	
	//학과번호
	private String majorNumber;
	
	
	
	public String getMajorPayDisplay() {
		return new DecimalFormat("#,###").format(majorPay);
	}

	public void setMajorPayDisplay(String majorPayDisplay) {
		if(majorPayDisplay != null && !majorPayDisplay.trim().equals("")) {
			majorPay = Integer.parseInt(majorPayDisplay.replaceAll(",", ""));
		}
	}
	
}
