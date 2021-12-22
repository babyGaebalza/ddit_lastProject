<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>


	<input type="hidden" value="${classNo }" id="classNo"/> 


<script type="text/javascript">

	window.onload = function(){
		var info = "https://192.168.0.153/SCM/QR/QRform.do?classNo=${classNo }"
		console.log(info);
		
		var googleBase = "http://chart.googleapis.com/chart?cht=qr&chs=300&choe=UTF-8";
		var googlechl = googleBase + "&chl=" + encodeURIComponent(info);
		var googletotal = googlechl;
		
		window.open(googletotal, "height=300, width=300");
		alert(document.getElementById('classNo').value);
	};

</script>