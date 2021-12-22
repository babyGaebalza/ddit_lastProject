<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/includee/preScript.jsp" />

<style>
.table-size-1300{
	margin-top: 100px;
}
</style>

<form:form modelAttribute="member" method="post" id="editForm">

<div class="table-size-1300">
	<h3>지도교수 수정</h3>
	<table class="table table-bordered">
		<tr>
			<th>학번</th>
			<td><input type="text" name="memId" value="${member.memId}" disabled/></td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td><input type="text" name="memName" value="${member.memName}" required/>
			<form:errors path="memName" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<th>주민번호</th>
			<td><input type="text" name="memReg1" value="${member.memReg1}" disabled/>
			- <input type="text" name="memReg2" value="${member.memReg2}" disabled/></td>
		</tr>
		
		<tr>
			<th>전공코드</th>
			<td><input type="text" name="memMajor" value="${member.major1.majorName}" disabled/></td>
		</tr>
		
		<tr>
			<th>이메일주소</th>
			<td><input type="text" name="memMail" value="${member.memMail}" required/></td>
		</tr>
		
		<tr>
			<th>주소</th>
			<td><input type="text" name="memAdd1" value="${member.memAdd1}" required/>
			<input type="text" name="memAdd2" value="${member.memAdd2 }" required/>
			<form:errors path="memAdd1" element="span" cssClass="error" />
			<form:errors path="memAdd2" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<th>핸드폰번호</th>
			<td><input type="text" name="memTel" value="${member.memTel}" required/>
			<form:errors path="memTel" element="span" cssClass="error" /></td>
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

<jsp:include page="/includee/postScript.jsp"/>