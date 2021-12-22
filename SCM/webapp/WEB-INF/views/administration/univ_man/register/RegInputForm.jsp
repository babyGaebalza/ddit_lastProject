<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
학적 등록창
<c:set var="memberDetail" value="${member }"/>
	<form:form method="post" action="${cPath }/register/registerInsert.do" id="regInputForm">
	<table class="table table-bordered">
		<thead>
			<th>등록번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>생년월일</th>
			<th>전공</th>
		</thead>
		<c:forEach items="${memberDetail }" var="mem">
			<tr>
				<td><input type="text" value="${mem.memId }" readonly="readonly" name="regStudent" /></td>
				<td>${mem.memName }</td>
				<td>${mem.memAge }</td>
				<td>${mem.memBirth }</td>
				<td>${mem.memMajor }</td>		
			</tr>
		</c:forEach>
	</table>
	
		<div class="form-group">
		<select id="regCode" name="regCode" required="required">
			<option value>학적 종류</option>
			<c:forEach items="${regList }" var="reg">
				<option value="${reg.cateCode }">${reg.cateName1 }</option>
			</c:forEach>
		</select>
		</div>
		
		<div id="changeDate" style="display: none;">
			<div class="form-group">
					휴학기간[최대 6학기(3년)]
				<c:forEach items="${memberDetail }" var="mem">
					<div>남은 일반휴학  : ${mem.memAbsLimit }</div>
				<div>
					<input type="NUMBER" id="regLeavedate" name="regLeavedate" style="width:300px;height:30px;" min="0" max="${mem.memAbsLimit }" value="0"/>
				</div>
				</c:forEach>
			</div>
		</div>

		<div id="changeMil" style="display: none;">
			<c:forEach items="${memberDetail }" var="mem" >
				<input id="checkRes" type="text" value="${mem.memMiLimit }" style="display: none;"/>
				<input id="memMiLimit" name="memMiLimit" type="number" value="0" style="display: inline;"/>
			</c:forEach>
			<div>
				<input type="text" id="regMil" name="regMil" value="" readonly="readonly"/>
			</div>
		</div>		
		
		<div class="form-group">
		변경사유
		<div>
		<input type="text" id="regReason" name="regReason" style="width:300px;height:30px;" required="required"/>
		</div>
		</div>
		
		<input type="submit" value="학적 등록" id="submit" style="display: inline;">
	</form:form>
	
	<script type="text/javascript">
	$("#regCode").on("change", function(){
 		var regCodeList = document.getElementById("regCode");
 		var codevalue = regCodeList.options[regCodeList.selectedIndex].text;
 		
		if(codevalue == "휴학"){
			$("#submit").attr("style", "display : inline;");
			$("#changeDate").attr("style", "display : inline;");
		};
		if(codevalue != "휴학"){
			$("#submit").attr("style", "display : inline;");
			$("#changeDate").attr("style", "display : none;");
			$("#regLeavedate").val('0');
		};
		
		
		if(codevalue == "군휴학"){
			var checkRes = $("#checkRes").val();
			
			if(checkRes == '1'){
				$("#regMil").val("군휴학이 가능합니다.");
				$("#submit").attr("style", "display : inline;");
				$("#memMiLimit").val("1");
			}
			if(checkRes != '1'){
				$("#regMil").val("군휴학이 불가능합니다.");
				$("#submit").attr("style", "display : none;");
				$("#memMiLimit").val("0");
			}		
			$("#changeMil").attr("style", "display : inline;");
		};

		if(codevalue != "군휴학"){
			$("#changeMil").attr("style", "display : none;");
			$("#memMiLimit").val("0");
		};
		
		
	});
	
	</script>
</html>