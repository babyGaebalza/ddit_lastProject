<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="table table-bordered">	
<table class="table table-bordered">
		<tr>
			<th>트랙구분번호</th>
			<td>${track.trackNo}</td>
		</tr>
		<tr>
			<th>학과코드</th>
			<td>${track.majorCode}</td>
		</tr>
		<tr>
			<th>확과명</th>
			<td>${track.majorName}</td>
		</tr>
		<tr>
			<th>트랙명</th>
			<td>${track.trackName}</td>
		</tr>
		<tr>
			<th>트랙삭제여부</th>
			<td>${track.trackDelete}</td>
		</tr>
		<tr>
			<th>트랙생성일자</th>
			<td>${track.trackDate}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:url value="/common/document/documentManage.do" var="updateURL">
		    </c:url>
		    <input type="button" class="linkBtn btn btn-secondary btn-sm"" data-gopage="${updateURL}"
				value="트랙 수정" /></td>
		</tr>
	</table>
</div>
<script>
$('#trackManage').on("click", function() {
	$.confirm({
		title: '트랙관리',
	    content: '선택한 트랙을 수정/삭제신청 하시겠습니까? ',
	    buttons: {
	         예: function () {
	            $.alert('트랙 변경신청 양식으로 이동합니다.');  
	            location.replace("${pageContext.request.contextPath}/common/document/documentManage.do") 
	         },
	        아니오: function () {
	            $.alert('이전화면으로 돌아갑니다.');
	        }
	    }
	});
})
</script>