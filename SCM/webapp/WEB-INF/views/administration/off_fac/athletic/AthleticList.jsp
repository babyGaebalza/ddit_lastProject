<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>


<c:set var="athleticList" value="${pagingVO.dataList }" />
	<div class="table-size-1300">
	<h3>시설관리 페이지</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">시설명</th>
				<th style="border-right: 1px solid #b6bccd;">수용인원</th>
				<th style="border-right: 1px solid #b6bccd;">예약 가능 여부</th>
			</tr>
		</thead>

	<tbody>
		<c:choose>
			<c:when test="${not empty athleticList }">
				<c:forEach items="${athleticList }" var="athletic">
					<tr style="text-align:center">
						<td>
							<c:url value="/off_fac/athleticView.do" var="viewURL">
								<c:param name="facNo" value="${athletic.facNo }" />
							</c:url>
							<a href="${viewURL }">${athletic.facName }</a>
						</td>
						<td>${athletic.facNumber } 명</td>
						<td>${athletic.facResult }</td>
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
							<select name="searchType" class="m-right-5">
								<option value>전체</option>
								<option value="name">시설명</option>
								<option value="result">예약가능여부</option>
							</select> 
							<input type="text" name="searchWord" onkeyup="enterkey();" /> 
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5" /> 
						</div>
						<div>
							<input type="button" value="시설 추가" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/off_fac/athleticInsert.do" />
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

</body>
</html>