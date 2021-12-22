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
졸업생 / 졸업예정자 수정 페이지 입니다.
 
 	<form:form modelAttribute="member" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=memId value="${member.memId }" />
		<table>
			<tr>
				<th>학번</th>
				<td>
					${member.memId}
					<form:errors path="memId" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					${member.memName}
					<form:errors path="memName" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>졸업예정일</th>
				<td>
					<input type="text" name="memGradate"  value="${member.memGradate}" />
					<form:errors path="memGradate" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>졸업여부</th>
				<td>
					<input type="text" name="memGraduate"  value="${member.memGraduate}" />
					<form:errors path="memGraduate" element="span" cssClass="error" />
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