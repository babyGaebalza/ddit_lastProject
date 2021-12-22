<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/includee/preScript.jsp" />

<h3>증명서 발급 수정</h3>

<form:form modelAttribute="certi" method="post" id="editForm">

<table class="table table-bordered">
	<tr>
		<th>번호</th>
		<td><input type="text" name="certiNo" value="${certi.certiNo}" disabled/></td>
	</tr>
	
	<tr>
		<th>증명서코드</th>
		<td><input type="text" name="cateCode" value="${certi.category1.cateCode}" disabled/></td>
	</tr>
	
	<tr>
		<th>증명서명</th>
		<td><input type="text" name="cateName1" value="${certi.category1.cateName1}" disabled/></td>
	</tr>
	
	<tr>
		<th>발급서명</th>
		<td><input type="text" name="certiReason" value="${certi.certiReason}" disabled/></td>
	</tr>
	
	<tr>
		<th>신청인</th>
		<td><input type="text" name="memName" value="${certi.member1.memName}" disabled/></td>
	</tr>
	
	<tr>
		<th>수량</th>
		<td><input type="text" name="certiCount" value="${certi.certiCount}" disabled/></td>
	</tr>
	
	<tr>
		<th>수수료</th>
		<td><input type="text" name="cateValue1" value="${certi.category1.cateValue1}" disabled/></td>
	</tr>
	
	<tr>
		<th>상태</th>
		<td>
			<select name="certiState">
				<option value="N">미승인</option>
				<option value="Y">승인</option>
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
