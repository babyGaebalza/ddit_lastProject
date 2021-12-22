<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includee/preScript.jsp" />

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bluebird/3.7.2/bluebird.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script> -->
<!-- <script src="https://unpkg.com/html2canvas@1.0.0-rc.5/dist/html2canvas.js"></script> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/html2canvas.js" ></script>

<style>
body{
	color: black;
}
.div{
	border: 1px solid black;
	position: relative;
}
h3{
	font-weight: bold;
}
a{
	margin-left: 200px;
	font-size: 20px;
}
#icon{
	margin-left : 200px;
	position: absolute;
	z-index: -1;
	bottom : 290px;
	right : 180px;
}
#stamp{
	margin-left : 200px;
	position: absolute;
	z-index: -1;
	bottom : 50px;
	right : 160px;
}
p{
	margin-left: 20px;
	margin-top : 20px;
}
</style>

<div id="print-box" style="width:700px">
<!-- 	<img src="../resources/images/CED04.PNG"/> -->
	<div class="div">
		<p>제 382020호</p>
		<br><br>
		<br>
		<center>
		<h1>재  학  증  명  서</h1>
		</center>
		<br><br><br><br>
		
		<a>성　　　명 : ${member.memName }</a>
		<p></p>
		
		<a>생 년 월 일 : 2002년 02월 21일</a>
		<p></p>
			
		<a>대　　　학 : SC대학(공과대학)</a>
		<p></p>
				
		<a>학　　　과 : 컴퓨터융합과</a>
		<p></p>
		
		<img id="icon" src="../resources/images/icon.jpg" width="350" height="300">
		
		<a>학　　　번 : 1학년</a>
		<p></p>
		<br><br><br>
		
		<center>
		<h2>위 사실을 증명합니다</h2>
		
		<br>
		<br>
		
		<h5>2021년 12월 21일</h5>
		<br>
		<h2>S　C　대　학　총　장</h2>
		
		<img id="stamp" src="../resources/images/stamp.png" width="50" height="50">
		
		</center>
		<br><br>
	</div>
</div>

<script>
html2canvas(document.getElementById("print-box"),{
	allowTaint: true,
    useCORS: true,
    logging: false
}).then(function(canvas) {

	   // 캔버스를 이미지로 변환
	   var imgData = canvas.toDataURL('image/png');
	   var imgWidth = 180; // 이미지 가로 길이(mm) / A4 기준 210mm
	   var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
	   var imgHeight = canvas.height * imgWidth / canvas.width;
	   var heightLeft = imgHeight;
	   var margin = 10; // 출력 페이지 여백설정
	   var position = 0;

	   // pdf 파일 생성
	   var doc = new jsPDF('p', 'mm');

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
	   doc.save('졸업증명서.pdf');
	   
	})


//$('#savePdf').click(function() { // pdf저장 button id
//html2canvas($('#pdfDiv')[0]).then(function(canvas) { //저장 영역 div id

//// 캔버스를 이미지로 변환
//var imgData = canvas.toDataURL('image/png');
  
//var imgWidth = 0; // 이미지 가로 길이(mm) / A4 기준 210mm
//var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
//var imgHeight = canvas.height * imgWidth / canvas.width;
//var heightLeft = imgHeight;
//var margin = 10; // 출력 페이지 여백설정

//var doc = new jsPDF('p', 'mm');
//var position = 0;

//// 첫 페이지 출력
//doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
//heightLeft -= pageHeight;
  
//// 한 페이지 이상일 경우 루프 돌면서 출력
//while (heightLeft >= 20) {
//  position = heightLeft - imgHeight;
//  doc.addPage();
//  doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
//  heightLeft -= pageHeight;
//}
//// 파일 저장
//doc.save('증명서');
//});
//});

</script>
