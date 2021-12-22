package kr.or.ddit.administration.off_acc.enrollmentmanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.administration.off_acc.enrollmentmanage.dao.EnrollmentManageDAO;
import kr.or.ddit.administration.off_acc.enrollmentmanage.service.EnrollmentManageService;
import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class EnrollmentManageController {
	@Inject
	private EnrollmentManageService service;
//	@Inject
//	private EnrollmentManageDAO dao;
	
	//학교 학생 리스트 출력/ 검색 -> 학과/납부여부
	@RequestMapping("/enrollmentManage/enrollmentManageList.do")
	public String enrollmentManageList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<TuitionVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveStudentList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/off_acc/enrollmentManage/EnrollmentManageList";
	}
	
	//등록 납부 수정
	@RequestMapping("/enrollmentManage/enrollmentManageUpdate.do")
	public String getEnrollmentManageUpdate() {
		return "administration/off_acc/enrollmentManage/EnrollmentManageEdit";
	}
	//등록 납부 수정
	@RequestMapping(value="/enrollmentManage/enrollmentManageUpdate.do", method=RequestMethod.POST)
	public String postEnrollmentManageUpdate() {
		return "administration/off_acc/enrollmentManage/EnrollmentManageList";
	}

	//신입생
	
	//신입생 리스트
//	@RequestMapping("/enrollmentManage/enrollmentNewStudentList.do")
//	public String enrollmentNewStudent(
//		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
//		, @ModelAttribute("searchVO") SearchVO searchVO
//		, Model model
//	) {
//		PagingVO<TuitionVO> pagingVO = new PagingVO<>();	
//		pagingVO.setCurrentPage(currentPage);
//		pagingVO.setSearchVO(searchVO);
//		
//		service.retrieveNewStudentList(pagingVO);
//		
//		model.addAttribute("pagingVO", pagingVO);
//		
//		return "administration/off_acc/enrollmentManage/EnrollmentNewStudentList";
//	}
	
	//신입생 insert(아이디, 비밀번호, 구분코드-학생, 이름, 학과-mem_major)
//	@RequestMapping("/enrollmentManage/enrollmentNewInsert.do")
//	public int getDepartmentManageInsert(
//		@ModelAttribute("member") MemberVO member
//	) {
//		for(int i = 0; i < 30; i++) {
//		//이름 랜덤 생성 후 배치
//		List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
//		        "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
//		        "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
//		        "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금");
//		 List<String> firstName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "다", "단", 
//		        "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박", "향",
//		        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
//		        "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
//		        "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
//		        "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔");
//		
//		 String name = lastName.get(0) + firstName.get(0) + firstName.get(1);
//		 
//		 member = new MemberVO();
//		 member.setMemName(name);
//		 
//		 dao.insertNewStudent(member);
//		}
//		return 0;
//	}
	
}
