<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/includee/preScript.jsp" />

<div class="content-wrap">
<h5>시설 추가 페이지 입니다.</h5>
 
 	<form:form modelAttribute="athletic" method="post">
		<table class="major-table">
<!-- 			<tr> -->
<!-- 				<th>시설구분번호</th> -->
<!-- 				<td> -->
<%-- 					<input type="text" name="facNo" value="${athletic.facNo }" /> --%>
<%-- 					<form:errors path="facNo" element="span" cssClass="error" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>시설구분번호</th> -->
<!-- 				<td> -->
<%-- 					${athletic.facCode } --%>
<%-- 					<form:errors path="facCode" element="span" cssClass="error" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<th>시설명</th>
				<td>
					<input type="text" name="facName" value="${athletic.facName }" placeholder="ex)축구장" />
					<form:errors path="facName" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>수용인원</th>
				<td>
					<input type="text" name="facNumber" value="${athletic.facNumber }" placeholder="ex)100" /> 명
					<form:errors path="facNumber" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>시설사용비용</th>
				<td>
					<input type="text" class="facPay" name="facPayDisplay" placeholder="ex)100,000" /> 원
					<form:errors path="facPay" element="span" cssClass="error" />
				</td>
			</tr>
		</table>
		<div class="flex-center m-top-10">			
			<input type="submit" value="저장" class="btn btn-secondary btn-n-sm m-right-5" />
			<input type="reset" value="취소" class="btn btn-secondary btn-n-sm" />
		</div>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	    //키를 누르거나 떼었을때 이벤트 발생
	    $(".facPay").bind('keyup keydown',function(){
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