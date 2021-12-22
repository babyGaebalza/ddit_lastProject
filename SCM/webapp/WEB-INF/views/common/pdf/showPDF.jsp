<%@page import="kr.or.ddit.vo.DocumentVO"%>
<%@page import="org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.pdfbox.pdmodel.font.PDFont"%>
<%@page import="org.apache.pdfbox.pdmodel.font.PDType1Font"%>
<%@page import="org.apache.pdfbox.pdmodel.common.PDRectangle"%>
<%@page import="org.apache.fontbox.ttf.TrueTypeCollection"%>
<%@page import="org.apache.pdfbox.pdmodel.font.PDType0Font"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.pdfbox.text.PDFTextStripper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.pdfbox.pdmodel.*" %>     
<%@ page import="org.apache.fontbox.*" %>     
<%@ page import="org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<%! 
private PDDocument doc = new PDDocument();
private PDPage blankPage =new PDPage(PDRectangle.A4); 

private PDPage thisPage; // = doc.getPage(0);
//private PDPageContentStream contentStream;  //= new PDPageContentStream(doc, thisPage) ;  

private File fontFile = new File("C:/Windows/Fonts/gulim.ttc");	//하드코딩인게 불안...
private String logoURL = "/resources/images/symbol.jpg";  //로고 
private PDType0Font fontGulim;  //폰트

private String person1 = null; 
private String person2 = "결재1"; 
private String person3= "결재2"; 
private String person4= null; 

private String docuNo ="00000"; 
private String docuReqdate ="문서작성일"; 
private String docuCode ="문서코드";
private String docuFilename="문서 제목을 입력하세요.";
private String content="내용을 입력하세요"; 

%>
<% 
//blankPage = new PDPage(PDRectangle.A4);
doc.addPage(blankPage);
// 현재 페이지 설정 //1장으로만 설정이됨. 
thisPage = doc.getPage(0);

// 컨텐츠 스트림 열기
final PDPageContentStream contentStream = new PDPageContentStream(doc, thisPage);

DocumentVO docuVO = (DocumentVO)request.getAttribute("docuVO"); 
person1 = (String)docuVO.getDocuReq(); 
person4 = (String)docuVO.getDocuApf(); 
person2 = (String)docuVO.getDocuAp1();  
person3 = (String)docuVO.getDocuAp2(); 
docuNo = (String)docuVO.getDocuNo(); 
docuReqdate = (String)docuVO.getDocuReqdate(); 
docuCode = (String)docuVO.getDocuCode(); 
docuFilename = (String)docuVO.getDocuFilename(); 
content = (String)docuVO.getDocuCont(); 


final int pageCount = 1; //만들장수  1장으로...
final String logo = request.getServletContext().getRealPath(logoURL); 
// 배경이미지 로드
 PDImageXObject pdImage = PDImageXObject.createFromFile(logo, doc);

// ttc 파일 사용하기		
 fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
    
    // 배경 이미지  그리기                           
    contentStream.drawImage(pdImage, 250, 350, 100, 100);                 	        
    drawText("S  C  대  학", fontGulim, 16, 260, 800, contentStream);
				//내용,	폰트종류,  글씨크기, 왼쪽패딩, 아래패딩, 스트림 	
    	        
    //결재라인 표
    String[][] people =  {
    		{"담당", "확인", "확인", "전결"} ,
    };	     
    drawTable(thisPage, contentStream, 750, 350, people, 180, 20f);
  
    //결재서명 표 
    String[][] sign =  {
    		{ person1, person2, person3, person4 }
    };	     
    drawTable(thisPage, contentStream, 730, 350, sign, 180, 40f);
    
    //문서캡션
    String[][] docDetailTitle = {
    		{"문서번호"}, 
    		{"작성일자"}, 
    		{"문서종류"}	        		
    };
    drawTable(thisPage, contentStream, 750, 70, docDetailTitle, 60, 20f);

    //문서캡션내용
    String[][] docDetail = {
    		{docuNo}, 
    		{docuReqdate}, 
    		{docuCode}     		
    }; 
    drawTable(thisPage, contentStream, 750, 130, docDetail, 100, 20f);

    //제목
    String[][] title = {
    		{"제목"}	        		
    }; 
    drawTable(thisPage, contentStream, 650, 70, title, 60, 20f);

    //제목입력값
    String[][] titleInput = {
    		{docuFilename}	        		
    }; 
    drawTable(thisPage, contentStream, 650, 130, titleInput, 400, 20f);

    //메인큰표 만들기 
    String[][] mainContent = {
    		{" "}
    };
    drawTable(thisPage, contentStream, 600, 70, mainContent,460,450f);

    //내용쓰기 	        	        
    drawText(content, fontGulim, 12, 100, 550, contentStream);
    	        				                 
    //맨 밑
    String[][] table =  {
    		{ " 주소 : 대전광역시 중구 대흥동 500-5   전화 : 042-821-5114"},
    		{" 팩스 : 042-821-5110   이메일 : daepyo@smcuniv.ac.kr"}
    };        
    drawCenterTable(thisPage, contentStream, 100, 100, table);
    							       	        
    contentStream.close();
//}

// 파일 다운로드 설정
response.setContentType("application/pdf");
String fileName = URLEncoder.encode(docuFilename, "UTF-8");
response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

// PDF 파일 출력
doc.save(response.getOutputStream());
doc.close();

%>



<%!
	// 글씨를 쓴다.
	//@param 내용, 폰트종류, 글씨크기, 왼쪽패딩, 아래패딩, 스트림 	
	private void drawText(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
        contentStream.beginText(); 
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(left, bottom);
        contentStream.showText(text);
        contentStream.endText();
	}
%>

<%! 
/**
 * 라인을 그린다.
 * @param x축시작, y축시작, x축끝, y축끝 
 */
	private void drawLine(PDPageContentStream contentStream, float xStart, float yStart, float xEnd, float yEnd) throws Exception{
		contentStream.moveTo(xStart,yStart);
		contentStream.lineTo(xEnd,yEnd);
		contentStream.stroke();			
}%>
<%!
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
%>

<%! 
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
}%>



</body>
</html>