package kr.or.ddit.academic.student.mainpage.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ScoreExcelDownload extends AbstractView {
	
	private XSSFWorkbook xssfWb; 
	private XSSFSheet xssfSheet; 
	private XSSFRow xssfRow; 
	private XSSFCell xssfCell;
	private int rownum;
	private final String fileNamePattern = "성적 정보(%s %s).xlsx"; 
	
	// 폰트 옵션 설정용 메서드
	private XSSFFont getFont(int fontPoint, boolean bold, String hexColorCode) {
		
		XSSFFont headerFont = xssfWb.createFont();
		headerFont.setFontName("맑은 고딕"); // 폰트 스타일 
		headerFont.setFontHeightInPoints((short)fontPoint); // 폰트 크기
		headerFont.setBold(bold); // Bold 설정
		headerFont.setColor(new XSSFColor(Color.decode(hexColorCode))); 
		
		return headerFont;
	}
	
	// 타이틀 셀 스타일 설정용 메서드
	private CellStyle getTitleCellStyle() {
		
		CellStyle titleCellStyle = xssfWb.createCellStyle();
		titleCellStyle.setFont(getFont(20, true, "#000000")); // 헤더폰트 세팅(14포인트, 볼드, 검정)
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 헤더셀 가운데 정렬 설정
		
		return titleCellStyle;
	}
	
	// 헤더 셀 스타일 설정용 메서드
	private CellStyle getHeaderCellStyle() {
		
		CellStyle headerCellStyle = xssfWb.createCellStyle();
		headerCellStyle.setFont(getFont(14, true, "#000000")); // 헤더폰트 세팅(14포인트, 볼드, 검정)
		headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 헤더셀 가운데 정렬 설정
		
		return headerCellStyle;
	}
	
	// 개별성적 시트 열 너비 조정 메서드
	private void setSheetColumnsWidth(Map<Integer, Integer> columnWidthSettingMap) {
		for(Entry<Integer, Integer> eachSetting : columnWidthSettingMap.entrySet()) {
			xssfSheet.setColumnWidth(eachSetting.getKey(), eachSetting.getValue());
		}
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> scoreMap = (Map<String, Object>) model.get("scoreMap");
		List<ClassListVO> classList = (List<ClassListVO>)scoreMap.get("classList");
		
		// 전역변수 초기화
		xssfWb = null; 
		xssfSheet = null; 
		xssfRow = null; 
		xssfCell = null; 
		rownum = 0;
		MemberVO authMember = ((MemberVOWrapper)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthMember();
		String fileName = URLEncoder.encode(String.format(fileNamePattern, authMember.getMemName(), authMember.getMemId()), "UTF-8").replaceAll("\\+", " ");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attatchment;filename=\"" + fileName + "\"");
		try (
			OutputStream os = response.getOutputStream();
		){ 
			xssfWb = new XSSFWorkbook(); // 워크북 생성
			
			// 개별 성적 시트&컨텐츠 생성
			makeEachClassScoreSheet(classList);
			
			// 누계 성적 정보 시트&컨텐츠 생성
			makeTotalScoreSheet(scoreMap);
			
			xssfWb.write(os); 
		}catch(Exception e){ 
			
		}
	}
	
	// 개별 성적 시트&컨텐츠 생성 메서드
	private void makeEachClassScoreSheet(List<ClassListVO> classList) {
		
		// 워크시트 생성
		xssfSheet = xssfWb.createSheet("개별 성적 정보");
		
		// 워크시트 열 너비 조정 맵
		Map<Integer, Integer> columnWidthSettingMap = new HashMap<>();
		columnWidthSettingMap.put(3, 4000);
		columnWidthSettingMap.put(4, 8000);
		columnWidthSettingMap.put(5, 4000);
		setSheetColumnsWidth(columnWidthSettingMap); // 워크시트 열 너비 조정
		
		// 개별 성적 헤더 생성
		CellStyle headerCellStyle = getHeaderCellStyle(); // 워크북에서 사용할 헤더셀 스타일
		String[] headerArray = {"No", "연도", "학기", "강의번호", "강의명", "이수구분", "학점", "평점", "등급", "백분위"};
		
		xssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, headerArray.length - 1));
		CellStyle titleCellStyle = getTitleCellStyle();
		xssfRow = xssfSheet.createRow(rownum++); 
		xssfCell = xssfRow.createCell(0);
		xssfCell.setCellStyle(titleCellStyle);
		xssfCell.setCellValue("강의별 성적 정보");
		
		xssfSheet.createRow(rownum++);
		xssfRow = xssfSheet.createRow(rownum++); 
		for(int i = 0; i < headerArray.length; i++) {
			xssfCell = xssfRow.createCell(i);
			xssfCell.setCellStyle(headerCellStyle);
			xssfCell.setCellValue(headerArray[i]);
		}
		
		String preYear = null;
		String preSemester = null;
		int noIdx = 1;
		for(ClassListVO eachClass : classList) {
			int columnIndex = 0;
			
			if((preYear != null && preSemester != null) && !(preYear.equals(eachClass.getClassYear()) && preSemester.equals(eachClass.getClassSemester()))) {
				xssfRow = xssfSheet.createRow(rownum++);
			}
			preYear = eachClass.getClassYear();
			preSemester = eachClass.getClassSemester();
			
			
			xssfRow = xssfSheet.createRow(rownum++); // 동적 행 생성
			// No
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(noIdx++);
			// 연도
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassYear());
			// 학기
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassSemester());
			// 강의번호
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassNo());
			// 강의명
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassInfo().getClassName());
			// 이수구분
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassCodeName());
			// 학점
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassInfo().getClassPoint());
			// 평점
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassGradePoint());
			// 등급
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassGrade());
			// 백분위
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(eachClass.getClassScore());
		}
	}
	
	// 누계 성적 정보 시트&컨텐츠 생성 메서드
	private void makeTotalScoreSheet(Map<String, Object> scoreMap) {
		List<Map<String, Object>> semesterScoreList = (List<Map<String,Object>>) scoreMap.get("semesterScoreList");
		Map<String, Integer> pointByCodeMap = (Map<String, Integer>) scoreMap.get("pointByCodeMap");
		Map<String, Object> totalScoreMap = (Map<String, Object>) scoreMap.get("totalScoreMap");
		
		// 전역변수 초기화
		rownum = 0;
		// 열 인덱스 생성
		int columnIndex = 0;
		
		// 워크시트 생성
		xssfSheet = xssfWb.createSheet("누계 성적 정보");
		// 워크시트 열 너비 조정
		Map<Integer, Integer> columnWidthSettingMap = new HashMap<>();
		columnWidthSettingMap.put(1, 4000);
		columnWidthSettingMap.put(2, 4000);
		columnWidthSettingMap.put(3, 4000);
		columnWidthSettingMap.put(4, 4000);
		columnWidthSettingMap.put(5, 4000);
		columnWidthSettingMap.put(6, 4000);
		setSheetColumnsWidth(columnWidthSettingMap);
		CellStyle titleCellStyle = getTitleCellStyle();
		CellStyle headerCellStyle = getHeaderCellStyle();
		
		// 학기별 성적 정보 타이틀 생성
		String[] semesterHeaderArray = {"No", "연도", "학기", "신청학점", "취득학점", "평점평균", "백분위평균"}; // 학기별 성적 헤더
		xssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, semesterHeaderArray.length - 1));
		xssfRow = xssfSheet.createRow(rownum++);
		xssfCell = xssfRow.createCell(columnIndex);
		xssfCell.setCellStyle(titleCellStyle);
		xssfCell.setCellValue("학기별 성적 정보");
		
		// 학기별 성적 정보 헤더 생성
		xssfSheet.createRow(rownum++);
		xssfRow = xssfSheet.createRow(rownum++);
		for(int i = 0; i < semesterHeaderArray.length; i++) {
			xssfCell = xssfRow.createCell(i);
			xssfCell.setCellStyle(headerCellStyle);
			xssfCell.setCellValue(semesterHeaderArray[i]);
		}
		
		// 학기별 성적 정보 컨텐츠 생성
		int noIdx = 1;
		for(Map<String, Object> semeseterScore : semesterScoreList) {
			log.info(semeseterScore.toString());
			
			xssfRow = xssfSheet.createRow(rownum++);
			
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(noIdx++); 
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue((String)semeseterScore.get("year"));
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue((String)semeseterScore.get("semester"));
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(String.valueOf(semeseterScore.get("registerPoint")));
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(String.valueOf(semeseterScore.get("gettedPoint")));
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(String.valueOf(semeseterScore.get("avgGradePoint")));
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(String.valueOf(semeseterScore.get("avgScore")));
			
			columnIndex = 0;
		}
		
		xssfRow = xssfSheet.createRow(rownum++); // 한칸 공백
		
		// 이수구분별 학점 취득정보 타이틀 생성
		String[] classCodeHeaderArray = {"No", "이수구분", "이수학점"}; // 이수구분별 학점 취득정보 헤더
		xssfSheet.addMergedRegion(new CellRangeAddress(rownum, rownum + 1, 0, classCodeHeaderArray.length - 1));
		xssfRow = xssfSheet.createRow(rownum++);
		xssfCell = xssfRow.createCell(columnIndex);
		xssfCell.setCellStyle(titleCellStyle);
		xssfCell.setCellValue("이수구분별 학점 취득정보");
		
		// 이수구분별 성적 정보 헤더 생성
		xssfSheet.createRow(rownum++);
		xssfRow = xssfSheet.createRow(rownum++);
		for(int i = 0; i < classCodeHeaderArray.length; i++) {
			xssfCell = xssfRow.createCell(i);
			xssfCell.setCellStyle(headerCellStyle);
			xssfCell.setCellValue(classCodeHeaderArray[i]);
		}
		
		// 학기별 성적 정보 컨텐츠 생성
		noIdx = 1;
		for(Entry<String, Integer> pointByCode : pointByCodeMap.entrySet()) {
			
			xssfRow = xssfSheet.createRow(rownum++);
			
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(noIdx++); 
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(pointByCode.getKey());
			xssfCell = xssfRow.createCell(columnIndex++);
			xssfCell.setCellValue(String.valueOf(pointByCode.getValue()));
			
			columnIndex = 0;
		}
		
		xssfRow = xssfSheet.createRow(rownum++); // 한칸 공백
		
		// 총 누적 성적 타이틀 생성
		String[] totalScoreHeaderArray = {"이수학점", "평점평균", "백분위평균"}; // 이수구분별 학점 취득정보 헤더
		xssfSheet.addMergedRegion(new CellRangeAddress(rownum, rownum + 1, 0, totalScoreHeaderArray.length - 1));
		xssfRow = xssfSheet.createRow(rownum++);
		xssfCell = xssfRow.createCell(columnIndex);
		xssfCell.setCellStyle(titleCellStyle);
		xssfCell.setCellValue("총 누적 성적");
		
		// 총 누적 성적 헤더 생성
		xssfSheet.createRow(rownum++);
		xssfRow = xssfSheet.createRow(rownum++);
		for(int i = 0; i < totalScoreHeaderArray.length; i++) {
			xssfCell = xssfRow.createCell(i);
			xssfCell.setCellStyle(headerCellStyle);
			xssfCell.setCellValue(totalScoreHeaderArray[i]);
		}
		
		// 총 누적 성적 컨텐츠 생성
		noIdx = 1;
		xssfRow = xssfSheet.createRow(rownum++);
		
		xssfCell = xssfRow.createCell(0);
		xssfCell.setCellValue(String.valueOf(totalScoreMap.get("totalPoint"))); 
		xssfCell = xssfRow.createCell(1);
		xssfCell.setCellValue(String.valueOf(totalScoreMap.get("totalAvgGradePoint")));
		xssfCell = xssfRow.createCell(2);
		xssfCell.setCellValue(String.valueOf(totalScoreMap.get("totalAvgScore")));
	}

}
