<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/includee/preScript.jsp" />


<form:form modelAttribute="certi" method="post" id="editForm">

<div class="table-size-1300">
	<h3>증명서 발급 등록</h3>
	<c:set var="mem" value="${member}" />
	${mem.memMajor }
	
	<table class="table table-bordered">
		<tr>
			<th>증명서</th>
			<td>
				<select name="certiCode">
					<option value>증명서 선택</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>발급사유</th>
			<td><input type="text" name="certiReason" required/></td>
		</tr>
		
		<tr>
			<th>수량</th>
			<td><input type="number" name="certiCount" value="" required/></td>
		</tr>
		
		<tr>
			<th>신청인</th>
			<td>
				<input type="text" name="certiReq" value="${mem.memId }" disabled>
				<input type="hidden" name="certiReq" value="${mem.memId }">
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-secondary btn-sm"/>
				<input type="reset" value="취소" class="btn btn-secondary btn-sm"/>
			</td>
		</tr>
	</table>
</div>

</form:form>

<script type="text/javascript">
$(function(){
	let certiCode = $('[name="certiCode"]');
	let certiCount = $("[name='certiCount']");
	let cateValue1 = $("[name='cateValue1']");
	
	$.ajax({
		url : "<%=request.getContextPath() %>/certificateManage/certificateList.do",
		dataType : "json",
		success : function(resp) {
			let options = [];
			$.each(resp, function(idx, cate){
				let option = $("<option>")
								.text(cate.cateName1)
								.attr("value", cate.cateCode);
				options.push(option);
			});
			certiCode.append(options);
			certiCode.val("${cate.cateCode}");
			certiCode.trigger("change");
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
	
	certiCode.on("change", function(){
		let cateCode = $(this).val();
		$.ajax({
			url : "<%=request.getContextPath() %>/certificateManage/certificateListValue.do",
			data : { "cateCode" : cateCode },
			dataType : "json",
			success : function(resp) {
				$(certiCount).click(function(){
				    amount = $(certiCount).val();
				    count = resp.cateValue1 * amount;
				    
				    cateValue1.val(count);
				  });
				alert("value1 : " + amount);
				$("input[name='cateValue1']").val(count)
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	});
	
	
});

</script>
	
<jsp:include page="/includee/postScript.jsp"/>
