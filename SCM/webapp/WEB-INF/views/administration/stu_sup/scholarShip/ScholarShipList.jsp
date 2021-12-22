<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<div class="table-size-1300">
	<h3>장학생</h3>
	<table class="table table-bordered tb-txt">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">장학코드</th>
				<th style="border-right: 1px solid #b6bccd;">장학금 수령인</th>
				<th style="border-right: 1px solid #b6bccd;">추천인</th>
				<th style="border-right: 1px solid #b6bccd;">추천/신청 사유</th>
				<th style="border-right: 1px solid #b6bccd;">상태</th>
			</tr>
		</thead>

	<c:set var="scholarShipList" value="${pagingVO.dataList }" />

		<tbody>
			<c:choose>
				<c:when test="${not empty scholarShipList }">
					<c:forEach items="${scholarShipList }" var="scholar">
						<tr>
							<td>
								<c:url value="/stu_sup/scholarShipView.do" var="viewURL">
									<c:param name="schCode" value="${scholar.schCode }" />
								</c:url>
								<a href="${viewURL }">${scholar.schMem }</a>
							</td>
							<td>${scholar.schRec }</td>
							<td>${scholar.schReason }</td>
							<td>${scholar.schState }</td>
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
					
		<div id="searchUI" class="search-align">
			<select name="searchType" class="m-right-5">
				<option value>전체</option>
				<option value="code">장학코드</option>
				<option value="state">상태</option>
			</select> 
			<input type="text" name="searchWord" onkeyup="enterkey();"/> 
			<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
		</div>
		<div>
			<input type="button" value="장학생 추가" data-gopage="${cPath }/stu_sup/scholarShipInsert.do" class="linkBtn btn btn-secondary btn-sm" />
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
</body>
</html>