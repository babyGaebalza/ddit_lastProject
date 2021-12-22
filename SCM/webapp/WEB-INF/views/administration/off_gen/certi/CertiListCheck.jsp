<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<div class="table-size-1300">
	<h3>발급 리스트 확인</h3>
	<c:set var="mem" value="${member}" />
	<input type="hidden" name="memId" value="${mem.memId }">
	
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd;">상태</th>
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">증명서명</th>
				<th style="border-right: 1px solid #b6bccd;">발급사유</th>
				<th style="border-right: 1px solid #b6bccd;">수량</th>
			</tr>
		</thead>
		<c:set var="certiList" value="${pagingVO.dataList }" />
		<tbody>
			<c:choose>
				<c:when test="${not empty certiList }">
					<c:forEach items="${certiList }" var="certi">
						<tr style="text-align:center">
							<input type="hidden" name="certiState" value="${certi.certiState}"/>
							
							<td>
								<c:choose>
									<c:when test="${certi.certiState eq 'Y'}">
										<span class='badge badge-info'>승인</span>
									</c:when>
									<c:otherwise>
										<span class='badge badge-danger'>미승인</span>
									</c:otherwise>
								</c:choose>
							
							</td>

							<td>${certi.certiNo}</td>
							
							<td>${certi.category1.cateName1}</td>

							<td>${certi.certiReason}</td>
							
							<td>${certi.certiCount}개</td>
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
