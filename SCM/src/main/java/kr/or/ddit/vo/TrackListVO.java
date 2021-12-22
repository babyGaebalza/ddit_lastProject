package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"classNo"})
public class TrackListVO {
	private String trackNo;
	private String classNo;
}
