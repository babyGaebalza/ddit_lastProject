package kr.or.ddit.vo;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
//@EqualsAndHashCode(of="facNo")
public class FacilityVO implements Serializable {
	
	//시설구분번호
	private String facNo;
	
	//시설구분코드
	private String facCode;
	
	//수용인원수
	@NotNull
	private Integer facNumber;
	
	//시설명
	@NotBlank
	private String facName;
	
	//(강의실)
	private String facDate;
	
	//(강의실)
	private String facPaystate;
	
	//시설사용비용
	private int facPay;
	
	//예약가능여부
	private String facResult;
	
	//단과대 코드(강의실)
	private String collegeCode;
	
	//학과구분코드(강의실)
	private String majorCode;
	
	
	public String getFacPayDisplay() {
		return new DecimalFormat("#,###").format(facPay);
	}

	public void setFacPayDisplay(String facPayDisplay) {
		if(facPayDisplay != null && !facPayDisplay.trim().equals("")) {
			facPay = Integer.parseInt(facPayDisplay.replaceAll(",", ""));
		}
	}
	
	
	
	
}
