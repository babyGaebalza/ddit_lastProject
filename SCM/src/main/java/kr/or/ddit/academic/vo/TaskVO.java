package kr.or.ddit.academic.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.ReportInsertGroup;
import kr.or.ddit.validate.groups.ReportScoreMakeGroup;
import kr.or.ddit.vo.AttatchVO;
import lombok.Data;

@Data
public class TaskVO implements Serializable{
	@NotBlank(message="과제를 선택해주세요.", groups = {ReportScoreMakeGroup.class})
	private String taskNo;
	private String memId;
	private String boardNo;
	private String taskDate;
	@NotBlank
	private String taskCont;
	
	@Min(0)
	@Max(10)
	private String taskScore;
	
	private MultipartFile[] repFiles;
	public void setRepFiles(MultipartFile[] repFiles) {
		if(repFiles==null) return;
		this.repFiles = repFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile tmp : repFiles) {
			if(tmp.isEmpty()) continue;
			attatchList.add(new AttatchVO(tmp));
		}
	}
	
	private List<AttatchVO> attatchList;
	
	private String memName;
	
	private String[] delAttNos;
	private String[] delAttOgFileNames;
	@NotBlank
	private String classNo;
}
