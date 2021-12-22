<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<!DOCTYPE html>
<html>
<h3>급여입금처리화면</h3>
<c:set var="payList" value="${pagingVO.dataList }"/>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>
				선택
				<input type="checkbox" class="payCheckAll" name="payCheckAll" id="payCheckAll" />
			</th>
			<th>이름</th>
			<th>월급(세전)</th>
			<th>보험</th>
			<th>세금</th>
			<th>월급(세후)</th>
			<th>입금은행</th>
			<th>입금계좌</th>
			<th>입금상태</th>
			<th>신청일</th>
			<th>행정직원</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<c:choose>
				<c:when test="${not empty payList }">
					<c:forEach items="${payList }" var="payList">
						<td>
							<input type="checkbox" class="payCheck" name="payCheck" value="${payList.payNo }" />
						</td>
						<td>${payList.payMem }</td>		
						<td>${payList.TPayDisplay }</td>
						<td>${payList.payInsuranceDisplay }</td>
						<td>${payList.payTaxDisplay }</td>
						<td>${payList.payFpayDisplay }</td>
						<td>${payList.payBank }</td>
						<td>${payList.payAccount }</td>
						<td>${payList.payState }</td>
						<td>${payList.payDate }</td>
						<td>${payList.payFwriter }</td>
					</c:forEach>
				</c:when>
			</c:choose>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="12">
				<input type="button" value="급여입금" id="inputPayBtn"/>
				<input type="button" value="입금신청취소(반려)" id="canclePayBtn"/>
				
				<select id="reason">
					<option value="">반려사유를선택하세요</option>
					<option value="급여정보문제">급여정보문제</option>
					<option value="사원정보문제">사원정보문제</option>
					<option value="기타">기타</option>
				</select>
				
				<div id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
				<div id="searchUI">
					<select name="searchType">
						<option value>전체 분류</option>
						<option value="ADMIN">행정</option>
						<option value="PROP">교수</option>
						<option value="ASSIS">조교</option>
						<option value="STUD">학생</option>
					</select>
					<input type="text" name="searchWord" placeholder="직원 이름"/>					
					<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />
</form>	

<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<script type="text/javascript">
	var checkArr = [];
	
	$("#payCheckAll").click(function(){
		checkArr=[];
		if($("#payCheckAll").prop("checked")){
			$(".payCheck").prop("checked", true);
		}
		else{
			$(".payCheck").prop("checked", false);
		}
	});
	
	$(".payCheck").click(function(){
		checkArr=[];
		if($("input[name='payCheck']:checked")){
			$("#payCheckAll").prop("checked", false);
		}
		else {
			$("#payCheckAll").prop("checked", true);
		}
		
	});
	
	
	
	$("#inputPayBtn").on("click", function(){
		$("input[name=payCheck]:checked").each(function() {
			checkArr.push($(this).val()); 
		})
		
		$.ajax({
			url : $.getContextPath() + "/pay/finalPay.do",
			data : {
				checkArr : checkArr
			},
			type : "POST",
			dataType : "json",
			success : function(resultMap) {
				
				$.confirm({
					title : '처리결과',
					content :	"성공 : "+resultMap.ok  +"<br>"+
								"임금미정 : "+resultMap.no +"<br>"+
								"실패: "+resultMap.fail,
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								location.reload();
							}
							
							
						}
					}
				});

			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}
		});
		
		checkArr=[];
	});
	
	
	$("#canclePayBtn").on("click", function(){
		$("input[name=payCheck]:checked").each(function() {
			checkArr.push($(this).val()); 
		})
		var reason = $("#reason option:selected").val();
		
		$.ajax({
			url : $.getContextPath() + "/pay/canclePay.do",
			data : {
				checkArr : checkArr
				,reason : reason
			},
			type : "POST",
			dataType : "json",
			success : function(resultMap) {
				
				$.confirm({
					title : '처리결과',
					content :	"성공 : "+resultMap.ok  +"<br>"+
								"실패 : "+resultMap.fail +"<br>",
					buttons : {
						확인 : {
							btnClass : 'btn-blue',
							action : function(){
								location.reload();
							}
						}
					}
				});
			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}
		});
		
		checkArr=[];
	});
</script>

</html>