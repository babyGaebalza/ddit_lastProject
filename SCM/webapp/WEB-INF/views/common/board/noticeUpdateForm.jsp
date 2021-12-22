<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<c:set var="order" value="${order }"/>

<style>
.thWidth {
    width: 100px;
    text-align: center;
}

.inputBox {
    width: 1000px;
    text-align: center;
    border: none;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
 	<div class="board-title">	
	 	<c:if test="${order eq 'update' }">
	 		<h5>공지 수정</h5>
	 	</c:if>
	 	<c:if test="${order eq 'insert' }">
			<h5>공지 등록</h5>
	 	</c:if>	
	</div>
	
 	<form:form modelAttribute="notice" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=boardNo value="${notice.boardNo }" />
		<table class="table table-bordered">
			<c:if test="${order eq 'update' }">
				<tr>
					<th class="thWidth">글번호</th>
					<td>${notice.boardNo }<form:errors path="boardNo" element="span" cssClass="error"/></td>
				</tr>
			</c:if>
			<tr>
				<th class="thWidth">제목</th>
				<td><input  class="inputBox" type="text" name="boardTitle"  value="${notice.boardTitle }" required="required"/>
					<form:errors path="boardTitle" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th class="thWidth">작성자</th>
				<td><input  class="inputBox" type="text" name="boardFwriter"  value="${notice.boardFwriter }" readonly="readonly" required="required" />
					<form:errors path="boardFwriter" element="span" cssClass="error" /></td>
			</tr>
			
<%-- 			<tr>
				<th>기존 첨부파일</th>
				<td>
					<c:forEach items="${board.attatchList }" var="atch">
						<span data-atch="${atch.attNo }">
							${atch.attFilename }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
				</td>
			</tr> --%>

			<tr>
				<th class="thWidth">첨부파일</th>
				<td id="fileArea">
					<div style="float: left;">
						<input type="file" name="boFiles" multiple />
					</div>
					<div style="float: right;">
						<input type="button" value="추가" id="plus" class="ctlBtn btn btn-outline-info"/>
						<input type="button" value="제거" id="minus" class="ctlBtn btn btn-outline-warning"/>
					</div>						
				</td>
			</tr>
			
			<tr>
				<th class="thWidth">내용</th>
				<td>
					<textarea rows="5" cols="100" name="boardContent" id="boContent">${notice.boardContent }</textarea>
					<form:errors path="boardContent" element="span" cssClass="error" /></td>
			</tr>

		</table>
		<div style="float: right;">
			<input type="submit" value="저장" class="btn btn-outline-success"/>
			<c:if test="${order eq 'insert' }">
				<input type="button" value="돌아가기" class="btn btn-outline-secondary" onclick="location.href='${cPath}/common/board/noticeList.do';"/>
			</c:if>
			<c:if test="${order eq 'update' }">
				<input type="button" value="돌아가기" class="btn btn-outline-secondary" onclick="location.href='${cPath}/board/noticeView.do?noticeNo=${notice.boardNo }';"/>
			</c:if>
		</div>
	</form:form>
</div>	
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
