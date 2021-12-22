<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의페이지 학생</title>
<script src="https://kit.fontawesome.com/c7e70a8ce5.js" crossorigin="anonymous"></script>
</head>
<body>
	
<!-- Project Card Example -->
<div class="all-wrap">
	<div class="left-wrap">
	    <div class="left-card mb-4 width-883">
	        <div class="card-header py-3">
	            <h6 class="m-0 font-weight-bold">수업참여</h6>
	        </div>
	        <div class="card-body">
	        	<c:if test="${resultMap.lectureTaskInfo.taskSum ne 0 }">
		            <fmt:parseNumber var="taskPer" value="${(resultMap.lectureTaskInfo.submitSum * 100  + 0.0)/(resultMap.lectureTaskInfo.taskSum + 0.0)}" integerOnly="true" />	        	
		            <h4 class="small font-weight-bold">과제 제출(${resultMap.lectureTaskInfo.submitSum }/${resultMap.lectureTaskInfo.taskSum })<span
		                    class="float-right">${taskPer }%</span></h4>
		            <div class="progress">
		                <div class="progress-bar" id="taskBar" role="progressbar" style="width: ${taskPer}%" 	
		                    aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
		            </div>
	        	</c:if>
	        	<c:if test="${resultMap.lectureTaskInfo.taskSum eq 0 }">
	        		<h4 class="small font-weight-bold">과제 제출(부여된 과제가 없습니다.)<span
		                    class="float-right">${taskPer }</span></h4>
		            <div class="progress">
		                <div class="progress-bar" id="taskBar" role="progressbar" style="width: 0%" 	
		                    aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
		            </div>
	        	</c:if>
	            <script type="text/javascript">
	            	let taskBarColor;
	            	if (${taskPer } >= 100) {
	            		taskBarColor = 'bg-success';
					}else if(${taskPer } >= 80){
						taskBarColor = 'bg-info';
					}else if(${taskPer } >= 60){
						taskBarColor = '';
					}else if(${taskPer } >= 40){
						taskBarColor = 'bg-warning';
					}else{
						taskBarColor = 'bg-danger';
					}
	            	let taskBar = $("#taskBar").addClass(taskBarColor);
	            </script>
	        </div>
	    </div>
	    <div class="table-box width-883 m-bottom-20">
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
						    			<th>제출여부</th>
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
					    					<c:choose>
					    						<c:when test="${board.submitTaskyn eq 'Y' }">
							    					<td>
							    					<span class="round-blue">제출완료</span>
							    					</td>		    						
					    						</c:when>
					    						<c:otherwise>
					    							<td>
					    								<span class="round-red">미제출</span>
					    							</td>
					    						</c:otherwise>
					    					</c:choose>
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
		
		<div style="float: right;margin-top: 12px;margin-right: 21px;">
			<input type="button" value="강의평가" id="stuEva" class="btn btn-outline-warning"/>
			<input type="button" value="성적조회" id="stuScore" class="btn btn-outline-danger linkBtn" data-gopage="<c:url value='/student/score/scoreView' />" />
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
			  	<c:set value="${resultMap.lectureTimeTableList }" var="timeList" />
			  	<tr>
			  		<td>1</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime1 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>2</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime2 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>3</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime3 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>4</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime4 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>5</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime5 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>6</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime6 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>7</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime7 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>8</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime8 }</td>
			  		</c:forEach>
			  	</tr>
			  	<tr>
			  		<td>9</td>
			  		<c:forEach items="${timeList }" var="time">
			  			<td>${time.stutime9 }</td>
			  		</c:forEach>
			  	</tr>
			  </tbody>
			</table>
		</div>
	</div>
	</div>
</div>
    	
    
   
    

</body>

<script type="text/javascript">

//stuEva	//강의평가 버튼
$("#stuEva").on("click", function(){
	$.ajax({
		url :"${cPath}/common/evaluation/stuEvaCheck.do",
		dataType : "text",
		success : function(result){
			if(result != "OK"){
				$.confirm({
					title : '강의평가 가능여부',
					content : result,
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								return;
							}
						}
					}
				});
			}
			if(result == "OK"){
				$.confirm({
					title : '강의평가 가능여부',
					content : '강의평가 페이지로 이동합니다.',
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								location.href="${cPath}/common/evaluation/stuEVA.do";
								return;
							}
						}
					}
				})
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
});


//stuScore	//성적조회버튼
$("#stuScore").on("click", function(){
	$.ajax({
		url : "${cPath}/common/evaluation/stuScoreEvaCheck.do",
		dataType : "text",
		success : function(result){
			if(result != "OK"){
				$.confirm({
					title : '성적조회 가능여부',
					content : result,
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								return;
							}
						}
					}
				});
			}
			if(result == "OK"){
				$.confirm({
					title : '성적조회 가능여부',
					content : '성적조회 페이지로 이동합니다.',
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								return;
							}
						}
					}
				})
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
});
	
</script>

</html>

























