<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<c:set var="complaintList" value="${pagingVO.dataList }" />
<div class="table-size-1300">
	<h3>문의게시판</h3>
	<table class="table table-bordered tb-txt">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">제목</th>
				<th style="border-right: 1px solid #b6bccd;">작성자</th>
				<th style="border-right: 1px solid #b6bccd;">작성일</th>
				<th style="border-right: 1px solid #b6bccd;">조회수</th>
			</tr>
		</thead>

		<tbody>
			<c:choose>
				<c:when test="${not empty complaintList }">
					<c:forEach items="${complaintList }" var="complaint">
						<tr>
							<td style="text-align: left">
								<c:url value="/stu_sup/complaintView.do" var="viewURL">
									<c:param name="boardNo" value="${complaint.boardNo }" />
								</c:url>
								<a href="${viewURL }">${complaint.boardTitle }</a>
							</td>
							<td>${complaint.boardFwriter }</td>
							<td>${complaint.boardDate }</td>
							<td>${complaint.boardHits }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="4">
					<div class="search-align">
						<div id="searchUI" class="search-align">
							<select name="searchType" class="m-right-5">
								<option value>전체</option>
								<option value="writer">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select> 
							<input type="text" name="searchWord" onkeyup="enterkey();"/> 
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5" /> 
						</div>
						<div>
							<input type="button" value="새글쓰기" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/stu_sup/complaintInsert.do" />
						</div>
					</div>
					
					
					<div id="pagingArea" class="p-center m-bottom-10">
						${pagingVO.pagingHTML }
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
	
	
	<form id="searchForm">
		<input type="hidden" name="searchType" />
		<input type="hidden" name="searchWord" />
		<input type="hidden" name="page" />
	</form>
	
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();
		
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	<jsp:include page="/includee/postScript.jsp" />