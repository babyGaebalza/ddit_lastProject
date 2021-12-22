<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.modal-content{
overflow-y: initial !important
}
.modal-body{
height: 650px;
overflow-y: auto;
}
</style>
<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>	
</security:authorize> 

<div style="width: 1000px; margin:0 auto; margin-top:110px; margin-bottom:20px;">
	<div class="left-card">
		<div>
		<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold">트랙개설신청 </h6>
		
		</div>
		<table class="table table-bordered">
			<tr>  
				<th>서류종류</th>
				<td> 
					트랙추가신청서
				    <input type="hidden" name= "docuCode" value="DOC17"/>
				</td>
			</tr>  
			<tr>
				<th>제목</th>
				<td><input type="text" id="writeFilename"
					value="${document.docuFilename}" />
				<form:errors path="docuFilename" element="span" cssClass="error" /></td>
			</tr>	
			<tr>
				<th>신규트랙명</th>
				<td>
				<input type="text" id="docuCont1"/>				
				</td>
			</tr>
			<tr>
				<th>개설 사유 </th>
				<td>
				<textarea id="docuCont3" name = "docuCont3" rows="5" cols="90"></textarea>		
				</td>
			</tr>
			<tr>
				<th>전공선택강의 추가</th>
				<td>
				<div type="text" id="docuCont2"></div>
				<button id="majorClassBtn" class="btn btn-secondary btn-sm">전공강의확인</button>
				</td>
			</tr>
			<tr>
				<th>신청자</th>  <!-- 하드코딩 수정하기 -->
				<td> ${authMember.memName}[${authMember.memId}]   
				</td>
			</tr>
<!-- 			<!-- 결재인1 --> 
<!-- 			<tr> 필요한것 . 이름으로 검색하면  해당하는 리스트가 나오게  이름이랑 아이디가 같이 나와야 함. -->
<!-- 				<th>결재인1</th> -->
<!-- 				<td><select  class="userCode"> -->
<!-- 						 -->
<!-- 						<option value="" selected>사용자구분</option> -->
<!-- 						<option value="US01">행정직원</option> -->
<!-- 						<option value="US02">교수</option> -->
<!-- 						<option value="US03">조교</option> -->
<!-- 				</select> -->
<%-- <%-- 				 <form:errors path="signMemberList[0].userCode" element="span" cssClass="error" /> --%> 
<!-- 				<select  class= "deptCode">  -->
<!-- 					<option value=" " selected>부서선택</option> -->
<!-- 				</select> -->
<%-- <%-- 				<form:errors path="signMemberList[0].deptCode" element="span" cssClass="error" />	 --%> 
<!-- 				<select  class="rankCode">  -->
<!-- 					<option value=" " selected>직급선택</option> -->
<!-- 				</select> -->
<%-- <%-- 				<form:errors path="signMemberList[0].rankCode" element="span" cssClass="error" /> --%> 
<!-- 				<select name = "docuAp1" class="memName">  -->
<!-- 					<option value=" " selected>이름[아이디]</option> -->
<!-- 				</select>	 -->
<%-- <%-- 				<form:errors path="signMemberList[0].memId" element="span" cssClass="error" /> --%> 
<!-- 				</td> -->
<!-- 			</tr>			 -->
<!-- <!-- 결재인2 -->
<!-- 			<tr> 필요한것 . 이름으로 검색하면  해당하는 리스트가 나오게  이름이랑 아이디가 같이 나와야 함. -->
<!-- 				<th>결재인2</th> -->
<!-- 				<td> -->
<!-- 				<select class="userCode">  -->
<!-- 					<option value=" " selected >사용자구분</option> -->
<!-- 						<option value="US01">행정직원</option> -->
<!-- 						<option value="US02">교수</option> -->
<!-- 						<option value="US03">조교</option> -->
					
<!-- 				</select>			 -->
<%-- <%-- 				<form:errors path="signMemberList[1].userCode" element="span" cssClass="error" /> --%> 
<!-- 				<select  class= "deptCode">  -->
<!-- 					<option value="" selected>부서선택</option> -->
<!-- 				</select> -->
<%-- <%-- 				<form:errors path="signMemberList[1].deptCode" element="span" cssClass="error" />	 --%> 
<!-- 				<select class="rankCode">  -->
<!-- 					<option value=" " selected>직급선택</option> -->
<!-- 				</select> -->
<%-- <%-- 				<form:errors path="signMemberList[1].rankCode" element="span" cssClass="error" /> --%> 
				
<!-- 				<select name="docuAp2" class="memName">  -->
<!-- 					<option value="" selected >이름[아이디]</option> -->
<!-- 				</select>	 -->
<%-- <%-- 				<form:errors path="signMemberList[1].memId" element="span" cssClass="error" /> --%> 
<!-- 				</td> -->
<!-- 			</tr>					 -->
<!-- 최종결재자 -->
			<tr> <!-- 필요한것 . 이름으로 검색하면  해당하는 리스트가 나오게  이름이랑 아이디가 같이 나와야 함.-->
				<th>결재자</th>
				<td>
				<input type="text" disabled value="교무처 교무관리과"/>		
				<input type="text" disabled value="소찬중[A012108]"/>						
				</td>
			</tr>	
			<tr>
			<td colspan="2">
				<input type="submit" value="전송"  id="mySubmitBtn" class="btn btn-secondary btn-sm"/>
			</td>
		</tr>
			
		</table>
		</div>                 																		    
	</div>
</div>
		
<%-- </form:form> --%>
<form id="otherSelectForm" action="${pageContext.request.contextPath}/others/getNameAndId.do">
<input type="hidden" name="rankCode"/>
<input type="hidden" name="deptCode"/>
<input type="hidden" name="userCode"/>
</form>
<form id="thisFormIsReal" method="post" action = "${pageContext.request.contextPath}/common/document/documentManage.do">
<input type="hidden" name="docuReq" value="${authMember.memId}">
<input type="hidden" name="docuFilename"/>
<input type="hidden" name="docuCont"/>
<input type="hidden" name="docuCode" value="DOC17"/>

<input type="hidden" value="A012108" name="docuApf" />
</form> 

  <!--  Modal-->
	<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<button type="button" class="close" aria-label="Close"></button>
<!-- 						<span aria-hidden="true">&times;</span> -->
					</button>
					<div id="myModalTitle">
					<h3 class="modal-title" id="exampleModalLabel"></h3>
					</div>
				</div>
				<div class="modal-body">
			    <table class="table table-bordered" id="modalTable">
<!-- 				<tr class="tr-style"> -->
<!-- 				<th>강의명</th> -->
<!-- 				<th>학년/학기</th> -->
<!-- 				<th>학점</th> -->
<!-- 				</tr> -->
			    </table>
				</div>
				<div class="modal-footer">
<!-- 					<a class="btn" id="modalY" href="#">예</a> -->
					<button class="btn" type="button" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
 let modalTable = $('#modalTable') 
// let btn =  modalTable.find("button");
//let btn =  $('.mdlBtn') //modalTable.find('td'); //mdlBtn
let docuCont2 = $('#docuCont2')
$(document).on("click", ".mdlBtn", function(){
	console.log('눌림')
	console.log($(this).data('classno'))
	let classNo = $(this).data('classno');
	$(this).attr("disabled", "disabled");
	$(this).val("완료")
	docuCont2.append(classNo + "  -  ")
}) 


let myModal = $('#testModal')
let title = $('#myModalTitle')
let myModalContent = $('.modal-body') 
let modalBtn = $('#majorClassBtn')
let memMajor = "${authMember.memMajor}"
modalBtn.click(function(e){
	console.log("버튼 눌림")
	e.preventDefault();
	
	$.ajax({   
		url : "<%=request.getContextPath()%>/common/pdf/showLectureList.do",
		data : {
				"memMajor" : memMajor
				},
		dataType : "json",
		success : function(resp) { //승인이 되면, 승인완료로 고치기 재로드
		//	let pointList = resp.myClassList
            let resTable = $("<table class='table table-bordered'>");
			
			let trTags = [];
 			let thTag = $("<tr class='tr-style'>").append(  
 					  $("<th>").text("강의명"),  $("<th>").text("학년/학기"),$("<th>").text("학점"),$("<th>").text("선택") 
  					)

			$.each(resp, function(idx, myClass){
			trTag = $("<tr>").append(
					$("<td  class='mdlBtn'>").text( myClass.className ),	
					$("<td>").text(myClass.classSemester),
					$("<td>").text(myClass.classPoint),
					$("<td>").append($("<input type='button' class='mdlBtn'>").val("선택").data("classno", myClass.className)
			)


			)
			trTags.push(trTag);
              //console.log("클래스 네임" + myClass.className);
			})
      		resTable.append(thTag);

			resTable.append(trTags);

			myModalContent.html(resTable);
			
					},
		error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
		});
	
	
	myModal.modal("show");
	}); 

let realForm = $('#thisFormIsReal');


$('#mySubmitBtn').on("click",function(){
	let fileName = $('#writeFilename').val();
	let docuCont1 = $('#docuCont1').val() + "#"; //신규트랙명
	let docuCont2 = $('#docuCont2').text(); //강의선택 배열로 옴
	let docuCont3 = "#" + $('#docuCont3').val();
	console.log(docuCont3);

    let docuCont = docuCont1 + docuCont2+docuCont3
	console.log(docuCont) //1234#1051700051,2051700054
	realForm.find('[name="docuFilename"]').val(fileName)
	realForm.find('[name="docuCont"]').val(docuCont)
	console.log(docuCont);
	realForm.submit();
})




let signMemberListUserCode; 
let searchForm = $('#searchForm'); 
//이름선택하면 아이디 

//직급코드 선택하면 이름이랑 아이디 불러오기 
$('.rankCode').on("change",function(){
	let memName = $(this).next();
	$('[name="rankCode"]').val($(this).val())
    $('[name="deptCode"]').val($(this).prev().val())
	$('[name="userCode"]').val($(this).prev().prev().val())

	$('#otherSelectForm').ajaxForm({
		dataType:"json",
		success:function(resp){
			let options = [];
			$.each(resp, function(idx, list){
			options.push(
				$("<option>").text(list.name + "[" + list.id+ "]")
							.attr("value",list.id)				
			);
			console.log(list.name); 	
			console.log(list.id); 	
			});
			memName.html(options);
			memName.val("${docu.signMemberList[0].memName}");
			memName.trigger("change");
		}
	}).submit();
	
})
//부서코드 선택되면  직급 코드 리스트 비동기요청
$('.deptCode').on("change", function(){
	let rankcode = $(this).next();
	let options = [];

	
	if(signMemberListUserCode == 'US01'){//행정직원 
	    options.push(
				$("<option>").text("처장")
				.attr("value","RANK01")
		);
	    options.push(
				$("<option>").text("부장")
				.attr("value","RANK02")
		);
	    options.push(
				$("<option>").text("사원")
				.attr("value","RANK03")			
		);   
	}else if(signMemberListUserCode == 'US02'){ //교수 
		  options.push(
					$("<option>").text("학과장")
					.attr("value","RANK04")			
		  );   
		  options.push(
					$("<option>").text("교수")
					.attr("value","RANK05")			
		  );  
	}else{ //조교
		 options.push(
					$("<option>").text("조교")
					.attr("value","RANK06")			
		  ); 
	}	
	rankcode.html(options);
	rankcode.val("${docu.signMemberList[0].rankCode}"); 
	rankcode.trigger("change"); 
})

//사용자구분코드가 선택되면 부서코드 리스트 비동기 요청 
$('.userCode').on("change",function(){
	signMemberListUserCode = $(this).val();  //US01 (행정직원)-> 부서조회, US02~US03 (교수, 조교) -> 학과 조회 
	let url; 
	let deptCode = $(this).next();
	console.log(deptCode); 
	if(signMemberListUserCode == 'US01'){
		url = "${pageContext.request.contextPath}/others/getDeptList.do"
	}else{
		url = "${pageContext.request.contextPath}/others/getMajorList.do"
	}	
		$.ajax({
		url : url,	
		dataType : "json",
		success : function(resp) { //resultList
			let options = [];
			$.each(resp, function(idx, list){
			options.push(
				$("<option>").text(list.name)
							.attr("value",list.code)
			);
		});
		deptCode.html(options);
		deptCode.val("${docu.signMemberList[0].deptCode}");
		deptCode.trigger("change");
		},
		error : function(xhr, errorResp, error) {
		console.log(xhr);
		console.log(errorResp);
		console.log(error);
		}
		})
})
</script>
