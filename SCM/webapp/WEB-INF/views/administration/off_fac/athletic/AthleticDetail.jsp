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
			<th>시설 번호</th>
			<td>${athletic.facNo}</td>
		</tr>
		<tr>
			<th>시설 코드</th>
			<td>${athletic.facCode}</td>
		</tr>
		<tr>
			<th>시설명</th>
			<td>${athletic.facName}</td>
		</tr>
		<tr>
			<th>수용인원수</th>
			<td>${athletic.facNumber} 명</td>
		</tr>
		<tr>
			<th>예약가능여부</th>
			<td>${athletic.facResult}</td>
		</tr>
		<tr>
			<th>시설사용비용</th>
			<td>${athletic.facPayDisplay} 원</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="시설 수정" id="updateBtn" class="btn btn-secondary btn-sm" />

				<input type="button" value="시설 삭제" id="delBtn" class="btn btn-secondary btn-sm" />
				
				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm" />
				
			</td>
		</tr>
	</table>
</div>
	
	<form id="updateForm" action="${cPath }/off_fac/athleticUpdate.do" method="get">
		<input type="hidden" name="facNo" value="${athletic.facNo }">
	</form>
 
 	<form id="deleteForm" action="${cPath }/off_fac/athleticDelete.do" method="post">
		<input type="hidden" name="facNo" value="${athletic.facNo }">
	</form>
	
 	<form id="listForm" action="${cPath }/off_fac/athleticList.do" method="post">
		<input type="hidden" name="facNo" value="${athletic.facNo }">
	</form>
	 
	<script type="text/javascript">
	let updateForm = $("#updateForm");
	let deleteForm = $("#deleteForm");
	let listForm = $("#listForm");
		
	$("#updateBtn").on("click", function() {
		let ok = $.confirm({
			title: '시설 수정',
			content: '해당 시설을 수정하시겠습니까?',
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
			title: '시설 삭제',
		    content: '해당 시설을 삭제하시겠습니까?',
		    buttons: {
				삭제 : function () {
					deleteForm.submit();
					$.alert('시설 삭제가 완료되었습니다.');
				},
				취소 : function () {
				},
			}
		});
	});
	
	$("#listBtn").on("click", function() {
		let ok = $.confirm({
			title: '목록으로 돌아가기',
		    content: '목록으로 돌아가시겠습니까?',
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
</html>