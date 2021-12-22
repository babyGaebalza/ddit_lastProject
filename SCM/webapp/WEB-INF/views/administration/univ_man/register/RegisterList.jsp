<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<!DOCTYPE html>
<html>

<div class="table-size-1300">
	<h3>학적 리스트</h3>
	<table class="table table-bordered">
		<c:set var="regList" value="${pagingVO.dataList }"/>
		
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">등록번호</th>
				<th style="border-right: 1px solid #b6bccd;">이름</th>
				<th style="border-right: 1px solid #b6bccd;">나이</th>
				<th style="border-right: 1px solid #b6bccd;">생년월일</th>
				<th style="border-right: 1px solid #b6bccd;">전공</th>
				<th style="border-right: 1px solid #b6bccd;">학적상태</th>
				<th style="border-right: 1px solid #b6bccd;">최종수정일</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${regList }" var="reg">
				<tr  style="text-align:center">
					<td>${reg.memId }</td>
					<td><a href="${cPath }/register/stuRegList.do?memId=${reg.memId }">${reg.memName }</a></td>
					<td>${reg.memAge }</td>
					<td>${reg.memBirth }</td>
					<td>${reg.memMajor }</td>
					<c:forEach items="${reg.register }" var="a">
						<td>${a.regCode }</td>
						
						<c:if test="${a.regCode ne '-'}">
							<td>${a.regFdate }</td>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="7">
					<div class="search-align">
						<div id="searchUI" class="search-align">
							<select name="searchType" class="m-right-5">
								<option value>전체 분류</option>
								<option value="NAME">이름</option>
								<option value="MAJOR">전공</option>
		
							</select>
							<input type="text" name="searchWord" onkeyup="enterkey();" />
							<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5"/>
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

</html>