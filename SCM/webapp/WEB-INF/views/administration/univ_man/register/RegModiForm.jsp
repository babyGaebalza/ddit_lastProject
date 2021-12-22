<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<style>

.num {
    width: 135px;
    text-align: center;
}
.head{
    background-color: aliceblue;
     text-align: center;
}
.birth {
	width: 230px;
	text-align: center;
}

.nameit{
    width: 200px;
    text-align: center;
}

.age{
    width: 150px;
    text-align: center;
}

.major {
	text-align: center;
}

.date{
    width: 300px;
    text-align: center;
}

.now{
    width: 125px;
    text-align: center;
}

.reg{
    border: none;
    width: 100px;
}
</style>

<c:set var="memberDetail" value="${member }"/>
<div class="table-size-1300" style="padding-top: 15px;">
	<form:form method="post" action="${cPath }/register/registerInsert.do" id="regInputForm">
	<h3>학적 변경</h3>
	<table class="table table-bordered">
		<thead>
			<th class="num head">등록번호</th>
			<th class="head nameit">이름</th>
			<th class="head age">나이</th>
			<th class="head birth">생년월일</th>
			<th class="head major">전공</th>
		</thead>
		<c:forEach items="${memberDetail }" var="mem">
			<tr>
				<input type="text" value="${mem.memId }" readonly="readonly" name="who" hidden />
				<td  class="num"><input type="text" value="${mem.memId }" readonly="readonly" name="regStudent" style=" border: none;"/></td>
				<td class="nameit">${mem.memName }</td>
				<td class='age'>${mem.memAge }</td>
				<td class="birth">${mem.memBirth }</td>
				<td class="major">${mem.memMajor }</td>
			</tr>
		</c:forEach>
	</table>
	
	
	<table class="table table-bordered">
		<tr>
			<th class="reg">
				학적종류
			</th>
			<th>
				변경사유
			</th>
		</tr>
		<div class="form-group">
		<tr>
			<td class="reg">
				<select id="regCode" name="regCode" required="required" class="reg">
					<option value>학적 종류</option>
					<c:forEach items="${regList }" var="reg">
						<option value="${reg.cateCode }">${reg.cateName1 }</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input type="text" id="regReason" name="regReason" style="width: 1130px;height:30px;border: none;" required="required" placeholder="사유를 입력해주세요"/>	
			</td>
		</tr>
		</div>
	</table>
	
		
	<div id="changeDate" style="display: none;">
			<div class="form-group" style="float: left;">
					
				<c:forEach items="${memberDetail }" var="mem">
				<div style="float: left; margin-right: 35px;">
					<div style="float: left;margin-right: 35px;">휴학기간[최대 6학기(3년)]</div>
					<div style="float: left;margin-right: 15px;">남은 일반휴학  : ${mem.memAbsLimit }</div>
					<div style="float: left;"><input type="NUMBER" id="regLeavedate" name="regLeavedate" style="width: 70px;height: 20px;" min="0" max="${mem.memAbsLimit }" value="0"/>학기</div>
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
		<div style="float: right;">
			<input type="submit" value="학적 등록" id="submit" style="display : inline;float: right;" class="btn btn-outline-warning">
		</div>
	</form:form>
</div>	
	<script type="text/javascript">
	window.onload = function(){
		if("<c:out value='${message }' />" !=null && "<c:out value='${message }' />" != ''){
			$.alert({
				title : '오류발생!',
				content : "<c:out value='${message }' />" 
			});
		}
	}
	
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