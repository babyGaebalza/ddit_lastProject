package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="bussNo")
public class BusinessVO implements Serializable {
	
	//업체 관리 번호
	private String bussNo;
	
	//업체명
	private String bussName;
	
	//업체 업무
	private String bussJob;
	
	//계약시작일
	private String bussSdate;
	
	//계약종료일
	private String bussEdate;
	
}
