<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/includee/preScript.jsp" />

<div class="content-wrap">
<h5>장학관련 입력 페이지 입니다.</h5>

 
 	<form:form modelAttribute="scholar" method="post" enctype="multipart/form-data" id="editForm">
		<table class="major-table">
<!-- 			<tr> -->
<!-- 				<th>장학관리번호</th> -->
<!-- 				<td> -->
<%-- 					${scholar.schNo } --%>
<%-- 					<form:errors path="schNo" element="span" cssClass="error" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<th>장학금수령인학번</th>
				<td>
					<input type="text" name="schMem" value="${scholar.schMem }" placeholder="ex)202100000" />
					<form:errors path="schMem" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>장학금수령인</th>
				<td>
<%-- 					<input type="text" name="schMem" value="${scholar.schMem }" placeholder="ex)202100000" /> --%>
					<form:errors path="schMem" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>장학구분코드</th>
				<td>
					<select name="schCode">
						<option value>장학구분</option>
						<option value="SCC100">국가장학금</option>
						<option value="SCC101">교내장학금</option>
						<option value="SCC102">교외장학금</option>
						<option value="SCC103">근로장학금</option>
					</select>
					<select class="typeCode">
						<option value>장학금 선택</option>
					</select>
					<form:errors path="schCode" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>장학금액</th>
				<td>
					<input type="text" name="schAmount" value="${scholar.schAmount }" placeholder="ex)100" />
					<form:errors path="schAmount" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>
					<input type="text" name="schNote"  value="${scholar.schNote }" placeholder="ex)" />
					<form:errors path="schNote" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>추천 / 신청 사유</th>
				<td>
					<input type="text" name="schReason"  value="${scholar.schReason }" placeholder="ex)성적 우수 학생" />
					<form:errors path="schReason" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>추천인</th>
				<td>
					<input type="text" name="schRec"  value="${scholar.schRec }" placeholder="ex)노제" />
					<form:errors path="schRec" element="span" cssClass="error" />
				</td>
			</tr>
			
		</table>
		
		<div class="flex-center m-top-10">
			<input type="submit" class="btn btn-secondary btn-n-sm m-right-5" value="저장" />
			<input type="reset" class="btn btn-secondary btn-n-sm" value="취소" />
		</div>
		
	</form:form>
</div>
	
	<form id="otherSelectForm" action="${pageContext.request.contextPath }/stu_sup/getScholarShip.do">
		<input type="hidden" name="schCode"/><!-- 국장, 교내, 교외, 근로 장학금 큰 분류 -->
		<input type="hidden" name=typeCode"/> <!-- 그 아래에 있는 장학금 종류-->
	</form>
	
	<script type="text/javascript">
		let signScholarShipListCode;
		let searchForm = $('#searchForm');
		
		$('.schCode').on("change", function(){
			signScholarShipListCode = $(this).val();
			let url;
			let scholarShipType = $(this).next();
			console.log(scholarShipType);
			
			if(signScholarShipListCode == 'SC100' ) {
				url = "${pageContext.request.contextPath}/stu_sup/getScholarList.do"
			} else {
				url = "${pageContext.request.contextPath}/stu_sup/getScholarType.do"
			}
			
			$.ajax({
				url : url,
				dataType : "json",
				success : function(resp) { //resultList
					let options = [];
					$.each(resp, function(idx, list){
					options.push(
						$("<option>").text(list.name)
									.attr("value",list.code)
					);
				});
				deptCode.html(options);
				deptCode.val("${docu.signMemberList[0].deptCode}");
				deptCode.trigger("change");
				},
				error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
				}
			})
		
		})
		
		
		
		
		
		
		
	</script>
	
	
	<jsp:include page="/includee/postScript.jsp" />	
	
</body>
</html>