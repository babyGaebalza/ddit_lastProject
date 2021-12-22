<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.comment-table {
    text-align: center;
    width: 100%;
    font-size: 14px;
    color: #555;
}    

.txt-wrap > div > textarea {
	width: 500px;
	height: 100px;  
}

.comment-btn {
    height: 22px;
    font-size: 12px;
    vertical-align: bottom;
    background-color: #6c757d;
    color: #fff;
    border-radius: 4px;
    border: 0px;
    box-sizing: border-box;
    line-height: 8px;
}

.comment-btn:hover {
    background-color: #5a6268;
}

.comment-table > thead > tr > th {
	height: 30px;
}

.comment-table > tbody > tr > td {
	height: 30px;
}

.comment-span-txt {
	font-size: 14px;
    display: block;
    padding: 2px;
    margin-left: 6px;
}

</style>	
<div class="board-content-wrap">
	<div class="board-wrap m-top-110">
		<div class="board-title">
			<h5>${task.boardTitle}</h5>
		</div>
		<div class="sub-txt">
			<div class="work-txt">
				<span>과제시작일 : ${task.boardDate}</span>
				<span>과제마감일 : ${task.boardDeadline}</span>
			</div>
			<div>
				<span>조회수 : ${task.boardHits}</span>
			</div>
		</div>
		<div class="stroke"></div>
		<div class="board-content">
			${task.boardContent}
		</div>
		<div class="file-wrap">
			<c:forEach items="${task.attatchList }" var="atch">
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
			<c:url value="/common/lecturePage/reportBoardList.do" var="goBacktoListURL"></c:url>
			<input type="button" class="linkBtn btn btn-secondary m-right-5 font-14" data-gopage="${goBacktoListURL}" value="목록으로" />	
			<security:authorize access="hasRole('US02')">
				<c:url value="/professor/lecturePage/update.do" var="updateURL">
					<c:param name="what" value="${task.boardNo}" />
				</c:url> 
				<input type="button" class="linkBtn btn btn-secondary  m-right-5 font-14" data-gopage="${updateURL}" value="게시글수정" />
				<input type="button" value="게시글삭제" class="btn btn-secondary font-14" id="delBtn"/>
			</security:authorize>
		</div>					
	</div>
	
	<c:if test="${not empty submitTasks }">
		<div id="taskList">
			<div class="m-top-30 m-bottom-10 m-left-20 bold">
				제출목록
			</div>
			<div class="txt-wrap">
				<table class="comment-table">
					<thead>
						<tr>
							<security:authorize access="hasRole('US02')">
								<th>No</th>
								<th>제출자</th>
							</security:authorize>
							<th>내용</th>
							<th>제출일</th>
							<th>첨부파일</th>
							<security:authorize access="hasRole('US02')">
								<th>평가</th>
							</security:authorize>
							<security:authorize access="hasRole('US04')">
								<c:if test="${submitTask.taskScore ne 'N' }">
									<th>제출관리</th>
								</c:if>
							</security:authorize>
						</tr>
					</thead>
					<c:forEach items="${submitTasks }" var="submitTask" varStatus="stat">
						<tr>
							<security:authorize access="hasRole('US02')">
								<td>${stat.count }</td>
								<td>${submitTask.memName }</td>
							</security:authorize>
							<td>${submitTask.taskCont }</td>
							<td>${submitTask.taskDate }</td>
							<td>
								<c:forEach items="${submitTask.attatchList }" var="atch" varStatus="innerStat">
									<c:url value="/board/download.do" var="downloadURL">
										<c:param name="what" value="${atch.attNo }" />
									</c:url>
									<a href="${downloadURL }">${atch.attOgfilename }</a>
									<c:if test="${not innerStat.last }">/</c:if>
								</c:forEach>
							</td>
							<security:authorize access="hasRole('US02')">
								<td>${submitTask.taskScore }</td>
								<c:choose>
									<c:when test="${submitTask.taskScore eq 'N' }">
										<td>
										    <input type="hidden" value="${submitTask.taskNo}"/>
											<input type="button" class="makeScoreBtn" value="채점하기" class="comment-btn width-60"/>
													
										</td>
									</c:when>
									<c:otherwise>
										<td>
										<input type="hidden" value="${submitTask.taskNo}"/>
										<input type="button" class="makeScoreBtn" value="변경하기" class="comment-btn width-60"/>		
										</td>
									</c:otherwise>
								</c:choose>
							</security:authorize>
							<security:authorize access="hasRole('US04')">
								<c:choose>
									<c:when test="${submitTask.taskScore eq 'N' }">
										<td>
											<input type="button" id="modifyBtn" value="수정하기" class="comment-btn width-60"/>
										</td>
									</c:when>
									<c:otherwise>
										<td>평가완료</td>
									</c:otherwise>
								</c:choose>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>
	
	<security:authorize access="hasRole('US04')">
		<div id="taskFormDiv">
			<div class="m-top-30 m-bottom-10 m-left-20">
				제출양식
			</div>
			
			<c:choose>
				<c:when test="${not empty submitTasks }">
					<c:url value="/student/lecturePage/modifyTask.do" var="taskFormURL">
					</c:url>
				</c:when>
				<c:otherwise>
					<c:url value="/student/lecturePage/submitTask.do" var="taskFormURL">
					</c:url>			
				</c:otherwise>
			</c:choose>
			
			<form:form id="taskForm" action="${taskFormURL }" method="post" enctype="multipart/form-data" >
				<input type="hidden" name="boardNo" value="${task.boardNo }"/>
				<input type="hidden" name="taskNo" value="${submitTasks[0].taskNo }"/>
				<div class="txt-wrap m-bottom-20">
					<div>
						<textarea placeholder="내용" name="taskCont" id="reportContent" class="inp_write m-top-10 m-left-10" maxlength="100" required>${submitTasks[0].taskCont }</textarea>
					</div>
					<hr>
					<c:if test="${not empty submitTasks }">
						<div class="m-bottom-5 m-left-5 font-14">기존 첨부파일</div>
						<c:if test="${fn:length(submitTasks[0].attatchList) > 0 }">
							<c:forEach items="${submitTasks[0].attatchList }" var="taskAtch">
								<span data-atch="${taskAtch.attNo }" data-atchname="${taskAtch.attSavefilename }" class="comment-span-txt">
									${taskAtch.attOgfilename }
									<input type="button" value="삭제" class="atchDelBtn comment-btn width-60"/>
								</span>
							</c:forEach>
						</c:if>
						<hr>
					</c:if>
					<div id="fileArea" class="m-bottom-10 m-left-20">
						<div class="m-bottom-10">
							<input type="file" name="repFiles" class="font-14" multiple />
							<input type="button" value="추가" id="plus" class="ctlBtn comment-btn width-60" />
							<input type="button" value="제거" id="minus" class="ctlBtn comment-btn width-60" />
						</div>
					</div>
					<div class="info_write">
		                <span class="bytes"><em id="textLength">${fn:length(submitTasks[0].taskCont) }</em>/100자</span>
		                <input type="submit" class="btn_post"/>
		                <c:if test="${not empty submitTasks }">
		                <input type="button" id="modifyCancelBtn" value="수정취소" class="btn_post"/>
		                </c:if>
		            </div>
				</div>
			</form:form>
		</div>
		<c:if test="${not empty submitTasks }">
			<script type="text/javascript">
				$("#taskFormDiv").hide();
			</script>
		</c:if>
	</security:authorize>
	
</div>
<form id="deleteForm" action="${cPath}/professor/lecturePage/reportDelete.do" method="get">
		<input type="hidden" name="boardNo" value="${task.boardNo}">
</form>
<form id="scoreForm" action="${cPath}/common/lecturePage/reportScoreMake.do">
<input type="hidden" name="taskScore"/>
<input type="hidden" name="taskNo"/>
<input type="hidden" name="boardNo" value="${task.boardNo}" />
</form>
<script type="text/javascript">
//교수 채점하기 버튼 눌렀을시, 
$('.makeScoreBtn').on("click", function(){
	let taskNo = $(this).prev().val(); 
	let scoreForm = $('#scoreForm'); 
	$.confirm({
	    title: '성적입력',
	    content: '' +
	    '<form action="${cPath}/common/lecturePage/reportScoreMake.do" class="formName">' +
	    '<div class="form-group">' +
	    '<label>과제점수입력(1-10점)</label>' +
	    '<input type="number" placeholder="1-10" name="taskScore" class="name form-control"  id ="taskScore" required />' +
	    '</div>' +
	    '</form>',
	    buttons: {
	        formSubmit: {
	            text: '확인',
	            btnClass: 'btn-blue',
	            action: function () {
	                var score = this.$content.find('#taskScore').val();
	                if(!score){
	                    $.alert('점수를 입력하세요');
	                    return false;
	                }
	                $.alert("점수("+score +") 가 입력됩니다.");
	                
	                scoreForm.find(":input[name='taskScore']").val(score)  
	                scoreForm.find(":input[name='taskNo']").val(taskNo)  
	                scoreForm.submit(); 
	            }
	        },
	          취소: function () {
	            //close
	        },
	    },
	    onContentReady: function () {
	        // bind to events
	        var jc = this;
	        this.$content.find('form').on('submit', function (e) {
	            // if the user submits the form by pressing enter in the field.
	            e.preventDefault(); 
	            jc.$$formSubmit.trigger('click'); // reference the button and click it
	        });
	    }
	});
	
})


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

let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
	let id = this.id;
	let divTag = $(this).closest("div");
	let divs = fileArea.find("div");
	if(id == 'plus'){
		if(divs.length >= 3){
			$.alert({
			    title: '첨부파일 수 초과',
			    content: '첨부파일은 3개까지 첨부가능합니다.',
			});
		}else{
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);		
		}
	}else{
		if(divs.length>1)
			divTag.remove();
	}
});

let reportContent = $("#reportContent").on("propertychange change keyup paste input", function() {
	let text = reportContent.val();
	let textLen = text.length;
	
	if(textLen > 100){
		reportContent.val(text.substring(0, 100));
		$.alert({
		    title: '글자수 초과',
		    content: '100글자 이하로 작성해주세요.',
		});
	}
	
	textLen = reportContent.val().length;
	
	$("#textLength").text(textLen);
}) 

let taskForm = $("#taskForm");
$(".atchDelBtn").on("click", function(){
	let span = $(this).closest("span");
	span.hide();
	let attNo = span.data("atch");
	let attName = span.data("atchname");
	taskForm.append(
			$("<input>").attr({
				type:"hidden",
				name:"delAttNos"
			}).val(attNo)
		);
	taskForm.append(
		$("<input>").attr({
			type:"hidden",
			name:"delAttOgFileNames"
		}).val(attName)
	);
});

let taskList = $("#taskList");
let taskFormDiv = $("#taskFormDiv");
let taskAtchs = $(".taskAtchs");
let modifyBtn = $("#modifyBtn").on("click", function() {
	taskList.hide();
	taskFormDiv.show();
});
let modifyCancelBtn = $("#modifyCancelBtn").on("click", function() {
	taskList.show();
	taskAtchs.show();
	$(":input[name='delAttNos']").remove();
	$(":input[name='delAttOgFileNames']").remove();
	taskFormDiv.hide();
});


</script>