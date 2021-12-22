<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<c:set var="table" value="${tableDetail }"/>
<c:set var="account" value="${accountDetail }"/>

<input type="text" value="${table.paytableNo }" id="paytableNo" hidden />
<input type="text" id="memId" name="paytableMem" value="${member.memId }" readonly hidden/>
<div class="table-size-1300">
	<table class="table table-bordered">
	<h3>급여테이블상세</h3>
		<thead>
			<tr>
				<th style="width: 125px;">이름</th>
				<th style="width: 140px;">급여</th>
				<th style="width: 300px;">적용일</th>
				<th>은행명</th>
				<th>계좌번호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:choose>
					<c:when test="${not empty table }">
								<td>
								<div id="inMem">${table.paytableMem}</div>
								</td>
								<td>
								<div class="pay" id="inPay" name="inPay"  style="display: inline;" >${table.paytablePayDisplay}</div>
								</td>
								<td>${table.paytableDate}</td>
					</c:when>
					<c:otherwise>
							<td colspan="3">임금 테이블이 없음.</td>
					</c:otherwise>
				</c:choose>	
				<c:choose>
					<c:when test="${not empty account }">
								<td>${account.accountBank }</td>
								<td>${account.accountNumber }</td>
					</c:when>
					<c:otherwise>
							<td colspan="2">계좌정보가 없음.</td>
					</c:otherwise>
				</c:choose>		
			</tr>
		</tbody>	
	</table>

	<!-- 새로운 급여 입력하는 곳 -->
	<form:form action="${cPath }/pay/payTableDetailInsert.do" name="insertForm" id="insertForm">
	
		<table class="table table-bordered" id="newinputForm" name="newinputForm" style="display: none;">
			<thead>
				<tr>
					<th>이름</th>
					<th>급여</th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><input type="text" value="${member.memName }" readonly /></th>
						<input type="text" id="memId" name="paytableMem" value="${member.memId }" readonly hidden/>
					<th><input type="text" id="inputPay" name="paytablePayDisplay" required="required" class="inputPay"/></th>
					<th><input type="text" id="inputReason" name="paytableReason" required="required" /></th>
				</tr>
			</tbody>
		</table>
	</form:form>	
	<!-- 여기까지 -->
		
		
	<!-- 새로운 계좌 입력하는 곳 -->	
	<form:form	action="" name="AccountForm" id="AccountForm">
		 <table class="table table-bordered" id="AccountTable" name="AccountTable" style="display: none;">
		 	<thead>
		 		<tr>
		 			<th>은행명</th>
		 			<th>계좌번호</th>
		 		</tr>
		 	</thead>
		 	<tbody>
			 	<tr>
			 		<th>
			 			<c:if test="${empty account.accountBank }">
			 				<input type="text" name="accountBank" id="accountBank" required="required">
			 			</c:if>
			 			<c:if test="${not empty account.accountBank }">
			 				<input type="text" name="accountBank" id="accountBank" value="${account.accountBank }" required="required">
			 			</c:if>		 			
			 		</th>
			 		
			 		<th>
			 			<c:if test="${empty account.accountNumber }">
			 				<input type="text" name="accountNumber" id="accountNumber" required="required">
			 			</c:if>
			 			<c:if test="${not empty account.accountNumber }">
			 				<input type="text" name="accountNumber" id="accountNumber" value="${account.accountNumber }" required="required">
			 			</c:if>
			 		</th>
			 	</tr>
		 	</tbody>
		 </table>
	</form:form>
	
	<div>
		<table class="table table-bordered">
			<tr>
			<c:if test="${empty account.accountBank}">
				<input type="button" value="계좌 등록" name="inAccountBtn" id="inAccountBtn" style="display: inline;"/>
				<input type="button" value="계좌 등록 완료" name="inAccountOKBtn" id="inAccountOKBtn" style="display: none;" />
				<input type="button" value="계좌 등록 취소" class="CancleBtn" name="accountCancleBtn" id="accountCancleBtn" style="display: none;" />
			</c:if>
			
			<c:if test="${not empty account.accountBank}">
				<input type="button" value="계좌 업데이트" name="moAccountBtn" id="moAccountBtn" style="display: inline;"/>
				<input type="button" value="계좌 수정 완료" name="moAccountOKBtn" id="moAccountOKBtn" style="display: none;" />
				<input type="button" value="계좌 수정 취소" class="CancleBtn" name="accountCancleBtn" id="accountCancleBtn" style="display: none;" />
			</c:if>
			</tr>
		</table>
	</div>
	<div>	
		<table class="table table-bordered">
			<tr>
			<c:if test="${empty table.paytablePay}">
			<input type="button" value="급여 등록" name="insertBtn" id="insertBtn" style="display: inline;"/>
			<input type="button" value="급여 등록 완료" name="insertOKBtn" id="insertOKBtn" style="display: none;" />
			<input type="button" value="급여 등록 취소" class="CancleBtn" name="CancleBtn" id="CancleBtn" style="display: none;" />
			</c:if>
			
			<c:if test="${not empty table.paytablePay}">
			<input type="button" value="급여 업데이트" name="insertBtn" id="insertBtn" style="display: inline;"/>
			<input type="button" value="급여 수정 완료" name="insertOKBtn" id="insertOKBtn" style="display: none;" />
			<input type="button" value="급여 수정 취소" class="CancleBtn" name="CancleBtn" id="CancleBtn" style="display: none;" />
			</c:if>
			</tr>
		</table>
	</div>
	
	<!-- 여기까지 -->
	
	<c:set var="tableDetail" value="${pagingVO.dataList }"/>
	
	<!-- 임금 변동내역 -->
	
	<table class="table table-bordered">
		<c:if test="${empty tableDetail }">
			<td>임금 변동 내역이 없습니다.</td>
		</c:if>
		<c:if test="${not empty tableDetail }">
			<h5>임금 변동내역</h5>
			<thead>
				<th>임금</th>
				<th>임금변동일</th>
				<th>임금변동사유</th>		
			</thead>
			<tbody>
				<c:forEach items="${tableDetail }" var="detail">
					<tr>
						<td  class="pay" >${detail.paytablePayDisplay }</td>
						<td>${detail.paytableDate }</td>
						<td>${detail.paytableReason }</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
</div>

  <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
  
	<script type="text/javascript">

	$("#updateBtn").on('click', function(){
		$("#updateReason").attr("style", "display:inline");
	
		$("#insertOKBtn").attr("style", "display:inline");
		$("#CancleBtn").attr("style", "display:inline");
		$("#insertBtn").attr("style", "display:none");
		
		$("#newinputForm").attr("style", "display:inline-table;");	
	});

	$("#insertBtn").on('click', function(){
		$("#updateReason").attr("style", "display:inline");
	
		$("#insertOKBtn").attr("style", "display:inline");
		$("#CancleBtn").attr("style", "display:inline");
		$("#insertBtn").attr("style", "display:none");
		
		$("#newinputForm").attr("style", "display:inline-table;");			
	});
	
	
	
	$("#inAccountBtn").on('click', function(){
		$("#inAccountOKBtn").attr("style", "display:inline");
		$("#accountCancleBtn").attr("style", "display:inline");
		$("#inAccountBtn").attr("style", "display:none");
		
		$("#AccountTable").attr("style", "display:inline-table;");	
	});
	
	
	$("#moAccountBtn").on('click', function(){
		$("#moAccountOKBtn").attr("style", "display:inline");
		$("#accountCancleBtn").attr("style", "display:inline");
		$("#moAccountBtn").attr("style", "display:none");
		
		$("#AccountTable").attr("style", "display:inline-table;");	
	});
	
	
	
	$(".CancleBtn").on("click", function(){
		location.reload();
	});

	$("#insertOKBtn").on("click", function(){
		$("#insertForm").submit();
	});
	

	$("#inAccountOKBtn").on("click", function(){
		var bank = $("#accountBank").val();
		var accNum = $("#accountNumber").val();
		var accountMem = $("#memId").val();
		$.ajax({
			url : $.getContextPath() + "/pay/AccountInsert.do",
			data : {
				accountBank : bank
				,accountNumber : accNum
				,accountMem : accountMem
			},
			dataType : "json",
			success : function(result) {
				console.log(result.result);
				$.confirm({
					title : '계좌정보입력 결과',
					content : result.result,
					buttons : {
						확인 : {
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
	});
	
	$("#moAccountOKBtn").on("click", function(){
		var bank = $("#accountBank").val();
		var accNum = $("#accountNumber").val();
		var accountMem = $("#memId").val();
		$.ajax({
			url : $.getContextPath() + "/pay/AccountModi.do",
			data : {
				accountBank : bank
				,accountNumber : accNum
				,accountMem : accountMem
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				$.confirm({
					title : '계좌정보입력 결과',
					content : result.result,
					buttons : {
						확인 : {
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
	});

	let searchForm = $("#searchForm").paging();

	$(document).ready(function(){
	    //키를 누르거나 떼었을때 이벤트 발생
	    $(".inputPay").bind('keyup keydown',function(){
	        inputNumberFormat(this);
	    });

	   
	    
	    //입력한 문자열 전달
	    function inputNumberFormat(obj) {
	        obj.value = comma(uncomma(obj.value));
	    }
	      
	    //콤마찍기
	    function comma(str) {
	        str = String(str);
	        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	    }

	    //콤마풀기
	    function uncomma(str) {
	        str = String(str);
	        return str.replace(/[^\d]+/g, '');
	    }

	    //숫자만 리턴(저장할때)
	    //alert(cf_getNumberOnly('1,2./3g')); -> 123 return
	    function cf_getNumberOnly (str) {
	        var len      = str.length;
	        var sReturn  = "";

	        for (var i=0; i<len; i++){
	            if ( (str.charAt(i) >= "0") && (str.charAt(i) <= "9") ){
	                sReturn += str.charAt(i);
	            }
	        }
	        return sReturn;
	    }
	});

	</script>

</html>	