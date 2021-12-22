<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<h3>결재할 리스트 출력</h3>
<table class="table">
	<thead>
		<tr>
			<th>문서번호</th> 
			<th>서류종류</th>
			<th>작성자</th> 
			<th>문서명</th> 
		</tr>
	</thead>
	<c:set var="todoDocuList" value="${pagingVO.dataList}" />
	<tbody>
		<c:choose>
			<c:when test="${not empty todoDocuList}">
				<c:forEach items="${todoDocuList}" var="docu">
				<c:set var="i" value="${i+1}" />
					<tr>
						<td> 
						<c:url value="/common/pdf/onePDF.do" var="viewURL">
						<c:param name="docuNo" value="${docu.docuNo}"/>
						</c:url>
						<a href="${viewURL}" target='_blank'>
						${docu.docuNo}</a></td>
						<td>${docu.docuCode}</td>
						<td>${docu.docuReq}</td>
						<td>${docu.docuFilename}</td>						
					</tr>					
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">해당하는 문서 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<div id="pagingArea">
					${pagingVO.pagingHTML}
				</div>                
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" action="${pageContext.request.contextPath}/common/document/documentSign.do" >                          
	<input type="hidden" name="page" />	
</form>