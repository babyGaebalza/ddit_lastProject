<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>


<head>

<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

</head>

<body>

<form:form onsubmit="formSubmit()" modelAttribute="facCom" action="${cPath }/board/facComUpdateprocess.do" method="post" id="facComForm" name="facComForm" enctype="multipart/form-data">
	<input type="text" hidden value="${board.boardNo }" name="boardNo" />
  <c:set property="${board.boardTitle }" var="title"/>
 	<table>
 		<tr>
		 	<th>시설 분류</th>
			 <td>
			 <select id="facList" required="required">
			 <c:forEach items="${facList }" var="fac">
				<c:if test="${cateName1 eq fac.cateName1}">
					<option value=${fac.cateCode } selected="selected">${fac.cateName1 }</option>
				</c:if>
			 </c:forEach>
			 </select>
		 </td>
		 </tr>
		 
		 <tr>
			 <th> 시설 명</th>
			 <td>
			 <input type="text" name="facName" id="facName" placeholder="시설명" value="${facName }" readonly="readonly" required="required"/>
			 </td>
		 </tr>
		 
		 <tr>
		 	<th>
		 	 사용자명
		 	</th>
		 	<td>
		 	<input type="text" name="memNo" id="memNo" placeholder="사용자명" value="${board.memNo }" readonly="readonly" required="required"/>
		 	</td>
		 </tr>
		 
		 <tr>
			 <th>
			 시설 사용일자
			 </th>
			 <td>
			 <input type="date" name="boardFdate" id="boardFdate" value="${board.boardDate }" required="required"/>
			 </td>
		 </tr>
		 
		 <tr>
			 <th>
			 민원 내용
			 </th>
			 <td>
				<textarea rows="5" cols="100" name="boardContent" id="boardContent">${board.boardContent }</textarea>
				<span class="error">${errors.boardContent }</span>
			</td>
		 </tr>
		 <tr>
			<th>첨부파일</th>
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
			 <th>
			 첨부 내용
			 </th>	
			 <td id="fileArea">
				<div>
					<input type="file" name="boFiles" multiple />
					<input type="button" value="추가" id="plus" class="ctlBtn btn btn-primary"/>
					<input type="button" value="제거" id="minus" class="ctlBtn btn btn-warning"/>
				</div>
			</td>
		 </tr>
		 
		 <tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success"/>
				<input type="reset" value="취소" class="btn btn-danger"/>
			</td>
		</tr>
	 </table>
	 
	 <input type="text" id="boardTitle" name="boardTitle" value=" " hidden/>
 </form:form>
 
  	<script type="text/javascript">
 	function formSubmit(){
 		var facName = $("#facName").val();
 		var facList = document.getElementById("facList");
 		var fac = facList.options[facList.selectedIndex].text;	
 		var facdate = $("#boardFdate").val();
 		
		var titlecont = facdate+"/"+fac+"/"+facName+"의 시설민원";
		$("#boardTitle").val(titlecont);
	
 	};
 	</script>
 
 
 <script type="text/javascript">
		CKEDITOR.replace("boardContent", {
			filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
		});
		let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
			let id = this.id;
			console.log(id);
			let divTag = $(this).closest("div");
			if(id == 'plus'){
				let clone = divTag.clone();
				$(clone).find(":input[name]").val("");
				divTag.after(clone);
			}else{
				let divs = fileArea.find("div");
				if(divs.length>1)
					divTag.remove();
			}
		});

	</script>
 </body>
 </html>