<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<style>
.search-align{
	float: right;
}
.table-size-1300{
	margin-top: 100px;
}
</style>

<div class="table-size-1300">
	<h3>학과 공지사항</h3>
	
	<c:set var="mem" value="${member }" />
	<input type="hidden" name="majorCode" value="${mem.memMajor }">
	<input type="hidden" name="memNo" value="${mem.memId}">
	<input type="hidden" name="boardFwriter" value="${mem.memId}">
	
	<div id="searchUI" class="search-align">
		<select name="searchType">
			<option value>전체</option>
			<option value="writer">작성자</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select> 
		<input type="text" name="searchWord" /> 
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm"/> 
	</div>
	
	<table class="table table-bordered">  
		<thead>
			<tr class="tr-style">
				<th><spring:message code="board.boWriter" /></th>
				<th><spring:message code="board.boTitle" /></th>
				<th><spring:message code="board.boHit" /></th>
				<th><spring:message code="board.boDate" /></th>
			</tr>
		</thead>
		
		<c:set var="noticeList" value="${pagingVO.dataList }" />
	
		<tbody>
			<c:choose>
				<c:when test="${not empty noticeList }">
					<c:forEach items="${noticeList }" var="notice">
						<tr>
							<td>${notice.memNo }</td>
							<td>
								<c:url value="/departmentManage/departmentManageDetail.do" var="viewURL">
									<c:param name="noticeNo" value="${notice.boardNo }" />
								</c:url>
								<a href="${viewURL }">${notice.boardTitle }</a>
								<c:forEach begin="1" end="${notice.atchCnt }">
									<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" style="width: 10px; height: 10px;"/>
								</c:forEach>
							</td>
							<td>${notice.boardHits }</td>
							<td>${notice.boardDate }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<div class="search-align">
			<div>
				<input type="button" value="새글쓰기" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/departmentManage/departmentManageInsert.do" />
			</div>
	</div>
	
	<div id="pagingArea" class="p-center m-bottom-10">
		${pagingVO.pagingHTML }
	</div>
</div>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>

<jsp:include page="/includee/postScript.jsp" />