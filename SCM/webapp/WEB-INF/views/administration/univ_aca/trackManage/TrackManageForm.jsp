<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<jsp:include page="/includee/preScript.jsp" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>

<style>
.form-control{
	display: inline;
	border : 1px solid #aaa;
}
#input{
	border : 1px solid #aaa;
	border-radius : 4px;
	height : 35px;
}
.table-size-1300{
	margin-top : 100px;
	float:left;
	width : 680px;
}
iframe{
	float: right;
	margin: 0px;
}

</style>

<form:form modelAttribute="track" method="post" id="editForm">
<div class="table-size-1300">
	<h3>트랙 등록</h3>

	<table class="table table-bordered">
		<tr>
			<th>단과/학과 선택</th>
			<td>
				<select name="collegeCode" class="form-control col-lg-5">
					<option value>단과 선택</option>
				</select>
				
				<select name="majorCode" class="form-control col-lg-5">
					<option value>학과 선택</option>
				</select>
				<input type="hidden" name="majorName" value=""/>
			</td>
		</tr>
		
		<tr>
			<th>트랙명</th>
			<td><input type="text" id="input" name="trackName" required/></td>
		</tr>
		
<!-- 		<tr> -->
<!-- 			<th>트랙 검색</th> -->
<!-- 				<td> -->
<!-- 					<select name="classSemester"> -->
<!-- 						<option value>전체</option> -->
<!-- 						<option value="1/1">1학년 1학기</option> -->
<!-- 						<option value="1/2">1학년 2학기</option> -->
<!-- 						<option value="2/1">2학년 1학기</option> -->
<!-- 						<option value="2/2">2학년 2학기</option> -->
<!-- 						<option value="3/1">3학년 1학기</option>	 -->
<!-- 						<option value="3/2">3학년 2학기</option>				 -->
<!-- 						<option value="4/1">4학년 1학기</option>				 -->
<!-- 						<option value="4/2">4학년 2학기</option>				 -->
<!-- 					</select> -->
<!-- 				<td> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		
		<tr>
			<th>트랙과목</th>
			<td id="subj">
				<div>
					<select class="js-example-basic-multiple" multiple="multiple" name="classNo" id="classNo" style="width: 430px;">
<!-- 					<select name="classNo" id="classNo"> -->
						<option value>강의 선택</option>
					</select>
<!-- 					<input type="button" value="추가" id="plus" class="ctlBtn btn btn-secondary btn-sm"/> -->
<!-- 					<input type="button" value="제거" id="minus" class="ctlBtn btn btn-secondary btn-sm"/> -->
				</div>
			</td>
		</tr>
	</table>
	<center>
		<input type="submit" value="저장" class="btn btn-secondary btn-sm"/>
		<input type="reset" value="취소" class="btn btn-secondary btn-sm"/>
	</center>
</div>

<c:url value="/docuPdf.do" var="viewURL">
	<c:param name="docuNo" value="${docuNo}" />
</c:url>
<iframe width="800" height="800" src="${viewURL}"></iframe>

</form:form>

<script>
$(function(){
	//비동기 selectbox
	let collegeCode = $('[name="collegeCode"]');
	let majorCode = $("[name='majorCode']");
	let classSemester = $("[name='classSemester']");
	let classNo = $("[name='classNo']");
	
	//학과 선택
	$.ajax({
		url : "<%=request.getContextPath() %>/trackManage/collegeCodeList.do",
		dataType : "json",
		success : function(resp) {
			let options = [];
			$.each(resp, function(idx, cate){
				let option = $("<option>")
								.text(cate.collegeName)
								.attr("value", cate.collegeCode);
				options.push(option);
			});
			collegeCode.append(options);
			collegeCode.val("${cate.collegeCode}");
			collegeCode.trigger("change");
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
	
// 	$("select[name='className']").change(function(){
// 		className = $(this).val(); //value값 가져오기
//  		console.log($("select[name='className'] option:selected").text()); //text값 가져오기
// 	});

	
	//전공 선택
	collegeCode.on("change", function(){
		let collegeCode = $(this).val();
		
		$.ajax({
			url : "<%=request.getContextPath() %>/trackManage/majorCodeList.do",
			data : { "collegeCode" : collegeCode },
			dataType : "json",
			success : function(resp) {
				let options = [];
				$.each(resp, function(idx, cate){
					let option = $("<option>")
									.text(cate.majorName)
									.attr("value", cate.majorCode);
					options.push(option);
				});
				if(options.length > 0){
					majorCode.empty(options);
				}
				majorCode.append(options);
				majorCode.val("${cate.majorCode}");
				majorCode.trigger("change");
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	});
	
	//학년 학기 선택
// 	majorCode.on("change", function(){
// 		let majorCode = $(this).val();
		
// 		$.ajax({
<%-- 			url : "<%=request.getContextPath() %>/trackManage/classSemesterList.do", --%>
// 			data : { "majorCode" : majorCode},
// 			dataType : "json",
// 			success : function(resp) {
// 				let options = [];
// 				$.each(resp, function(idx, cate){
// 					let option = $("<option>")
// 									.text(cate.classSemester)
// 									.attr("value", cate.classSemester);
// 					options.push(option);
// 				});
// 				if(options.length > 0){
// 					classSemester.empty(options);
// 				}
// 				classSemester.append(options);
// 				classSemester.val("${cate.majorCode}");
// 				classSemester.trigger("change");
// 			},
// 			error : function(xhr, errorResp, error) {
// 				console.log(xhr);
// 				console.log(errorResp);
// 				console.log(error);
// 			}
// 		});
// 	});
	
	//강의 선택
//	majorCode.on("change", ".classNo", function(){
	majorCode.on("change", function(){
		let majorCode = $(this).val();
		
		let majorName = $("[name='majorCode'] option:selected").text();
		$("[name='majorName']").val(majorName);
		
		$.ajax({
			url : "<%=request.getContextPath() %>/trackManage/classNameList.do",
			data : {"majorCode" : majorCode},
			dataType : "json",
			success : function(resp) {
				let options = [];
				$.each(resp, function(idx, cate){
					let option = $("<option>")
									.text(cate.className)
									.attr("value", cate.classNo)
					options.push(option);
				});
				if(options.length > 0){
					classNo.empty(options);
				}
				classNo.append(options);
				classNo.val("${cate.classNo}");
				classNo.trigger("change");
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
		
		$.ajax({
			url : "<%=request.getContextPath() %>/trackManage/classNameList2.do",
			data : {"majorCode" : majorCode},
			dataType : "json",
			success : function(resp) {
				console.log(resp)
			}
		});
		
	});
	
// 	$('#classNo').select2();
	$('#classNo').ready(function () {
        $('.js-example-basic-multiple').select2();
    });

	let subj = $("#subj").on("click", ".ctlBtn", function(){
		let id = this.id;
		let divTag = $(this).closest("div");
		if(id == 'plus'){
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);
		}else{
			let divs = subj.find("div");
			if(divs.length>1)
				divTag.remove();
		}
	});
	
	$('.classNo').on('change', function () {
		let calsArr = []
		$(".classNo option:selected").each(function(){
			value = $(this).val();
			calsArr.push(value);
			console.log(calsArr)
		})
	})
});

</script>
	
<jsp:include page="/includee/postScript.jsp"/>
