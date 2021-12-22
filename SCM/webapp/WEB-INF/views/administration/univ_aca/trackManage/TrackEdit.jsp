<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%> 

<jsp:include page="/includee/preScript.jsp" />

<h3>트랙 수정</h3>

<form:form modelAttribute="trackManage" method="post" id="editForm">

<table class="table table-bordered">
	<tr>
		<th>번호</th>
		<td>
			<input type="text" name="trackNo" value="${track.trackNo}" disabled/>
		</td>
	</tr>

	<tr>
		<th>학과코드</th>
		<td><input type="text" name="majorCode" value="${track.majorCode}" disabled/></td>
	</tr>

	<tr>
		<th>학과명</th>
		<td><input type="text" name="majorName" value="${track.majorName}" disabled/></td>
	</tr>

	<tr>
		<th>트랙명</th>
		<td><input type="text" name="trackName" value="${track.trackName}" disabled/></td>
	</tr>

	<tr>
		<th>생성일</th>
		<td><input type="text" name="trackDate" value="${track.trackDate}" disabled/></td>
	</tr>

	<tr>
		<th>상태</th>
		<td>
			<select name="trackDelete">
				<option value="N">미삭제</option>
				<option value="Y">삭제</option>
			</select>
		</td>
	</tr>

	<tr>
		<td colspan="2">
			<input type="submit" value="저장" />
			<input type="reset" value="취소" />
		</td>
	</tr>
</table>

</form:form>

<jsp:include page="/includee/postScript.jsp"/>