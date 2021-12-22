package kr.or.ddit.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="resNo")
@ToString
public class ReservationVO {

	private String resNo;
	private String resMem;
	private String facNo;
	private String libNo;
	private String resSdate;
	private String resEdate;
	private String resWdate;
	private String resRdate;
	private String resHistory;
	private String resRebank;
	private String resReaccount;
	private Integer resCount;
	private String resReason;
	private Date resClassA;
	private Date resClassB;
	private Integer resTimeClass;
	
}
