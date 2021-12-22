<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<style>
.table-size-1300{
	margin-top: 100px;
}
#divBtn{
	float: right;
}
.search-align{
	float: right;
}
</style>

<div class="table-size-1300">  
<h3>트랙 리스트</h3>

	<div id="searchUI" class="search-align">
		<select name="searchType" class="m-right-5">
			<option value>전체</option>
			<option value="major">학과명</option>
			<option value="track">트랙명</option>
		</select>
		<input type="text" name="searchWord" />
		<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5"/>
	</div>
						
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">
				선택
				<input type="checkbox" class="CheckAll" name="CheckAll" id="CheckAll" />
				</th>
				<th style="border-right: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">학과명</th>
				<th style="border-right: 1px solid #b6bccd;">트랙이름</th>
				<th style="border-right: 1px solid #b6bccd;">등록일</th>
			</tr>
		</thead>
		
		<c:set var="trackList" value="${pagingVO.dataList }" />
		
		<tbody>
			<c:choose>
				<c:when test="${not empty trackList }">
					<c:forEach items="${trackList }" var="track">
						<tr style="text-align:center">
							<input type="hidden" name=trackNo value="${track.trackNo}"/>
							<td>
								<c:choose>
									<c:when test="${track.trackDelete eq 'Y'}">
										<span class='badge badge-danger'>삭제완료</span>
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="stateCheck" name="stateCheck" value="${track.trackNo}" id="checkbox"/>
									</c:otherwise>
								</c:choose>
							</td>
							
							<td>
							<c:url value="/trackManage/trackDetail.do" var="viewURL">
								<c:param name="trackNo" value="${track.trackNo }" />
							</c:url>
							<a href="${viewURL }">${track.trackNo }</a>
							</td>
							
							<td>${track.majorName}</td>
		
							<td>${track.trackName}</td>
							
							<td>${track.trackDate}</td>
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
	<div id="divBtn">
<%-- 							<input type="button" value="트랙 등록" class="linkBtn btn btn-secondary btn-sm" data-gopage="${cPath }/trackManage/trackManageInsert.do" /> --%>
	<input type="button" value="트랙 삭제" id="stateBtn" class="btn btn-secondary btn-sm"/>
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

<script>
$(function(){
	var checkArr = [];
	
	$("#CheckAll").click(function(){
		checkArr=[];
		if($("#CheckAll").prop("checked")){
			$(".stateCheck").prop("checked", true);
		}
		else{
			$(".stateCheck").prop("checked", false);
		}
	});
	
	$(".stateCheck").click(function(){
		checkArr=[];
		if($("input[name='stateCheck']:checked")){
			$("#CheckAll").prop("checked", false);
		}
		else {
			$("#CheckAll").prop("checked", true);
		}
	})
	
	$("#stateBtn").on("click", function(){
		$("input[name='stateCheck']:checked").each(function() {
			checkArr.push($(this).val());
		})
		$.ajax({
			url : "<%=request.getContextPath()%>/trackManage/trackState.do",
			data : {
				checkArr : checkArr
			},
			type : "POST",
			dataType : "json",
			success : function(trackList) {	
				location.reload();
			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}
		});
		checkArr=[];
	})
})
</script>

<jsp:include page="/includee/postScript.jsp" />