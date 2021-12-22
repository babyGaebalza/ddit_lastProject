<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/includee/preScript.jsp" />
<div class="table-size-1300">
	<h3>상세페이지 입니다.</h3>
 	<table class="table table-bordered">
		<tr>
			<th>업체명</th>
			<td>${business.bussName}</td>
		</tr>
		<tr>
			<th>업체 업무</th>
			<td>${business.bussJob}</td>
		</tr>
		<tr>
			<th>계약시작일</th>
			<td>${business.bussSdate}</td>
		</tr>
		<tr>
			<th>계약종료일</th>
			<td>${business.bussEdate}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="해당 업체 수정" id="updateBtn" class="btn btn-secondary btn-sm" />

				<input type="button" value="해당 업체 삭제" id="delBtn" class="btn btn-secondary btn-sm" />

				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm" />
				
			</td>
		</tr>
	</table>
</div>
	
	<form id="updateForm" action="${cPath }/stu_sup/stuSupportUpdate.do" method="get">
		<input type="hidden" name="bussNo" value="${business.bussNo }">
	</form>
 
 	<form id="deleteForm" action="${cPath }/stu_sup/stuSupportDelete.do" method="post">
		<input type="hidden" name="bussNo" value="${business.bussNo }">
	</form>
 
 	<form id="listForm" action="${cPath }/stu_sup/stuSupportList.do" method="post">
		<input type="hidden" name="bussNo" value="${business.bussNo }">
	</form>
	 
	<script type="text/javascript">
	let updateForm = $("#updateForm");
	let deleteForm = $("#deleteForm");
	let listForm = $("#listForm");
		
	$("#updateBtn").on("click", function() {
		let ok = $.confirm({
			title: '게시글 수정',
			content: '해당 게시글을 수정하시겠습니까?',
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
		    content: '해당 게시글을 삭제하시겠습니까?',
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
				이동 : function () {
					listForm.submit();
				},
				취소 : function () {
				},
			}
		});
	});
		
	</script>
	<jsp:include page="/includee/postScript.jsp" />