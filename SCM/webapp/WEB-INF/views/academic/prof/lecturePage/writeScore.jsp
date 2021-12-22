<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
.table > thead > tr > th:not(:last-child) {
    border-right: 1px solid #e5e5e5;
}
.table-borderless > thead > tr > th {
    border-bottom: 2px solid #dee2e6;
    height: 34px;
    vertical-align: baseline;
    padding: 6px 0px 0px 0px;
}
.table > tbody > tr > td:not(:last-child) {
    border-right: 1px solid #e5e5e5;
}
.table > tbody > tr > td > input {
    border: 1px solid #cdd1d5;
}

</style>
<form:form method="post">
<div class="left-card" style="width: 900px; margin: 0 auto; margin-top: 110px;">
<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold">수강생 성적입력</h6>
</div>
	<table class="table table-borderless txt-center">
		<thead class="fix-head">
			<tr>
				<th>학번</th> 	
				<th>이름</th> 
				<th>중간시험점수</th> 
				<th>기말시험점수</th> 
			</tr>
		</thead>
		<c:set var="studentList" value="${studentList}" />
			<tbody>
			<c:choose>
				<c:when test="${not empty studentList}">
					<c:forEach items="${studentList}" var="student">
						<tr>
							<td>${student.memNo}</td>
							
							<td>${student.memName}</td>
							
							<td><input type="number" name="classMid" value="${student.classMid}"/>
	<!-- 						<form:errors  path="classMid" element="span" cssClass="error"/></td> -->
							
							<td><input type="number" name="classFin" value="${student.classFin}"/>
	<!-- 						<form:errors  path="classFin" element="span" cssClass="error"/></td>						 -->
								<input type="hidden" name="classNo" value="${student.classNo}"/>
								<input type="hidden" name="memNo" value="${student.memNo}"/>
								
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">수강생 목록이 존재하지 않음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<input type="submit" value="저장" class="btn btn-secondary btn-sm"/>
					<input type="reset" value="취소" class="btn btn-secondary btn-sm"/>
				</td>
			</tr>
		</tfoot> 
	</table>
</div>
</form:form>