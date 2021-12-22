package kr.or.ddit.academic.professor.mainPage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.academic.vo.StuTimeTableVO;

@Repository
public interface ProftMainDAO {

	
	// 시간표 조회
		public List<StuTimeTableVO> selectTimeTable(String memId);
		
}


