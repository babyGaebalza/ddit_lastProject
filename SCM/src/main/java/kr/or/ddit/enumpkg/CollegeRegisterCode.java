package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollegeRegisterCode {
	RC01("재학")
	, RC02("휴학")
	, RC03("군휴학")
	, RC04("재입학")
	, RC05("편학")
	, RC06("졸업유예")
	, RC07("졸업");
	
	private String collegeRegisterName;
}
