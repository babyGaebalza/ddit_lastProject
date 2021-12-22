<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />


<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>



	<!-- choose a theme file -->
	<link rel="stylesheet" href="${cPath }/resources/js/tablesorter/dist/css/theme.default.min.css">
	<!-- load jQuery and tablesorter scripts -->
	<script type="text/javascript" src="${cPath }/resources/js/tablesorter/dist/js/jquery-latest.js"></script>
	<script type="text/javascript" src="${cPath }/resources/js/tablesorter/dist/js/jquery.tablesorter.js"></script>

	<!-- tablesorter widgets (optional) -->
	<script type="text/javascript" src="${cPath }/resources/js/tablesorter/dist/js/jquery.tablesorter.widgets.js"></script>
		

<c:set var="classList" value="${pagingVO.dataList }" />
<div class="table-size-1300">	
	<h3>강의 리스트 입니다</h3>
	<table id="myTable" class="table-bordered" style="height:100%;">
		<thead>
			<tr class="tr-style height-44 tb-txt">
				<th class="th-border">강의관리번호</th>
				<th class="th-border">사용자등록번호</th>
				<th class="th-border">강의실명</th>
				<th class="th-border">강의명</th>
				<th class="th-border">강의관리번호</th>
				<th class="th-border">학과구분코드</th>
				<th class="th-border">등록일</th>
			</tr>
		</thead>
	
		<tbody class="tb-txt">
			<c:choose>
				<c:when test="${not empty classList }">
					<c:forEach items="${classList }" var="uclass">
						<tr>
							<td>
								<c:url value="/univ_man/classView.do" var="viewURL">
									<c:param name="classNo" value="${uclass.classNo }" />
								</c:url>
								<a href="${viewURL }">${uclass.classNo }</a>
							</td>
							<td>${uclass.memNo }</td>
							<td>${uclass.classRoom }</td>
							<td>${uclass.className }</td>
							<td>${uclass.classExtend }</td>
							<td>${uclass.majorCode }</td>
							<td>${uclass.classDate }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="17">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<div class="search-align m-top-10">
	<div id="searchUI" class="search-align">
		<select name="searchType" class="m-right-5">
			<option value>전체</option>
			<option value="name">강의명</option>
			<option value="result">강의실명</option>
		</select> 
		<input type="text" name="searchWord" onkeyup="enterkey();" /> 
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5" /> 
	</div>
		<input type="button" value="교직원 수업 추가" class="btn btn-secondary btn-sm" data-gopage="${cPath }/univ_man/classInsert.do" />
	</div>
	
	<div id="pagingArea" class="p-center m-bottom-10">
		${pagingVO.pagingHTML }
	</div>
</div>
	
	<form id="searchForm">
		<input type="hidden" name="searchType" />
		<input type="hidden" name="searchWord" />
		<input type="hidden" name="page" />
	</form>
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();
		
		$(function() {
			  $("#myTable").tablesorter();
// 			if ($("table#myTable tbody tr").length > 0)
// 				   $(this).tablesorter({ sortList: [[0,0]]});
			});
		
		
// 		$(function() {
// 			$("#myTable").tablesorter({ sortList: [[0,0], [1,0]] });
// 			console.log(sortList[0,0])
// 		});


		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	<jsp:include page="/includee/postScript.jsp" />

</body>
</html>