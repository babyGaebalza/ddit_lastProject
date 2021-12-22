<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includee/preScript.jsp" />
<div class="table-size-1300">
	<h3>상세페이지 입니다.</h3>
 	
 	<table class="table table-bordered">
		<tr>
			<th>제목</th>
			<td>${complaint.boardTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${complaint.boardFwriter}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${complaint.boardDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${complaint.boardHits}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${complaint.boardContent}</td>
		</tr>
		<tr>
			<th>확인</th>
			<td>${complaint.boardNo}</td>
		</tr>
		
		
		
		
		<tr>
			<td colspan="2">
				<input type="button" value="글수정" id="updateBtn" class="btn btn-secondary btn-sm" />

				<input type="button" value="글삭제" id="delBtn" class="btn btn-secondary btn-sm" />

				<input type="button" value="목록" id="listBtn" class="btn btn-secondary btn-sm" />
				
			</td>
		</tr>
	</table>
	
	<form id="updateForm" action="${cPath }/stu_sup/complaintUpdate.do" method="get">
		<input type="hidden" name="boardNo" value="${complaint.boardNo }">
	</form>
 
 	<form id="deleteForm" action="${cPath }/stu_sup/complaintDelete.do" method="post">
		<input type="hidden" name="boardNo" value="${complaint.boardNo }">
	</form>
 
 	<form id="listForm" action="${cPath }/stu_sup/complaintList.do" method="post">
		<input type="hidden" name="boardNo" value="${complaint.boardNo }">
	</form>
	
	
	<!-- 	덧글 관련 UI -->
	<form method="post" class="form-inline" id="repInsertForm" action="${pageContext.request.contextPath }/reply/replyInsert.do">
		<input type="hidden" name="boardNo" />
		<input type="hidden" name="repParent" value="${complaint.boardNo }"/>
		<table class="col-md-10">
		답글
			<tr>
				<td>
					<input type="text" class="form-control mb-2" name="repWriter" placeholder="작성자" maxlength="15"/>
				</td>
				<td>
					<input type="password" class="form-control mb-2" name="repPass" placeholder="비밀번호"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="input-group">
					<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="boardContent"></textarea>
					</div>
				</td>
				<td colspan="3">
					<input type="submit" value="작성" class="btn btn-primary" />
				</td>
			</tr>
		</table>
	</form>
	
	<h4>댓글리스트 (비동기)</h4>
	<table id="replyTable" class="table table-bordered">
		<thead class="table-dark">
			<tr>	
				<th class="text-center">작성자</th>
				<th class="text-center">작성일</th>
				<th class="text-center">내용</th>
				<th class="text-center">&nbsp;</th>
			</tr>
		</thead>
		<tbody id="listBody" class="overflow-auto">
		
		</tbody>
	</table>
	
	
	
	<div id="pagingArea"></div>
	
	
	<form id="searchForm2" action="${cPath }/reply/replyList.do" method="get">
		<input type="hidden" name="boardNo" value="${complaint.boardNo }" />
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
	      	<input  name="boardNo"  required value="${complaint.boardNo }"  hidden/>
		      <div class="modal-body">
		      
		      	<table class="table form-inline">
		      		<tr>
		      			<td>
		      				<input type="text" required name="memNo" class="form-control" placeholder="작성자" />
		      			</td>
		      			<td>
		      				 입력한 비밀번호를 입력하세요<input type="password" required name="repPass" class="form-control" />
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
	      	<input  name="boardNo"  required value="${complaint.boardNo }" />
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
	      	<input  name="boardNo"  required value="${complaint.boardNo }" hidden/>
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
	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/reply.js"></script>	
	
<script type="text/javascript">
let updateForm = $("#updateForm");
let deleteForm = $("#deleteForm");
let listForm = $("#listForm");
	
	$("#updateBtn").on("click", function(){
		let ok = confirm("수정하시겠습니까?");
		if(ok){
			updateForm.submit();
			}
		});

	 $("#delBtn").on("click", function(){
		let ok = confirm("삭제하시겠습니까?");
		if(ok){
			deleteForm.submit();
			}
		});

	 $("#listBtn").on("click", function(){
		let ok = confirm("목록으로 이동하시겠습니까?");
		if(ok){
			listForm.submit();
			}
		});
	
 </script>
 <jsp:include page="/includee/postScript.jsp" />
</body>
</html>