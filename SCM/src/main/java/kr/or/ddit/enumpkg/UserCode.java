package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum UserCode {
	US01("/admin/main.do", "교직원")
	, US02("/professor/main.do", "교수")
	, US03("/main/assistantMain.do", "조교")
	, US04("/student/main.do", "학생");
	
	private String viewURL;
	private String userCodeName;
	
	private UserCode(String viewURL, String userCodeName) {
		this.viewURL = viewURL;
		this.userCodeName = userCodeName;
	}
}
