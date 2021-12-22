<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
.center {
	text-align: center;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
		<h5 style="padding-top: 10px;">공지사항게시판</h5>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="width: 200px;"><spring:message code="board.boWriter" /></th>
				<th><spring:message code="board.boTitle" /></th>
				<th style="width: 80px;"><spring:message code="board.boHit" /></th>
				<th style="width: 200px;"><spring:message code="board.boDate" /></th>
			</tr>
		</thead>

		<c:set var="noticeList" value="${pagingVO.dataList }" />

		<tbody>
			<c:choose>
				<c:when test="${not empty noticeList }">
					<c:forEach items="${noticeList }" var="notice">
						<tr>
							<td>${notice.memNo }</td>
							<td><c:url value="/board/noticeView.do" var="viewURL">
									<c:param name="noticeNo" value="${notice.boardNo }" />
								</c:url> <a href="${viewURL }">${notice.boardTitle }</a> <c:forEach
									begin="1" end="${notice.atchCnt }">
									<img
										src="${pageContext.request.contextPath }/resources/images/attatchment.png"
										style="width: 10px; height: 10px;" />
								</c:forEach></td>
							<td class="center">${notice.boardHits }</td>
							<td class="center">${notice.boardDate }</td>
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

		<tfoot>
			<tr>
				<td colspan="6">
					<div class="search-align">
						<div id="searchUI" class="search-align">
							<select name="searchType">
								<option value>전체</option>
								<option value="writer">작성자</option>
								<option value="title">제목</option>
							</select> 
							<input type="text" name="searchWord" /> 
							<input type="button"value="검색" id="searchBtn" /> 
						</div>
						<div>
							<input type="button" value="새글쓰기"class="linkBtn" data-gopage="${cPath }/board/noticeInsert.do" />
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
</body>
 <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();
	</script>

	
	