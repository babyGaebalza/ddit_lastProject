package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.ReportInsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boardNo")
@ToString(exclude= {"attatchList"})
public class BoardVO {
	private String boardNo;
	private String memNo;
	private String classNo;
	private String boardCode;
	
	@NotBlank(message="제목을 입력해주세요.", groups= {InsertGroup.class, UpdateGroup.class, ReportInsertGroup.class})
	private String boardTitle;
	
	@NotBlank(message="내용을 입력해주세요.", groups= {InsertGroup.class, UpdateGroup.class, ReportInsertGroup.class})
	private String boardContent;
	@NotBlank(message="기간을 설정해주세요.", groups= {ReportInsertGroup.class}) //, ReportInsertGroup.class})
	private String boardDeadline;
	private Integer boardHits;
	private String boardDate;
	private String boardDelete;
	private String boardFwriter;
	private String boardFdate;
	private String repParent;
	private String repPass;
	private String majorCode;
	
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile tmp : boFiles) {
			if(tmp.isEmpty()) continue;
			attatchList.add(new AttatchVO(tmp));
		}
	}
	
	private List<AttatchVO> attatchList;
	
	private int startAttNo;

	private int[] delAttNos;
	
	private int repCnt;
	
	private int atchCnt;
		
	public void setMemName(String memName) {
	    	this.memNo = memName; 
	}

	
	private String submitTaskyn;
	
	public void setSubmitTaskyn(int submitTaskyn) {
		if(submitTaskyn > 0) {
			this.submitTaskyn = "Y";
		}else {
			this.submitTaskyn = "N";
		}
	}
	
	private String majorName;
	
	private String taskSubmitCount;
}
