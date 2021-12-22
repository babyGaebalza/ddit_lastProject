package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ScholarVO implements Serializable {
	
	//장학관리 번호
	private String schNo;
	
	//장학금 수령인
	private String schMem;
	
	//장학구분 코드
	private String schCode;
	
	//장학금액
	private Integer schAmount;
	
	//상태
	private String schState;
	
	//비고
	private String schNote;
	
	//추천/신청사유
	private String schReason;
	
	//추천인
	private String schRec;
}
