package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="trackNo")
public class TrackVO {
	
	private String trackNo;
	private String majorCode;
	private String majorName;
	private String trackName;
	private String trackDelete;
	private String trackDate;
	
	private CategoryVO category1;
	private TrackListVO trackList1;
	private ClassVO class1;
	private MajorVO major1;
	
	private List<ClassVO> trackList;
}
