<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.use {
    width: 110px;
    text-align: center;
}

.num{
	width: 80px;
	text-align: center;
}
</style>

	
	<div class="table-size-1300">
		<div class="board-title">
			<h5 style="padding-top: 10px;">강의실상세</h5>
		</div>
	<table class="table table-bordered">
			<thead>
			<tr class="tr-style">
				<th>강의실정보</th>
				<th class="use">사용가능여부</th>
				<th>단과대</th>
				<th>해당학과</th>
				<th class="num">수용인원</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td><div id="facName">${croom.facName }</div></td>
				<td  class="use">
					<c:if test="${croom.facResult eq 'Y'}">
						<div id="oUse">사용가능</div>
					</c:if>
					<c:if test="${croom.facResult eq 'N'}">
						<div id="oUse">사용불가</div>
					</c:if>
					<select id="reUse" name="reUse" style="display: none;" required="required">
								<option value>사용가능여부</option>
							<c:if test="${croom.facResult eq 'Y'}">
								<option  value="Y" selected="selected">사용가능</option>
								<option value="N">사용불가</option>
							</c:if>
							<c:if test="${croom.facResult eq 'N'}">
								<option value="Y">사용가능</option>
								<option value="N" selected="selected">사용불가</option>
							</c:if>
							<c:if test="${croom.facResult ne 'N' and croom.facResult ne 'Y'}">
								<option value="Y">사용가능</option>
								<option value="N">사용불가</option>
							</c:if>						
					</select>
				</td>
				<td id="college">${croom.collegeCode }</td>
				<td id="major">
				<div id="dMajor" style="display: inline;">${croom.majorCode }</div> 
				
					<select id='majorCode' name='majorCode' onchange='majorChange(this)' style="display: none;">
						<option value>전공</option>
							<c:forEach items='${majorList }' var='majorCode'>
								<c:if test="${croom.majorCode eq majorCode.majorName }">	
									<option value='${majorCode.majorCode }' lang="${majorCode.collegeCode }" selected="selected">${majorCode.majorName }</option>
								</c:if>
								<option value='${majorCode.majorCode }' lang="${majorCode.collegeCode }">${majorCode.majorName }</option>
							</c:forEach>
					</select>

			</td>
				<td  class="num">
				<div id="aNum" style="display: inline;">${croom.facNumber }</div>
				<input type="number" value="${croom.facNumber }" name="refacNum" id="refacNum" style="display: none;"/>
				</td>
			</tr>	
			</tbody>

	</table>
	
	<div id="btnForm" style="float: right;padding-bottom: 35px;">
		<input type="button" value="강의실 정보 수정" name="modiBtn" id="modiBtn" style="display: inline;" class="btn btn-secondary btn-sm">
		<input type="button" value="완료" name="okBtn" id="okBtn" style="display: none;" class="btn btn-secondary btn-sm">
		<input type="button" value="취소" name="cancleBtn" id="cancleBtn" style="display: none;" class="btn btn-secondary btn-sm">			
	</div>
	
	<c:set var="crInfo" value="${pagingVO.dataList }"/>
	
	
	
	<div class="board-title" style="padding-top: 35px;">
		<h5>사용내역</h5>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th>구분</th>
				<th>신청자</th>
				<th>사용시작일</th>
				<th>사용종료일</th>
				<th>강의날짜A</th>
				<th>강의날짜B</th>
				<th>강의실예약시간</th>
			</tr>
		</thead>
			
	<tbody>
		<c:if test="${empty crInfo}">
			<tr>
				<td colspan="7">이용내역이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty crInfo}">
			<c:forEach items="${crInfo }" var="a">
					<tr>
						<td>${a.resNo }</td>
						<td>${a.resMem }</td>
						<td>${a.resSdate }</td>
						<td>${a.resEdate }</td>
						<td>${a.resClassA }</td>
						<td>${a.resClassB }</td>
						<td>${a.resTimeClass }</td>
					</tr>
			</c:forEach>
		</c:if>			
	</tbody>
						
	</table>
</div>

<c:forEach items='${collegeList }' var='collegeCode'>
	<div class="collegeName" value="${collegeCode.cateName1 }" hidden></div>
</c:forEach>


<script type="text/javascript">
var facNo = ${croom.facNo};
var facName = $("#facName").text();

var Use = document.getElementById("reUse");
var reUse = Use.options[Use.selectedIndex].value;

$("#reUse").on("change", function(){	
	reUse = Use.options[Use.selectedIndex].value;
});

var selMajor = document.getElementById("majorCode");
var majorCode = selMajor.options[selMajor.selectedIndex].text;		
var collegeCode = selMajor.options[selMajor.selectedIndex].lang;

var refacNum = $("#refacNum").val(); 
	
	$("#refacNum").on("change", function(){
		refacNum = $("#refacNum").val(); 
	});
	
	$("#modiBtn").on("click", function(){
		$("#modiBtn").attr('style', 'display: none;');
		$("#okBtn").attr('style', 'display: inline;');
		$("#cancleBtn").attr('style', 'display: inline;');
		
		$("#dMajor").attr('style', 'display: none;');
		$("#majorCode").attr('style', 'display: inline-flex;');

		$("#refacNum").attr('style', 'display: inline-flex;width: 40px;');
		$("#aNum").attr('style', 'display: none;');

		$("#oUse").attr('style', 'display: none;');
		$("#reUse").attr('style', 'display: inline-flex;width: 90px;');
		
		
	});
	
	function majorChange(obj) {
		var selMajor = document.getElementById("majorCode");
		majorCode = selMajor.options[selMajor.selectedIndex].text;		
		collegeCode = selMajor.options[selMajor.selectedIndex].lang;
		
		$("#college").text(collegeCode);
		
	}
	

	
	
	
	$("#okBtn").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/classroom/modiclassroom.do",
			data : {
				facNo : facNo,
				reUse : reUse,
				reMaj : majorCode,
				reNum : refacNum,
				facName : facName
			},
			method : "post",
			dataType: "json",
			success : function(result){
				if(result=="OK"){					
					location.reload();
				}
				else{
					$.alert({
						title:'오류',
						content:"오류가 발생했습니다."
					});
					location.reload();	
				}
			},
			error : function(xhr, errorResp, error) {
				console.log("에러");
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
				location.reload();
			}
		});
	});
	
	$("#cancleBtn").on("click", function(){
		location.reload();
	});
	
	
</script>

	
