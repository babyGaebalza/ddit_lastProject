<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />

<div class="table-size-1300">
<h3>상세페이지 입니다.</h3>
 
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
			<th>영문이름</th>
			<td>${member.memEname}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${member.memBirth}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${member.memGender}</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${member.memTel}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>입학일</th>
			<td>${member.memEntdate}</td>
		</tr>
		<tr>
			<th>주전공</th>
			<td>${member.memMajor}</td>
		</tr>
		<tr>
			<th>지도교수</th>
			<td>${member.memAdviser}</td>
		</tr>
		<tr>
			<th>졸업예정일</th>
			<td>${member.memGradate}</td>
		</tr>
		<tr>
			<th>졸업여부</th>
			<td>${member.memGraduate}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="졸업정보수정" id="updateBtn" class="btn btn-secondary btn-sm" />
				
				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm" />
			</td>
		</tr>
	</table>
</div>
	
	<form id="updateForm" action="${cPath }/univ_man/graduateUpdate.do" method="get">
		<input type="hidden" name="memId" value="${member.memId }">
	</form>
	
	<form id="listForm" action="${cPath }/univ_man/graduateList.do" method="post">
		<input type="hidden" name="memId" value="${member.memId }">
	</form>
 
	<script type="text/javascript">
	let updateForm = $("#updateForm");
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
		
	$("#listBtn").on("click", function() {
		let ok = $.confirm({
			title: '목록이동',
			content: '이전 목록으로 돌아가시겠습니까?',
			buttons: {
				수정 : function () {
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