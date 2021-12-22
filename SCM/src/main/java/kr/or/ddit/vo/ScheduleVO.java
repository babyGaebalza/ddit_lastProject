package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="schNo")
public class ScheduleVO implements Serializable{
	private String schYear;
	private String schDecode;
	private String schNo;
	private String schMem;
	private String schCode;
	private String schDelete;
	private String schCont;
	private String schSdate;
	private String schEdate;
	private String schLocation;
	private String schManager;
	private String schState;
	
	private Integer semesterPercentage; // 학기 진행률
}
