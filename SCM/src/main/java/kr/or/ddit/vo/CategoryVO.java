package kr.or.ddit.vo;

import kr.or.ddit.administration.vo.CertiVO;
import lombok.Data;

@Data
public class CategoryVO {

	private String cateCode;
	private String cateGroup;
	private String cateName1;
	private String cateName2;
	private String cateDate1;
	private String cateDate2;
	private String cateDelete;
	private Integer cateValue1;
	
	private CertiVO certi1;
}
