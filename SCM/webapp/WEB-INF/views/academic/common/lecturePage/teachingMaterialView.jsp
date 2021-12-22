<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	    
<style>

.txt-wrap > div > textarea {
	width: 500px;
	height: 100px;  
}
</style>
<div class="board-content-wrap">
<div class="board-wrap m-top-110">
		<div class="board-title">
			<h5>${material.boardTitle}</h5>
		</div>
		<div class="sub-txt">
			<div class="work-txt">
				<span>최종수정일 : ${material.boardFdate}</span>
<%-- 				<span>작성자 : ${material.memNo}</span> --%>
			</div>
			<div>
				<span>조회수 : ${material.boardHits}</span>
			</div>
	    </div>


		<div class="stroke"></div>
		<div class="board-content">
			${material.boardContent}
	    </div>	
   		 <div class="file-wrap">
			 <c:forEach items="${material.attatchList }" var="atch">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${atch.attNo }" />
					</c:url>
					<a href="${downloadURL}">${atch.attOgfilename }
					<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" 
							style="width: 20px; height: 20px;"/>
					</a>		
				</c:forEach>
				</div>

				<div class="btn-wrap">	
				<c:url value="/common/lecturePage/materialList.do" var="goBacktoListURL"></c:url>
				<input type="button" class="linkBtn btn btn-secondary m-right-5 font-14" data-gopage="${goBacktoListURL}" value="목록으로" />
				<security:authorize access="hasRole('US02')">
				
				<c:url value="/professor/lecturePage/materialBoard/update.do" var="updateURL">
					<c:param name="what" value="${material.boardNo}" />
				</c:url>
				<input type="button" class="linkBtn btn btn-secondary  m-right-5 font-14" data-gopage="${updateURL}" value="게시글수정" />
				<input type="button" value="게시글삭제"  id="delBtn" class="btn btn-secondary font-14"/>			
				</security:authorize>
				</div>
				</div>
		</div>
<form id="deleteForm" action="${cPath}/professor/lecturePage/materialDelete.do" method="get">
		<input type="hidden" name="boardNo" value="${material.boardNo}">
</form>
<script type="text/javascript">
let deleteForm = $('#deleteForm');

$("#delBtn").on("click", function(){
	$.confirm({
		title: '삭제',
	    content: '선택한 게시글을 삭제하시겠습니까? ',
	    buttons: {
	         예: function () {
	            $.alert('삭제처리되었습니다.');  
				deleteForm.submit();
	         },
	        아니오: function () {
	            $.alert('이전화면으로 돌아갑니다.');
	        }
	    }
	});
	
});
</script>