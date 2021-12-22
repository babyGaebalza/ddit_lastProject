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
시설 수정 페이지 입니다.

 	<form:form modelAttribute="uclass" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=classNo value="${uclass.classNo }" />
		<table>
			<tr>
				<th>강의명</th>
				<td>
					<input type="text" name="memNo"  value="${uclass.memNo}" />
					<form:errors path="memNo" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의명</th>
				<td>
					<input type="text" name="className"  value="${uclass.className}" />
					<form:errors path="className" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의실명</th>
				<td>
					<input type="text" name="classRoom"  value="${uclass.classRoom}" />
					<form:errors path="classRoom" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의시간</th>
				<td>
					<input type="text" name="classTime"  value="${uclass.classTime}" />
					<form:errors path="classTime" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>학점</th>
				<td>
					<input type="text" name="classPoint"  value="${uclass.classPoint}" />
					<form:errors path="classPoint" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>수강인원현황</th>
				<td>
					<input type="text" name="classPerson"  value="${uclass.classPerson}" />
					<form:errors path="classPerson" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의최대인원</th>
				<td>
					<input type="text" name="classMax"  value="${uclass.classMax}" />
					<form:errors path="classMax" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>대면강의여부</th>
				<td>
					<input type="text" name="classOn"  value="${uclass.classOn}" />
					<form:errors path="classOn" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>분반</th>
				<td>
					<input type="text" name="classDivide"  value="${uclass.classDivide}" />
					<form:errors path="classDivide" element="span" cssClass="error" />
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