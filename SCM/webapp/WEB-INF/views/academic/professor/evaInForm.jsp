<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>
.ans{
	width : 137px;
    text-align: center;
}

.lab {
    text-align: center;
}
</style>

<div class="table-size-1300">
	<div>
		<h3 style="padding-top: 30px;">강의평가 설문지 작성화면</h3>
		<form:form action="${cPath }/common/evaluation/evaInputForm.do" method="post" modelAttribute="EvaluationListVO">
			<table class="table table-bordered">
				<tr>
					<th>평가할 강의 번호</th>
					<td><input type="text" value="${classNo }" readonly="readonly" style="border: none;" /></td>
				</tr>
			</table>
			<div style="padding-bottom: 5px;display: inline-flex;">
				<div style="font-size: 24px;padding-right: 10px;">질문 추가하기</div>
				<input type="button" class="btn btn-outline-primary" value="추가" id="inputQBTN" style="margin-right: 5px;"/> 
				<input type="button" class="btn btn-outline-warning" value="제거" id="cancleQBTN" />

			</div>
					<div style="float: right;">
						<select id="selectBox">
							<option value="">자주하는 설문항목</option>
							<option value="강의실의 환경(크기, 청결, 조명, 온도 등)은 적절하였다.">강의실의 환경(크기, 청결, 조명, 온도 등)은 적절하였다.</option>
							<option value="나는 전체적으로 이 수업에 만족한다.">나는 전체적으로 이 수업에 만족한다.</option>
							<option value="교수는 효과적으로 학생들을 수업에 참여시켰다.">교수는 효과적으로 학생들을 수업에 참여시켰다.</option>
							<option value="교수는 성실히(질문답변, 학생존중) 수업하였다.">교수는 성실히(질문답변, 학생존중) 수업하였다.</option>
							<option value="교수는 학생들의 도움을 요청 할 수 있도록 배려하였다.(이메일, 게시판 등)">교수는 학생들의 도움을 요청 할 수 있도록 배려하였다.(이메일, 게시판 등)</option>
							<option value="교수는 강의계획서대로 수업을 진행하였다.">교수는 강의계획서대로 수업을 진행하였다.</option>
							<option value="교수는 다양한 매체로 수업을 진행하였다.">교수는 다양한 매체로 수업을 진행하였다.</option>
						</select>
					</div>
			<table class="table table-bordered" id="qArea">
				<tr>
					<th style="width: 60px;">번호</th>
					<th class="ti">질문내용</th>
					<th class="ans">전혀그렇지않다</th>
					<th class="ans">그렇지않다</th>
					<th class="ans">보통이다</th>
					<th class="ans">그렇다</th>
					<th class="ans">매우그렇다</th>
				</tr>

			</table>
			<input type="submit" value="적용" id="finBtn" class="btn btn-outline-success" style="float: right;"/>
		</form:form>
		
	</div>
</div>


<script>
		var index = 1;
		var inputindex = 0;
		
	$("#inputQBTN").on("click", function(){
		
		
		var inputForm = "";
		inputForm += "<tr>";
		inputForm += "<td>";
		inputForm += ""+index+"";
		inputForm += "</td>";
		inputForm += "<td><input type='text' name='evaluationList["+inputindex+"].eveCont' class='textwidth' style='width: 500px;border: none;'/></td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>①<input type='radio' name='insertA' value='1'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>②<input type='radio' name='insertA' value='2'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>③<input type='radio' name='insertA' value='3'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>④<input type='radio' name='insertA' value='4'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>⑤<input type='radio' name='insertA' value='5'/></label>";
		inputForm += "</td>";
		inputForm += "</tr>";

		$("#qArea").append(inputForm);
		index++;
		inputindex++;
	});
	
	$("#cancleQBTN").on("click", function(){
		var trCnt = $('#qArea tr').length;
		if(trCnt > 1){
			$('#qArea tr:last').remove();
			index--;
			inputindex--;
		}else{
			return false;
		}
	});

	
	$("#selectBox").on("change", function(){
		var content = $("#selectBox option:selected").val();
		if(content != null && content != ""){
			
		var inputForm = "";
		inputForm += "<tr>";
		inputForm += "<td>";
		inputForm += ""+index+"";
		inputForm += "</td>";
		inputForm += "<td><input type='text' value='"+content+"' name='evaluationList["+inputindex+"].eveCont' class='textwidth' style='width: 500px;border: none;' readonly/></td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>①<input type='radio' name='insertA' value='1'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>②<input type='radio' name='insertA' value='2'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>③<input type='radio' name='insertA' value='3'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>④<input type='radio' name='insertA' value='4'/></label>";
		inputForm += "</td>";
		inputForm += "<td class='lab'>";
		inputForm += "<label>⑤<input type='radio' name='insertA' value='5'/></label>";
		inputForm += "</td>";
		inputForm += "</tr>";
		
		$("#qArea").append(inputForm);
		index++;
		inputindex++;
		}
		
	})
</script>