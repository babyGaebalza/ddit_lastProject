<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h3>학사일정리스트</h3>
<table class="table table-bordered">
	<thead>
		<tr>
		    <th>No.</th>
			<th>일정명</th> 
			<th>시작일</th> 
			<th>종료일</th> 
		</tr>
	</thead>
	<c:set var="scheduleList" value="${scheduleList}" />
	<tbody>
		<c:choose>
			<c:when test="${not empty scheduleList}">
				<c:forEach items="${scheduleList}" var="schedule">
				<c:set var="i" value="${i+1}" />
					<tr>
						<td>${i}</td>
						<td>${schedule.schCont}</td>
						<td>${schedule.schSdate}</td>	
						<td>${schedule.schEdate}</td>	
					</tr>							
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">등록된 학사일정이 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
		<tfoot>
		<tr>
			<td colspan="4">
			<input type="button" id="scheduleMakeBtn"value="일정등록" />
			</td>
		</tr>
	</tfoot>
</table>

<div id="scheduleMakeForm" >
<input type="hidden" name="schCode"  value="SCE01"/><form:errors  path="schCode" element="span" cssClass="error"/>
			<input type="text" name="schCont"  value="${schedule.schCont}"/><form:errors  path="schCont" element="span" cssClass="error"/>
			<input type="date" name="schSdate"  value="${schedule.schSdate}"/><form:errors  path="schSdate" element="span" cssClass="error"/>
			<input type="date" name="schEdate"  value="${schedule.schEdate}"/><form:errors  path="schEdate" element="span" cssClass="error"/>
</div>

<script type="text/javascript">
let scheduleMakeForm = $('#scheduleMakeForm');
$('#scheduleMakeBtn').on("click", function(){
	scheduleMakeForm.show();
}) 

</script>

