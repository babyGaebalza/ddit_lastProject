package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ClassKindCode {
	CL01("전공선택", 36), 
	CL02("전공필수", 18), 
	CL03("교양선택", 30), 
	CL04("교양필수", 12);
	
	private String categoryName;
	private int codePoint;
	
	private ClassKindCode(String classCodeName, int codePoint) {
		this.categoryName = classCodeName;
		this.codePoint = codePoint;
	}
}
