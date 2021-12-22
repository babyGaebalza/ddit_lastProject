package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ServiceResult {
	OK, FAILED, NOTEXIST, INVALIDPASSWORD, PKDUPLICATED, 
	NOTPERIOD, CARTMAX(8), NOTMAJOR, LIMITEXCEED, TIMEDUPLICATED, RETAKE
	, CREATESUCCESS, MODIFYSUCCESS;
	
	private int value;
}
