package kr.or.ddit.common.pdf.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.logging.log4j.core.impl.LocationAwareLogEventFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.administration.univ_aca.trackmanage.dao.TrackManageDAO;
import kr.or.ddit.common.document.service.DocuService;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.TrackVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class CreatePDF {
//	@Inject
//	private TrackManageDAO dao;
	@Inject
	private DocuService service; 
	
	private String logoURL = "/resources/images/symbol.jpg";
	//폰트
	private static PDType0Font fontGulim;
	private String person1 = " "; 
	private String person2 = " "; 
	private String person3 = " "; 
	private String person4 = " "; 
	
	private String docuNo =" "; 
	private String docuReqdate=" "; 
	private String docuCode =" ";
	private String docuFilename=" ";	
	private String allContent =" "; 
	private String signYN = "완료"; 
		

	
	//틀 그리는 부분 
	@RequestMapping(value = "/pdf.do", method = RequestMethod.GET)
	public void pdf(HttpServletResponse response, HttpServletRequest req, 
			@SessionAttribute(value="docuVO", required=true) DocumentVO docuVO
			) throws Exception {		
		//세션에 넣어진 데이터 > 변수로 옮기기 
		person1 = docuVO.getDocuReq(); 
		person4 = docuVO.getDocuApf(); 
		person2 = "   ";		
		person3 = "   ";	

	//	person3 = docuVO.getDocuAp2(); 
		docuNo = "2021-"+docuVO.getDocuNo(); 
		docuCode = docuVO.getDocuCode(); 
		
		String originDate = docuVO.getDocuReqdate(); 
		String originDateSplit[] = originDate.split(" "); 
		docuReqdate = originDateSplit[0]; 

		docuFilename = docuVO.getDocuFilename(); 
		
		
		allContent = docuVO.getDocuCont(); 	
		//신규트랙명임당#프로그래밍의원리[2051700054]  -  자료구조[2051700053]  -  전지전자회로[1051700052]  -  #qwe
		String content[] = allContent.split("#");
		String trackName =content[0];
		String reason =content[2];
		String classArr = content[1];
		String newClass[] = classArr.split("-");
		//newClass.length;
		
		File fontFile = new File("C:/Windows/Fonts/gulim.ttc");
		PDDocument doc = new PDDocument();

		////틀 
		final String logo = req.getServletContext().getRealPath(logoURL); 
		// 배경이미지 로드
		PDImageXObject pdImage = PDImageXObject.createFromFile(logo, doc);
		// ttc 파일 사용하기		
		fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
		PDPage blankPage = new PDPage(PDRectangle.A4);
		doc.addPage(blankPage);
		// 현재 페이지 설정 //1장으로만 설정이됨. 
		PDPage page = doc.getPage(0);
		// 컨텐츠 스트림 열기
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		// 배경 이미지  그리기                           
		contentStream.drawImage(pdImage, 250, 150, 100, 100);      // 350이었음            	        
		drawText("S  C  대  학", fontGulim, 16, 260, 800, contentStream);
		//내용,	폰트종류,  글씨크기, 왼쪽패딩, 아래패딩, 스트림 	

		//결재라인 표
		String[][] peopleline =  {
				{"담당", "확인", "확인", "전결"} 
				//{"담당",  "전결"} 
		};	     
		drawTable(page, contentStream, 750, 350, peopleline, 200, 20f);

		//결재자이름 표 
		String[][] people =  {
				{ person1, person2, person3, person4 }
		};	     
		drawTable(page, contentStream, 730, 350, people, 200, 20f);

		//결재여부 표 
		String[][] sign =  {
				{ signYN, "  ", "  ", signYN }
		};	     
		drawTable(page, contentStream, 710, 350, sign, 200, 20f);

		//문서캡션
		String[][] docDetailTitle = {
				{"문서번호"}, 
				{"작성일자"}, 
				{"문서종류"}	        		
		};
		drawTable(page, contentStream, 750, 70, docDetailTitle, 80, 20f);

		//문서캡션내용
		String[][] docDetail = {
				{docuNo}, 
				{docuReqdate}, 
				{docuCode}     		
		}; 
	    drawTable(page, contentStream, 750, 150, docDetail, 100, 20f);
        /**********/
		//제목
		String[][] title = {
				{"문서제목"}	        		
		}; 
		drawTable(page, contentStream, 650, 70, title, 160, 40f);

		//제목입력값
		String[][] titleInput = {
				{docuFilename}	        		
		}; 
		drawTable(page, contentStream, 650, 230, titleInput, 300, 40f);
        /**********/

        /**********/
		//트랙이름 표 만들기 
		String[][] track1 = {
				{"트 랙 명"}
		};
		drawTable(page, contentStream, 600, 70, track1,160,40f);  //전체 너비는 460으로 했었음
 
		String[][] track2 = {
				{trackName}
		};
		drawTable(page, contentStream, 600, 230, track2,300,40f); 
        /**********/

	    /**********/
			//사유
			String[][] reason1 = {
					{"트랙 추가 사유"}
			};
			drawTable(page, contentStream, 540, 70, reason1,160,40f);  //높이 간격 20
	 
			String[][] reason2 = {
					{reason}
			};
			drawTable(page, contentStream, 540, 230, reason2,300,40f); 
	    /**********/

	 /**********/
				//과목
				String[][] myClass1 = {
						{"해당과목"}
				};
				drawTable(page, contentStream, 480, 70, myClass1,160,80f);  //높이 간격 20
		 
				for(int i= 0 ; i < newClass.length -1;  i ++) {
					String[][]  myClass2 = {
						{newClass[i].trim()}
					};
					drawTable(page, contentStream, 480-20*i, 230, myClass2,300,20f); 
				}
				/**********/		
		
		
		
		
		
		
		//맨 밑
		String[][] table =  {
				{ " 주소 : 대전광역시 중구 대흥동 500-5   전화 : 042-821-5114"},
				{" 팩스 : 042-821-5110   이메일 : daepyo@smcuniv.ac.kr"}
		};        
		drawCenterTable(page, contentStream, 100, 100, table);

		contentStream.close();
		//}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("트랙추가신청서"+ docuNo, "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
	}
	
	
	@RequestMapping("/docuPdf.do")
	public void pdf(HttpServletResponse response, HttpServletRequest req, 
			@RequestParam("docuNo") String docuNo
			) throws Exception {		
		DocumentVO docuVO = service.retrieveOneDocu(docuNo);
		
		person1 = docuVO.getDocuReq(); 
		person4 = docuVO.getDocuApf(); 
		person2 = "   ";		
		person3 = "   ";	

	//	person3 = docuVO.getDocuAp2(); 
		docuNo = "2021-"+docuVO.getDocuNo(); 
		docuCode = docuVO.getDocuCode(); 
		
		String originDate = docuVO.getDocuReqdate(); 
		String originDateSplit[] = originDate.split(" "); 
		docuReqdate = originDateSplit[0]; 

		docuFilename = docuVO.getDocuFilename(); 
		
		
		allContent = docuVO.getDocuCont(); 	
		//신규트랙명임당#프로그래밍의원리[2051700054]  -  자료구조[2051700053]  -  전지전자회로[1051700052]  -  #qwe
		String content[] = allContent.split("#");
		String trackName =content[0];
		String reason =content[2];
		String classArr = content[1];
		String newClass[] = classArr.split("-");
		//newClass.length;
		
		File fontFile = new File("C:/Windows/Fonts/gulim.ttc");
		PDDocument doc = new PDDocument();

		////틀 
		final String logo = req.getServletContext().getRealPath(logoURL); 
		// 배경이미지 로드
		PDImageXObject pdImage = PDImageXObject.createFromFile(logo, doc);
		// ttc 파일 사용하기		
		fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
		PDPage blankPage = new PDPage(PDRectangle.A4);
		doc.addPage(blankPage);
		// 현재 페이지 설정 //1장으로만 설정이됨. 
		PDPage page = doc.getPage(0);
		// 컨텐츠 스트림 열기
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		// 배경 이미지  그리기                           
		contentStream.drawImage(pdImage, 250, 150, 100, 100);      // 350이었음            	        
		drawText("S  C  대  학", fontGulim, 16, 260, 800, contentStream);
		//내용,	폰트종류,  글씨크기, 왼쪽패딩, 아래패딩, 스트림 	

		//결재라인 표
		String[][] peopleline =  {
				{"담당", "확인", "확인", "전결"} 
				//{"담당",  "전결"} 
		};	     
		drawTable(page, contentStream, 750, 350, peopleline, 200, 20f);

		//결재자이름 표 
		String[][] people =  {
				{ person1, person2, person3, person4 }
		};	     
		drawTable(page, contentStream, 730, 350, people, 200, 20f);

		//결재여부 표 
		String[][] sign =  {
				{ signYN, "  ", "  ", signYN }
		};	     
		drawTable(page, contentStream, 710, 350, sign, 200, 20f);

		//문서캡션
		String[][] docDetailTitle = {
				{"문서번호"}, 
				{"작성일자"}, 
				{"문서종류"}	        		
		};
		drawTable(page, contentStream, 750, 70, docDetailTitle, 80, 20f);

		//문서캡션내용
		String[][] docDetail = {
				{docuNo}, 
				{docuReqdate}, 
				{docuCode}     		
		}; 
	    drawTable(page, contentStream, 750, 150, docDetail, 100, 20f);
        /**********/
		//제목
		String[][] title = {
				{"문서제목"}	        		
		}; 
		drawTable(page, contentStream, 650, 70, title, 160, 40f);

		//제목입력값
		String[][] titleInput = {
				{docuFilename}	        		
		}; 
		drawTable(page, contentStream, 650, 230, titleInput, 300, 40f);
        /**********/

        /**********/
		//트랙이름 표 만들기 
		String[][] track1 = {
				{"트 랙 명"}
		};
		drawTable(page, contentStream, 600, 70, track1,160,40f);  //전체 너비는 460으로 했었음
 
		String[][] track2 = {
				{trackName}
		};
		drawTable(page, contentStream, 600, 230, track2,300,40f); 
        /**********/

	    /**********/
			//사유
			String[][] reason1 = {
					{"트랙 추가 사유"}
			};
			drawTable(page, contentStream, 540, 70, reason1,160,40f);  //높이 간격 20
	 
			String[][] reason2 = {
					{reason}
			};
			drawTable(page, contentStream, 540, 230, reason2,300,40f); 
	    /**********/

	 /**********/
				//과목
				String[][] myClass1 = {
						{"해당과목"}
				};
				drawTable(page, contentStream, 480, 70, myClass1,160,80f);  //높이 간격 20
		 
				for(int i= 0 ; i < newClass.length -1;  i ++) {
					String[][]  myClass2 = {
						{newClass[i].trim()}
					};
					drawTable(page, contentStream, 480-20*i, 230, myClass2,300,20f); 
				}
				/**********/		
		
		
		
		
		
		
		//맨 밑
		String[][] table =  {
				{ " 주소 : 대전광역시 중구 대흥동 500-5   전화 : 042-821-5114"},
				{" 팩스 : 042-821-5110   이메일 : daepyo@smcuniv.ac.kr"}
		};        
		drawCenterTable(page, contentStream, 100, 100, table);

		contentStream.close();
		//}

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("트랙추가신청서"+ docuNo, "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
	}
	
	//아래는 디자인틀임. 수정 No~
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 글씨를 쓴다.
	 * @param 	내용, 폰트종류, 글씨크기, 왼쪽패딩, 아래패딩, 스트림 	
	 */
	private void drawText(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
        contentStream.beginText(); 
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(left, bottom);
        contentStream.showText(text);
        contentStream.endText();
	}
	
	/**
	 * 라인을 그린다.
	 * @param x축시작, y축시작, x축끝, y축끝 
	 */
	private void drawLine(PDPageContentStream contentStream, float xStart, float yStart, float xEnd, float yEnd) throws Exception{
			contentStream.moveTo(xStart,yStart);
			contentStream.lineTo(xEnd,yEnd);
			contentStream.stroke();			
	}
	
	/**
	 * 너비 지정 테이블 그리기 
	 *@param PDPage, PDPageContentStream, 높이(바닥0~), 왼쪽마진, 내용, 테이블너비, 셀 높이
	 * 주의 : 파라미터로 테이블 너비를 직접 지정해서 넘겨줘야 함. 가운데 정렬 아님 
	 */        
	public void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content, float tableWidth, float rowHeight) throws Exception {
  
		final int rows = content.length;
        final int cols = content[0].length;
       // final float rowHeight = 20f;  //여기서 셀 높이 지정됨. 20f였음
        //final float tableWidth = page.getMediaBox().getWidth() - (2 * margin); 
        final float tableHeight = rowHeight * rows;
        
        final float colWidth = tableWidth / (float)cols;
        final float cellMargin = 5f;

        // 행을 그린다.
        float nexty = y ;
        for(int i = 0; i <= rows; i++) {          //drawLine에서 여기가 xend가 됨
            drawLine(contentStream, margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;   //여기가 xstart임. 
        }

        // 열을 그린다.
        float nextx = margin;
        for(int i = 0; i <= cols; i++) {
            drawLine(contentStream, nextx, y, nextx, y - tableHeight);
            nextx += colWidth;
        }

        float textx = margin + cellMargin;
        float texty = y - 15;
        for(int i = 0; i < content.length; i++) {
            for(int j = 0 ; j < content[i].length; j++) {
                String text = content[i][j];
                drawText(text, fontGulim, 16, textx, texty, contentStream);
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }
	
	/**가운데 정렬 테이블 그리기
	 * @param PDPage, PDPageContentStream, 높이(바닥0~), 왼쪽마진, 내용
	 * */ 
     public void drawCenterTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content) throws Exception {
        
		
		final int rows = content.length;
        final int cols = content[0].length;
        
        final float rowHeight = 20f;  //여기서 셀 높이 지정됨. 20f였음
        final float tableWidth = page.getMediaBox().getWidth() - (2 * margin); 
        final float tableHeight = rowHeight * rows;
        
        final float colWidth = tableWidth / (float)cols;
        final float cellMargin = 5f;

        // 행을 그린다.
        float nexty = y ;
        for(int i = 0; i <= rows; i++) {          //end가 됨
            drawLine(contentStream, margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;   //여기가 xstart임. 
        }

        // 열을 그린다.
        float nextx = margin;
        for(int i = 0; i <= cols; i++) {
            drawLine(contentStream, nextx, y, nextx, y - tableHeight);
            nextx += colWidth;
        }

        float textx = margin + cellMargin;
        float texty = y - 15;
        for(int i = 0; i < content.length; i++) {
            for(int j = 0 ; j < content[i].length; j++) {
                String text = content[i][j];
                drawText(text, fontGulim, 12, textx, texty, contentStream);
                //여기서 먼가 문제가 있는듯. 폰트때문
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }
	
	//여러줄 처리하는거 
	/**https://www.debugcn.com/ko/article/3242395.html*/
}
