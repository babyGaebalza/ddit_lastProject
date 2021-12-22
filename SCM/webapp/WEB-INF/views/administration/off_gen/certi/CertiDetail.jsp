<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="/includee/preScript.jsp" />

<div class="table-size-1300">
	<h3>증명서 발급 상세페이지</h3>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<td>${certi.certiNo}</td>
		</tr>
		
		<tr>
			<th>증명서코드</th>
			<td>${certi.category1.cateCode}</td>
		</tr>
		
		<tr>
			<th>증명서명</th>
			<td>${certi.category1.cateName1}</td>
		</tr>
		
		<tr>
			<th>발급사유</th>
			<td>${certi.certiReason}</td>
		</tr>
		
		<tr>
			<th>신청인</th>
			<td>${certi.certiReq}</td>
		</tr>
		
		<tr>
			<th>수량</th>
			<td>${certi.certiCount}개</td>
		</tr>
		
		<tr>
			<th>상태</th>
			<c:choose>
				<c:when test="${certi.certiState eq 'Y'}">
					<td>승인</td>
				</c:when>
				<c:otherwise>
					<td>미승인</td>
				</c:otherwise>
			</c:choose>
			
		</tr>
	</table>
</div>

<script type="text/javascript">
let updateForm = $("#updateForm");

$("#modifyBtn").on("click", function(){
	let ok = $.confirm({
	    title: '증명서발급 수정',
	    content: '해당 증명서발급을 수정하시겠습니까?',
	    buttons: {
	        수정: function () {
	        	updateForm.submit();
	        },
	        취소: function () {
	        },
	    }
	});
});
 </script>

<jsp:include page="/includee/postScript.jsp"/>
