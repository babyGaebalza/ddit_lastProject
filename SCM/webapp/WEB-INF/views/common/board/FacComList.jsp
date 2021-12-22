<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
.tColor {
	background-color: mintcream;
}

.tdLine:hover {
    color: #07c6ff;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
	<h3>시설민원게시판</h3>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th class="tColor">작성자</th>
				<th class="tColor">제목</th>
				<th class="tColor">조회수</th>
				<th class="tColor">작성일</th>
			</tr>
		</thead>
			
	<c:set var="fcList" value="${pagingVO.dataList }" />
		<tbody>
				<c:forEach items="${fcList }" var="fc">
			<tr class="tdLine">
				<td>${fc.memNo }</td>
				<td>
					<c:url value="facComView.do" var="viewURL">
						<c:param name="fcNo" value="${fc.boardNo }" />
					</c:url>
					<a href="${viewURL }">${fc.boardTitle }</a>
				</td>
				<td>${fc.boardHits }</td>
				<td>${fc.boardDate }</td>				
			</tr>
				</c:forEach>
		</tbody>

		<tfoot>
			<tr>
				<td colspan="6">
				<div class="search-align">
					<div id="searchUI" class="form-inline">
						<select name="searchType" style="height: 34px;" class="m-right-5">
							<option value>전체</option>
							<option value="name">작성자</option>
							<option value="title">제목</option>
						</select>
						<input type="text" name="searchWord" class="form-control mr-3" />
						<input type="button" value="검색" id="searchBtn" class="btn btn-outline-secondary"/>
					</div>
					
					<div>
						<input type="button" value="새민원작성" id="insertBtn" class="btn btn-outline-success"/>
						<input type="button" value="돌아가기" id="backBtn" class="btn btn-outline-dark"/>
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

	
	 <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
	 
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();
		
		
		$("#insertBtn").on("click", function(){
			location.href="${cPath}/board/facComForm.do";
		});

		$("#backBtn").on("click", function(){
			location.href="${cPath}/admin/main.do";
		});
		
	</script>
