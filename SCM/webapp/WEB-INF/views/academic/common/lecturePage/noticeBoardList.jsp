<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
		<h3>강의 공지사항</h3>
	</div>		
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th>No.</th> 
				<th>제목</th> 
				<th>작성자</th> 
				<th>조회수</th> 
			</tr>
		</thead>
		<c:set var="noticeList" value="${pagingVO.dataList}" />
		<tbody>
			<c:choose>
				<c:when test="${not empty noticeList}">
					<c:forEach items="${noticeList}" var="notice">
					<c:set var="i" value="${i+1}" />
						<tr>
							<td>${i}</td>
							<td>   
							<c:url value="/common/lecturePage/noticeBoardView.do" var="viewURL">
							<c:param name="what" value="${notice.boardNo}"/>
							</c:url>
							<a href="${viewURL}">${notice.boardTitle}</a>
							</td>
							<td>${notice.memNo}</td>	
							<td>${notice.boardHits}</td>	
						</tr>
											
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">등록된 공지글이 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		</table>   
			
		<div class="search-align">
		  <div>			
			<input type="button" class="linkBtn btn btn-secondary btn-sm"                                            
			data-gopage="${pageContext.request.contextPath}/professor/lecturePage/noticeBoardInsert.do?classNo=${pagingVO.searchVO.searchWord}" value="게시글 등록" />
		  </div>
		</div>		
   
	<div id="pagingArea" class="p-center m-bottom-10">            																		    
	${pagingVO.pagingHTML}
	</div>
</div>	
<form id="searchForm" action="${pageContext.request.contextPath}/common/lecturePage/noticeList.do?classNo=${pagingVO.searchVO.searchWord}" >                          
	<input type="hidden" name="page" />	
</form>
    