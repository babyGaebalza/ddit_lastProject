<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%> 

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<style>
.table-size-1300{
	margin-top: 100px;
}
</style>

<security:authorize access="isAuthenticated()">
<security:authentication property="principal.authMember" var="authMember"/>
<form:form modelAttribute="notice" method="post" enctype="multipart/form-data" id="editForm">

<div class="table-size-1300">
	<h3>학과 공지사항 수정</h3>
	<input type="hidden" name=boardNo value="${notice.boardNo }" />
	<table class="table-size-1300">
		<tr>
			<th>글번호</th>
			<td>${notice.boardNo }
				<form:errors path="boardNo" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="boardTitle"  value="${notice.boardTitle }" />
				<form:errors path="boardTitle" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="boardFwriter"  value="${authMember.memId }" readonly="readonly" />
				<form:errors path="boardFwriter" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td id="fileArea">
				<div>
					<input type="file" name="boFiles" multiple />
					<input type="button" value="추가" id="plus" class="ctlBtn"/>
					<input type="button" value="제거" id="minus" class="ctlBtn"/>
				</div>
			</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="5" cols="100" name="boardContent" id="boContent">${notice.boardContent }</textarea>
				<form:errors path="boardContent" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</div>	
</form:form>
</security:authorize>

<script type="text/javascript">
	CKEDITOR.replace("boardContent", {
		filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
	});
	let editForm = $("#editForm");
	$(".atchDelBtn").on("click", function(){
		let span = $(this).closest("span");
		span.hide();
		let attNo = span.data("atch");
		editForm.append(
			$("<input>").attr({
				type:"text",
				name:"delAttNos"
			}).val(attNo)
		);
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

<jsp:include page="/includee/postScript.jsp" />