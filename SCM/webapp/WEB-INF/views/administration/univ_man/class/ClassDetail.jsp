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
			<th>강의관리번호</th>
			<td>${uclass.classNo}</td>
		</tr>
		<tr>
			<th>사용자등록번호</th>
			<td>${uclass.memNo}</td>
		</tr>
		<tr>
			<th>시설구분번호</th>
			<td>${uclass.facNo}</td>
		</tr>
		<tr>
			<th>강의구분코드</th>
			<td>${uclass.classCode}</td>
		</tr>
		<tr>
			<th>단과대코드</th>
			<td>${uclass.collegeCode}</td>
		</tr>
		<tr>
			<th>강의실명</th>
			<td>${uclass.classRoom}</td>
		</tr>
		<tr>
			<th>강의명</th>
			<td>${uclass.className}</td>
		</tr>
		<tr>
			<th>강의시간</th>
			<td>${uclass.classTime}</td>
		</tr>
		<tr>
			<th>학점</th>
			<td>${uclass.classPoint}</td>
		</tr>
		<tr>
			<th>수강인원현황</th>
			<td>${uclass.classPerson}</td>
		</tr>
		<tr>
			<th>강의최대인원</th>
			<td>${uclass.classMax}</td>
		</tr>
		<tr>
			<th>대면강의여부</th>
			<td>${uclass.classOn}</td>
		</tr>
		<tr>
			<th>폐강여부</th>
			<td>${uclass.classDelete}</td>
		</tr>
		<tr>
			<th>강의관리번호</th>
			<td>${uclass.classExtend}</td>
		</tr>
		<tr>
			<th>학과구분코드</th>
			<td>${uclass.majorCode}</td>
		</tr>
		<tr>
			<th>학사과정</th>
			<td>${uclass.classSemester}</td>
		</tr>
		<tr>
			<th>분반</th>
			<td>${uclass.classDivide}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${uclass.classDate}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="글수정" id="updateBtn" class="linkBtn btn btn-secondary btn-sm" />

				<input type="button" value="글삭제" id="delBtn" class="linkBtn btn btn-secondary btn-sm" />

				<input type="button" value="목록" id="listBtn" class="linkBtn btn btn-secondary btn-sm" />
				
			</td>
		</tr>
	</table>
</div>
	<form id="updateForm" action="${cPath }/univ_man/classUpdate.do" method="get">
		<input type="hidden" name="classNo" value="${uclass.classNo }">
	</form>
 
 	<form id="deleteForm" action="${cPath }/univ_man/classDelete.do" method="post">
		<input type="hidden" name="classNo" value="${uclass.classNo }">
	</form>
 
 	<form id="listForm" action="${cPath }/univ_man/classList.do" method="post">
		<input type="hidden" name="classNo" value="${uclass.classNo }">
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