<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
.btn:hover{
background : silver;
border:none;
outline:none;
}
</style>
<div style="width: 1000px; margin:0 auto; margin-top:110px; margin-bottom:20px;">
	<div class="left-card">
		<div>
			<div class="card-header py-3">
				<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC10'}">																	
				<h6 class="m-0 font-weight-bold">담당학생 트랙신청내역 </h6>
				<input class ="studentApplyFlag" type="hidden" value="DOC10"/>
				
				</c:if>
				<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC11'}">																	
				<h6 class="m-0 font-weight-bold">담당학생 졸업신청내역 </h6> 
						<input class ="studentApplyFlag" type="hidden" value="DOC11"/>
				
				</c:if>
			</div>		
			<table class="table table-borderless txt-center">
				<thead>
					<tr>
						<th>학번</th> 
						<th>이름</th> 
						<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC10'}">																	
						<th>신청트랙명</th> 		
						</c:if>
						<th>승인여부</th> 				
					</tr>
				</thead>
				<c:set var="docuList" value="${pagingVO.dataList}" />
				<tbody>
					<c:choose>
						<c:when test="${not empty docuList}">
							<c:forEach items="${docuList}" var="docu">
								<tr>
		<%-- 							<td>${docu.docuReq}</td> --%>
									<td> ${docu.docuReq}</td>
									<td>${docu.studentName}
									<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC11'}">																	
				
									<button class="testBtn btn btn-secondary btn-sm">&nbsp;수강내역</button>
									</c:if>
									</td>
									<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC10'}">																	
									<td>${docu.trackName}
									<input type=hidden value="${docu.trackNo}" name= "myStudTrackNo" class="myStudTrackNo" /> 
									
									</td>	
									</c:if>
									<c:if test="${docu.docuState  eq 'N'}">																		
									<td>
									<input type="hidden" value="${docu.docuNo}"/>
									미승인<input type="button" value="승인" class="trackAdmin btn btn-secondary btn-sm"/>
									</td>	
									</c:if>
									<c:if test="${docu.docuState  eq 'Y'}">
									<td>
									<input type="hidden" value="${docu.docuNo}"/>																		
									승인완료
									<%-- <input type="button" value="승인취소" class="trackCancle btn btn-secondary btn-sm"/>  --%>
									</td>	
									</c:if>	
								</tr>								
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">신청내역이 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
		<!-- 	<input type="button" class="linkBtn"                                             -->
		<%-- 	data-gopage="${pageContext.request.contextPath}/professor/lecturePage/noticeBoardInsert.do?classNo=${pagingVO.searchVO.searchWord}" value="게시글 등록" /> --%>
			</table> 
		</div>	 
	</div>
	<div class="flex-center m-top-10">
		<div id="pagingArea">
			${pagingVO.pagingHTML}
		</div>                 																		    
	</div>
</div>

  <!--  Modal-->
	<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<button type="button" class="close" aria-label="Close"></button>
<!-- 						<span aria-hidden="true">&times;</span> -->
					</button>
					<div id="myModalTitle">
					<h3 class="modal-title" id="exampleModalLabel"></h3>
					</div>
				</div>
				<div class="modal-body">
			
				</div>
				<div class="modal-footer">
<!-- 					<a class="btn" id="modalY" href="#">예</a> -->
					<button class="btn" type="button" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	
<!-- 모달관련 스크립트 -->
	<script>
	let myModal = $('#testModal')
	let title = $('#myModalTitle')
	let myModalContent = $('.modal-body') 
	let modalBtn = $('.testBtn')
	
	modalBtn.click(function(e){
			e.preventDefault();
			
			//제목만들기(학번+이름)
			let thisId = $(this).parent().prev().text()
			let thisName = $(this).parent().text()
			title.text("[" +thisId+ "]" + thisName)

			//학번으로 수강 정보 가져오기 >> 모달내용만들기
				$.ajax({   
					url : "<%=request.getContextPath()%>/professor/myStuGraduInfo.do",
					data : {
									stuId : thisId
								},
					dataType : "json",
					success : function(resp) { //승인이 되면, 승인완료로 고치기 재로드
						console.log(resp.graduatePointList)
						let pointList = resp.graduatePointList
                        let resTable = $("<table class='table table-bordered'>");
						
						let trTags = [];
						$.each(pointList, function(idx, pointMap){
						console.log(pointMap.classCodeName)
						let trTag = $("<tr>").append(
								$("<td>").text(pointMap.classCodeName),	
								$("<td>").text("필수학점(" + pointMap.getedPoint + ")"),	
								$("<td>").text("이수학점(" + pointMap.codePoint + ")")
						)
						trTags.push(trTag);

						})
						resTable.append(trTags);

						myModalContent.html(resTable);
						
								},
					error : function(xhr, errorResp, error) {
									console.log(xhr);
									console.log(errorResp);
									console.log(error);
								}
					});

					//내용 만들기
				

					myModal.modal("show");
				});
	</script>







<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC10'}">																	
<form id="searchForm" action="${pageContext.request.contextPath}/professor/stuTrackManage.do" >                          
	<input type="hidden" name="page" />	
</form>
</c:if>
<c:if test="${pagingVO.searchVO.searchWord2  eq 'DOC11'}">							 									
<form id="searchForm" action="${pageContext.request.contextPath}/professor/stuGraduManage.do" >                          
	<input type="hidden" name="page" />	
</form>
</c:if>



<script type="text/javascript">
let flag = $('.studentApplyFlag').val(); 
$('.trackAdmin').on("click",function(){
 let stuId =  $(this).parent().parent().children(":first").text() 	
 let docuNo = $(this).prev().val();
 let trackNo = $(this).parent().prev().find("input[name=myStudTrackNo]").val();
 
 console.log("flag 클래스인데 하나만 들어오는지" + flag)
	$.ajax({
		url : "<%=request.getContextPath()%>/professor/stuTrackGraduAdmin.do",
		data : {
			"docuNo":docuNo, 
			"flag" : flag,
			"stuId":  stuId, 
			"trackNo" : trackNo
		},
		dataType : "json",
		success : function(resp) { //승인이 되면, 승인완료로 고치기 재로드
				console.log("승인완료되어서 ajax success로 옴") 
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});	
	window.location.reload();	
})

 $('.trackCancle').on("click",function(){
 let docuNo = $(this).prev().val() ;
 let stuId =  $(this).parent().prev().prev().prev().val() 	

 console.log("승인취소 버튼문서번호" + docuNo); 
 console.log("flag 클래스인데 하나만 들어오는지" + flag)

	$.ajax({
		
		url : "<%=request.getContextPath()%>/professor/stuTrackGraduAdmin.do",  
		data : {
			docuNo:docuNo, 
			cancle : "Y", 
			"flag" : flag,
			"stuId":  stuId

		},
		dataType : "json",
		success : function(resp) { //승인이 되면, 승인완료로 고치기 재로드
			 console.log("flag 클래스인데 하나만 들어오는지" + flag)

		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});	
	window.location.reload();	
}) 
</script>