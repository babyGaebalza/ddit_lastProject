<%@page import="kr.or.ddit.enumpkg.ClassKindCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.table-box > table > thead > tr > th {
	background-color: rgba(0,0,0,.03);
    padding: 8px 0px 0px 0px;
    height: 40px;
}

</style>
</head>
<body>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>


<c:choose>
	<c:when test="${not empty authMember.memTrack }">
		
		<div class="all-size">
			<div class="table-box  m-top-110">
			<div class="card-header">
				<h6 class="m-0 font-weight-bold" id="trackName">트랙 : ${authMember.trackName }</h6>
			</div>
			<div class="p-wrap m-bottom-20">	
				<table class="table table-borderless txt-center">
				  <thead>
				    <tr>
				      <th scope="col" class="p-th">No</th>
				      <th scope="col" style="width: 370px;">트랙요건(강의)</th>
				      <th scope="col">수강여부(대체강의 포함)</th>
				    </tr>
				  </thead>
				  <tbody id="trackDetailBody">
				  	<c:forEach items="${resultMap.trackDetail.trackList }" var="trackClass" varStatus="stat">
				  		<tr>
				  			<td>${stat.count }</td>
				  			<td>${trackClass.className }</td>
				  			<c:choose>
				  				<c:when test="${not empty trackClass.trackSatisfied }">
				  					<td>
				  						<span class="span-wrap">
					  						<span class="satisfied round-green">수강완료</span>(${trackClass.className })
				  						</span>
				  					</td>
				  				</c:when>
				  				<c:otherwise>
				  					<td>
				  						<span class="span-wrap">
					  						<span class="notSatisfied round-red">미수강</span>
				  						</span>
				  					</td>
				  				</c:otherwise>
				  			</c:choose>
				  		</tr>
				  	</c:forEach>
				  </tbody>
				</table>
			</div>
			</div>
			<div class="table-box m-top-20">
			<div class="card-header">
				<h6 class="m-0 font-weight-bold" id="trackName">학점이수 상태</h6>
			</div>
			<div class="p-wrap">
				<table class="table table-borderless txt-center">
				  <thead>
				    <tr>
				      <th scope="col">No</th>
				      <th scope="col" style="width: 334px;">이수구분</th>
				      <th scope="col">(충족학점/이수학점)</th>
				    </tr>
				  </thead>
				  <tbody id="pointDetailBody">
				  	<c:forEach items="${resultMap.graduatePointList }" var="graduatePoint" varStatus="stat">
				  		<tr>
				  			<td>${stat.count }</td>
				  			<td>${graduatePoint.classCodeName }</td>
				  			<td>
				  				<c:choose>
				  					<c:when test="${graduatePoint.codePoint <= graduatePoint.getedPoint }">
				  					<span class="span-wrap p-44">
				  						<span class="satisfied round-green">충족</span>(${graduatePoint.codePoint }/${graduatePoint.getedPoint })
				  					</span>
				  					</c:when>
				  					<c:otherwise>
				  					<span class="span-wrap p-44">
				  						<span class="notSatisfied round-red">미달</span>(${graduatePoint.codePoint }/${graduatePoint.getedPoint })(${graduatePoint.codePoint - graduatePoint.getedPoint }학점 부족)
				  					</span>
				  					</c:otherwise>
				  				</c:choose>
				  			</td>
				  		</tr>
				  	</c:forEach>
				  	<tr>
				  		<td>5</td>
				  		<td>총학점</td>
				  		<td>
				  			<c:choose>
			  					<c:when test="${130 <= resultMap.graduatePointList[0].getedPoint + resultMap.graduatePointList[1].getedPoint + resultMap.graduatePointList[2].getedPoint + resultMap.graduatePointList[3].getedPoint }">
			  						<span class="span-wrap p-44">
			  							<span class="satisfied round-green">충족</span>
			  						(130/${resultMap.graduatePointList[0].getedPoint + resultMap.graduatePointList[1].getedPoint + resultMap.graduatePointList[2].getedPoint + resultMap.graduatePointList[3].getedPoint})
			  						</span>
			  					</c:when>
			  					<c:otherwise>
			  						<span class="span-wrap p-44">
			  							<span class="notSatisfied round-red">미달</span>
			  						(130/${resultMap.graduatePointList[0].getedPoint + resultMap.graduatePointList[1].getedPoint + resultMap.graduatePointList[2].getedPoint + resultMap.graduatePointList[3].getedPoint})
			  						(${130 - (resultMap.graduatePointList[0].getedPoint + resultMap.graduatePointList[1].getedPoint + resultMap.graduatePointList[2].getedPoint + resultMap.graduatePointList[3].getedPoint) }학점 부족)
			  						</span>
			  					</c:otherwise>
			  				</c:choose>
				  		</td>
				  	</tr>
				  </tbody>
				</table>
			</div>
			</div>
			<c:choose>
				<c:when test="${resultMap.graduationState eq 'graduate' }">
					<button type="button" class="search-btn gradu-btn width-100">
							<i class="fas fa-graduation-cap m-right-5"></i>
							<span>졸업완료</span>
						</button>
				</c:when>
				<c:when test="${resultMap.graduationState eq 'wait' }">
					<button type="button" class="search-btn gradu-btn width-100">
							<i class="fas fa-graduation-cap m-right-5"></i>
							<span>승인대기</span>
						</button>
				</c:when>
				<c:otherwise>
					<div class="flex-center m-top-10">
						<button type="button" id="graduateRegisterBtn" class="search-btn gradu-btn width-100">
							<i class="fas fa-graduation-cap m-right-5"></i>
							<span>졸업신청</span>
						</button>
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
	</c:when>
</c:choose>

<c:if test="${not empty authMember.memTrack }">
	<script type="text/javascript">
		let notSatisfied = $(".notSatisfied");
		let graduateRegisterBtn = $("#graduateRegisterBtn");
		
		if(notSatisfied.length >= 1){
			graduateRegisterBtn.find("span").text("신청불가");
			graduateRegisterBtn.attr("disabled", "disabled");
		}else{
			if(graduateRegisterBtn){
				graduateRegisterBtn.on("click", function() {
					$.confirm({
					    title: '알림',
					    content: '졸업신청을 하시겠습니까?<br>(졸업이 가능한 상태입니다.)',
					    buttons: {
					        예: function () {
					        	location.href = $.getContextPath() + "/student/graduation/graduationRegister.do"
					        },
					        아니오: function () {
					           
					        }
					    }
					});
				});
			}
		}
	</script>
</c:if>
	
</body>
</html>