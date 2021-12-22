package kr.or.ddit.administration.univ_aca.majormanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface MajorManageDao {
	
	//총 학과 수
	public int selectTotalCount(PagingVO<MajorVO> pagingVO);
	
	//학과 리스트 조회
	public List<MajorVO> selectMajorList(PagingVO<MajorVO> pagingVO);
	
	//해당 학과 조회
	public MajorVO selectMajor(String majorName);
	
	//학과 수정
	public int updateMajor(MajorVO major);
	
	//학과 삭제여부
	public int deleteMajor(MajorVO major);
	
	//학과 신설
	public int insertMajor(MajorVO major);
	
	// 특정 단과대 학과리스트 조회
	public List<MajorVO> selectCollegeMajorList(String collegeCode);
}
