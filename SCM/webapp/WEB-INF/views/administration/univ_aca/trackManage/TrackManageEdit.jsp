<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%> 

<jsp:include page="/includee/preScript.jsp" />

<style>
.table-size-1300{
	margin: 100px;
}
</style>

<form:form modelAttribute="trackManage" method="post" id="editForm">
<div class="table-size-1300">
<h3>트랙 수정</h3>
<table class="table table-bordered">
	<tr>
		<th>번호</th>
		<td>
			<input type="text" name="docuNo" value="${docu.docuNo}" disabled/>
		</td>
	</tr>

	<tr>
		<th>서류코드</th>
		<td><input type="text" name="cateCode" value="${docu.category1.cateCode}" disabled/></td>
	</tr>

	<tr>
		<th>서류명</th>
		<td><input type="text" name="cateName" value="${docu.category1.cateName1}" disabled/></td>
	</tr>

	<tr>
		<th>서류내용</th>
		<td><input type="text" name="docuFilename" value="${docu.docuFilename}" disabled/></td>
	</tr>

	<tr>
		<th>신청인</th>
		<td><input type="text" name="docuReq" value="${docu.member1.memId}" disabled/>${docu.member1.memName}
			<input type="hidden" name="docuReq" value="${docu.member1.memId}"/>
		</td>
	</tr>

	<tr>
		<th>신청일</th>
		<td><input type="text" name="docuReqdate" value="${docu.docuReqdate}" disabled/></td>
	</tr>

	<tr>
		<th>상태</th>
		<td>
			<select name="docuState">
				<option value="N">미승인</option>
				<option value="Y">승인</option>
			</select>
		
		</td>
	</tr>

	<tr>
		<th>결재자</th>
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal.authMember" var="authMember"/>
			<td>
				<input type="text" name="docuApf" value="${authMember.memId}" disabled/>${authMember.memName}
				<input type="hidden" name="docuApf" value="${authMember.memId}"/>
			</td>
			</security:authorize>
	</tr>
	
</table>
	
	<center>
		<input type="submit" value="저장" />
		<input type="reset" value="취소" />
	</center>

</div>
</form:form>

<jsp:include page="/includee/postScript.jsp"/>