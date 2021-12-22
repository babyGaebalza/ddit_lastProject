<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>

<jsp:include page="/includee/preScript.jsp" />


<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>


<div class="content-wrap">
<h5>학과 입력 페이지 입니다.</h5>
 
 	<form:form modelAttribute="complaint" method="post">
		<input type="hidden" name=boardNo value="${complaint.boardNo }" />
		<table class="major-table">
			<tr>
				<th>제목 </th>
				<td>
					<input type="text" name=boardTitle value="${complaint.boardTitle }" />
					<form:errors path="boardTitle" element="span" cssClass="error" />
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th>작성자</th> -->
<!-- 				<td> -->
					<input type="hidden" name="boardFwriter" value="${authMember.memName }" />
					<form:errors path="boardFwriter" element="span" cssClass="error" />
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="8" cols="100" name="boardContent">${complaint.boardContent }</textarea>
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

<script type="text/javascript">
	CKEDITOR.replace("boardContent", {
		filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
	});
</script>

<jsp:include page="/includee/postScript.jsp" />