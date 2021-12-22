<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="table-box width-780 m60-120">
	 <div class="card-header">
		<c:choose>
			<c:when test="${not empty resultMap.currentSemester }">
				<span>강의페이지 리스트(${resultMap.currentSemester.schYear }년 ${resultMap.currentSemester.schCont })</span>
			</c:when>
			<c:otherwise>
				<h4>강의진행기간이 아닙니다.</h4>
			</c:otherwise>
		</c:choose>
	 </div>
		<div class="p-wrap">
			<table class="table table-borderless txt-center">
		   		<c:choose>
		   			<c:when test="${fn:length(resultMap.classList) > 0 }">
			    		<thead>    		
			    			<tr>
				    			<th>No</th>
				    			<th>강의</th>
				    			<th>학점/시간</th>
			    			</tr>
			    		</thead>    		
			    		<tbody>
			    			<c:forEach items="${resultMap.classList }" var="lecture" varStatus="stat">
			    				<tr>
			    					<td>${stat.count }</td>
			    					<td class="txt-left">
			    						<c:url value="/student/lecturePage/main.do" var="lecturePageURL">
			    							<c:param name="classNo" value="${lecture.classNo }"/>
			    						</c:url>
			    						<a href="${lecturePageURL }">(${lecture.classCodeName}) ${lecture.classInfo.className }</a>
			    					</td>
			    					<td>(${lecture.classInfo.classPoint}학점) ${lecture.classInfo.classTime }</td>
			    				</tr>
			    			</c:forEach>
			    		</tbody>
		   			</c:when>
		   			<c:otherwise>
		   				<h6>진행중인 강의가 없습니다.</h6>
		   			</c:otherwise>
		   		</c:choose>
		   	</table>
		</div>
</div>
   	
</script>
</body>
</html>