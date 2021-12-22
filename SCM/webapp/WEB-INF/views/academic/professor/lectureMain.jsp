<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<style>
#majorNameinput{
width : 120px;
}
</style>

<form:form modelAttribute="class" method="post" action="${pageContext.request.contextPath}/academic/professor/lectureMain.do">
<h3>강의페이지</h3>

<div class="table-size-1300">
<input type ="text" id ="majorNameinput" name= "majorName" value="${majorName}"  disabled/>
<select name="classCode" >
<option value>강의구분</option>
<option value="CL01" >전공선택</option>
<option value="CL02" selected>전공필수</option>
<option value="CL03" >교양선택</option>
<option value="CL04" >교양필수 </option>
</select>
<select name="classDate">
<option value="2021" selected>2021년도</option>
<option value="2020">2020년도</option>
<option value="2019">2019년도</option>
<option value="2018">2018년도</option>
<option value="2017">2017년도</option>
<option value="2016">2016년도</option>
<option value="2015">2015년도</option>
<option value="2014">2014년도</option>
<option value="2013">2013년도</option>
</select>
<select name="classSemester">
<option value="2" selected>2학기</option>
<option value="1">1학기</option>
</select>

<input type="submit" value="강의조회" class="btn btn-secondary btn-sm"/>
</div>
</form:form>
<div class="table-size-1300">
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th>강의번호</th> 
				<th>강의명</th> 
				<th>강의시간</th> 
				<th>이수학점</th> 
			</tr> 
		</thead>   
		<c:set var="classList" value="${pagingVO.dataList}" />
		<tbody id="listBody">
			<c:choose>
				<c:when test="${not empty classList}">
					<c:forEach items="${classList}" var="myClass">
						<tr>
							<td>						
							${myClass.classNo}
							</td>	
							<c:url value="/academic/professor/lecturePage/main.do" var="viewURL">
								<c:param name="classNo" value="${myClass.classNo}" />
							</c:url>
							<td>
								<a href="${viewURL}">${myClass.className}</a>
							</td>	
							<td>${myClass.classTime}</td>	     
							<td>${myClass.classPoint}</td>	     						
						</tr>
											
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">등록된 강의가 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>


