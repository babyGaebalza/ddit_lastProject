<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<div class="table-size-1300">
<h3>증명서 발급 리스트</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd;">
				선택
				<input type="checkbox" class="CheckAll" name="CheckAll" id="CheckAll" />
				</th>
				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">증명서코드</th>
				<th style="border-right: 1px solid #b6bccd;">증명서명</th>
				<th style="border-right: 1px solid #b6bccd;">발급사유</th>
				<th style="border-right: 1px solid #b6bccd;">신청인</th>
				<th style="border-right: 1px solid #b6bccd;">수량</th>
			</tr>
		</thead>
		<c:set var="certiList" value="${pagingVO.dataList }" />
		<tbody>
			<c:choose>
				<c:when test="${not empty certiList }">
					<c:forEach items="${certiList }" var="certi">
						<tr style="text-align:center">
							<input type="hidden" name="certiState" value="${certi.certiState}"/>
							
							<td>
								<c:choose>
									<c:when test="${certi.certiState eq 'Y'}">
										<span class='badge badge-info'>승인완료</span>
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="stateCheck" name="stateCheck" value="${certi.certiNo }" id="checkbox"/>
									</c:otherwise>
								</c:choose>
							
							</td>

							<td>${certi.certiNo}</td>
							
							<td>${certi.category1.cateCode}</td>
	
							<td>${certi.category1.cateName1}</td>

							<td>${certi.certiReason}</td>
	
							<td>${certi.member1.memName}</td>
							
							<td>${certi.certiCount}개</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<div class="search-align">
						<div id="searchUI" class="search-align">
							<select name="searchType" class="m-right-5">
								<option value>검색 아직 안넣음</option>
								<option value="number">번호</option>
								<option value="code">증명서코드</option>
								<option value="state">상태</option>
							</select>
							<input type="text" name="searchWord" />
							<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm m-left-5"/>
						</div>
						
						<div>
							<input type="button" value="상태변경" id="stateBtn" class="btn btn-secondary btn-sm"/>
						</div>
					</div>
				</td>
			</tr>
		</tfoot>
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
$(function(){
// 	var state = $("input[name='certiState']").val();

// 	if(state == "Y"){
// 		$("#checkbox").remove();	
// 		$("#checkMessage").append($("<span class='badge badge-info'>" + "승인완료" + "</span>"))
// 		$("#checkMessage").attr('style', 'display: inline');
// 	}
	
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
			url : "<%=request.getContextPath()%>/certificateManage/certirficateStateList.do",
			data : {
				checkArr : checkArr
			},
			type : "POST",
			dataType : "json",
			success : function(certiList) {
				location.reload();
			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}
		});
	})
})
</script>

<jsp:include page="/includee/postScript.jsp" />
