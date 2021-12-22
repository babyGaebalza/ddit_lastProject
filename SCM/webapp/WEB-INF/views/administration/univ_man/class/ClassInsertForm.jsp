<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학과 입력 창</title>
<jsp:include page="/includee/preScript.jsp" />

</head>
<body>
학과 입력 페이지 입니다.
 
 	<form:form modelAttribute="uclass" method="post" enctype="multipart/form-data" id="editForm">
		<table>
			<tr>
				<th>강의관리번호</th>
				<td>
					<input type="text" name="classNo" value="${uclass.classNo }" placeholder="ex)1051700001" />
					<form:errors path="classNo" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>사용자등록번호</th>
				<td>
					<input type="text" name="memNo" value="${uclass.memNo }" placeholder="ex)사번" />
					<form:errors path="memNo" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>시설구분번호</th>
				<td>
					<input type="text" name="facNo" value="${uclass.facNo }" placeholder="ex)311" />
					<form:errors path="facNo" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의구분코드</th>
				<td>
					<input type="text" name="classCode" value="${uclass.classCode }" placeholder="ex)CL01" />
					<form:errors path="classCode" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>단과대코드</th>
				<td>
					<input type="text" name="collegeCode"  value="${uclass.collegeCode }" placeholder="ex)CO05" />
					<form:errors path="collegeCode" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의실명</th>
				<td>
					<input type="text" name="classRoom"  value="${uclass.classRoom }" placeholder="ex)공과대학 301호" />
					<form:errors path="classRoom" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의명</th>
				<td>
					<input type="text" name="className"  value="${uclass.className }" placeholder="ex)자바" />
					<form:errors path="className" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의시간</th>
				<td>
					<input type="text" name="classTime"  value="${uclass.classTime }" placeholder="ex)월12/화34" />
					<form:errors path="classTime" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>학점</th>
				<td>
					<input type="text" name="classPoint"  value="${uclass.classPoint }" placeholder="ex)3" />
					<form:errors path="classPoint" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>수강인원현황</th>
				<td>
					<input type="text" name="classPerson"  value="${uclass.classPerson }" placeholder="ex)13" />
					<form:errors path="classPerson" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의최대인원</th>
				<td>
					<input type="text" name="classMax"  value="${uclass.classMax }" placeholder="ex)30" />
					<form:errors path="classMax" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>대면강의여부</th>
				<td>
					<input type="text" name="classOn"  value="${uclass.classOn }" placeholder="ex) Y / N" />
					<form:errors path="classOn" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>강의관리번호</th>
				<td>
					<input type="text" name="classExtend"  value="${uclass.classExtend }" placeholder="ex) 새로 생성하는 경우는 빈칸" />
					<form:errors path="classExtend" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>학과구분코드</th>
				<td>
					<input type="text" name="majorCode"  value="${uclass.majorCode }" placeholder="ex)C05M17" />
					<form:errors path="majorCode" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>학사과정</th>
				<td>
					<input type="text" name="classSemester"  value="${uclass.classSemester }" placeholder="ex)1/2" />
					<form:errors path="classSemester" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>분반</th>
				<td>
					<input type="text" name="classDivide"  value="${uclass.classDivide }" placeholder="ex)01" />
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