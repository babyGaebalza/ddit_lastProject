package kr.or.ddit.administration.vo;

import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="tuiNo")
public class TuitionVO {
	private String tuiNo;
	private String tuiMem;
	private String tuiPayment;
	private String tuiBank;
	private String tuiAccount;
	private String tuiRebank;
	private String tuiReaccount;
	private String tuiState;
	
	private MemberVO member1;
	private MajorVO major1;
}
