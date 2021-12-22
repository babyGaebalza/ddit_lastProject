<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.thColor {
    background-color: aliceblue;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
		<h5>강의 출석부 (${classNo }) 출석 : ${attcount} 총원 : ${count }</h5>
	</div>
	<table class="table table-bordered">
		<tr>
			<th class="thColor">출석시간</th>
			<th class="thColor">이름</th>
			<th class="thColor">단과</th>
			<th class="thColor">전공</th>
		</tr>
		<c:forEach var="att" items="${attList }">
			<tr>
				<td>${att.atdcDate }</td>
				<td>${att.classStudent }</td>
				<td>${att.memCollege }</td>
				<td>${att.memMajor }</td>
			</tr>
		</c:forEach>
	</table>
	
	
	
</div>