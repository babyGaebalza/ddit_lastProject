<%@page import="kr.or.ddit.enumpkg.UserCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>	
</security:authorize>

<script type="text/javascript">
	// 로그인이 되어있을때 (메인페이지 접근시 각자 로그인페이지로 리다이렉트)
	if("${authMember}"){
		let userCode = "${authMember.userCode}";
		
		let viewURL = $.getContextPath();
		
		switch (userCode) {
		<%
			for(UserCode userCode : UserCode.values()){
		%>
				case "<%=userCode.name()%>":
					viewURL += "<%=userCode.getViewURL()%>";
					break;
		<%		
			}
		%>
		}
		
		location.href = viewURL;
	}
</script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.notosanskr * { 
 font-family: 'Noto Sans KR', sans-serif;
}
a {
	text-decoration: none;
    color: #3a3a3a;
    margin-right: 18px;
}

a:hover {
	text-decoration: none;
    color: #374bbd;
}

body {
	margin: 0px;
	padding: 0px;
}
.bg-wrap {
	width: 100vw;
    height: 100vh;
    position: absolute;
}

.bg-wrap > img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
	
.login-wrap {
	position: relative;
	top:300px;
	background-color:rgba(255, 255, 255, 0.5);
	width: 600px;
    height: 100%;
    border-radius: 10px;
    margin: 0 auto;
    display: flex;
    justify-content: center;
    box-shadow: 1px 1px 8px 1px rgb(0 0 0 / 20%);
}

.form-control {
    width: 293px;
    height: 43px;
    border: none;
    font-family: "Dotum";
    line-height: 43px;
    vertical-align: middle;
    box-sizing: border-box;
	border-top: 1px solid #a1a1a1;
    border-bottom: 1px solid #a1a1a1;
    border-left: 1px solid #a1a1a1;
    margin-bottom: -1px;
    padding: 0px 0px 0px 12px;
}

.btn-login {
    position: absolute;
    top: 0;
    left: 294px;
    width: 98px;
    height: 85px;
    color: #fff;
    font-family: "NotoR";
    font-size: 20px;
    border: none;
    background: #394b61;
    cursor: pointer;
}

.btn-login:hover {
	background: #3d5d85;
}

.input-wrap {
	position: relative;
    width: 392px;
    height: 85px;
}


.text-center {
	width: 480px;
	height: 306px;
	font-family: 'Noto Sans KR', sans-serif;
}

.text-center > span {
	margin-bottom: 10px;
    display: block;
    font-size: 18px;
}

.a-txt {
	margin-top: 6px;
}
	
</style>



</head>

<body>
	<div class="bg-wrap">
		<img alt="" src="<%=request.getContextPath()%>/resources/images/mainimg.jpg"> 
	</div>
	<div class="login-wrap">
		<div class="text-center">
	          <h1>Smart Campers Management</h1>
	          <span>로그인이 필요한 서비스입니다.</span>
	
	          <form:form action="${pageContext.request.contextPath }/login.do" method="post" class="user">
	              <div class="input-wrap">
	              	<div>
	                  <input type="text" class="form-control form-control-user"
	                      name="memId" aria-describedby="emailHelp"
	                      placeholder="아이디" required>
	                  <input type="password" class="form-control form-control-user"
	                      name="memPass" placeholder="비밀번호" required>
	              	</div>
	             	 <input type="submit" class="btn-login btn-user btn-block" value="로그인"/>
	              </div>
	              <div id="errorMessage">
	              </div>
	          </form:form>
	          <div class="a-txt">
	              <a class="small" href="${pageContext.request.contextPath }/login/processForm/findIdForm">학번/사번 찾기</a>
	              <a class="small" href="${pageContext.request.contextPath }/login/processForm/resetPasswordForm">비밀번호 초기화</a>
	              <a class="small" href="${pageContext.request.contextPath }/login/processForm/unlockAccountForm">잠금계정 해제</a>
	          </div>
         </div>
	</div>
</body>
</html>