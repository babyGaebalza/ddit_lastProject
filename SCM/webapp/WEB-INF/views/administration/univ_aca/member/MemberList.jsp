<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>

.th-width-100{
	width: 100px;
}

.th-width-170{
	width: 170px;
}

.th-width-200{
	width: 200px;
}

.th-width-230{
    width: 230px;
}

.td-align-center{
	text-align: center;
}

.td-font-link{
	font-weight: bold;
}

.tdLine:hover {
    color: #07c6ff;
}

.table-font-size{
	font-size: 20px;
}
</style>

<c:set var="memberList" value="${pagingVO.dataList }"/>
<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
	<h5>사용자리스트</h5>
	</div>
	<table class="table table-bordered">
	<div>${gogogo.page2 }</div>
	<div>${gogogo.searchType2 }</div>
	<div>${gogogo.sratchWord2 }</div>
		<thead class="table-font-size">
		<tr class="tr-style " >
			<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;" class="th-width-200">사번/학번</th>
			<th style="border-right: 1px solid #b6bccd;" class="th-width-230">이름</th>
			<th style="border-right: 1px solid #b6bccd;" class="th-width-170">분류</th>
			<th style="border-right: 1px solid #b6bccd;" class="th-width-100">직급</th>
			<th style="border-right: 1px solid #b6bccd;" class="th-width-170">전공</th>
		</tr>
		</thead>
		<tbody class="table-font-size">
			<c:choose>
				<c:when test="${not empty memberList }">
					<c:forEach items="${memberList }" var="member">
							<tr class="tdLine">	
								<td>${member.memId }</td>
		
								<td>
									<form:form action="${cPath }/member/memberView.do" class="goDetail">
										<input type="hidden" name="memId" value="${member.memId }">
										<input type="hidden" name="searchType" id="searchType" />
										<input type="hidden" name="searchWord" id="searchWord" />
										<input type="hidden" name="page" id="page" value="${pagingVO.currentPage }" />
										<a class="detailBtn td-font-link">${member.memName }</a>
										
									</form:form>
								</td>
		
								<td>
									<c:if test="${member.userCode eq 'US01'}">
									행정직원
									</c:if>
									<c:if test="${member.userCode eq 'US02'}">
									교수
									</c:if>
									<c:if test="${member.userCode eq 'US03'}">
									조교
									</c:if>
									<c:if test="${member.userCode eq 'US04'}">
									학생
									</c:if>						
								</td>
		
								<td>
									<c:if test="${member.rankCode eq 'RANK01'}">
									처장
									</c:if>
									<c:if test="${member.rankCode eq 'RANK02'}">
									부장
									</c:if>
									<c:if test="${member.rankCode eq 'RANK03'}">
									사원
									</c:if>
									<c:if test="${member.rankCode eq 'RANK04'}">
									학과장
									</c:if>
									<c:if test="${member.rankCode eq 'RANK05'}">
									교수
									</c:if>
									<c:if test="${member.rankCode eq 'RANK06'}">
									조교
									</c:if>
								</td>
		
								<td>${member.memMajor }</td>
		
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
		<tfoot>
			<tr>
				<td colspan="6">
				<div class="search-align">
						<div id="searchUI" >
							<select name="searchType" style="height: 34px;" class="m-right-5">
								<option value>전체 분류</option>
								<option value="ADMIN">행정</option>
								<option value="PROP">교수</option>
								<option value="ASSIS">조교</option>
								<option value="STUD">학생</option>
							</select>
							<input type="text" name="searchWord"  onkeyup="enterkey();" style="height: 34px;"/>
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5""/>
						</div>
						
						<div>
							<input type="button" value="신규 사용자 등록" class="linkBtn btn btn-secondary" data-gopage="${cPath }/member/memberform.do"/>
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
<form:form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form:form>

  <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
	<script type="text/javascript">	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}")
		let searchForm = $("#searchForm").paging();

		
		$(".detailBtn").on("click", function(){
			var searchType = $("#searchType").val("${searchVO.searchType}");
			var searchWord = $("#searchWord").val("${searchVO.searchWord}");
			var goDetail = $(this).closest(".goDetail");
			goDetail.submit();
		});
		
		function enterkey(){
			if(window.event.keyCode == 13){
				document.getElementById("searchBtn").click();
			}
		}

	</script>
