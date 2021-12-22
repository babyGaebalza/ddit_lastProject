<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="/includee/preScript.jsp" />

<h3>조교 메인페이지</h3>
<hr>

<style>
#assistantDiv1{
	float: left;
	width : 45%;
}
#assistantDiv3{
	width : 50%;
	float: right;
	margin-right : 2%;
}
</style>

<div id="assistantDiv1">
	<div>
	   	<div class="card-header">
	   		<span>학과 공지사항</span>
	   		<a href="${cPath }/departmentManage/departmentManageList.do"><i class="fas fa-angle-double-right"></i></a> 
	   	</div>	
	   	
	   	<div class="p-wrap">
		   	<table class="table table-borderless txt-center">
		  		<c:choose>
		  			<c:when test="${fn:length(mainAssistant.majorNoticeList) > 0 }">
		    		<thead>    		
		    			<tr>
			    			<th>제목</th>
			    			<th>작성자</th>
			    			<th>작성일</th>
			    			<th>조회수</th>
		    			</tr>
		    		</thead>    		
		    		<tbody>
		    			<c:forEach items="${mainAssistant.majorNoticeList }" var="board">
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

<div id="assistantDiv3">
	<jsp:include page="/WEB-INF/views/common/calendar/Calendar.jsp" flush="false"/>
</div>

<jsp:include page="/includee/postScript.jsp" />