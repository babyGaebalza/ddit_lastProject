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
</style>

<form:form id="regForm" commandName="regDetail" method="post" action="${cPath }/register/registerForm.do">
<c:set var="pass" value="ok" />

<div class="table-size-1300">
	<h3>학적상세</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th class="num">등록번호</th>
				<th class="nameit">이름</th>
				<th class="age">나이</th>
				<th class="birth">생년월일</th>
				<th class="major">전공</th>
			</tr>
		</thead>
		<c:set var="regList" value="${member }"/>
		
		<tbody>
			<c:forEach items="${regList }" var="reg">
			<input type="text" name="memId" hidden value="${reg.memId }"/>
				<td  class="num" >${reg.memId }</td>
				<td  class="nameit">${reg.memName }</td>
				<td  class="age">${reg.memAge }</td>
				<td class="birth">${reg.memBirth }</td>
				<td  class="major">${reg.memMajor }</td>
		</tbody>
	</table>
	
	<table class="table table-bordered">
				<thead>
					<tr class="tr-style">
						<th class="num">학적상태</th>
						<th class="date">최종수정일</th>
						<th>변동 사유</th>
						<th class="now">현재 적용여부</th>
						<th class="num">적용(휴학)기간</th>
					</tr>
				</thead>
					<c:forEach items="${reg.register }" var="a">
				<tbody>
					<c:if test="${a.regCode eq '-'}">
						<c:set var="pass" value="no" />
					</c:if>
						<tr>
							<td class="num">${a.regCode }</td>
							<td  class="date">${a.regFdate }</td>
							
							<td>${a.regReason }</td>
							
							<td class="now">${a.regState }</td>
							
							<td class="num">
								<c:if test="${not empty a.regLeavedate }">
									${a.regLeavedate }
								</c:if>
								<c:if test="${empty a.regLeavedate }">
									-
								</c:if>
							</td>
						</tr>
				</tbody>
					</c:forEach>
			</c:forEach>
			<div style="margin-left: 1225px;">
				<c:if test="${pass eq 'no' }">
					<input type="text" name="order" hidden value="등록"/>
					<input type="submit" value="학적등록" class="btn btn-secondary btn-sm"/>
				</c:if>
				<c:if test="${pass eq 'ok' }">
					<input type="text" name="order" hidden value="수정"/>
					<input type="submit" value="학적수정" class="btn btn-secondary btn-sm" style="margin-bottom: 10px;"/>
				</c:if>
			</div>
	</table>
</div>



</form:form>
</html>