<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="table-size-1300">
	<div class="board-title">
		<h5 style="padding-top: 10px;">트랙관리 페이지</h5>
	</div>
<table class="table table-bordered">
	<thead>
		<tr class="tr-style">
			<th>No.</th> 
			<th>트랙명</th> 
			<th>트랙번호</th> 
			<th>삭제여부</th> 
		</tr>
	</thead>
	<c:set var="trackList" value="${pagingVO.dataList}" />
	
	<tbody>
		<c:choose>
			<c:when test="${not empty trackList}">
				<c:forEach items="${trackList}" var="track">
				<c:set var="i" value="${i+1}" />
					<tr>
						<td>${i}</td>
						<td>
						<c:url value="/professor/trackView.do" var="viewURL">
						<c:param name="what" value="${track.trackNo}"/>
						
						</c:url>
						<a href="${viewURL}">${track.trackName}</a>
						</td>
						<td>${track.trackNo}</td>
						<td>${track.trackDelete}</td>
					</tr>
										
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">해당 전공에 트랙설정이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<div class="search-align">
					<div id="searchUI" class="search-align">
						<input type="text" name="searchWord2" value="키워드 검색 " />
						<input type="button" id="searchBtn" class="btn btn-secondary btn-sm" value="검색" />
						<input type="button" class="linkBtn btn btn-secondary btn-sm"    
							data-gopage="${pageContext.request.contextPath}/common/document/documentManage.do" value="트랙추가" />
					</div>                 <%-- postScript에 commom.js에서 처리 --%>    <!-- 결재문서 입력 컨트롤러로 이동 -->
				</div>

				<div id="pagingArea" class="p-center m-bottom-10">
					${pagingVO.pagingHTML}
				</div>
			</td>
		</tr>
	</tfoot>
</table>
</div>
<form id="searchForm">                           <%--페이징 처리 --%>
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />	
</form>
<script type="text/javascript">	
	$("[name='searchWord2']").val("${searchVO.searchWord2}");
	let searchForm = $("#searchForm").paging();
</script>
