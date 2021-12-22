<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>      
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

	<h4>QR 출석 정보입력 화면입니다.</h4>
	<form:form action="QRsubmit.do" method="POST">
		<div class="outform" style="display: none;" >이미 출석처리 되었습니다.</div>
		<table class="inform">
			<tr>
				<th>강의번호</th>
				<td><input  readonly="readonly" type="text" value="${classNo }" name="classNo" id="classNo">강의</td>				
			</tr>
			<tr>
				<th>강의명</th>
				<td><input  readonly="readonly" type="text" value="${classInfo.className }" name="className" id="className">강의</td>				
			</tr>
			<tr>
				<th>학번을 입력하세요</th>
				<td><input   type="text" name="stuNo" id="stuNo" value="202110036"/></td>
			</tr>
			<tr>
				<th>이름을 입력하세요</th>
				<td><input  type="text" name="stuName" id="stuName" value="소혜민"/></td>
			</tr>
			<tr>
				<th>
					<input type="submit" value="전송!" name="subBtn" id="subBtn" style="display: inline;"/>
				</th>
			</tr>
		</table>
	</form:form>
	<input type="hidden" value="${message }" id="message" />
	
	<script type="text/javascript">
	$(window).on('load', function(){
		alert(document.getElementById('message').value);
		var cookieName = document.cookie
		if(cookieName == "Attends=OK"){
			$.alert({
				title : '이미 출석처리되었습니다.',
				content : '이미 출석처리 되었습니다.'
			});
			
			$(".inform").attr('style', 'display: none;')
			$(".outform").attr('style', 'display: inline;')
		};
		
		
	});

	</script>
</html>