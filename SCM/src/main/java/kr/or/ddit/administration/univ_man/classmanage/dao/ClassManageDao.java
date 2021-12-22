package kr.or.ddit.administration.univ_man.classmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ClassManageDao {
	
	//총 수업관리 수
	public int selectTotalCount(PagingVO<ClassVO> pagingVO);
	
	//수업신청 리스트 조회
	public List<ClassVO> selectClassList(PagingVO<ClassVO> pagingVO);
	
	//수업신청 조회
	public ClassVO selectClass(String classNo);
	
	//수업신청 정보 수정
	public int updateClass(ClassVO uclass);
	
	//수업 삭제여부
	public int deleteClass(ClassVO uclass);
	
	//수업 신설
	public int insertClass(ClassVO uclass);
	
}
