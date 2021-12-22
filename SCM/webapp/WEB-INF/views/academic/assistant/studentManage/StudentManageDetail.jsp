<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/includee/preScript.jsp" />

<style>
#divBtn{
	float: right;
}
.table-size-1300{
	margin-top: 100px;
}
</style>

<div class="table-size-1300">
<h3>학생 상세페이지</h3>
	<table class="table table-bordered">
		<tr>
			<th>학번</th>
			<td>${member.memId}</td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td>${member.memName}</td>
		</tr>
		
		<tr>
			<th>주민번호</th>
			<td>${member.memReg1} - ${member.memReg2}</td>
		</tr>
		
		<tr>
			<th>전공</th>
	        <td>${member.major1.majorName }</td>
		</tr>
		
		<tr>
			<th>지도교수</th>
			<td>${member.adviser1.memName}</td>
		</tr>
	
		<tr>
			<th>이메일주소</th>
			<td>${member.memMail}</td>
		</tr>
		
		<tr>
			<th>주소</th>
			<td>${member.memAdd1 } ${member.memAdd2 }</td>
		</tr>
		
		<tr>
			<th>핸드폰번호</th>
			<td>${member.memTel}</td>
		</tr>
			
	</table>
	
	<div id="divBtn">
		<c:url value="/studentManage/studentManageUpdate.do" var="updateURL">
			<c:param name="memId" value="${member.memId}" />
		</c:url>
		<input type="button" value="수정" id="modifyBtn" class="btn btn-secondary btn-sm" data-gopage="${updateURL }" />
	</div>
</div>

<form id="updateForm" action="${cPath }/studentManage/studentManageUpdate.do" method="get">
	<input type="hidden" name="memId" value="${member.memId }">
</form>

<script type="text/javascript">
let updateForm = $("#updateForm");

$("#modifyBtn").on("click", function(){
	let ok = $.confirm({
	    title: '학생정보 수정',
	    content: '해당 학생정보를 수정하시겠습니까?',
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