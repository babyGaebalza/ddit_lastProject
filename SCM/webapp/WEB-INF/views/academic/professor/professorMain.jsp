<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<style type="text/css">
	.p-wrap > table > tbody > tr > td {
		font-size: 14px;
	}
</style>
<h3>교수메인화면</h3>
<div class="all-wrap">
	<div class="left-wrap">
		<!-- Project Card Example -->
		    <div class="left-card mb-4 width-883">
		        <div class="card-header py-3">
		            <h6 class="m-0 font-weight-bold">${mainContent.currentSemester.schYear }년 ${mainContent.currentSemester.schCont }</h6>
		            <a href="#none" onclick="window.open('${cPath}/calendar/calendarList.do','new','scrollbars=yes,resizable=no width=800 height=670, left=500,top=150');return false"><i class="far fa-calendar-alt"></i>&nbsp;학사일정</a>
		        </div>
		        <div class="card-body">
		            <h6 class="small font-weight-bold">개강일 : ${mainContent.currentSemester.schSdate }</h6>
		            <h6 class="small font-weight-bold">종강일 : ${mainContent.currentSemester.schEdate }<span
		                    class="float-right">${mainContent.currentSemester.semesterPercentage }%</span></h6>  
		            <div class="progress mb-4">
		                <div class="progress-bar" role="progressbar" style="width: ${mainContent.currentSemester.semesterPercentage }%"
		                    aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
		            </div>
		        </div>
		    </div>
		<div class="table-box width-883 m-bottom-20">
			<div id="collegeNoticeList">
			    <div class="card-header">
			    	<span>학사 공지사항</span>
			    	<a href="${cPath }/common/board/noticeList.do"><i class="fas fa-angle-double-right"></i></a> 
			    </div> 
			    	<div class="p-wrap">
				    	<table class="table table-borderless txt-center">
				    		<c:choose>
				    			<c:when test="${fn:length(mainContent.collegeNoticeList) > 0 }">
						    		<thead>    		
						    			<tr>
							    			<th>제목</th>
							    			<th style="width: 200px;">작성자</th>
							    			<th style="width: 100px;">작성일</th>
							    			<th style="width: 70px;">조회수</th>
						    			</tr>
						    		</thead>    		
						    		<tbody>
						    			<c:forEach items="${mainContent.collegeNoticeList }" var="board">
						    				<tr>
						    					<td class="txt-left">
						    						<c:url value="/board/noticeView.do" var="boardURL">
						    							<c:param name="noticeNo" value="${board.boardNo }"/>
						    						</c:url>
						    						<a href="${boardURL }">${board.boardTitle }</a>
						    					</td>
						    					<td>학생지원과(${board.memNo })</td>
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
		<div class="table-box width-883 m-bottom-20"> 
			<div id="majorNoticeList">
			    <div class="card-header">
			    	<span>학과 공지사항</span>
			    	<a href="${cPath }/departmentManage/departmentManageList.do"><i class="fas fa-angle-double-right"></i></a> 
			    </div> 
			    	<div class="p-wrap">
				    	<table class="table table-borderless txt-center">
				    		<c:choose>
				    			<c:when test="${fn:length(mainContent.majorNoticeList) > 0 }">
						    		<thead>    		
						    			<tr>
							    			<th>제목</th>
							    			<th style="width: 200px;">작성자</th>
							    			<th style="width: 100px;">작성일</th>
							    			<th style="width: 70px;">조회수</th>
						    			</tr>
						    		</thead>    		
						    		<tbody>
						    			<c:forEach items="${mainContent.majorNoticeList }" var="board">
						    				<tr>
						    					<td class="txt-left">
						    						<c:url value="/departmentManage/departmentManageDetail.do" var="boardURL">
						    							<c:param name="noticeNo" value="${board.boardNo }"/>
						    						</c:url>
						    						<a href="${boardURL }">${board.boardTitle }</a>
						    					</td>
						    					<td>${board.majorName }(${board.memNo })</td>
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
	</div>
	<div class="right-wrap">
	<div class="left-card">
		<div class="time-wrap">
			 <div class="card-header py-3">
				<h6 class="m-0 font-weight-bold">현재 시간표</h6>
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
				  	<c:set value="${mainContent.timeTable }" var="timeList" />
				  	<tr>
				  		<td>1</td>
			  			<td>(1051700051)<br>데이터통신<br>/공과대학 205호</td>
			  			<td></td>
			  			<td></td>
			  			<td></td>
			  			<td></td>
				  	</tr>
				  	<tr>
				  		<td>2</td>
				  		<td>(1051700051)<br>데이터통신<br>/공과대학 205호</td>
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
			  			<td>(1051700052)<br>전기전자회로<br>/공과대학 206호</td>
			  			<td></td>
			  			<td>(1051700052)<br>전기전자회로<br>/공과대학 206호</td>
			  			<td></td>
				  	</tr>
				  	<tr>
				  		<td>5</td>
				  		<td></td>
			  			<td>(1051700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
			  			<td></td>
			  			<td>(1051700052)<br>전기전자회로<br>/공과대학 206호</td>
			  			<td></td>
				  	</tr>
				  	<tr>
				  		<td>6</td>
				  		<td></td>
			  			<td>(1051700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
			  			<td></td>
			  			<td>(1051700054)<br>프로그래밍의원리<br>/공과대학 302호</td>
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
