<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/includee/preScript.jsp" />


<form:form modelAttribute="certi" method="post" id="editForm">
<div class="table-size-1300">
<h3>증명서 관리 등록/수정</h3>
	<table class="table table-bordered">
		<tr>
			<th>코드</th>
			<td><input type="text" name="cateCode" value="${cate.cateCode}" disabled/></td>
		</tr>
		
		<tr>
			<th>증명서명</th>
			<td><input type="text" name="cateName1"  value="${cate.cateName1 }" required/>
				<form:errors path="cateName1" element="span" cssClass="error" /></td>
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

<jsp:include page="/includee/postScript.jsp" />
