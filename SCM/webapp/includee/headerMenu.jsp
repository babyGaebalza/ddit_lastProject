<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>

<div class="wrapper">
	<div class="top_navbar">
		<div class="hamburger" id="sidebarToggle">
			<div class="one"></div>
			<div class="two"></div>
			<div class="three"></div>
		</div>
		<div class="top_menu">
			<div class="logo"><a href="${cPath }/main">SCM</a></div>
			<c:if test="${not empty currentLecturePageInfo }">
				<security:authorize access="hasRole('US02')">
					<div>
						<i class="fas fa-angle-right title-icon"></i><a href="${cPath }/academic/professor/lecturePage/main.do">${currentLecturePageInfo.className }</a>
					</div>
				</security:authorize>
				<security:authorize access="hasRole('US04')">
					<div>
						<i class="fas fa-angle-right title-icon"></i><a href="${cPath }/student/lecturePage/main.do">${currentLecturePageInfo.className }</a>
					</div>
				</security:authorize>
			</c:if>
			
			<security:authorize access="isAuthenticated()">
			<ul class="menu">
				<li>
				<b>${authMember.memName }</b>
				<security:authorize access="hasRole('US01')">
					<span class="m-left-3 round-admin">${authMember.userCodeName }</span>				
				</security:authorize>
				<security:authorize access="hasRole('US02')">
					<span class="m-left-3 round-professor">${authMember.userCodeName }</span>				
				</security:authorize>
				<security:authorize access="hasRole('US04')">
					<span class="m-left-3 round-student">${authMember.userCodeName }</span>				
				</security:authorize>
				</li>
				
				<li class="dropdown"><a href="#"><i class="fas fa-user"></i></a>
					<div class="dropdown-menu"></div>
				<div class="dropdown-content">
					<div>
						<!-- 학생 -->
						<c:if test="${authMember.userCode eq 'US04' }">
							<a href="<%=request.getContextPath() %>/common/member/stuMyPage.do"> <!-- 마이페이지 가는 링크 -->
								<span class="circle" style="display: inline-block;"><i class="fas fa-user"></i></span>
								<span style="display: inline-block; vertical-align: top;">마이페이지</span>
							</a>
						</c:if>
						
						<!-- 교수 -->
						<c:if test="${authMember.userCode eq 'US02' }">
							<a href="<%=request.getContextPath() %>/common/member/proMyPage.do"> <!-- 마이페이지 가는 링크 -->
								<span class="circle" style="display: inline-block;"><i class="fas fa-user"></i></span>
								<span style="display: inline-block; vertical-align: top;">마이페이지</span>
							</a>
						</c:if>
						
						<!-- 조교 -->
						<c:if test="${authMember.userCode eq 'US03' }">
							<a href="<%=request.getContextPath() %>/common/member/assiMyPage.do"> <!-- 마이페이지 가는 링크 -->
								<span class="circle" style="display: inline-block;"><i class="fas fa-user"></i></span>
								<span style="display: inline-block; vertical-align: top;">마이페이지</span>
							</a>
						</c:if>
						
						<!-- 행정직원 -->
						<c:if test="${authMember.userCode eq 'US01' }">
							<a href="<%=request.getContextPath() %>/common/member/manageMyPage.do"> <!-- 마이페이지 가는 링크 -->
								<span class="circle" style="display: inline-block;"><i class="fas fa-user"></i></span>
								<span style="display: inline-block; vertical-align: top;">마이페이지</span>
							</a>
						</c:if>
					</div>
					<security:authorize access="isAuthenticated()">
						<a href="${pageContext.request.contextPath }/logout.do">
							<span class="circle" style="display: inline-block; vertical-align: top;">
								<i class="fas fa-sign-out-alt"></i></span>
							<span style="display: inline-block;">로그아웃</span>
						</a>
					</security:authorize>
				</div>
				</li>
			</ul>
			</security:authorize>
		</div>
	</div>
</div>