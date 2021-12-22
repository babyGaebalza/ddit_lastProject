<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<form:form action="${cPath }/off_fac/classroomInsertProcess.do" method="post" name="classroomForm">
		<h2>신규 강의실 등록</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>강의실이름</th>
				<th>단과대위치</th>
				<th>수용인원</th>
				<th>저장 강의실명</th>
			</tr>
		</thead>
		<tbody>
			<tr>	
				<td>
					<input type="text" name="facNo" id="facNo" value="" placeholder="강의실 호" required="required"/>
				</td>		
				<td>
					<select required="required" name="collegeCode" id="collegeCode">
						<option value>단과대</option>
						<c:forEach items="${collegeList }" var="collegeList">
								<option value="${collegeList.cateCode }">${collegeList.cateName1 }</option>
						</c:forEach>
					</select>
				</td>
							
				<td>
					<input type="number" value="0" name="facNumber" id="facNumber" min="0" required="required"/>
				</td>
				
				<td>
					<input type="text" name="facName" id="facName" value="" placeholder="저장될 이름" required="required" readonly="readonly"/>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="submit" value="등록완료" />
</form:form>

<script type="text/javascript">
	var facName = $("#facName");
	
	
	$("#collegeCode").on("change", function(){
		var college = document.getElementById("collegeCode");
		var selectedCollege = college.options[college.selectedIndex].text;
		var facNo = $("#facNo").val();
		facName.val(selectedCollege+" "+facNo);
	});
	
	$("#facNo").on("keyup", function(){
		var college = document.getElementById("collegeCode");
		var selectedCollege = college.options[college.selectedIndex].text;
		var facNo = $("#facNo").val();
		facName.val(selectedCollege+" "+facNo);
	});
</script>

</html>