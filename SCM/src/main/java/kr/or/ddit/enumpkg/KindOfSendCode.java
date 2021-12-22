package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindOfSendCode {
	FINDID("findId"), 
	RESETPASSWORD("resetPassword"), 
	UNLOCKACCOUNT("unlockAccount");
	
	private String camelCase;
}
