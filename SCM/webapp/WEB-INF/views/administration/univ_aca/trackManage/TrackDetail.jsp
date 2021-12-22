<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="/includee/preScript.jsp" />

<style>
.table-size-1300{
	margin-top: 100px;
}
</style>

<div class="table-size-1300">
<h3>트랙 상세페이지</h3>

<table class="table table-bordered">
	<tr>
		<th>번호</th>
		<td>${track.trackNo}</td>
	</tr>
	
	<tr>
		<th>학과코드</th>
		<td>${track.majorCode}</td>
	</tr>
	
	<tr>
		<th>학과명</th>
		<td>${track.majorName}</td>
	</tr>
	
	<tr>
		<th>트랙명</th>
		<td>${track.trackName}</td>
	</tr>
	
	<c:forEach items="${list}" var="list">
	<tr>
		<th>트랙과목</th>
  	 	<td><c:out value="${list.classNo}"/></td>
    </tr>
	</c:forEach>	
	
	<tr>
		<th>생성일</th>
		<td>${track.trackDate}</td>
	</tr>
	
	<tr>
		<th>상태</th>
		<c:choose>
			<c:when test="${track.trackDelete eq 'N'}">
				<td>미삭제</td>
			</c:when>
			<c:otherwise>
				<td>삭제</td>
			</c:otherwise>
		</c:choose>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td colspan="2"> -->
<%-- 			<c:url value="/trackManage/trackUpdate.do" var="updateURL"> --%>
<%-- 				<c:param name="trackNo" value="${track.trackNo}" /> --%>
<%-- 			</c:url> --%>
<%-- 			<input type="button" value="수정" id="modifyBtn" data-gopage="${updateURL }" /> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
</table>
</div>
<%-- <form id="updateForm" action="${cPath }/trackManage/trackUpdate.do" method="get"> --%>
<%-- 	<input type="hidden" name=trackNo value="${track.trackNo}"> --%>
<!-- </form> -->

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