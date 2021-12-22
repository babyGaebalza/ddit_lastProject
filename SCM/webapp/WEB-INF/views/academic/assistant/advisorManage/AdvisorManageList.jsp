<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<style>
.search-align{
	float: right;
}
.table-size-1300{
	margin-top: 100px;
}
</style>

<div class="table-size-1300">
<h3>지도교수 관리 리스트(학생)</h3>
<c:set var="mem" value="${member}" />
	<input type="hidden" name="memMajor" value="${mem.memMajor }">
	
<a href="${cPath }/advisorManage/advisorList.do"><input type="button" value="교수" class="btn btn-secondary btn-sm"/></a>
<div class="search-align">
	<div id="searchUI" class="search-align">
		<select name="searchType">
			<option value>전체</option>
			<option value="number">학번</option>
			<option value="seme">학년</option>
			<option value="name">이름</option>
		</select>
		<input type="text" name="searchWord" />
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm"/>
	</div>
</div>
	
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th>학번</th>
				<th>이름</th>
				<th>전공(학년)</th>
				<th>지도교수</th>
			</tr>
		</thead>
		<c:set var="memberList" value="${pagingVO.dataList }" />
		<tbody>
			<c:choose>
				<c:when test="${not empty memberList }">
					<c:forEach items="${memberList }" var="member">
						<tr>
							<td>
								<c:url value="/studentManage/studentManageDetail.do" var="viewURL">
									<c:param name="memId" value="${member.memId}" />
								</c:url>
								<a href="${viewURL }">${member.memId}</a>
							</td>
							<td>${member.memName}</td>
							
							<td>${member.major1.majorName }</td>
							
							<td>${member.adviser1.memName}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div id="pagingArea" class="p-center m-bottom-10">
		${pagingVO.pagingHTML }
	</div>
</div>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>

<script>

// $(function(){
// 	var seme = $("#seme").val();
// })		 	
// var arr = new Array();
// 	<c:forEach items="${memberList }" var="member">
// 		arr.push("${member.memSemester}");
// 	</c:forEach>
	
// 	for(var i = 0; i < arr.length; i++){
// 	 	var text1 = "";
// 	 	var text2 = "";
// 	 	var text3 = "";
		
// 	 	var strArray = arr[i].split('/');
		
// 	 	console.log(strArray);
	 	
// 	 	console.log(i+"번쨰");
	 	
// 	 	if(strArray[0] == 'RC01'){
// 	 		text1 = "재학";
// 	 	} else if(strArray[0] == 'RC02') {
// 	 		text1 = "휴학"
// 	 	} else if(strArray[0] == 'RC03') {
// 	 		text1 = "군휴학"
// 	 	} else if(strArray[0] == 'RC04') {
// 	 		text1 = "재입학"
// 	 	} else if(strArray[0] == 'RC05') {
// 	 		text1 = "편입"
// 	 	} else if(strArray[0] == 'RC06') {
// 	 		text1 = "졸업유예"
// 	 	} else if(strArray[0] == 'RC07') {
// 	 		text1 = "졸업"
// 	 	} else {
// 	 		text1 = "재적"
// 	 	}
		
// 	 	if(strArray[1] == '1'){
// 	 		text2 = "1학년";
// 	 	} else if(strArray[1] == '2') {
// 	 		text2 = "2학년"
// 	 	} else if(strArray[1] == '3') {
// 	 		text2 = "3학년"
// 	 	} else {
// 	 		text2 = "4학년"
// 	 	}
		
// 	 	if(strArray[2] == '1'){
// 	 		text3 = "1학기";
// 	 	} else {
// 	 		text3 = "2학기"
// 	 	}
		
// 	 	console.log(text1 + "" + text2 + "" + text3);

// 		$("#seme").append( '[' + text1 + "" + text2 + "" + text3 + ']');
// 	}
</script>

<jsp:include page="/includee/postScript.jsp" />