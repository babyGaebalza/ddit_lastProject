<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학과 수정 창</title>
<jsp:include page="/includee/preScript.jsp" />

</head>
<body>
장학관련 수정 페이지 입니다.
 
 	<form:form modelAttribute="scholar" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=schNo value="${scholar.schNo }" />
		<table>
			<tr>
				<th>장학금 종류</th>
				<td>
					<input type="text" name="schCode"  value="${scholar.schCode}" />
					<form:errors path="schCode" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>장학 금액</th>
				<td>
					<input type="text" name="schAmount"  value="${scholar.schAmount}" />
					<form:errors path="schAmount" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>상태</th>
				<td>
					<input type="text" name="schState"  value="${scholar.schState}" />
					<form:errors path="schState" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>
					<input type="text" name="schNote"  value="${scholar.schNote}" />
					<form:errors path="schNote" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>추천 / 신청사유</th>
				<td>
					<input type="text" name="schReason"  value="${scholar.schReason}" />
					<form:errors path="schReason" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>추천인</th>
				<td>
					<input type="text" name="schRec"  value="${scholar.schRec}" />
					<form:errors path="schRec" element="span" cssClass="error" />
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
	<jsp:include page="/includee/postScript.jsp" />	
	
</body>
</html>