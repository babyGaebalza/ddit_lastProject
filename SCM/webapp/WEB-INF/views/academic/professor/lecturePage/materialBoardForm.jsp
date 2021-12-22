<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>    

<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>
    
<security:authentication property="principal.authMember" var="authMember"/>
<h3>강의자료 등록 게시판</h3>
<form:form modelAttribute="notice" method="post" enctype="multipart/form-data">
<input type="hidden" name="memNo" value="${authMember.memId }" />
<input type="hidden" name="classNo" value="${board.classNo}" />

<div class="table-size-1300">
	<table class="table table-bordered">
		<tr>
			<th>제목</th>
			<td><input type="text" name="boardTitle"
				value="${notice.boardTitle}" /> <form:errors path="boardTitle"
					element="span" cssClass="error" /></td>
		</tr>

		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="100" name="boardContent">${notice.boardContent}</textarea>
				<form:errors path="boardContent" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td id="fileArea">
				<div>
					<input type="file" name="boFiles" multiple /> <input type="button"
						value="추가" id="plus" class="ctlBtn" /> <input type="button"
						value="제거" id="minus" class="ctlBtn" />
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-secondary btn-sm"/>
				<input type="reset" value="취소" class="btn btn-secondary btn-sm"/>
			</td>
		</tr>
	</table>
</div>

</form:form>

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