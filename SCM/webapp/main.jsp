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

	<div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                       	<b>역할선택 :</b>  
                                       	<select id="idSelect">
                                        	<option>역할선택</option>
                                        	<option data-id="202110036">1학년(소혜민)</option>
                                        	<option data-id="201710001">졸업예정(소혜원)</option>
                                        	<option data-id="P012104">교수님</option>
                                        	<option data-id="202110099">조교(컴퓨터융합과)</option>
                                        	<option data-id="A012108">행정직(교무과)</option>
                                        	<option data-id="202110067">행정직(재무과)</option>
                                        </select>
                                    </div>
                                    <form:form action="${pageContext.request.contextPath }/login.do" method="post" class="user">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                name="memId" aria-describedby="emailHelp"
                                                placeholder="아이디" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                name="memPass" placeholder="비밀번호" required>
                                        </div>
                                        <input type="submit" class="btn btn-primary btn-user btn-block" value="로그인"/><br>
                                        <div id="errorMessage">
                                        </div>
                                    </form:form>
                                    <hr>
                                    <div>
                                        <a class="small" href="${pageContext.request.contextPath }/login/processForm/findIdForm">학번/사번 찾기</a>
                                    </div>
                                    <div>
                                        <a class="small" href="${pageContext.request.contextPath }/login/processForm/resetPasswordForm">비밀번호 초기화</a>
                                    </div>
                                    <hr>
                                    <div>
                                        <a class="small" href="${pageContext.request.contextPath }/login/processForm/unlockAccountForm">잠금계정 해제</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
    
<c:if test="${not empty errorMessage }">
	<script type="text/javascript">
		let error = $("#errorMessage").empty().html("${errorMessage}").css({
			color : "red"
		});
	</script>
	<c:remove var="errorMessage" scope="session"/>
</c:if>   

<script type="text/javascript">
	$("#idSelect").on("change", function() {
		let id = $(this).find("option:selected").data("id");
		$(":input[name='memId']").val(id);
		$(":input[name='memPass']").val("java");
		
	})
</script>