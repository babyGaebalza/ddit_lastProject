<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>

<div class="all-size">
	<div class="m-top-110">
	<c:if test="${not empty resultMap.majorTrackList }">
		<div class="title-box">
			<i class="fas fa-chevron-right" style="margin-right: 8px;"></i>
			<h5 style="margin: 0px;">${resultMap.majorTrackList[0].majorName }</h5>
		</div>
	</c:if>
		<span>현 트랙상태 : 
		<c:choose>
			<c:when test="${not empty authMember.memTrack }">
				
				<span class="span-wrap" style="display: inline-block; vertical-align: middle; margin-bottom: 5px;">
					<span class="round-blue">
						승인완료
					</span>
				</span>
			</c:when>
			<c:when test="${not empty resultMap.trackRegister }">
				<span class="round-yellow">
					승인대기
				</span>
				&nbsp;&nbsp;&nbsp; 
				<i class="fas fa-chevron-right" style="margin-right: 8px;"></i> ${resultMap.trackRegister.track.trackName}
			</c:when>
			<c:otherwise>
				<span class="round-grey">
					미정
				</span>
			</c:otherwise>
		</c:choose>
		</span>	
		<div>
			<span>트랙 조회하기</span>
			<c:choose>
				<c:when test="${not empty authMember.memTrack }">
					<select id="trackSelector" disabled data-track="got">	
				</c:when>
				<c:when test="${not empty resultMap.trackRegister.track.trackNo }">
					<select id="trackSelector" data-track="wait" data-trackno="${resultMap.trackRegister.track.trackNo }">	
				</c:when>
				<c:otherwise>
					<select id="trackSelector" data-track="no">	
				</c:otherwise>
			</c:choose>
				<option value>트랙선택</option>
				<c:forEach items="${resultMap.majorTrackList }" var="track">
					<c:choose>
						<c:when test="${track.trackNo eq authMember.memTrack or track.trackNo eq resultMap.trackRegister.track.trackNo}">
							<option value="${track.trackNo }" selected>${track.trackName }</option>
						</c:when>
						<c:otherwise>
							<option value="${track.trackNo }">${track.trackName }</option>			
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
	
	<div class="table-box m-bottom-20 m-top-10">
		<div class="card-header">
			<h6 class="m-0 font-weight-bold" id="trackName">트랙을 선택해주세요</h6>
		</div>
		<div class="p-wrap">
			<table class="table table-borderless txt-center">
			  <thead>
			    <tr>
			      <th scope="col">No</th>
			      <th scope="col">트랙요건(강의)</th>
			      <th scope="col">수강여부(대체강의 포함)</th>
			    </tr>
			  </thead>
			  <tbody id="trackDetailBody">
			  	
			  </tbody>
			  <tfoot>
			  	<tr>
			  		<td colspan="3" id="trackRegisterArea">
			  			
			  		</td>
			  	</tr>
			  </tfoot>
			</table>
		</div>
	</div>
</div>


<script type="text/javascript">

let trackRegisterArea = $("#trackRegisterArea");
let trackName = $("#trackName");
let trackDetailBody = $("#trackDetailBody");
let trackSelector = $("#trackSelector");

function searchTrack(){
	let select = trackSelector.val();
	trackDetailBody.empty();
	trackRegisterArea.empty();
	if(select){
		$.ajax({
			url : $.getContextPath() + "/student/graduation/trackSearch.do",
			data : {
				trackNo : select
			},
			dataType : "json",
			success : function(resultMap) {
				trackName.text("트랙 : " + resultMap.trackDetail.trackName);
				
				let trTags = [];
				
				$.each(resultMap.trackDetail.trackList, function(idx, trackClass) {
					let trTag = $("<tr>").append(
						$("<td>").text(idx + 1)
						, $("<td>").text(trackClass.className)
					);
					
					if(trackClass.trackSatisfied){
						trTag.append(
							$("<td>").append(
								$("<span>").addClass("span-wrap").append(
									$("<span>").addClass("satisfied round-green").text("수강완료")			
									, "(" + trackClass.className + ")"
								)
							)
						);
					}else{
						trTag.append(
							$("<td>").append(
								$("<span>").addClass("span-wrap").append(
									$("<span>").addClass("notSatisfied round-red").text("미수강")			
								)
							)
						)
					}
					
					trTags.push(trTag);
				})
				
				trackDetailBody.append(trTags);
				
				
				let memTrack = trackSelector.data("track");
				let trackNo = trackSelector.data("trackno")
				console.log(memTrack);
				console.log(trackNo);
				console.log(select);
				
				if(memTrack != "got" && trackNo != select){
					let btnVal = "트랙신청";
					let state = "new";
					if(memTrack == "wait"){
						btnVal = "트랙신청변경";	
						state = "wait";
					}
					console.log(btnVal);
					trackRegisterArea.append(
						$("<input>").attr({
							"type" : "button"
							, "id" : "registerBtn"
							, "value" : btnVal
						}).data({
							"trackno" : resultMap.trackDetail.trackNo
							, "state" : state
						})
					);
				}
			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}

		});
	}
}

searchTrack();

trackSelector.on("change", function() {
	searchTrack();
})

let registerBtn = $(document).on("click", "#registerBtn", function() {
	let trackNo = $(this).data("trackno");
	let state = $(this).data("state");
	console.log(trackNo);
	console.log(state);
	
	let content = "해당 트랙을 신청 하시겠습니까?";
	if(state == "wait"){
		content = "해당 트랙으로 신청변경 하시겠습니까?";
	}
	$.confirm({
	    title: '트랙신청',
	    content: content,
	    buttons: {
	    	신청하기 : function () {
	            location.href = $.getContextPath() + "/student/graduation/trackRegister.do?trackNo=" + trackNo
	        },
	        취소 : function () {
	        }
	    }
	});
})

</script>
</body>
</html>