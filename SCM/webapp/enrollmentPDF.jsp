<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includee/preScript.jsp" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/html2canvas.js" ></script>
 
<button type="button" class="btn btn-primary btn-sm" id="savePdf" >등록고지서 발급</button>
<div id="pdfDiv">

<h3>등록고지서</h3>
등록고지서 내용<br>
등록고지서 내용<br>
등록고지서 내용<br>
등록고지서 내용<br>
등록고지서 내용<br>
</div>

<script>
$('#savePdf').click(function() { // pdf저장 button id
    html2canvas($('#pdfDiv')[0]).then(function(canvas) { //저장 영역 div id
	
    // 캔버스를 이미지로 변환
    var imgData = canvas.toDataURL('image/png');
	     
    var imgWidth = 0; // 이미지 가로 길이(mm) / A4 기준 210mm
    var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
    var imgHeight = canvas.height * imgWidth / canvas.width;
    var heightLeft = imgHeight;
    var margin = 10; // 출력 페이지 여백설정
    
    var doc = new jsPDF('p', 'mm');
    var position = 0;
       
    // 첫 페이지 출력
    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
    heightLeft -= pageHeight;
         
    // 한 페이지 이상일 경우 루프 돌면서 출력
    while (heightLeft >= 20) {
        position = heightLeft - imgHeight;
        doc.addPage();
        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;
    }
    // 파일 저장
    doc.save('등록고지서');
	});
});
</script>


