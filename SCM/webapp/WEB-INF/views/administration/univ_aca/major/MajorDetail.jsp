<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />


<div class="">
	<div class="table-size-1300">
		<h3>학과 상세조회 페이지</h3>
	 	<table class="table table-bordered">
			<tr>
				<th>전공명</th>
				<td>${major.majorName }</td>
			</tr>
			<tr>
				<th>단과대명</th>
				<td>${major.collegeName }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${major.majorWriter}</td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td>${major.majorDate}</td>
			</tr>
			<tr>
				<th>등록금</th>
				<td>${major.majorPayDisplay} 원</td>
			</tr>
			<tr>
				<th>학과정원</th>
				<td>${major.majorCount} 명</td>
			</tr>
			<tr>
				<th>최종수정자</th>
				<td>${major.majorFWriter}</td>
			</tr>
			<tr>
				<th>최종변경일</th>
				<td>${major.majorFDate}</td>
			</tr>
			<tr>
				<th>졸업충족학점</th>
				<td>${major.majorPoint} 점</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="학과 정보 수정" id="updateBtn" class="btn btn-secondary btn-sm" />
					
					<input type="button" value="학과 정보 삭제" id="delBtn" class="btn btn-secondary btn-sm"/>
					
					<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm"/>
				</td>
			</tr>
		</table>
	</div>
</div>
 
	<form id="updateForm" action="${cPath }/univ_aca/majorUpdate.do" method="get">
		<input type="hidden" name="majorName" value="${major.majorName }">
	</form>
	
 	<form id="deleteForm" action="${cPath }/univ_aca/majorDelete.do" method="post">
		<input type="hidden" name="majorName" value="${major.majorName }">
	</form>
	
 	<form id="listForm" action="${cPath }/univ_aca/majorList.do" method="post">
		<input type="hidden" name="majorName" value="${major.majorName }">
	</form>
	
	
	<script type="text/javascript">
	let updateForm = $("#updateForm");
	let deleteForm = $("#deleteForm");
	let listForm = $("#listForm");
	
	
	$("#updateBtn").on("click", function() {
		let ok = $.confirm({
			title: '학과 정보 수정',
			content: '해당 학과를 수정하시겠습니까?',
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
			title: '학과 정보 삭제',
		    content: '해당 학과를 삭제하시겠습니까?',
		    buttons: {
				삭제 : function () {
					deleteForm.submit();
					$.alert('해당 학과 삭제가 완료되었습니다.');
				},
				취소 : function () {
				},
			}
		});
	});
	
	$("#listBtn").on("click", function() {
		let ok = $.confirm({
			title: '학과 목록',
		    content: '이전 화면으로 돌아가시겠습니까?',
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
 
</html>