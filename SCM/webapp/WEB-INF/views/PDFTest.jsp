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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PDF 페이지 </title>
</head>
<body>
<%! 
static final PDDocument doc = new PDDocument();
private File fontFile = new File("C:/Windows/Fonts/gulim.ttc");	//하드코딩인게 불안...
private String logoURL = "/resources/images/symbol.jpg";

%>

<%!
private void drawText(String text, PDFont font, int fontSize, float left, float bottom, PDPageContentStream contentStream) throws Exception {
    contentStream.beginText(); 
    contentStream.setFont(font, fontSize);
    contentStream.newLineAtOffset(left, bottom);
    contentStream.showText(text);
    contentStream.endText();
}
%>
<%! 
private void drawLine(PDPageContentStream contentStream, float xStart, float yStart, float xEnd, float yEnd) throws Exception {
		contentStream.moveTo(xStart,yStart);
		contentStream.lineTo(xEnd,yEnd);
		contentStream.stroke();
}
%>

<%! 
public void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content) throws Exception {
    final int rows = content.length;
    final int cols = content[0].length;
    
    final float rowHeight = 20f;
    final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
    final float tableHeight = rowHeight * rows;
    
    final float colWidth = tableWidth / (float)cols;
    final float cellMargin = 5f;

    // 행을 그린다.
    float nexty = y ;
    for(int i = 0; i <= rows; i++) {
        drawLine(contentStream, margin, nexty, margin + tableWidth, nexty);
        nexty -= rowHeight;
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
            drawText(text, PDType1Font.HELVETICA_BOLD, 12, textx, texty, contentStream);
            textx += colWidth;
        }
        texty -= rowHeight;
        textx = margin + cellMargin;
    }
}
%>

<% 
		final int pageCount = 1;

		final PDDocument doc = new PDDocument();
		File fontFile = new File("C:/Windows/Fonts/gulim.ttc");	
		PDType0Font fontGulim = PDType0Font.load(doc, new TrueTypeCollection(fontFile).getFontByName("Gulim"), true);
		
	  	for(int i = 0; i < pageCount; i++) {
				// 페이지 추가
				PDPage blankPage = new PDPage(PDRectangle.A4);
		        doc.addPage(blankPage);		        
		        // 현재 페이지 설정
		        PDPage ppage = doc.getPage(0);
		        // 컨텐츠 스트림 열기
		        PDPageContentStream contentStream = new PDPageContentStream(doc, ppage);		        
	             // 배경 이미지  그리기
		        // contentStream.drawImage(pdImage, 0, 0, 595, 842);
		        
		        // 글씨 쓰기
		        drawText("기안문서", fontGulim, 18, 100, 600, contentStream);
		        drawText("PDF파일 만들기", fontGulim, 18, 100, 560, contentStream);
		        
		        // 테이블 그리기
		        String[][] contents = {
	                {"Apple",    "Banana",    "1"},
	                {"Chestnut", "Persimmon", "2"},
	                {"Eggplang", "Potato",    "3"},
	                {"Guava",    "Radish",    "4"},
	                {"Lemon",    "Lime",      "5"}
	            };

	            drawTable(ppage, contentStream, 500, 100, contents);
		        
		        // 컨텐츠 스트림 닫기
		        contentStream.close();
	  	}

	  // 파일 다운로드 설정
	 		response.setContentType("application/pdf");
	 		String fileName = URLEncoder.encode("샘플PDF2", "UTF-8");
	 		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
	 		
	 		// PDF 파일 출력
	 		doc.save(response.getOutputStream());
	 		doc.close();
	 	 
%>


</body>


</html>