<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="board-content-wrap" style="padding-top: 25px;">
	<div class="board-wrap">
		<div class="board-title">
			<h5>${notice.boardTitle}</h5>
		</div>
		
		<div class="sub-txt">
			<div class="work-txt">
				<span>작성자 : ${notice.boardFwriter}</span>
			</div>
			<div>
				<span>조회수 : ${notice.boardHits}</span>
				<span>작성일 : ${notice.boardDate}</span>
			</div>
		</div>
		
		<div class="stroke"></div>
		<div class="board-content">
			${notice.boardContent}
		</div>
		
		<div class="file-wrap">
			<c:forEach items="${notice.attatchList }" var="atch">
				<c:url value="/board/download.do" var="downloadURL">
					<c:param name="what" value="${atch.attNo }" />
				</c:url>
				<a href="${downloadURL }">
				${atch.attOgfilename }
				</a>		
				<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" style="width: 20px; height: 20px;"/>
			</c:forEach>
		</div>
		
		<div class="btn-wrap">
			<input type="button" value="글삭제" id="delBtn" class="btn btn-outline-warning"/>
			<input type="button" value="글수정" id="modifyBtn" class="btn btn-outline-secondary" style=" margin-left: 10px; margin-right: 10px;"/>
			<input type="button" value="돌아가기" id="backBtn" class="btn btn-outline-info" onclick="location.href='${cPath}/common/board/noticeList.do';"/>
		</div>
		
		
	</div>
</div>
 
 	<form id="deleteForm" action="${cPath }/board/noticeDelete.do" method="post">
		<input type="hidden" name="noticeNo" value="${notice.boardNo }">
	</form>
	
	 <form id="updateForm" action="${cPath }/board/noticeUpdate.do" method="get">
		<input type="hidden" name="noticeNo" value="${notice.boardNo }">
	</form>
	
<script type="text/javascript">
let deleteForm = $("#deleteForm");
let updateForm = $("#updateForm");

	  $("#delBtn").on("click", function(){
		 $.confirm({
			title  : "정말 삭제하시겠습니까?",
			content : "정말 삭제하시겠습니까?",
			buttons : {
				확인 : {
					btnClass : 'btn-blue',
					action : function(){
						$.alert({
							title : '삭제 처리됩니다.',
							content : '삭제 처리됩니다.'
						});
					deleteForm.submit();
					}
				},
				취소 : function(){
					$.alert({
						title : '취소되었습니다.',
						content : '취소되었습니다.'
					});
				}
			}
		 })
		 
	 });
					

	
	$("#modifyBtn").on("click", function(){
		 $.confirm({
				title  : "정말 수정하시겠습니까?",
				content : "정말 수정하시겠습니까?",
				buttons : {
					확인 : {
						btnClass : 'btn-blue',
						action : function(){
							$.alert({
								title : '수정페이지로 이동합니다.',
								content : '수정페이지로 이동합니다.'
							});
							updateForm.submit();
						}
					},
					취소 : function(){
						$.alert({
							title : '취소되었습니다.',
							content : '취소되었습니다.'
						});
					}
				}
		 	})
		}); 
	
 </script>
