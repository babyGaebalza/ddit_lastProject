<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<style>
.table-size-1300{
	margin-top: 100px;
}
.search-align{
	float: right;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
	<h3>과제게시글 리스트 출력</h3>
	</div>
	<table class="table table-bordered">
	<thead>
		<tr class="tr-style">
			<th>No.</th> 
			<th>제목</th> 
			<th>조회수</th> 
			<th>작성일</th> 
			<th>과제마감일</th> 			
		</tr>
	</thead>
	<c:set var="taskList" value="${pagingVO.dataList}" />
	<tbody>
		<c:choose>
			<c:when test="${not empty taskList}">
				<c:forEach items="${taskList}" var="task">
				<c:set var="i" value="${i+1}" />
					<tr>
						<td>${i}</td>
						<td> 
						<c:url value="/common/lecturePage/reportBoardView.do" var="viewURL">
						<c:param name="what" value="${task.boardNo}"/>
						</c:url>
						<a href="${viewURL}">${task.boardTitle}</a>
						</td>
						<td>${task.boardHits}</td>	
						<td>${task.boardDate}</td>	
						<td>${task.boardDeadline}</td>	     
					</tr>
										
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">등록된 과제 게시글이 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	</table>
	
	<div class="search-align">
		 <div>
		 	<security:authorize access="hasRole('US02')">
		 	
		 	<input type="button" class="linkBtn btn btn-secondary btn-sm"                                              
data-gopage="${pageContext.request.contextPath}/professor/lecturePage/reportBoardInsert.do" value="게시글 등록" />
		 	</security:authorize>
	 	 </div>
	</div>	
	<div id="pagingArea" class="p-center m-bottom-10">            																		    
	${pagingVO.pagingHTML}
	</div>

	
</div>
<form id="searchForm">                          
	<input type="hidden" name="page" />	
</form>
