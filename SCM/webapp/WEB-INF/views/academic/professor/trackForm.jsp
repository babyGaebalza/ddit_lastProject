<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>
<form:form modelAttribute="docu" method="post">
	<body>
	
	<div class="table-size-1300">
		<table class="table table-bordered">
			<tr>
				<th>서류종류</th>
				<td><input type="text" name="docuCode"
					value="${document.getDocuCode}" />
				<form:errors path="docuCode" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><input type="text" name="docuCont"
					value="${document.getDocuCont}" />
				<form:errors path="docuCont" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>필요결재횟수</th>
				<td><input type="number" name="docuReqcnt"
					value="${document.getDocuReqcnt}" />
				<form:errors path="docuReqcnt" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>누적결재횟수</th>
				<td><input type="number" name="docuReqnow"
					value="${document.getDocuReqnow}" />
				<form:errors path="docuReqnow" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>서류번호</th>
				<td><input type="text" name="docuNo" required
					value="${document.getDocuNo}" />
				<form:errors path="docuNo" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>신청자</th>
				<td><input type="text" name="docuReq" required
					value="${document.getDocuReq}" />
				<form:errors path="docuReq" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>결재인1</th>
				<td><input type="text" name="docuAp1"
					value="${document.getDocuAp1}" />
				<form:errors path="docuAp1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>결재인2</th>
				<td><input type="text" name="docuAp2"
					value="${document.getDocuAp2}" />
				<form:errors path="docuAp2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>신청일자</th>
				<td><input type="date" name="docuReqdate"
					value="${document.getDocuReqdate}" />
				<form:errors path="docuReqdate" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>최종결재여부</th>
				<td><input type="text" name="docuState"
					value="${document.getDocuState}" />
				<form:errors path="docuState" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>최종결재자</th>
				<td><input type="text" name="docuApf" required
					value="${document.getDocuApf}" />
				<form:errors path="docuApf" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>최종결재일자</th>
				<td><input type="date" name="docuFdate"
					value="${document.getDocuFdate}" />
				<form:errors path="docuFdate" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>취소여부</th>
				<td><input type="text" name="docuCancle" required
					value="${document.getDocuCancle}" />
				<form:errors path="docuCancle" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="docuFilename"
					value="${document.getDocuFilename}" />
				<form:errors path="docuFilename" element="span" cssClass="error" /></td>
			</tr>
		</table>
	</div>
</form:form>
