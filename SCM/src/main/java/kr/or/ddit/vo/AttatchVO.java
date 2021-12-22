package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of= {"attNo"})
@NoArgsConstructor
public class AttatchVO {
	
	private MultipartFile boFile;
	
	public AttatchVO(MultipartFile boFile) {
		super();
		this.boFile = boFile;
		this.attOgfilename = boFile.getOriginalFilename();
		this.attMime = boFile.getContentType();
		this.attFilesize = boFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
		this.attSavefilename = UUID.randomUUID().toString();
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(boFile!=null) {
			boFile.transferTo(new File(saveFolder, attSavefilename));
		}
	}
		
	private String attNo;
	private Integer attCount;
	private String boardNo;
	private String taskNo;
	private String memNo;
	private String mailNo;
	private String attOgfilename;
	private String attSavefilename;
	private String attMime;
	private Long attFilesize;
	private String attFancysize;
	
}
