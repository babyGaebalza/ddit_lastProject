package kr.or.ddit.vo;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class PayTableVO {

	private String paytableMem;
	private int paytablePay;
	private String paytableDate;
	private String paytableReason;
	private String paytableNo;
	
	public String getPaytablePayDisplay() {		
		return new DecimalFormat("#,###").format(paytablePay);
	}
	
	public void setPaytablePayDisplay(String paytablePayDisplay) {
		if(paytablePayDisplay != null && !paytablePayDisplay.trim().equals("")) {
			paytablePay = Integer.parseInt(paytablePayDisplay.replaceAll(",", ""));
		}
	}
}
