<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="/includee/preScript.jsp" />

<style>
.table-size-1300{
	margin-top: 100px;
}
#divBtn{
	float: right;
}
</style>

<div class="table-size-1300">
<h3>신청트랙 상세페이지</h3>
<table class="table table-bordered">
	<tr>
		<th>번호</th>
		<td>
			<c:url value="/docuPdf.do" var="viewURL">
				<c:param name="docuNo" value="${docu.docuNo}" />
			</c:url>
			<a href="${viewURL }" onclick="window.open(this.href, '_blank', 'width=800, height=600, left=0'); return false;">${docu.docuNo}</a>
		</td>
	</tr>
	
	<tr>
		<th>서류코드</th>
		<td>${docu.category1.cateCode}</td>
	</tr>
	
	<tr>
		<th>서류명</th>
		<td>${docu.docuFilename}</td>
	</tr>
	
	<tr>
		<th>서류내용</th>
		<td>${docu.docuCont}</td>
	</tr>
	
	<tr>
		<th>신청인</th>
		<td>${docu.member1.memName}</td>
	</tr>
	
	<tr>
		<th>신청일</th>
		<td>${docu.docuReqdate}</td>
	</tr>
	
	<tr>
		<th>상태</th>
		<c:choose>
			<c:when test="${docu.docuState eq 'Y'}">
				<td>승인</td>
			</c:when>
			<c:otherwise>
				<td>미승인</td>
			</c:otherwise>
		</c:choose>
	</tr>
	
	<tr>
		<th>결재자</th>
		<c:choose>
			<c:when test="${docu.docuApf eq null}">
				<td>없음</td>
			</c:when>
			<c:otherwise>
				<td>${docu.docuApf}</td>
			</c:otherwise>
		</c:choose>
	</tr>
</table>
<c:url value="/trackManage/trackManageInsert.do" var="updateURL">
	<c:param name="docuNo" value="${docu.docuNo}" />
</c:url>
	<div id="divBtn">
	<input type="button" value="트랙 등록" id="modifyBtn" class="btn btn-secondary btn-sm" data-gopage="${updateURL }" />
	</div>
</div>

<form id="updateForm" action="${cPath }/trackManage/trackManageInsert.do" method="get">
	<input type="hidden" name="docuNo" value="${docu.docuNo}">
</form>

<script type="text/javascript">
let updateForm = $("#updateForm");

$("#modifyBtn").on("click", function(){
	let ok = $.confirm({
	    title: '신청트랙 등록',
	    content: '해당 트랙을 등록하시겠습니까?',
	    buttons: {
	      	등록: function () {
	        	updateForm.submit();
	        },
	        취소: function () {
	        },
	    }
	});
});

</script>

<jsp:include page="/includee/postScript.jsp"/>