<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<style>
.bColor {	
    background-color: aliceblue;
    width: 140px;
    text-align: center;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
	<h5>시설사용 민원 게시판 입니다.</h5>
	</div>
	<form:form onsubmit="formSubmit()" modelAttribute="facCom" action="${cPath }/board/facComInsert.do" method="post" id="facComForm" name="facComForm" enctype="multipart/form-data">
	<input type="hidden" value="${memid }" name="memNo" id="memNo" placeholder="사용자명" readonly="readonly" required="required" /> 
	 	<table class="table table-bordered">
	 		<tr>
			 	<th class="bColor">시설 분류</th>
				 <td>
				 <select id="facList" required="required">
				 <option value>시설 분류</option>
				 <c:forEach items="${facList }" var="fac">
				 	<div>${fac.cateName1 }</div>
				 	<option value=${fac.cateCode }>${fac.cateName1 }</option>
				 </c:forEach>
				 </select>
			 </td>
			 </tr>
			 
			 <tr>
				 <th class="bColor"> 시설 명</th>
				 <td>
				 <input type="text" name="facName" id="facName" placeholder="시설명" required="required"/>
				 </td>
			 </tr>		 
			 <tr>
				 <th class="bColor"> 사용자</th>
				 <td>
				 <input type="hidden" name="memNo" id="memNo" value="${memid }" required="required"/>
				 <input type="text" value="${memName }" required="required"/>
				 </td>
			 </tr>		 
			 <tr>
				 <th class="bColor">
				 시설 사용일자
				 </th>
				 <td>
				 <input type="date" name="facdate" id="facdate" required="required"/>
				 </td>
			 </tr>
			 
			 <tr>
				 <th class="bColor">
				 	민원 내용
				 </th>
				 <td>
					<textarea rows="5" cols="100" name="boardContent" id="boContent" required="required">${board.boContent }</textarea>
					<span class="error">${errors.boContent }</span>
				</td>
			 </tr>
			 
			 <tr>
				 <th class="bColor">
					 첨부 내용
				 </th>	
				 <td id="fileArea">
					<div>
						<input type="file" name="boFiles" multiple />
						<div style="float: right;">
							<input type="button" value="추가" id="plus" class="btn btn-outline-success"/>
							<input type="button" value="제거" id="minus" class="btn btn-outline-warning"/>
						</div>
					</div>
				</td>
			 </tr>
			 
			 <tr>
				<td colspan="2">
					<div style="float: right;">
						<input type="submit" value="저장" class="btn btn-outline-info"/>
						<input type="button" class="btn btn-outline-secondary" onClick="location.href='${cPath}/board/facComList.do'" value="목록으로" />
					</div>
				</td>
			</tr>
		 </table>
		 
		 <input type="text" id="boardTitle" name="boardTitle" value=" " hidden/>
	</form:form>
</div> 
 	<script type="text/javascript">
 	function formSubmit(){
 		var facName = $("#facName").val();
 		var facList = document.getElementById("facList");
 		var fac = facList.options[facList.selectedIndex].text;	
 		var facdate = $("#facdate").val();
 		
		var titlecont = facdate+"/"+fac+"/"+facName+"의 시설민원";
		$("#boardTitle").val(titlecont);
	
 	};
 	</script>
 
 	<script type="text/javascript">
		CKEDITOR.replace("boContent", {
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
 
