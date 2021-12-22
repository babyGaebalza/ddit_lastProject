package kr.or.ddit.vo;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class PayVO {

	private String payNo;
	private String payMem;
	private Integer payTpay;
	private Integer payInsurance;
	private Integer payTax;
	private Integer payFpay;
	private String payBank;
	private String payAccount;
	private String payState;
	private String payDate;
	private String payFwriter;
	private String payReason;
	
	
	public String getTPayDisplay() {		
		return new DecimalFormat("#,###").format(payTpay);
	};
	
	public String getpayInsuranceDisplay() {		
		return new DecimalFormat("#,###").format(payInsurance);
	};
	
	public String getpayTaxDisplay() {		
		return new DecimalFormat("#,###").format(payTax);
	};
	
	public String getpayFpayDisplay() {		
		return new DecimalFormat("#,###").format(payFpay);
	};
	

	
	
}
