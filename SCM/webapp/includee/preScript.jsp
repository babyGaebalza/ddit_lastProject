<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="${cPath }/resources/js/bootstrap-4.6.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${cPath }/resources/css/main.css">
<link rel="stylesheet" href="${cPath }/resources/css/common.css">

<link href="${cPath }/resources/templateFile/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">    
<link href="${cPath }/resources/templateFile/css/sb-admin-2.min.css" rel="stylesheet">
<link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet">

<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>

<!-- jquery(alert, confirm..) -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

	
<script type="text/javascript">
	$.getContextPath=function(){
		return "${cPath}";
	}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>



<c:if test="${not empty message }">
	<script type="text/javascript">
		$.alert({
		    title: '알림',
		    content: '${message}',
		});
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
