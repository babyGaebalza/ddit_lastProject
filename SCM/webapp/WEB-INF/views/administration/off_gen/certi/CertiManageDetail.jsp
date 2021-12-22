<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/includee/preScript.jsp" />


<div class="table-size-1300">
	<h3>증명서 관리 상세페이지</h3>
	<table class="table table-bordered">
		<tr>
			<th>코드</th>
			<td>${cate.cateCode}</td>
		</tr>
		
		<tr>
			<th>증명서명</th>
			<td>${cate.cateName1}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<c:url value="/certificateManage/certificateManageUpdate.do" var="updateURL">
					<c:param name="cateCode" value="${cate.cateCode}" />
				</c:url>
				<input type="button" value="수정" id="modifyBtn" data-gopage="${updateURL }" class="btn btn-secondary btn-sm"/>
			</td>
		</tr>
	</table>
<form id="updateForm" action="${cPath }/certificateManage/certificateManageUpdate.do" method="get">
	<input type="hidden" name="cateCode" value="${cate.cateCode}">
</form>
</div>	


<script type="text/javascript">
let updateForm = $("#updateForm");

$("#modifyBtn").on("click", function(){
	let ok = $.confirm({
	    title: '증명서 수정',
	    content: '해당 증명서를 수정하시겠습니까?',
	    buttons: {
	        수정: function () {
	        	updateForm.submit();
	        },
	        취소: function () {
	        },
	    }
	});
});
 </script>

<jsp:include page="/includee/postScript.jsp"/>
