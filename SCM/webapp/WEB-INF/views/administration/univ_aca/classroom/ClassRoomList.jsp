<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="classroomList" value="${pagingVO.dataList }"/>

	<div class="table-size-1300">
	<h3 style="padding-top: 10px;">강의실 관리</h3>
		<table class="table table-bordered">
			<thead>
				<tr class="tr-style">
					<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">구분번호  </th>
					<th style="border-right: 1px solid #b6bccd;">강의실정보</th>
					<th style="border-right: 1px solid #b6bccd;">사용가능</th>
					<th style="border-right: 1px solid #b6bccd;">단과대</th>
					<th style="border-right: 1px solid #b6bccd;">해당학과</th>
					<th style="border-right: 1px solid #b6bccd;">수용인원</th>
				</tr>
			</thead>
			
			<c:forEach items="${classroomList }" var="croom">
				<tr style="text-align:center">
					<td>${croom.facNo }</td>					
					<td><a href="${cPath }/classroom/classroom.do?facNo=${croom.facNo}"/>${croom.facName }</td>
					<c:if test="${croom.facResult eq 'Y'}">
						<td>사용가능</td>
					</c:if>
					<c:if test="${croom.facResult eq 'N'}">
						<td>사용불가</td>
					</c:if>
					<td>${croom.collegeCode }</td>
					<td>${croom.majorCode }</td>
					<td>${croom.facNumber }</td>
				</tr>
			</c:forEach>
			
			<tfoot>
					<tr>
						<td colspan="6">
							<div class="search-align">
								<div id="searchUI" class="search-align">
									<select name="searchType" class="m-right-5">
										<option value>전체</option>
										<option value="college">단과대</option>
										<option value="name">강의실정보</option>
									</select> 
									<input type="text" name="searchWord" onkeyup="enterkey();" /> 
									<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5" /> 
								</div>
							</div>
							
							<div id="pagingArea" class="p-center m-bottom-10">
								${pagingVO.pagingHTML }
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
	</div>
	
	<form id="searchForm">
		<input type="hidden" name="searchType" />
		<input type="hidden" name="searchWord" />
		<input type="hidden" name="page" />
	</form>
	
	<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();
		
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	
