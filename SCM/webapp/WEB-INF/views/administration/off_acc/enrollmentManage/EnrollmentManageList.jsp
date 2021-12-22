<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<div class="table-size-1300">
	<h3>학생리스트</h3>
	
	<table class="table table-bordered">  
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">학번</th>
				<th style="border-right: 1px solid #b6bccd;">이름</th>
				<th style="border-right: 1px solid #b6bccd;">학과</th>
				<th style="border-right: 1px solid #b6bccd;">등록금 납부 여부</th>
			</tr>
		</thead>
		
		<c:set var="enrollList" value="${pagingVO.dataList }" />
	
		<tbody>
			<c:choose>
				<c:when test="${not empty enrollList }">
					<c:forEach items="${enrollList }" var="enroll">
						<tr>
							<td>${enroll.tuiNo }</td>
							<td>${enroll.member1.memId }</td>
							<td>${enroll.member1.memName }</td>
							<td>${enroll.major1.majorName }</td>
							
							<c:choose>
								<c:when test="${enroll.tuiPayment eq 'Y'}">
									<td><span class='badge badge-info'>납부완료</span></td>
								</c:when>
								<c:otherwise>
									<td><span class='badge badge-danger'>미납</span></td>
								</c:otherwise>
							</c:choose>
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
									<option value="writer">학번</option>
									<option value="title">전공</option>
									<option value="title">납부여부</option>
								</select> 
								<input type="text" name="searchWord" /> 
								<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm"/> 
							</div>
						</div>
					</td>
				</tr>
			</tfoot>
	</table>
</div>

<div id="pagingArea" class="p-center m-bottom-10">
	${pagingVO.pagingHTML }
</div>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>

<jsp:include page="/includee/postScript.jsp" />