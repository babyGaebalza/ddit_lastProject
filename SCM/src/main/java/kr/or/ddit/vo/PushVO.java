package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PushVO {

	private String pushNo;
	private String pushMem;
	private String pushCode;
	private String pushTitle;
	private String pushCont;
	private String pushUrl;
	private String pushDate;
	
	
	private String major;
	private List<MemberVO> whoList;
}
