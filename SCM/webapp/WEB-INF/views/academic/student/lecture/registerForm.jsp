<%@page import="kr.or.ddit.enumpkg.ServiceResult"%>
<%@page import="kr.or.ddit.enumpkg.ClassKindCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<link rel="stylesheet" href="${cPath }/resources/css/student/registerForm.css">

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>	
</security:authorize> 

<div class="wrap">
	<div class="aa-wrap">
		<%// ==================================================수강신청 내역================================================== %>
		<div class="table-box">
		<div class="card-header">
			<h6 class="m-0 font-weight-bold">수강신청 내역</h6>
				<input type="button" value="강의바구니기간" id="cartChangeBtn" class="btn btn-outline-warning"/>  
				<input type="button" value="수강신청기간" id="regiChangeBtn" class="btn btn-outline-warning"/>
			<div id="pointInfo">
			</div>
		</div>
			<div style="overflow-y: scroll; height: 250px;"> 
				<table class="table table-borderless2 txt-center" style="font-size: 11px;">
				  <thead>
				  	  <tr>
		                 <th>No</th>
		                 <th>강의코드</th>
		                 <th style="width: 46px;">분반</th>
		                 <th style="width: 162px;">강의명</th>
		                 <th style="width: 87px;" >이수구분</th>
		                 <th style="width: 50px;">학점</th>
		                 <th style="width: 100px;">담당교수</th>
		                 <th style="width: 100px;">강의시간</th>
		                 <th style="width: 90px;">인원현황</th>
		                 <th style="width: 110px;">강의계획서</th>
		                 <th style="width: 90px;">수강관리</th>
		             </tr>
				  </thead>
				  <tbody id="registerList">
				  
				  </tbody>
				</table>
			</div>
		</div>
		<%// ==================================================수강신청 내역================================================== %>
		
		<%// ==================================================수강신청/강의바구니================================================== %>
		<div class="tab-wrap">
			<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 cont-box">
                        <nav style="margin-bottom: 10px;">
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            	<%// ==================================================탭버튼================================================== %>
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">수강신청</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">강의바구니</a>
                            </div>
                        </nav>
                        <%// ==================================================수강신청================================================== %>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                            	<div class="table-box">
		                            <div class="card-header select-padding">
		                            	<div class="search-wrap">
		                                	<form id="classSearchForm" action="${cPath }/student/lecture/search.do" method="post">
		                                		이수구분
		                                		<select name="classCode">
		                                			<option value>전체</option>
		                                			<%
		                                			for(ClassKindCode classKind : ClassKindCode.values()){
		                                			%>
		                                				<option value="<%=classKind.name() %>"><%=classKind.getCategoryName() %></option>	
		                                			<%	
		                                			}
		                                			%>
		                                		</select>
		                                		강의명:
		                                		<input name="className" type="text" placeholder="강의명 입력" style="margin-bottom: 4px;"/>
		                                		<br>
		                                		단과대학:
		                                		<select name="collegeCode" id="collegeList">
		                                			<option value>전체</option>
		                            				<%//페이지 접근시 기본으로 단과대 선택(로그인 정보 기반) %>
		                                			<c:forEach items="${collegeList}" var="college">
		                                				<c:choose>
		                                					<c:when test="${authMember.memCollege eq college.cateCode}">
		                                						<option value="${college.cateCode }" selected>${college.cateName1 }</option>
		                                					</c:when>
		                                					<c:otherwise>
				                                				<option value="${college.cateCode }">${college.cateName1 }</option>                        					
		                                					</c:otherwise>
		                                				</c:choose>
		                                			</c:forEach>
		                                		</select>
		                                		학부(과):
		                                		<select name="majorCode" id="majorList">
		                                		</select>
		                                		<select name="classSemester" id="gradeList">
		                                			<option value="/${semester }">전체</option>
		                                			<option value="1/${semester }">1학년</option>
		                                			<option value="2/${semester }">2학년</option>
		                                			<option value="3/${semester }">3학년</option>
		                                			<option value="4/${semester }">4학년</option>
		                                		</select>
		                                		<input type="submit" class="search-btn" value="검색" />
		                                	</form>
		                            	</div>
		                            </div>
		                            	<div style="overflow-y: scroll; height: 360px;">
			                                <table class="table table-borderless2 txt-center" cellspacing="0" style="font-size: 11px;">
			                                    <thead class="fix-head">
			                                        <tr>
			                                            <th style="width: 40px;">No</th>
			                                            <th style="width: 80px;">수강신청</th>
			                                            <th style="width: 53px;">학년</th>
			                                            <th style="width: 100px;">강의코드</th>
			                                            <th style="width: 53px;">분반</th>
			                                            <th style="width: 100px;">강의명</th>
			                                            <th style="width: 90px;">이수구분</th>
			                                            <th style="width: 53px;">학점</th>
			                                            <th style="width: 80px;">담당교수</th>
			                                            <th style="width: 100px;">강의시간</th>
			                                            <th style="width: 80px;">인원현황</th>
			                                            <th style="width: 100px;">강의계획서</th>
			                                        </tr>
			                                    </thead>
			                                    <tbody id="classList">
			                                        
			                                    </tbody>
			                                </table>
		                            	</div>
                            	</div>
                                
                            </div>
                            <%// ==================================================수강신청================================================== %>
                            
                            <%// ==================================================강의바구니================================================== %>
                            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                                <table class="table table-borderless2 txt-center" cellspacing="0" style="font-size: 11px;">
                                    <thead class="fix-head">
                                        <tr style="font-size: 11px;">
                                            <th style="width: 40px;">No</th>
                                            <th style="width: 90px;">수강신청</th>
                                            <th style="width: 53px;">학년</th>
                                            <th style="width: 100px;">강의코드</th>
                                            <th style="width: 53px;">분반</th>
                                            <th style="width: 100px;">강의명</th>
                                            <th style="width: 100px;">이수구분</th>
                                            <th style="width: 53px;">학점</th>
                                            <th style="width: 90px;">담당교수</th>
                                            <th style="width: 100px;">강의시간</th>
                                            <th style="width: 100px;">인원현황</th>
                                            <th style="width: 100px;">강의계획서</th>
                                            <th style="width: 100px;">바구니관리</th>
                                        </tr>
                                    </thead>
                                    <tbody id="cartList">
                                    </tbody>
                                </table>
                            </div>
                            <%// ==================================================강의바구니================================================== %>
                        </div>
                    </div>
                </div>
            </div>
        </section>
		</div>
	</div>
	<%// ==================================================수강신청/강의바구니================================================== %>
	
	<%// ==================================================시간표================================================== %>
	<div>
		<div class="left-card2">
			<div class="time-wrap">
				<div class="card-header">
					<h6 class="m-0 font-weight-bold">현재 시간표</h6>
				</div>
				<table class="right-table2">
				  <thead>
				    <tr>
				      <th scope="col">교시/요일</th>
				      <th scope="col">월</th>
				      <th scope="col">화</th>
				      <th scope="col">수</th>
				      <th scope="col">목</th>
				      <th scope="col">금</th>
				    </tr>
				  </thead>
				  <tbody id="timeTable" class="time-body">
				  </tbody>
				</table>
			</div>
		</div>
	</div>
	<%// ==================================================시간표================================================== %>
</div>
	

<!-- Modal -->
<div class="modal fade" id="cartRegisterResultModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">알림</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="modalBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="cartRemoveChecktModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">알림</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="checkBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
 
<!--  동적 스크립트를 위해 el사용하려고 js파일이 아닌 jsp로 별도 분리 -->
<%@ include file="/WEB-INF/views/academic/student/lecture/registerFormScript.jsp" %>