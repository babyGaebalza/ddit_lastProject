<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>      
<!DOCTYPE html>
<html>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
출석결과화면
강의실<input type="text" readonly="readonly" value="${classMember.classNo }" >
학생번호<input type="text" readonly="readonly" value="${classMember.stuNo }" >
학생이름<input type="text" readonly="readonly" value="${classMember.stuName }" >

	<%
		Cookie cookie = new Cookie("Attends", "OK");
		cookie.setMaxAge(300); /* 쿠키 설정 60 = 1분 , 이 기간내에는 재 출석 못함*/
		response.addCookie(cookie);
	%>
</html>