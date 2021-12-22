<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<style>

.th-width-100{
	width: 100px;
}

.th-width-170{
	width: 170px;
}

.th-width-200{
	width: 200px;
}

.th-width-230{
    width: 230px;
}

.td-align-center{
	text-align: center;
}

.td-font-link{
	font-weight: bold;
}

.tdLine:hover {
    color: #07c6ff;
}

.table-font-size{
	font-size: 20px;
}


.fc-sticky{
	overflow: auto !important;
}
</style>



<div style="width: 1620px;">
	<div class="table-size-1300">
		<div class="board-title">
		<h5>나의 출석부</h5>
			<table class="table table-bordered">
				<thead class="table-font-size">
					<tr class="tr-style " >
						<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;" class="th-width-200">강의번호</th>
						<th style="border-right: 1px solid #b6bccd;" class="th-width-230">출석일</th>
						<th style="border-right: 1px solid #b6bccd;" class="th-width-170">수강신청정보</th>
						<th style="border-right: 1px solid #b6bccd;" class="th-width-100">이름</th>
						<th style="border-right: 1px solid #b6bccd;" class="th-width-100">출석점수</th>
						<th style="border-right: 1px solid #b6bccd;" class="th-width-170">비고</th>
					</tr>
				</thead>
			
				<tbody class="table-font-size">
					<c:if test="${not empty myAtt }">
						<c:forEach items="${myAtt }" var="att">
							<tr>
								<td>${att.classNo }</td>
								<td>${att.atdcDate }</td>
								<td>${att.classlistNo }</td>
								<td>${att.classStudent }</td>
								<td>${att.atdcPoint }</td>
								<td>${att.atdcNote }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
		</div>
			<jsp:include page="/WEB-INF/views/common/calendar/AttendCalendar.jsp" flush="false"/>	
	
	</div>
</div>












