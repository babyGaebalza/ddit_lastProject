<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>     
<!DOCTYPE html>
<html>
<style>
.left-card2 {
    position: relative;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: 0.25rem;
}
</style>
	<head>
		<h3>SCM 교무처 교무과입니다.</h3>
	</head>
<div class="all-wrap" style="padding-right: 10px;">
	<div class="left-wrap" style="margin-right: 12px;">
			<div class="left-card2" style="margin-bottom: 10px;">
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
		<div class="table-box m-bottom-20">
			<div id="collegeNoticeList">
			    <div class="card-header">
			    	<span>학사 공지사항</span>
			    	<a href="${cPath }/common/board/noticeList.do"><i class="fas fa-angle-double-right"></i></a> 
			    </div> 
			    	<div class="p-wrap">
				    	<table class="table table-borderless txt-center">
				    		<c:choose>
				    			<c:when test="${fn:length(mainContent.collegeNoticeList) > 0 }">
						    		<thead>    		
						    			<tr>
							    			<th>제목</th>
							    			<th style="width: 200px;">작성자</th>
							    			<th style="width: 100px;">작성일</th>
							    			<th style="width: 70px;">조회수</th>
						    			</tr>
						    		</thead>    		
						    		<tbody>
						    			<c:forEach items="${mainContent.collegeNoticeList }" var="board">
						    				<tr>
						    					<td class="txt-left">
						    						<c:url value="/board/noticeView.do" var="boardURL">
						    							<c:param name="noticeNo" value="${board.boardNo }"/>
						    						</c:url>
						    						<a href="${boardURL }">${board.boardTitle }</a>
						    					</td>
						    					<td>학생지원과(${board.memNo })</td>
						    					<td>${board.boardDate }</td>
						    					<td>${board.boardHits }</td>
						    				</tr>
						    			</c:forEach>
						    		</tbody>
				    			</c:when>
				    			<c:otherwise>
				    				<h6>공지사항이 없습니다.</h6>
				    			</c:otherwise>
				    		</c:choose>
				    	</table>
			    	</div>
			    </div>
			</div>
    	</div>
		<div class="table-box m-bottom-20" style="width:940px;">
			<div id="trackList" style="overflow: auto;">
				<div class="card-header" style="height: 57px;">
			    	<span>승인대기 트랙신청</span>
			    	<a href="${cPath }/trackManage/trackManageList.do"><i class="fas fa-angle-double-right"></i></a> 
			    </div> 
			    <table class="table table-bordered">
		<thead>
			<tr class="tr-style">
				<th style="border-right: 1px solid #b6bccd;">상태</th>
				<th style="border-right: 1px solid #b6bccd;">번호</th>
				<th style="border-right: 1px solid #b6bccd;">서류명</th>
				<th style="border-right: 1px solid #b6bccd;">신청과</th>
				<th style="border-right: 1px solid #b6bccd;">신청인</th>
				<th style="border-right: 1px solid #b6bccd;">신청일</th>
				</th>
			</tr>
		</thead>
		
		<c:set var="docuList" value="${pagingVO.dataList }" />
		
		<tbody>
			<c:choose>
				<c:when test="${not empty docuList }">
					<c:forEach items="${docuList }" var="docu">
						<tr style="text-align:center">
							<input type="hidden" name="docuState" value="${docu.docuState}"/>
							
							<td>
								<c:choose>
									<c:when test="${docu.docuState eq 'Y'}">
										<span class='badge badge-info'>승인</span>
									</c:when>
									<c:otherwise>
										<span class='badge badge-danger'>미승인</span>
									</c:otherwise>
								</c:choose>
							</td>

							<td>
								<c:url value="/trackManage/trackManageDetail.do" var="viewURL">
									<c:param name="docuNo" value="${docu.docuNo}" />
								</c:url>
								<a href="${viewURL }">${docu.docuNo}</a>
							</td>
							
							<td>${docu.docuFilename }</td>
							
<%-- 							<td>${docu.category1.cateName1}</td> --%>
							
							<td>${docu.member1.memMajor}</td>
							
							<td>${docu.member1.memName}</td>
	
							<td>${docu.docuReqdate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">대기중인 트랙신청이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
			    
			    
			    
			</div>
		</div>
</div>
	
</html>