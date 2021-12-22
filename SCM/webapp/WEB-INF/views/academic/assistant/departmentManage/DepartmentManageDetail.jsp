<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.board-content-wrap{
	margin-top: 100px;
}
</style>

<div class="board-content-wrap">
	<div class="board-wrap">
		
		<div class="board-title">
			<h5>${notice.boardTitle}</h5>
		</div>
		
		<div class="sub-txt">
			<div class="work-txt">
				<span>작성자  : ${notice.boardFwriter}</span>
			</div>
			<div>
				<span>조회수 : ${notice.boardHits}</span>
				<span>작성일: ${notice.boardDate}</span>
			</div>
		</div>
		
		<div class="stroke"></div>
		<div class="board-content">
			${notice.boardContent}
		</div>
		
		<div class="file-wrap">
			<c:forEach items="${notice.attatchList }" var="atch">
				<c:url value="/board/download.do" var="downloadURL">
					<c:param name="what" value="${atch.attNo }" />
				</c:url>
				<a href="${downloadURL }">
				${atch.attOgfilename }
				<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" 
						style="width: 20px; height: 20px;"/>
				</a>		
			</c:forEach>
		</div>
		
		<div class="btn-wrap">
			<c:url value="/departmentManage/departmentManageUpdate.do" var="updateURL">
				<c:param name="what" value="${notice.boardNo }" />
			</c:url>
			<input type="button" value="글수정" id="modifyBtn" data-gopage="${updateURL }" class="btn btn-secondary btn-sm"/>
			
			&nbsp&nbsp
			
			<input type="button" value="글삭제" id="delBtn" class="btn btn-secondary btn-sm"/>
		</div>
	</div>
</div>

<form id="deleteForm" action="${cPath }/departmentManage/departmentManageDelete.do" method="post">
	<input type="hidden" name="noticeNo" value="${notice.boardNo }">
</form>

 <form id="updateForm" action="${cPath }/departmentManage/departmentManageUpdate.do" method="get">
	<input type="hidden" name="noticeNo" value="${notice.boardNo }">
</form>
	
<script type="text/javascript">
let deleteForm = $("#deleteForm");
let updateForm = $("#updateForm");

$("#delBtn").on("click", function (){
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

$("#modifyBtn").on("click", function (){
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
</script>

<jsp:include page="/includee/postScript.jsp" />