<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- <jsp:include page="/includee/preScript.jsp" /> --%>

<div class="">


<c:set var="majorList" value="${pagingVO.dataList }" />
	<div class="table-size-1300">
	<h3>학과 관리 페이지</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학과명</th>
				<th style="border-right: 1px solid #b6bccd;">작성자</th>
				<th style="border-right: 1px solid #b6bccd;">등록금</th>
				<th style="border-right: 1px solid #b6bccd;">학과정원</th>
				<th style="border-right: 1px solid #b6bccd;">최종수정자</th>
				<th style="border-right: 1px solid #b6bccd;">최종변경일</th>
				<th style="border-right: 1px solid #b6bccd;">졸업충족학점</th>
			</tr>
		</thead>

	<tbody>
		<c:choose>
			<c:when test="${not empty majorList }">
				<c:forEach items="${majorList }" var="major">
					<tr style="text-align:center">
						<td>
							<c:url value="/univ_aca/majorView.do" var="viewURL">
								<c:param name="majorName" value="${major.majorName }" />
							</c:url>
							<a href="${viewURL }">${major.majorName }</a>
						</td>
						<td>${major.majorWriter }</td>
						<td>${major.majorPayDisplay } 원</td>
						<td>${major.majorCount } 명</td>
						<td>${major.majorFWriter }</td>
						<td>${major.majorFDate }</td>
						<td>${major.majorPoint } 점</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">검색 조건에 맞는 게시글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</div>

		<tfoot>
			<tr>
				<td colspan="7">
					<div class="search-align">
						<div id="searchUI" class="search-align">
							<select name="searchType" class="m-right-5">
								<option value>전체</option>
								<option value="major">전공명</option>
								<option value="colleage">단과명</option>
								<option value="writer">작성자</option>
							</select> 
							<input type="text" name="searchWord" onkeyup="enterkey();" /> 
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5"" /> 
						</div>
						<div>
							<input type="button" value="새 학과 등록" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/univ_aca/majorInsert.do" />
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
		
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</html>