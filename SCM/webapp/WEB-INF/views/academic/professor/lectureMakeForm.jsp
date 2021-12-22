<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath}/resources/js/ckeditor/ckeditor.js"></script>

<h3>강의개설신청서</h3>

<form:form  id ="makeLectureForm" modelAttribute="myClass" method="post" action="${pageContext.request.contextPath}/common/document/classMakedocumentManage.do">
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.authMember" var="authMember"/>	
</security:authorize> 
	<table class="table table-bordered">
		<tr>
			<th>강의구분</th>
			<td><select name="classCode" id="classCode">
					<option value>선택</option>
					<option value="CL01">전공선택</option>
					<option value="CL02">전공필수</option>
					<option value="CL03">교양선택</option>
					<option value="CL04">교양필수</option>
			</select>
			<form:errors path="classCode" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>학사과정</th>
			<td>학년<select name="classSemester1">
					<option value>선택</option>
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
			</select> 학기<select name="classSemester2">
				<option value>선택</option>
				<option>1</option>
				<option>2</option>
			</select>	
			</td>
		</tr>

		<tr>
			<th>강의실</th>
			<td><select name="facNo" id="facNo">
			<option value>선택</option></select> 	
			<form:errors path="facNo" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>신규개설여부</th>
			<td>신규개설<input type="radio" name="newOld" class="newOld"
				value="new"/>
				기존<input type="radio" name="newOld"  class="newOld"
				value="old" />
			</td>		
		</tr>
		<tr>
			<th>강의명</th>
			<td id="classNameTd">
			<div id="hereToclassNameInput"></div>
			<input type="text" name="className" 
				value="${myClass.className}"/>
			<form:errors path="className" element="span" cssClass="error" />
			<div id="hereToclassNameList"></div>
<!-- 			<input type='button' value='강의검색' id='oldLectureSearch'/>	 -->
			</td>
		</tr>
		<tr> 
			<th>강의시간</th>
			<td>
			<select name="classTime1">
			<option value selected>요일선택</option>
			<option value="월">월</option>
			<option value="화">화</option>
			<option value="수">수</option>
			<option value="목">목</option>
			<option value="금">금</option>
			</select>
			<select name="classTime1.1" multiple>
			<option value selected>교시선택</option>
			<option value="12">1-2교시</option>
			<option value="34">3-4교시</option>
			<option value="56">5-6교시</option>
			<option value="78">7-8교시</option>
			</select>
			<br>
			<select name="classTime2">
			<option value>요일선택</option>
			<option value="월">월</option>
			<option value="화">화</option>
			<option value="수">수</option>
			<option value="목">목</option>
			<option value="금">금</option>
			</select>
			<select name="classTime2.2" multiple>
			<option value selected>교시선택</option>
			<option value="12">1-2교시</option>
			<option value="34">3-4교시</option>
			<option value="56">5-6교시</option>
			<option value="78">7-8교시</option>
			</select>
			</td>
		</tr>
		<tr>
			<th>학점</th>
			<td>
			<select name="classPoint">
			<option value selected>학점선택</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			</select>	
			<form:errors path="classPoint" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>정원</th>
			<td><input type="number" name="classMax"
				value="${myClass.classMax}" />
			<form:errors path="classMax" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>대면여부</th>
			<td>대면<input type="radio" name="classOn"
				value="Y" />
			비대면<input type="radio" name="classOn"
				value="N" />
			<form:errors path="classOn" element="span" cssClass="error" /></td>
		</tr>
		
		<tr>
			<th>분반</th>
			<td><input type="text" name="classDivide" value="${myClass.classDivide}" />
			<form:errors path="classDivide" element="span" cssClass="error" /></td>
		</tr>

		<!-- 점수비율 -->
		<tr>
			<th>출석점수비율</th>
			<td><select name="classAttpoint">
				<option value="0">0%</option>
				<option value="10">10%</option>
				<option value="20">20%</option>
				<option value="30">30%</option>
				<option value="40">40%</option>	
				<option value="50">50%</option>				
				<option value="60">60%</option>				
				<option value="70">70%</option>				
				<option value="80">80%</option>				
				<option value="90">90%</option>				
				<option value="100">100%</option>											
			</select>
			<form:errors path="classAttpoint" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>과제점수비율</th>
			<td><select name="classTaskpoint">
				<option value="0">0%</option>
				<option value="10">10%</option>
				<option value="20">20%</option>
				<option value="30">30%</option>
				<option value="40">40%</option>	
				<option value="50">50%</option>				
				<option value="60">60%</option>				
				<option value="70">70%</option>				
				<option value="80">80%</option>				
				<option value="90">90%</option>				
				<option value="100">100%</option>		
			</select>
			<form:errors path="classTaskpoint" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>중간점수비율</th>
			<td><select name="classMidpoint">
				<option value="0">0%</option>
				<option value="10">10%</option>
				<option value="20">20%</option>
				<option value="30">30%</option>
				<option value="40">40%</option>	
				<option value="50">50%</option>				
				<option value="60">60%</option>				
				<option value="70">70%</option>				
				<option value="80">80%</option>				
				<option value="90">90%</option>				
				<option value="100">100%</option>
			</select>
			<form:errors path="classMidpoint" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>기말점수비율</th>
			<td><select name="classFinpoint">
				<option value="0">0%</option>
				<option value="10">10%</option>
				<option value="20">20%</option>
				<option value="30">30%</option>
				<option value="40">40%</option>	
				<option value="50">50%</option>				
				<option value="60">60%</option>				
				<option value="70">70%</option>				
				<option value="80">80%</option>				
				<option value="90">90%</option>				
				<option value="100">100%</option>
			</select> 
			<form:errors path="classFinpoint" element="span" cssClass="error" /></td>
		</tr>
		
		<!-- 최종결재자만 선택 -->
		<tr> 
				<th>최종결재자</th>
				<td>
				<select name="docuApf"  id="docuApf">
					<option value>이름[아이디]</option>
				</select>				
				</td>
		</tr>	
		</table>
	<input type="hidden" name="memNo" value="${authMember.memId}" />
	<input type="hidden" name="classSemester"/>  <!-- 여기 값넣어주기 -->
	<input type="hidden" name="classTime"/>
	<input type="hidden" name="classExtend"/>
	<input type="hidden" name="collegeCode" value="${authMember.memCollege}">
	<input type="hidden" name="majorCode" value="${authMember.memMajor}">
	
	<input type="button" value="제출" id="allSubmitBtn">
	</form:form>
<form id="otherSelectForm1" action="${pageContext.request.contextPath}/makeLecture/getMajorChef.do">
<input type="hidden" name="memId" value="${authMember.memMajor}"/>
</form>	
<form id="otherSelectForm2" action="${pageContext.request.contextPath}/makeLecture/getFacCode.do">
<input type="hidden" name="majorCode" value="${authMember.memMajor}"/>
</form>	
<form id="otherSelectForm3" action="${pageContext.request.contextPath}/makeLecture/getOldClassList.do">
<input type="hidden" name="majorCode" value="${authMember.memMajor}"/>
<input type="hidden" name="classCode"/>
</form>			
<script type="text/javascript">
//전체 폼 제출하기 
let makeLectureForm = $('#makeLectureForm')
$('#allSubmitBtn').on("click", function(){
	let classSemester1 = makeLectureForm.find(":input[name='classSemester1']").val()
    let classSemester2 = makeLectureForm.find(":input[name='classSemester2']").val()
	makeLectureForm.find(":input[name='classSemester']").val(classSemester1 + "/" + classSemester2)
	
	let classTime1 = makeLectureForm.find(":input[name='classTime1']").val()
	let classTime1_1 = makeLectureForm.find(":input[name='classTime1.1']").val()
	let classTime2 = makeLectureForm.find(":input[name='classTime2']").val()
	let classTime2_2 = makeLectureForm.find(":input[name='classTime2.2']").val()
	makeLectureForm.find(":input[name='classTime']").val(
			classTime1+classTime1_1+"/"+classTime2+classTime2_2
	)
	
	let flag = makeLectureForm.find('.newOld').val(); 
	
	if(flag == 'old'){
		let classExtendNo = makeLectureForm.find(":input[name='className']").val()
		makeLectureForm.find(":input[name='classExtend']").val(
				classExtendNo
		)	
	}
	
	makeLectureForm.submit(); 	
})
let otherSelectForm1 = $('#otherSelectForm1')
let otherSelectForm2 = $('#otherSelectForm2')
let otherSelectForm3 = $('#otherSelectForm3')

//학과장 리스트 불러오기 
let docuApf = $("#docuApf")
$('#otherSelectForm1').ajaxForm({
		dataType:"json",
		success:function(resp){
			let options = [];
		    options.push(
					$("<option>").text("최종결재자선택")	
			); 
			$.each(resp, function(idx, list){

			options.push(
				$("<option>").text(list.name + "[" + list.id+ "]")
							.attr("value",list.id)				
			);
			});
			docuApf.html(options);
		}
	}).submit();


//신규개설여부 선택하면 >강의명 검색버튼 보이기
//신규아닌거 선택하면 > selectBox로 하기 
$('.newOld').on("change", function(){	
	console.log("버튼눌림")
	let newOld = $(this).val();
	if(newOld =='old'){
		$('#oldLectureSearch').show(); 
        let classNameinput = $('#classNameTd').find(":input[name='className']")
        classNameinput.remove(); 
        $('#hereToclassNameList').append("<select name='className' id='classNameList'>" 
       	+ "<option value>강의명[강의코드]</option>" +
       	 "</select>") 
		
		let classNameList = $('#classNameList')
		let classCode = $('#classCode').val()
		//let majorCode = $('#majorCode').val()
     	//otherSelectForm3.find(":input[name='majorCode']").val(majorCode)
     	otherSelectForm3.find(":input[name='classCode']").val(classCode)

		
		$('#otherSelectForm3').ajaxForm({
    		dataType:"json",
    		success:function(resp){
    			let options = [];
    			$.each(resp, function(idx, list){

    			options.push(
    				$("<option>").text(list.name + "[" + list.id+ "]")
    							.attr("value",list.id)				
    			);
    			});
    			classNameList.html(options);
    		}
    	}).submit();
	}

	else if(newOld == 'new'){
		$('#oldLectureSearch').hide(); 
		let classNameList = $('#classNameList')
        classNameList.remove(); 
		let originInputTag = $('#classNameTd').find(":input[name='className']")
		if(originInputTag.val() == null){
			$('#hereToclassNameInput').append("<input type='text' name='className'  value='${myClass.className}'/>" ) ;
		}	
	}	
})


//(학과선택하면 걍 비동기)해당강의실 불러오기 
//$('#majorCode').on("change",function(){
let	facNo = $('#facNo')
//let majorCode = $(this).val(); 
//otherSelectForm2.find(":input[name]").val(majorCode)

$('#otherSelectForm2').ajaxForm({
	dataType:"json",
	success:function(resp){
		let options = [];
	    options.push(
				$("<option>").text("강의실선택")	
		); 
		$.each(resp, function(idx, list){

		options.push(
			$("<option>").text(list.name)
						.attr("value",list.id)				
		);
		});
		facNo.html(options);
	}
}).submit();

//})

/* //대학선택하면 학과코드 불러오기 
let collegeCode; 
$('#collegeCode').on("change",function(){
	collegeCode = $(this).val();
	otherSelectForm1.find(":input[name]").val(collegeCode)
    let majorCode = $('#majorCode')
	$('#otherSelectForm1').ajaxForm({
		dataType:"json",
		success:function(resp){
			let options = [];
		    options.push(
					$("<option>").text("학과선택")	
			); 
			$.each(resp, function(idx, list){

			options.push(
				$("<option>").text(list.name + "[" + list.id+ "]")
							.attr("value",list.id)				
			);
			});
			majorCode.html(options);
		}
	}).submit();
}) */
 

</script>

