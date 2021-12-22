<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>

<div class="content-wrap">
<h5>강의 공지 수정 페이지 입니다.</h5>

 	<form:form modelAttribute="board" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=boardNo value="${board.boardNo }" />
		<input type="hidden" name="boardFwriter" value="${authMember.memName }" />
		
		<table class="major-table">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle"  value="${board.boardTitle}" />
					<form:errors path="boardTitle" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td id="fileArea">
					<div style="width: 442px;">
						<input type="file" name="boFiles" multiple />
						<input type="button" value="추가" id="plus" class="ctlBtn"/>
						<input type="button" value="제거" id="minus" class="ctlBtn"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="8" cols="100" name="boardContent">${board.boardContent }</textarea>
					<form:errors path="boardContent" element="span" cssClass="error" />
				</td>
			</tr>
		</table>
		
		<div>
			<input type="submit" class="btn btn-secondary btn-n-sm m-right-5" value="저장" />
			<input type="reset" class="btn btn-secondary btn-n-sm" value="취소" />
		</div>
	</form:form>
</div>

	<jsp:include page="/includee/postScript.jsp" />	
	
	<script type="text/javascript">
		CKEDITOR.replace("boardContent", {
			filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
		});
	
		let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
			let id = this.id;
			console.log(id);
			let divTag = $(this).closest("div");
			if(id == 'plus'){
				let clone = divTag.clone();
				$(clone).find(":input[name]").val("");
				divTag.after(clone);
			}else{
				let divs = fileArea.find("div");
				if(divs.length>1)
					divTag.remove();
			}
		});
	</script>
	
</body>
</html>