<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.thColor {
    background-color: #d6dcdd;
}

.tdHeight {
    height: 70px;
}
</style>

<div class="all-wrap">
	<div class="left-wrap">
	    <div class="table-box width-883 m-bottom-20 m-top-10">
		    <div id="mainTaskList">
		    <div class="card-header">
		    	<span>진행중인 과제</span>
		    	<a href="${cPath }/common/lecturePage/reportBoardList.do"><i class="fas fa-angle-double-right"></i></a> 
		    </div> 
		    	<div class="p-wrap">
			    	<table class="table table-borderless txt-center">
			    		<c:choose>
			    			<c:when test="${fn:length(resultMap.ongoingTaskList) > 0 }">
					    		<thead>    		
					    			<tr>
						    			<th>과제명</th>
						    			<th>시작일</th>
						    			<th>마감일</th>
						    			<th>제출현황</th>
					    			</tr>
					    		</thead>    		
					    		<tbody>
					    			<c:forEach items="${resultMap.ongoingTaskList }" var="board">
					    				<tr>
					    					<td class="txt-left">
					    						<c:url value="/common/lecturePage/reportBoardView.do" var="boardURL">
					    							<c:param name="what" value="${board.boardNo }"/>
					    						</c:url>
					    						<a href="${boardURL }">${board.boardTitle }</a>
					    					</td>
					    					<td>${board.boardDate }</td>
					    					<td>${board.boardDeadline }</td>
					    					<td>${board.taskSubmitCount }</td>
					    				</tr>
					    			</c:forEach>
					    		</tbody>
			    			</c:when>
			    			<c:otherwise>
			    				<h6>진행중인 과제가 없습니다.</h6>
			    			</c:otherwise>
			    		</c:choose>
			    	</table>
		    	</div>
		    </div>
	    </div>
	    <div class="table-box width-883">
		     <div id="mainNoticeList">
			    <div class="card-header">
			    	<span>강의 공지사항</span>
			    	<a href="${cPath }/common/lecturePage/noticeList.do"><i class="fas fa-angle-double-right"></i></a> 
			    </div>
		    	<div class="p-wrap">
			    	<table class="table table-borderless txt-center">
			    		<c:choose>
			    			<c:when test="${fn:length(resultMap.lectureNoticeList) > 0 }">
					    		<thead>    		
					    			<tr>
						    			<th scope="col">No</th> 
						    			<th scope="col">제목</th>
						    			<th scope="col">작성일</th>
						    			<th scope="col">조회수</th>
					    			</tr>
					    		</thead>    		
					    		<tbody>
					    			<c:forEach items="${resultMap.lectureNoticeList }" var="board" varStatus="stat">
					    				<tr>
					    					<td>${stat.count }</td>
					    					<td class="txt-left">
					    						<c:url value="/common/lecturePage/noticeBoardView.do" var="boardURL">
					    							<c:param name="what" value="${board.boardNo }"/>
					    						</c:url>
					    						<a href="${boardURL }">${board.boardTitle }</a>
					    					</td>
					    					<td>${board.boardDate }</td>
					    					<td>${board.boardHits }</td>
					    				</tr>
					    			</c:forEach>
					    		</tbody>
			    			</c:when>
			    			<c:otherwise>
			    				<h6>공지사항이 없습니다.</h6>
			    			</c:otherwise>
			    		</c:choose>
			    	</table>
		    	</div>
		    </div>
		</div>
		
<!-- 		<div style="float: right;margin-top: 12px;margin-right: 21px;"> -->
<!-- 			<input type="button" value="강의평가" id="stuEva" class="btn btn-outline-warning"/> -->
<%-- 			<input type="button" value="성적조회(강의평가로 연결 테스트용/미구현)" id="stuScore" class="btn btn-outline-danger linkBtn" data-gopage="<c:url value='/student/score/scoreView' />" /> --%>
<!-- 		</div> -->
		
		<div class="m-top-20 m-right-10">
			<div style="float: left;">
				<button id="QRCheck" class="btn btn-dark">QR출석 코드 생성하기 </button>
			</div>
			<div style="float: right;">
				<button id="backToList" class="btn btn-outline-success">목록으로 돌아가기 </button>
				<button id="evaluation" class="btn btn-outline-primary">강의평가등록</button>
				<button id="evaResult" class="btn btn-outline-danger">강의평가결과조회</button>
			</div>
		</div>
			
	</div>
	<div class="right-wrap">
	<div class="left-card">
			<div class="time-wrap">
			 <div class="card-header py-3">
				<h6 class="m-0 font-weight-bold">시간표</h6>
			 </div>
			<table class="right-table">
			  <thead class="time-head">
			    <tr>
			      <th scope="col">교시/요일</th>
			      <th scope="col">월</th>
			      <th scope="col">화</th>
			      <th scope="col">수</th>
			      <th scope="col">목</th>
			      <th scope="col">금</th>
			    </tr>
			  </thead>
			  <tbody id="timeTable" class="time-body">
			  	<tr>
			  		<td>1</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>2</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>3</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>4</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>5</td>
			  		<td></td>
			  		<td>(205700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>6</td>
			  		<td></td>
			  		<td>(205700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
			  		<td></td>
			  		<td>(205700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>7</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>8</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  	<tr>
			  		<td>9</td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  	</tr>
			  </tbody>
			</table>
		</div>
	</div>
	</div>
	
	
	
</div>

<input type="hidden" value="${classNo}" id="classNo"/>

<script type="text/javascript">

$('#backToList').on("click",function(){  
	location.href = "${pageContext.request.contextPath}/academic/professor/lectureMain.do";
});

$('#QRCheck').on("click", function(){
		var classNo = document.getElementById('classNo').value;
		console.log(classNo)
		var info = "https://192.168.0.153/SCM/QR/QRform.do?classNo="+classNo;
		
		var googleBase = "http://chart.googleapis.com/chart?cht=qr&chs=300&choe=UTF-8";
		var googlechl = googleBase + "&chl=" + encodeURIComponent(info);
		var googletotal = googlechl;
		window.open(googletotal, "height=300, width=300");
})


//강의평가 하는 부분임
$("#evaluation").on("click", function(){
	$.ajax({
		url : $.getContextPath() + "/common/evaluation/evaCheck.do",
		dataType : "text",
		success : function(result) {
			if(result == "OK"){
				$.confirm({
					title : '강의평가 등록여부',
					content : '강의평가 양식등록이 가능합니다.',
					buttons : {
						확인 : function(){
							location.href = "${cPath}/common/evaluation/evaInForm.do";
						}
					}
				});
			}
			else{
				$.confirm({
					title : '강의평가 등록여부',
					content : '이미 강의평가 양식등록이 되어있습니다.',
					buttons : {
						확인 : function(){
							return;
						}
					}
				});
			}
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});

$("#evaResult").on("click", function(){
	$.ajax({
		url : $.getContextPath() + "/common/evaluation/evaCheck.do",
		dataType : "text",
		success : function(result) {
			if(result == "FAIL"){
				$.confirm({
					title : '평가결과 조회가능 여부',
					content : '강의평가 조회가 가능합니다.',
					buttons : {
						확인 : function(){
							location.href = "${cPath}/common/evaluation/profEvaDetail.do";
						}
					}
				});
			}
			else{
				$.confirm({
					title : '평가결과 조회가능 여부',
					content : '강의평가 조회가 불가합니다.<br>양식 제출여부를 확인해주세요.',
					buttons : {
						확인 : function(){
							return;
						}
					}
				});
			}
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});

</script>
