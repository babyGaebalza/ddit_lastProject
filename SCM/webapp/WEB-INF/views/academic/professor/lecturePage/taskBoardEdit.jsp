<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>

<security:authentication property="principal.authMember" var="authMember"/>

<form:form modelAttribute="board" method="post" enctype="multipart/form-data" id="editForm">
	<input type="hidden" name="boardNo" value="${board.boardNo}" />
	<input type="hidden" name="boardFwriter" value="${authMember.memId}" />

<div class="table-size-1300">	
	<h3>과제게시글 수정폼</h3>
	<table class="table table-bordered">
		<tr>
			<th>게시글번호</th>
			<td>${board.boardNo}
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="boardTitle"
				value="${board.boardTitle}" />
			<form:errors path="boardTitle" element="span" cssClass="error" /></td>
		</tr>
		<tr>  
			<th>마감기한</th>
			<td><span id ="oldDeadLine">${board.boardDeadline}</span>
			<input type="button" value ="수정"   class="btn btn-secondary btn-sm" id="showdeadLineDateInput"/> 
			<input type="date" name="boardDeadline" id="deadLineDateInput" style="display:none;" 
				value="${board.boardDeadline}"/><span id="duringTaskSpan"></span>
			<form:errors path="boardDeadline" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="100" name="boardContent">${board.boardContent}</textarea>
			<form:errors path="boardContent" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>기존 첨부파일</th>
				<td>
					<c:forEach items="${board.attatchList }" var="atch">
						<span data-atch="${atch.attNo }">
							${atch.attOgfilename }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
				</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td id="fileArea">
				<div>
					<input type="file" name="boFiles" multiple /> <input type="button"
						value="추가" id="plus" class="ctlBtn btn btn-secondary btn-sm" /> <input type="button"
						value="제거" id="minus" class="ctlBtn btn btn-secondary btn-sm" />
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
let duringTaskSpan = $('#duringTaskSpan')
$('#showdeadLineDateInput').on("click",function(){
	$('#oldDeadLine').hide();
	$('#deadLineDateInput').show();
})
//입력시 마감기한 또는 날짜 검증 alert 띄우는 용도 
$('#deadLineDateInput').on("change",function(){
	let boardDeadLine = $(this).val();
	$.ajax({
		url : "<%=request.getContextPath()%>/professor/lecturePage/update.do",
		data : {
			"boardDeadLine":boardDeadLine
		},		
		dataType : "json",
		success : function(resp) { //승인이 되면, 승인완료로 고치기 재로드
			console.log("마감기한 입력 ajax success로 옴") 
			duringTaskSpan.text(resp.result); 
		},
		error : function(xhr, errorResp, error) {
		console.log(xhr);
		console.log(errorResp);
		console.log(error);
		}
		
	})	
	
	
})
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
			type:"hidden",
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