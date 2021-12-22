package kr.or.ddit.enumpkg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RankAdmin {
	처장("RC01"), 부장("RC02"), 사원("RC03");
	
	private String label;


};
