<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.enumpkg.CollegeRegisterCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="wrapper">
<div class="sidebar">
      <ul>
      <!-- 드롭다운 -->
    	
          <c:forEach items="${menuList }" var="uppperMenuEntry" varStatus="status">
          
          	<li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse${status.count }"
                    aria-expanded="true" aria-controls="collapse${status.count }">
                <c:set var="uppperMenus" value="${fn:split(uppperMenuEntry.key, '/') }"/>          
                    <i class="fas ${uppperMenus[0] }"></i>
                    <span>${uppperMenus[1] }</span>
                </a>
                <div id="collapse${status.count }" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">${uppperMenus[1] }</h6>
                        
	          			<c:forEach items="${uppperMenuEntry.value}" var="lowerMenu">
	          				<security:authorize url="${lowerMenu.menuURL}">
		          				<a class="collapse-item" href="<c:url value='${lowerMenu.menuURL}'/>">
		          					<i class="fas fa-angle-right"></i>&nbsp;
		          					${lowerMenu.menuText}
		          				</a>
	          				</security:authorize>
	          			</c:forEach>
          			
          			</div>
                </div>
            </li>
          	
          </c:forEach>
    </ul>
  </div>
  <div class="main_container">
  </div>
  
  </div>
  
<!-- Modal -->
<div class="modal fade" id="alarmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">알림</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>  

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>	
</security:authorize>
  
<script>


$(document).ready(function(){
	$(".hamburger").click(function(){
	   $(".wrapper").toggleClass("collapse");
	});
});


let alarmModal = $("#alarmModal");
let alarmBody = alarmModal.find(".modal-body");

// 수강신청 기간 아닐때 처리
$(".collapse-item").on("click", function(event) {
	event.preventDefault();
	let requestURL = this.href;
	console.log(requestURL);
	
/* 	//강의페이지관련 요청일때 
	if(requestURL.indexOf("/lecturePage/") >= 0 ){
	 location.href = requestURL +"?what=" +classNo;
		return false; 
	} */
	
	
	
	
	// 수강신청 요청일 때
	if(requestURL.indexOf("student/lecture/registerForm") >= 0){
		let collegeRegister = "${authMember.memSemester}".split("/")[0];
		// 재학생이 아닐 경우
		if(collegeRegister != "RC01"){
			let collegeRegisterName;
			switch (collegeRegister) {
			<%
				for(CollegeRegisterCode colRegCode : CollegeRegisterCode.values()){
			%>
					case "<%=colRegCode.name() %>":
						collegeRegisterName = "<%=colRegCode.getCollegeRegisterName() %>";
						break;		
			<%		
				}
			%>
			}
			alarmBody.empty().html("수강신청기능은 재학생만 이용가능합니다.<br>(현 학적상태: " + collegeRegisterName + ")");
			alarmModal.modal("show");
		}else{
			// 재학생인 경우(수강신청 기간인지 체크)
			$.ajax({
				url : $.getContextPath() + "/student/lecture/registerYN.do",
				dataType : "json",
				success : function(resultMap) {
					if(resultMap.result == "Y"){
						location.href = requestURL;
					}else if(resultMap.result == "N"){
						alarmBody.empty().text("수강신청 기간이 아닙니다.")
						alarmModal.modal("show");
					}
				},
				error : function(xhr, errorResp, errorMessage) {
					console.log(xhr);
					console.log(errorResp);
					console.log(errorMessage);
				}
			});
		}
	}else{
		location.href = requestURL;
	}
});

</script>
  