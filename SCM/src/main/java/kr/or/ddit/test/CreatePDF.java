package kr.or.ddit.test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class CreatePDF {

	static final PDDocument doc = new PDDocument();
	private File fontFile = new File("C:/Windows/Fonts/gulim.ttc");	//하드코딩인게 불안...
	private String logoURL = "/resources/images/symbol.jpg";

	//폰트
	private static PDType0Font fontGulim;
	
	private String person1 = "서명"; 
	private String person2 = "서명"; 
	private String person3 = "서명"; 
	private String person4 = "서명"; 
	
	private String docuNo ="00000"; 
	private String docuReqdate ="0000/00/00"; 
	private String docuCode ="트랙변경신청서";
	private String docuFilename="문서 제목을 입력하세요.";
	
	//틀 그리는 부분 
	@RequestMapping(value = "/pdff.do", method = RequestMethod.POST)
	public void pdf(HttpServletResponse response, HttpServletRequest req) throws Exception {
		
		final int pageCount = 1; //만들장수  걍 1장으로...
		//final String webroot = this.context.getRealPath("/");		
		final String logo = req.getServletContext().getRealPath(logoURL); 

		String content =    "주ㅠㄱ일까";       //req.getParameter("content");   //얘도 아예 모델로 만들면 편할듯..?	        
		
		// 배경이미지 로드
	     PDImageXObject pdImage = PDImageXObject.createFromFile(logo, doc);
		
	    // 폰트 생성
	    // ttf 파일 사용하기
	    //InputStream fontStream = new FileInputStream("C:/fonts/gulim.ttf");
	    //PDType0Font fontGulim = PDType0Font.load(doc, fontStream);
	    
	    // ttc 파일 사용하기		
	     fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	     // 두 개의 페이지를 만든다.
	     //for(int i = 0; i < pageCount; i++) {
			// 페이지 추가
			PDPage blankPage = new PDPage(PDRectangle.A4);
	        doc.addPage(blankPage);
	        
	        // 현재 페이지 설정 //1장으로만 설정이됨. 
	        PDPage page = doc.getPage(0);
	        
	        // 컨텐츠 스트림 열기
	        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	        
            // 배경 이미지  그리기                           
	        contentStream.drawImage(pdImage, 250, 350, 100, 100);                 	        
	        drawText("S  C  대  학", fontGulim, 16, 260, 800, contentStream);
						//내용,	폰트종류,  글씨크기, 왼쪽패딩, 아래패딩, 스트림 	
	        	        
	        //결재라인 표
	        String[][] people =  {
	        		{"담당", "확인", "확인", "전결"} //,{ person1, person2, person3, person4 }
	        };	     
	        drawTable(page, contentStream, 750, 350, people, 180, 20f);
	      
	        //결재서명 표 
	        String[][] sign =  {
	        		{ person1, person2, person3, person4 }
	        };	     
	        drawTable(page, contentStream, 730, 350, sign, 180, 40f);
	        
	        //문서캡션
	        String[][] docDetailTitle = {
	        		{"문서번호"}, 
	        		{"작성일자"}, 
	        		{"문서종류"}	        		
	        };
	        drawTable(page, contentStream, 750, 70, docDetailTitle, 60, 20f);

	        //문서캡션내용
	        String[][] docDetail = {
	        		{docuNo}, 
	        		{docuReqdate}, 
	        		{docuCode}     		
	        }; 
	        drawTable(page, contentStream, 750, 130, docDetail, 100, 20f);

	        //제목
	        String[][] title = {
	        		{"제목"}	        		
	        }; 
	        drawTable(page, contentStream, 650, 70, title, 60, 20f);

	        //제목입력값
	        String[][] titleInput = {
	        		{docuFilename}	        		
	        }; 
	        drawTable(page, contentStream, 650, 130, titleInput, 400, 20f);

	        //메인큰표 만들기 
	        String[][] mainContent = {
	        		{" "}
	        };
	        drawTable(page, contentStream, 600, 70, mainContent,460,450f);
   
	        //내용쓰기 	        	        
	        drawText(content, fontGulim, 12, 100, 550, contentStream);
	        	        				               
	       
	        
	        
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
		String fileName = URLEncoder.encode("샘플PDF", "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
		// PDF 파일 출력
		doc.save(response.getOutputStream());
		doc.close();
	}
	
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
                drawText(text, fontGulim, 12, textx, texty, contentStream);
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
