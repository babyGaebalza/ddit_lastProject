package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdmissionCode {
	신규입학("AC01"),
	편입입학("AC02");
	
	private String Admission;
}
