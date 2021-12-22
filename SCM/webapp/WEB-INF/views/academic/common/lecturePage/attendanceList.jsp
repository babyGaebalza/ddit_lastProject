<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
.thColor {
    background-color: #d6dcdd;
}

.tdHeight {
    height: 70px;
}
.day {
	width : 186px;
	text-align: center;
}

.fc-sticky{
	overflow: auto !important;
}
</style>
<h3>출석현황</h3> 
             <div style="padding-left: 190px;"> 
		    <div class="left-card mb-4 width-883">
		        <div class="card-header py-3">
		            <h6 class="m-0 font-weight-bold">${mainContent.currentSemester.schYear }년 ${mainContent.currentSemester.schCont }</h6>
		            <a href="#none" onclick="window.open('${cPath}/calendar/calendarList.do','new','scrollbars=yes,resizable=no width=800 height=670, left=500,top=150');return false"><i class="far fa-calendar-alt"></i>&nbsp;학사일정</a>
		        </div>
		        <div class="card-body">
		            <h6 class="small font-weight-bold">개강일 : ${mainContent.currentSemester.schSdate }</h6>
		            <h6 class="small font-weight-bold">종강일 : ${mainContent.currentSemester.schEdate }<span
		                    class="float-right">${mainContent.currentSemester.semesterPercentage }%</span></h6>  
		            <div class="progress mb-4">
		                <div class="progress-bar" role="progressbar" style="width: ${mainContent.currentSemester.semesterPercentage }%"
		                    aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
		            </div>
		        </div>
		    </div>
		    </div>
<div style="width: 1620px;">
<div class="table-size-1300" style="padding-top: 15px;">
		      
<jsp:include page="/WEB-INF/views/common/calendar/ClassCalendar.jsp" flush="false"/>	

</div>
</div>


<form id="searchForm" action="${pageContext.request.contextPath}/common/lecturePage/attendanceList.do" >   
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />	
</form>


















