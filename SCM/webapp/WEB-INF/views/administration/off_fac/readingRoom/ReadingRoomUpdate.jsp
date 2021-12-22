<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/includee/preScript.jsp" />

<div class="content-wrap">
<h5>열람실 수정 페이지 입니다.</h5>
 
 	<form:form modelAttribute="readingRoom" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=facNo value="${readingRoom.facNo }" />
		<table class="major-table">
			<tr>
				<th>시설명</th>
				<td>
					${readingRoom.facName}
					<form:errors path="facName" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>수용인원수</th>
				<td>
					<input type="text" name="facNumber"  value="${readingRoom.facNumber}" /> 명
					<form:errors path="facNumber" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>예약가능여부</th>
				<td>
					<select name="facResult" required="required">
						<option value>가능여부</option>
						<option value="Y">사용가능</option>
						<option value="N">사용불가능</option>
					</select>
					<form:errors path="facResult" element="span" cssClass="error" />
				</td>
			</tr>
		</table>
			<div class="flex-center m-top-10">
				<input type="submit" class="btn btn-secondary btn-n-sm m-right-5" value="저장" />
				<input type="reset" class="btn btn-secondary btn-n-sm" value="취소" />
			</div>
	</form:form>
</div>

<jsp:include page="/includee/postScript.jsp" />	
	
