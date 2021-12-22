<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<style>
.table-size-1300{
	margin-top: 100px;
}
.search-align{
	float: right;
}
#divBtn{
	float: right;
}
</style>

<div class="table-size-1300">               
	<h3>신청트랙 리스트</h3>
	
	<div id="searchUI" class="search-align">
		<select name="searchType" class="m-right-5">
			<option value>전체</option>
			<option value="major">학과명</option>
			<option value="member">신청인</option>
		</select>
		<input type="text" name="searchWord" id="input"/>
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5"/>
	</div>
	
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd;">상태</th>
				<th style="border-right: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">서류명</th>
				<th style="border-right: 1px solid #b6bccd;">신청과</th>
				<th style="border-right: 1px solid #b6bccd;">신청인</th>
				<th style="border-right: 1px solid #b6bccd;">신청일</th>
				</th>
			</tr>
		</thead>
		
		<c:set var="docuList" value="${pagingVO.dataList }" />
		
		<tbody>
			<c:choose>
				<c:when test="${not empty docuList }">
					<c:forEach items="${docuList }" var="docu">
						<tr style="text-align:center">
							<input type="hidden" name="docuState" value="${docu.docuState}"/>
							
							<td>
								<c:choose>
									<c:when test="${docu.docuState eq 'Y'}">
										<span class='badge badge-info'>승인</span>
									</c:when>
									<c:otherwise>
										<span class='badge badge-danger'>미승인</span>
									</c:otherwise>
								</c:choose>
							</td>

							<td>
								<c:url value="/trackManage/trackManageDetail.do" var="viewURL">
									<c:param name="docuNo" value="${docu.docuNo}" />
								</c:url>
								<a href="${viewURL }">${docu.docuNo}</a>
							</td>
							
							<td>${docu.docuFilename }</td>
							
<%-- 							<td>${docu.category1.cateName1}</td> --%>
							
							<td>${docu.member1.memMajor}</td>
							
							<td>${docu.member1.memName}</td>
	
							<td>${docu.docuReqdate}</td>
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
	
	<div id="divBtn">
			<a href="${cPath }/trackManage/trackList.do"><input type="button" value="트랙 확인" id="stateBtn" class="btn btn-secondary btn-sm"/></a>
	<!-- 							<input type="button" value="상태변경" id="stateBtn" class="btn btn-secondary btn-sm"/> -->
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

<jsp:include page="/includee/postScript.jsp" />