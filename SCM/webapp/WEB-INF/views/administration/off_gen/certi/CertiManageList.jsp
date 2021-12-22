<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<style>
.table-size-1300{
	margin-top: 100px;
}
.search-align{
	float: right;
}
</style>

<div class="table-size-1300">
<h3>출력 가능한 증명서</h3>

<!-- 	<select name="cateCode" id="cateCode"> -->
<!-- 		<option value>증명서 선택</option> -->
<!-- 	</select> -->
<!-- 	<input type="button" value="증명서 발급" class="linkBtn btn btn-secondary btn-sm" id="pdf"/> -->
	
	<div id="searchUI" class="search-align">
		<select name="searchType" class="m-right-5">
			<option value>전체</option>
			<option value="code">증명서코드</option>
			<option value="name">증명서명</option>
		</select>
		<input type="text" name="searchWord" />
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5"/>
	</div>
	
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">증명서코드</th>
				<th style="border-right: 1px solid #b6bccd;">증명서명</th>
			</tr>
		</thead>
		<c:set var="cateList" value="${pagingVO.dataList }" />
		<tbody>
			<c:choose>
				<c:when test="${not empty cateList }">
					<c:forEach items="${cateList }" var="cate">
						<tr style="text-align:center">
							<c:url value="/certificateManage/certiPdf.do" var="viewURL">
								<c:param name="cateCode" value="${cate.cateCode}" />
							</c:url>
							<td><a href="${viewURL }" target='_blank'>${cate.cateCode}</a></td>
							
<%-- 							<td>${cate.cateCode}</td> --%>
							
							<td>${cate.cateName1}</td>
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
