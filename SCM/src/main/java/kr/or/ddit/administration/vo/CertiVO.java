package kr.or.ddit.administration.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="certiNo")
public class CertiVO {
	private String certiNo;
	
	@NotBlank(message="증명서를 선택해주세요.", groups= {InsertGroup.class, UpdateGroup.class})
	private String certiCode;
	private String certiReq;
	private String certiReqdate;
	private String certiIssdate;
	
	@NotBlank(message="발급사유를 입력해주세요.", groups= {InsertGroup.class})
	private String certiReason;
	
	@NotBlank(message="상태를 선택해주세요.", groups= {UpdateGroup.class})
	private String certiState;
	private Integer certiPrice;
	
	@NotBlank(message="수량을 입력해주세요.", groups= {InsertGroup.class})
	private Integer certiCount;
	private String certiIssue;
	
	private CategoryVO category1;
	private MemberVO member1;
}
