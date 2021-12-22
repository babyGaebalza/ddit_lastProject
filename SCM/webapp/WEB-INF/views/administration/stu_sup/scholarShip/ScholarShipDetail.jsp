<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="table-size-1300">
	<h3>상세페이지 입니다.</h3>
 	<table class="table table-bordered">
		<tr>
			<th>장학금 종류</th>
			<td>${scholar.schCode}</td>
		</tr>
		<tr>
			<th>장학 금액</th>
			<td>${scholar.schAmount}</td>
		</tr>
		<tr>
			<th>상태</th>
			<td>${scholar.schState}</td>
		</tr>
		<tr>
			<th>비고</th>
			<td>${scholar.schNote}</td>
		</tr>
		<tr>
			<th>추천 / 신청사유</th>
			<td>${scholar.schReason}</td>
		</tr>
		<tr>
			<th>추천인</th>
			<td>${scholar.schRec}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="장학생 수정" id="updateBtn" class="linkBtn btn btn-secondary btn-sm"/>

				<input type="button" value="장학생 삭제" id="delBtn" class="linkBtn btn btn-secondary btn-sm"/>

				<input type="button" value="목록" id="listBtn" class="linkBtn btn btn-secondary btn-sm"/>
				
			</td>
		</tr>
	</table>
	
	<form id="updateForm" action="${cPath }/stu_sup/scholarShipUpdate.do" method="get">
		<input type="hidden" name="schCode" value="${scholar.schCode }">
	</form>
 
 	<form id="deleteForm" action="${cPath }/stu_sup/scholarShipDelete.do" method="post">
		<input type="hidden" name="schCode" value="${scholar.schCode }">
	</form>
 
 	<form id="listForm" action="${cPath }/stu_sup/scholarShipList.do" method="post">
		<input type="hidden" name="schCode" value="${scholar.schCode }">
	</form>
	 
	<script type="text/javascript">
	let updateForm = $("#updateForm");
	let deleteForm = $("#deleteForm");
	let listForm = $("#listForm");
		
	$("#updateBtn").on("click", function() {
		let ok = $.confirm({
			title: '게시글 수정',
			content: '해당 장학생을 수정하시겠습니까?',
			buttons: {
				수정 : function () {
					updateForm.submit();
				},
				취소 : function () {
				},
			}
		});
	});
	
	$("#delBtn").on("click", function() {
		let ok = $.confirm({
			title: '게시글 삭제',
		    content: '해당 리스트를 삭제하시겠습니까?',
		    buttons: {
				삭제 : function () {
					deleteForm.submit();
					$.alert('글 삭제가 완료되었습니다.');
				},
				취소 : function () {
				},
			}
		});
	});
	
	$("#listBtn").on("click", function() {
		let ok = $.confirm({
			title: '목록으로 이동',
		    content: '목록으로 이동하시겠습니까?',
		    buttons: {
				목록 : function () {
					listForm.submit();
				},
				취소 : function () {
				},
			}
		});
	});
		
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>