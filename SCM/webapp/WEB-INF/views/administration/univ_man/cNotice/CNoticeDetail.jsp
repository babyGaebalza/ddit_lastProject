<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />

<div class="table-size-1300">
	<h3>강의 공지 상세화면 입니다.</h3>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<td>${cNotice.boardNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${cNotice.boardTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${cNotice.boardFwriter}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${cNotice.boardDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${cNotice.boardHits}</td>
		</tr>

		<tr>
			<th>첨부파일</th>
			<td><c:forEach items="${cNotice.attatchList }" var="atch">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${atch.attNo }" />
					</c:url>
					<a href="${downloadURL }"> ${atch.attOgfilename }
						<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" style="width: 20px; height: 20px;" />
					</a>
				</c:forEach></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${cNotice.boardContent}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="강의 공지사항 수정" id="updateBtn" class="btn btn-secondary btn-sm" />
				
				<input type="button" value="강의 공지사항 삭제" id="delBtn" class="btn btn-secondary btn-sm" />
				
				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm" />
				
			</td>
		</tr>
	</table>
</div>

	<form id="updateForm" action="${cPath}/univ_man/cNoticeUpdate.do" method="get">
		<input type="hidden" name="boardNo" value="${cNotice.boardNo }">
	</form>

	<form id="deleteForm" action="${cPath}/univ_man/cNoticeDelete.do" method="post">
		<input type="hidden" name="boardNo" value="${cNotice.boardNo }">
	</form>

	<form id="listForm" action="${cPath}/univ_man/cNoticeList.do" method="post">
		<input type="hidden" name="boardNo" value="${cNotice.boardNo }">
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
</body>
</html>