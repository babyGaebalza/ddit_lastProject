<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>


<div class="table-size-1300">
<h3>학과 학생리스트</h3>
	<c:set var="mem" value="${member}" />
	${mem.memMajor }
	<input type="hidden" name="memMajor" value="${mem.memMajor }">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전공</th>
			</tr>
		</thead>
		<c:set var="memberList" value="${pagingVO.dataList }" />
		<tbody>
			<c:choose>
				<c:when test="${not empty memberList }">
					<c:forEach items="${memberList }" var="member">
						<tr>
							<td>
								<c:url value="/studentManage/studentManageDetail.do" var="viewURL">
									<c:param name="memId" value="${member.memId}" />
								</c:url>
								<a href="${viewURL }">${member.memId}</a>
							</td>
							<td>${member.memName}</td>
							
							<td>${member.major1.majorName }</td>
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
								<option value="number">학번</option>
								<option value="name">이름</option>
							</select>
							<input type="text" name="searchWord" />
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm"/>
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

<jsp:include page="/includee/postScript.jsp" />