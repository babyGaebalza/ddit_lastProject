<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.table-size-1300{
	margin-top: 100px;
}
.search-align{
	float: right;
}
</style>
<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
		<h3>결재요청문서</h3>
	</div>
<table class="table table-bordered">
	<thead>
		<tr class='tr-style'>
			<th>문서번호</th> 
			<th>서류종류</th>
			<th>문서명</th> 
			<th>작성자</th> 
			<th>신청일자</th> 
			<th>최종결재여부</th> 
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
					${pagingVO.pagingHTML}
				</div>
				<div id="searchUI">
					<input type="text" name="searchWord2" value="제목 키워드 검색 " />
					<input type="button" id="searchBtn" value="검색" />
					<input type="button" class="linkBtn" 
						data-gopage="${pageContext.request.contextPath }/common/document/documentManage.do" value="신규등록" />
				</div>                 
			</td>
		</tr>
	</tfoot>
</table>
</div>
<form id="searchForm">                          
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />	
</form>

<form id="goPDF" action="${pageContext.request.contextPath }/common/pdf/onePDF.do" method="post">
	<input type ="hidden" name="docuNo"/>
</form>
<%-- 검색버튼, 페이징 [N]버튼 처리 --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>

<script type="text/javascript">
	let listBody = $("#listBody");
	let pagingArea = $("#pagingArea");
	let pageTag = $("[name='page']");
	let goPDF = $('#goPDF')

	$("#searchForm").paging({
		searchUI:"#searchUI",
		searchBtn:"#searchBtn",
		pagingArea:"#pagingArea",
		pageKey:"page",
		pageName:"[name='page']"
	}).ajaxForm({
		dataType:"json",
		success:function(resp){
			listBody.empty();
			pagingArea.empty();
			pageTag.val("");
			
			let docuList = resp.dataList;

			let pagingHTML = resp.pagingHTML;
			let trTags = [];
			if(docuList){
				$.each(docuList, function(idx, docu){
					let trTag = $("<tr>").append(
						//$("<td>").html(docu.rnum),		
						$("<td>").html(docu.docuNo).attr({id : "docuNo"}),
						$("<td>").html(docu.docuCode),
						$("<td>").html(		
								$("<a>").text(docu.docuFilename)
								.attr({
									href : "${pageContext.request.contextPath}/pdf.do",             //"${pageContext.request.contextPath}     ?what=" + docu.docuNo								
								    class : "pdfGoClick",
								    target : "_blank"
								})  //https://localhost/SCM/pdf.do
						),
						$("<td>").html(docu.docuReq),
						$("<td>").html(docu.docuReqdate),
						$("<td>").html(docu.docuState)	
						
					);
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
					$("<td>").attr({
						colspan:"6"
					})
				);
				trTags.push(trTag);
			}
			
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		},
		error : function(xhr, jQuery, error){
			console.log(xhr.status); 				
		}
	}).submit();
	
    <%--문서 하나 클릭했을때 바로 잡으면 안잡힘. 후손 형식으로 잡아야함--%>
	$('#listBody').on("click", '.pdfGoClick', function(event){
		event.preventDefault();
		let docuNo =  $(this).parent().prevAll("#docuNo").text(); 
	    $(goPDF).find(":input[name='docuNo']").val(docuNo); 
		goPDF.submit(); 
		return false;
	})
	
	</script>














