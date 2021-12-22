<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawPieChart);

      function drawPieChart() {

        var pData = google.visualization.arrayToDataTable([
          ['Score', 'Score percentage'],
          ['출석',   ${scorePercentage.attPoint}],  
          ['과제',   ${scorePercentage.taskPoint}],  
          ['중간시험',${scorePercentage.midPoint}],   
          ['기말시험',${scorePercentage.finPoint}]     
        ]);

        var pOptions = {
          title: ''
        };

        var pChart = new google.visualization.PieChart(document.getElementById('piechart'));

        pChart.draw(pData, pOptions);
      }
      
      
//       google.charts.load('current', {'packages':['bar']});
//       google.charts.setOnLoadCallback(drawBarChart);

//       function drawBarChart() {
//         var bData = google.visualization.arrayToDataTable([
//           ['Year', 'Sales', 'Expenses', 'Profit'],
//           ['2014', 1000, 400, 200],
//           ['2015', 1170, 460, 250],
//           ['2016', 660, 1120, 300],
//           ['2017', 1030, 540, 350]
//         ]);

//         var bOptions = {
//           chart: {
//             title: 'Company Performance',
//             subtitle: 'Sales, Expenses, and Profit: 2014-2017',
//           },
//           bars: 'horizontal' // Required for Material Bar Charts.
//         };

//         var bChart = new google.charts.Bar(document.getElementById('barchart_material'));

//         bChart.draw(bData, google.charts.Bar.convertOptions(bOptions));
//       }
//  </script> 
<style>
.table > tbody > tr > td:not(:last-child) {
    border-right: 1px solid #e5e5e5;
}

.table > thead > tr > th:not(:last-child) {
    border-right: 1px solid #e5e5e5;
}

.table > tbody > tr {
    border-bottom: 1px solid #e5e5e5;
</style>

<div class="all-wrap">
	<div class="left-card">
		<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold">평가 항목 비중</h6>
		</div>
		<div id="piechart" style="width: 590px; height: 500px; margin-right : 0px"></div>
		 <div>
		<!--  <div id="barchart_material" style="width: 700px; height: 500px; float :right; margin-left : 100px"></div> -->
		 </div>
	</div>

	<div class="left-card" style="width: 870px;">
	<div class="card-header py-3">
	<h6 class="m-0 font-weight-bold">수강생 성적 조회</h6>
	</div>
		<table class="table table-borderless txt-center">
		   <thead>
		      <tr>
		         <th>학번</th> 
		         <th>이름</th> 
		         <th>출석변환점수</th>                  
		         <th>과제변환점수</th>
		         <th>중간변환점수</th>
		         <th>기말변환점수</th>
		         <th>누적총점</th>
		         
		      </tr>
		   </thead>
		   <c:set var="scoreList" value="${pagingVO.dataList}" />
		   <tbody>
		      <c:choose>
		         <c:when test="${not empty scoreList}">
		            <c:forEach items="${scoreList}" var="score">
		               <tr>
		                  <td>
		                     <%--                   <c:url value="/common/lecturePage/noticeBoardView.do" var="viewURL"> --%>
		                     <%--                   <c:param name="what" value="${notice.boardNo}"/> --%>
		                     <%--                   </c:url> --%> <%--                   <a href="${viewURL}"> --%>
		                     ${score.memNo} <!--                   </a> -->
		                  </td>
		                  <td>${score.memName}</td>
		                  <td>${score.atdcChangePointsum}</td>
		                  <td>${score.avgTaskChangeScore}</td>
		                  <td>${score.classMidChange}</td>
		                  <td>${score.classFinChange}</td>
		                  <td>${score.classScore}</td>               
		               </tr>
		            </c:forEach>
		         </c:when>
		         <c:otherwise>
		            <tr>
		               <td colspan="9">등록된 공지글이 없음</td>
		            </tr>
		         </c:otherwise>
		      </c:choose>
		   </tbody>
		<!-- <input type="button" class="linkBtn"                                             -->
		<%-- data-gopage="${pageContext.request.contextPath}/professor/lecturePage/noticeBoardInsert.do?classNo=${pagingVO.searchVO.searchWord}" value="게시글 등록" /> --%>
		</table>      
		<div class="flex-center">
		    <div id="pagingArea">
               ${pagingVO.pagingHTML}
            </div>                                                                  <%--여기서는 classNo가 안뜸 --%>   
		</div> 
		<form id="searchForm" action="${pageContext.request.contextPath}/academic/professor/lecturePage/retrieveScore.do" >                          
		   <input type="hidden" name="page" />   
		</form>
	</div>
</div>
    