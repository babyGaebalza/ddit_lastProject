<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
<div class="table-size-1300">
	<h3>업체 관리 리스트</h3>
	<table class="table table-bordered tb-txt">

	<c:set var="stuSupportList" value="${pagingVO.dataList }" />
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">업체명</th>
				<th style="border-right: 1px solid #b6bccd;">업체업무</th>
				<th style="border-right: 1px solid #b6bccd;">계약시작일</th>
				<th style="border-right: 1px solid #b6bccd;">계약종료일</th>
			</tr>
		</thead>

		<tbody>
			<c:choose>
				<c:when test="${not empty stuSupportList }">
					<c:forEach items="${stuSupportList }" var="business">
						<tr>
							<td>
								<c:url value="/stu_sup/stuSupportView.do" var="viewURL">
									<c:param name="bussNo" value="${business.bussNo }" />
								</c:url>
								<a href="${viewURL }">${business.bussName }</a>
							</td>
							<td>${business.bussJob }</td>
							<td>${business.bussSdate }</td>
							<td>${business.bussEdate }</td>
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
				<option value="name">업체명</option>
				<option value="job">업체업무</option>
			</select> 
			<input type="text" name="searchWord" onkeyup="enterkey();" class="m-right-5" /> 
			<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm" /> 
		</div>
		<div>
			<input type="button" value="업체추가" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/stu_sup/stuSupportInsert.do" />
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
</html>