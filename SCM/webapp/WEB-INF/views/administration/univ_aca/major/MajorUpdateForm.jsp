<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.authMember" var="authMember"/>
	</security:authorize>

<div class="">
	<div class="content-wrap">
		학과 수정 페이지 입니다.
 
	 	<form:form modelAttribute="major" method="post" enctype="multipart/form-data" id="editForm">
			<table class="major-table">
				<tr>
					<th>단과대명</th>
					<td>
						${major.collegeName }
						<form:errors path="collegeName" element="span" cssClass="error" />
					</td>
				</tr>
				<tr>
					<th>전공명</th>
					<td>
						${major.majorName }
						<form:errors path="majorName" element="span" cssClass="error" />
					</td>
				</tr>
				<tr>
					<th>등록금</th>
					<td>
						<input type="text" class="majorPay input-box width-150" name="majorPayDisplay" value="${major.majorPayDisplay }" /> 원
						<form:errors path="majorPay" element="span" cssClass="error" />
					</td>
				</tr>
				<tr>
					<th>학과정원</th>
					<td>
						<input type="text" class="input-box width-150" name="majorCount" value="${major.majorCount }" /> 명
						<form:errors path="majorCount" element="span" cssClass="error" />
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th>최종수정자</th> -->
<!-- 					<td> -->
						<input type="hidden" class="input-box width-150" name="majorFWriter" value="${authMember.memName }" readonly/>
						<form:errors path="majorFWriter" element="span" cssClass="error" />
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<th>졸업충족학점</th>
					<td>
						<input type="text" class="input-box width-150" name="majorPoint" value="${major.majorPoint }" /> 점
						<form:errors path="majorPoint" element="span" cssClass="error" />
					</td>
				</tr>
			</table>
			<div>
				<input type="submit" class="btn btn-secondary btn-sm m-right-5" value="저장" />
				<input type="reset" class="btn btn-secondary btn-sm" value="취소" />
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