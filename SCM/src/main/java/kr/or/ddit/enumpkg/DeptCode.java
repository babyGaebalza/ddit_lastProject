package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeptCode {
	교무처_학사관리과("D1C1"),
	교무처_교무과("D1C2"),
	
	학생처_학생지원과("D2C1"),
	학생처_취업지원과("D2C2"),
	
	입학처_입학관리과("D3C1"),
	
	사무국_총무과("D4C1"),
	사무국_재무과("D4C2"),
	사무국_시설과("D4C3");
	
	private String label;
}
