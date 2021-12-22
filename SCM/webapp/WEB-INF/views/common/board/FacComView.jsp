<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.reTR {
    width: 90px;
    text-align: center;
}

.reTd {
	width: 550px;
	border: none;
}

.reInput {
    width: 200px;
    border: none;
}

.contTh{
    width: 100px;
    text-align: center;
	background-color: aliceblue;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
		<h3>상세조회</h3>
	</div>
	<form:form>
	
	
		<table class="table table-bordered">
		<tr>
			<th class="contTh">글번호</th>
			<td>${board.boardNo}</td>
		</tr>
		<tr>
			<th class="contTh">제목</th>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<th class="contTh">작성자</th>
			<td>${boardWName}</td>
		</tr>
		<tr>
			<th class="contTh">작성일</th>
			<td>${board.boardDate}</td>
		</tr>
		<tr>
			<th class="contTh">조회수</th>
			<td>${board.boardHits}</td>
		</tr>
		<tr>
			<th class="contTh">첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="atch">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${atch.attNo }" />
					</c:url>
					<a href="${downloadURL }">
					${atch.attOgfilename }
					<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" 
							style="width: 20px; height: 20px;"/>
					</a>		
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th class="contTh">내용</th>
			<td>${board.boardContent}</td>
		</tr>
	</table>
		<div style="float: right;">
			<c:url value="/board/facComDelete.do" var="deleteURL">
				<c:param name="what" value="${board.boardNo }" />
			</c:url>
			<input type="button" value="글삭제" id="delBtn" class="btn btn-outline-danger" data-gopage="${deleteURL }" />
					
					
			<c:url value="/board/facComUpdate.do" var="updateURL">
				<c:param name="what" value="${board.boardNo }" />
			</c:url>
			<input type="button" value="글수정" id="updateBtn" class="btn btn-outline-warning" data-gopage="${updateURL }" />
		</div>
		
	</form:form>
	
	

	<!-- 	덧글 관련 UI -->
<form method="post" class="form-inline" id="repInsertForm" action="${pageContext.request.contextPath }/reply/replyInsert.do" style="padding-top: 40px;">
	<input type="hidden" name="boardNo" />
	<input type="hidden" name="repParent" value="${board.boardNo }"/>
	<div class="board-title">
		<h3>답글작성</h3>
	</div>
	<table class="table table-bordered">
			<tr>
				<th class="reTR" style="background-color: antiquewhite;">
					작성자
				</th>
				<td class="reTd">
					<input class="reInput" type="hidden" name="memNo" value="${memid }" readonly="readonly" required="required" maxlength="15"/>
					<input class="reInput" type="text" value="${memName }" readonly="readonly" required="required" maxlength="15"/>
				</td>
			</tr>
			<tr>
				<th class="reTR" style="background-color: antiquewhite;">
					비밀번호
				</th>
				<td class="reTd">
					<input class="reInput" type="password"  name="repPass" required="required" placeholder="비밀번호"/>
				</td>
			</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="boardContent"></textarea>
				</div>
			</td>
		</tr>
	</table>
		<div style="float: right;">
			<input type="submit" value="전송" class="btn btn-outline-info" />
		</div>
</form>

<h4 style="padding-top: 40px;">답글</h4>
<table id="replyTable" class="table table-bordered">
	<thead class="table-dark">
		<tr>	
			<th class="text-center" style="width: 170px;">작성자</th>
			<th class="text-center" style="width: 200px;">작성일</th>
			<th class="text-center">내용</th>
			<th class="text-center" style="width: 220px;">&nbsp;</th>
		</tr>
	</thead>
	<tbody id="listBody" class="overflow-auto">
	
	</tbody>
</table>



<div id="pagingArea" class="p-center m-bottom-10"></div>


<form id="searchForm2" action="${cPath }/reply/replyList.do" method="get">
	<input type="hidden" name="boardNo" value="${board.boardNo }" />
	<input type="hidden" name="page"  />
</form>


<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${pageContext.request.contextPath }/reply/replyUpdate.do" method="post">
      	<input type="hidden" name="repNo" required/>
      	<input type="hidden" name="boardNo"  required value="${board.boardNo }"  />
	      <div class="modal-body">
	      
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<div>작성자</div>
	      				<input type="text" required name="memNo" class="modiForm" placeholder="작성자" />
	      			</td>
	      			<td>
	      				 <div>비밀번호</div>
	      				 <input type="password" required class="modiForm" name="repPass" />
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="boardContent"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>


<div class="modal fade" id="replyChildModal" tabindex="-1" aria-labelledby="replyChildModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyChildModalLabel">대댓글</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${cPath }/reply/replyInsert.do" method="post">
      	<input type="text" name="repParent" required hidden/>
      	<input  name="boardNo"  required value="${board.boardNo }" />
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				비밀번호를 입력하세요<input type="password" required name="repPass" class="form-control" />
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="boardContent"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">등록</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>


<div class="modal fade" id="replyDeleteModal" tabindex="-1" aria-labelledby="replyDeleteModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${pageContext.request.contextPath }/reply/replyDelete.do" method="post">
      	<input type="hidden" name="repNo" required/>
      	<input  name="boardNo"  required value="${board.boardNo }" hidden/>
	      <div class="modal-body">
   				입력한 비밀번호를 입력하세요<input type="password" required name="repPass" class="form-control" />
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">삭제</button>
	        <button type="reset" class="btn btn-warning" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>
</div>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/reply.js"></script>	
