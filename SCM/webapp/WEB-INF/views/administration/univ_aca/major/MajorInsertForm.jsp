<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<jsp:include page="/includee/preScript.jsp" />

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.authMember" var="authMember"/>
	</security:authorize>
<div class="">
	<div class="content-wrap">
	<h5>학과 입력 페이지 입니다.</h5>
	 
		<form:form modelAttribute="major" method="post" enctype="multipart/form-data" id="editForm">
				<table class="major-table">
					<tr>
						<th>단과대명</th>
						<td>
							<select name="collegeCode" class="input-box width-300">
								<option value>선택</option>
								<option value="CO01">인문대학</option>
								<option value="CO02">사회과학대학</option>
								<option value="CO03">자연과학대학</option>
								<option value="CO04">경상대학</option>
								<option value="CO05">공과대학</option>
								<option value="CO06">농업과학대학</option>
								<option value="CO07">약학대학</option>
								<option value="CO08">의과대학</option>
								<option value="CO09">생활과학대학</option>
								<option value="CO10">예술대학</option>
								<option value="CO11">수의과대학</option>
								<option value="CO12">사범대학</option>
								<option value="CO13">간호대학</option>
								<option value="CO14">생명과학대학</option>
								<option value="CO15">자유전공대학</option>
							</select>
							<form:errors path="collegeCode" element="span" cssClass="error" />
						</td>
					</tr>
					
		<!-- 			<tr> -->
		<!-- 				<th>학과코드</th> -->
		<!-- 				<td> -->
		<%-- 					<input type="text" name="majorCode" value="${major.majorCode }" required="required" placeholder="ex) C01M00" /> --%>
		<%-- 					<form:errors path="majorCode" element="span" cssClass="error" /> --%>
		<!-- 				</td> -->
		<!-- 			</tr> -->
					
					<tr>
						<th>전공명</th>
						<td>
							<input type="text" class="input-box width-300" name="majorName" value="${major.majorName }" required="required" placeholder="ex) OO학과" />
							<form:errors path="majorName" element="span" cssClass="error" />
						</td>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th>작성자</th> -->
<!-- 						<td> -->
							<input type="hidden" class="input-box width-300" name="majorWriter" value="${authMember.memName }" required="required" placeholder="ex) 용용" readonly />
							<form:errors path="majorWriter" element="span" cssClass="error" />
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th>등록금</th>
						<td>
							<input type="text" class="majorPay input-box width-300" name="majorPayDisplay" required="required" placeholder="ex) 120" /> 원
							<form:errors path="majorPay" element="span" cssClass="error" />
						</td>
					</tr>
					<tr>
						<th>학과정원</th>
						<td>
							<input type="text" class="input-box width-300" name="majorCount"  value="${major.majorCount }" required="required" placeholder="ex) 30" /> 명
							<form:errors path="majorCount" element="span" cssClass="error" />
						</td>
					</tr>
		<!-- 			<tr> -->
		<!-- 				<th>최종수정자</th> -->
		<!-- 				<td> -->
		<%-- 					<input type="text" name="majorFWriter"  value="${major.majorFWriter }" required="required" placeholder="ex) 용용" /> --%>
		<%-- 					<form:errors path="majorFWriter" element="span" cssClass="error" /> --%>
		<!-- 				</td> -->
		<!-- 			</tr> -->
					<tr>
						<th>졸업충족학점</th>
						<td>
							<input type="text" class="input-box width-300" name="majorPoint"  value="${major.majorPoint }" required="required" placeholder="ex) 30" /> 점
							<form:errors path="majorPoint" element="span" cssClass="error" />
						</td>
					</tr>
				</table>
				<div class="flex-center m-top-10">
					<input type="submit" class="btn btn-secondary btn-n-sm m-right-5" value="저장" />
					<input type="reset" class="btn btn-secondary btn-n-sm" value="취소" />
				</div>
			
		</form:form>
	</div>
</div>	

<script type="text/javascript">
	$(document).ready(function(){
	    //키를 누르거나 떼었을때 이벤트 발생
	    $(".majorPay").bind('keyup keydown',function(){
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

	<jsp:include page="/includee/postScript.jsp" />	
	
