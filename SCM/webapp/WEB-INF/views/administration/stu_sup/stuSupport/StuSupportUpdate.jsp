<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/includee/preScript.jsp" />

<div class="content-wrap">
업체 수정 페이지 입니다.
 
 	<form:form modelAttribute="business" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name=bussNo value="${business.bussNo }" />
		<table class="major-table">
			<tr>
				<th>업체명</th>
				<td>
					<input type="text" name="bussName"  value="${business.bussName}" />
					<form:errors path="bussName" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>업체 업무</th>
				<td>
					<input type="text" name="bussJob"  value="${business.bussJob}" />
					<form:errors path="bussJob" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>계약시작일</th>
				<td>
					<input type="date" name="bussSdate"  value="${business.bussSdate}" />
					<form:errors path="bussSdate" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>계약종료일</th>
				<td>
					<input type="date" name="bussEdate"  value="${business.bussEdate}" />
					<form:errors path="bussEdate" element="span" cssClass="error" />
				</td>
			</tr>
		</table>
		<div class="flex-center m-top-10">
			<input type="submit" value="저장" class="btn btn-secondary btn-n-sm m-right-5" />
			<input type="reset" value="취소" class="btn btn-secondary btn-n-sm" />
		</div>
	</form:form>
</div>

<jsp:include page="/includee/postScript.jsp" />	