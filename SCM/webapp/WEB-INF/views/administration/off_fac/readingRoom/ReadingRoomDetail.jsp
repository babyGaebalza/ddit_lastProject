<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
 <div class="table-size-1300">
 	<table class="table table-bordered">
	<h3>열람실 상세페이지</h3>
		<tr>
			<th>열람실</th>
			<td>${readingRoom.facName}</td>
		</tr>
		<tr>
			<th>수용인원</th>
			<td>${readingRoom.facNumber}</td>
		</tr>
		<tr>
			<th>예약가능여부</th>
			<td>${readingRoom.facResult}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="열람실 수정" id="updateBtn" class="btn btn-secondary btn-sm"/>

				<input type="button" value="열람실 삭제" id="delBtn" class="btn btn-secondary btn-sm"/>
				
				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm"/>
				
			</td>
		</tr>
	</table>
</div>
	
	<form id="updateForm" action="${cPath }/off_fac/readingRoomUpdate.do" method="get">
		<input type="hidden" name="facNo" value="${readingRoom.facNo }">
	</form>
	
 	<form id="deleteForm" action="${cPath }/off_fac/readingRoomDelete.do" method="post">
		<input type="hidden" name="facNo" value="${readingRoom.facNo }">
	</form>
	
 	<form id="listForm" action="${cPath }/off_fac/readingRoomList.do" method="post">
		<input type="hidden" name="facNo" value="${readingRoom.facNo }">
	</form>
	 
<script type="text/javascript">
let updateForm = $("#updateForm");
let deleteForm = $("#deleteForm");
let listForm = $("#listForm");
	
	$("#updateBtn").on("click", function(){
		let ok = confirm("수정하시겠습니까?");
		if(ok){
			updateForm.submit();
			}
		});

	 $("#delBtn").on("click", function(){
		let ok = confirm("삭제하시겠습니까?");
		if(ok){
			deleteForm.submit();
			}
		});

	 $("#listBtn").on("click", function(){
		let ok = confirm("목록으로 돌아가시겠습니까?");
		if(ok){
			listForm.submit();
			}
		});
	
 </script>
 <jsp:include page="/includee/postScript.jsp" />
</body>
</html>