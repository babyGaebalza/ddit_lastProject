<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

.font-14 {
	font-size: 14px;
}

.m-right-5 {
	margin-right: 5px;
}

.m-left-5 {
	margin-left: 5px;
}

.board-content-wrap {
	width: 1300px;
	margin-left: 100px;
}

.board-title {
	margin-bottom: 20px;
}

.board-title > h5 {
	color: #222;
    font-weight: bold;
}

.board-wrap {
	width: 1300px;
    min-height: 500px;
    padding: 40px 32px 38px;
    border: 1px solid #b7b7b7;
    border-radius: 8px;
    box-shadow: 0 10px 18px 0 rgb(0 75 151 / 10%);
    background: #fff;
}

.sub-txt {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    color: #495057;
}

.board-content {
	margin-top: 10px;
    min-height: 340px;
    padding: 10px;
    font-size: 14px;
    line-height: 26px;
}
.btn-wrap {
	display: flex;
	justify-content: center;
}

.work-txt > span {
	display: block;
}

.txt-wrap {
	margin-top: 32px;
    overflow: hidden;
    border: 1px solid #b3b3b3;
    border-radius: 8px;
    background: #fff;
}

.inp_write {
	padding: 13px 16px;
    width: 100%;
    height: 50px;
    border: 0;
    box-sizing: border-box;
    font-size: 15px;
    line-height: 24px;
    letter-spacing: -.5px;
    vertical-align: top;
    background: transparent;
    resize: none;
}

.info_write {
	position: relative;
    height: 47px;
    border-top: 1px solid #eee;
}

.stroke {
	margin-top: 20px;
    width: 100%;
    border-bottom: 1px solid #d7d7d7;
}

.bytes {
	display: inline-block;
    padding: 12px 22px 11px;
    color: #949494;
    font-size: 13px;
    line-height: 24px;
    letter-spacing: -.5px;
    vertical-align: top;
}

.btn_post {
	float: right;
    margin-top: -1px;
    width: 128px;
    height: 48px;
    color: #fff;
    font-size: 15px;
    line-height: 24px;
    letter-spacing: -.5px;
    background: #464554;
    cursor: pointer;
}

.btn_post:hover {
	 background: #696876;
}
</style>	
<div class="board-content-wrap">
	<div class="board-wrap">
		<div class="board-title">
			<h5>${task.boardTitle}</h5>
		</div>
		<div class="sub-txt">
			<div class="work-txt">
				<span>과제시작일 : 2021/12/01</span>
				<span>과제마감일 : ${task.boardDeadline}</span>
			</div>
			<div>
				<span>조회수 : ${task.boardHits}</span>
				<span>작성일: ${task.boardDate}</span>
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
			<c:url value="/professor/lecturePage/update.do" var="updateURL">
				<c:param name="what" value="${task.boardNo}" />
			</c:url>
			<input type="button" class="linkBtn btn btn-secondary  m-right-5 font-14" data-gopage="${updateURL}" value="게시글수정" />
			<input type="button" value="게시글삭제" class="btn btn-secondary font-14" id="delBtn"/>	
		</div>
	</div>
		<div class="txt-wrap">
			<div>
				<textarea placeholder="“나도 이런 경험했었지, 라떼는 말이야~” 위와 같은 경험이 있거나, 알고 계신 정보가 있다면 공유 부탁드려요!" class="inp_write" disabled=""></textarea>
			</div>
			<div class="info_write">
                <span class="bytes"><em>0</em>/1000자</span>
                <button type="button" class="btn_post" disabled="">댓글 등록</button>
            </div>
		</div>
</div>
<form id="deleteForm" action="${cPath}/professor/lecturePage/reportDelete.do" method="get">
		<input type="hidden" name="boardNo" value="${task.boardNo}">
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