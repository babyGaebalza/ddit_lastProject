<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.lab {
	width : 137px;
	text-align: center;
	border : none;
}

.context {
	border: none; 
	width: 500px;
}

.num {
	width : 15px;
	border: none; 
	text-align : center;
}
.headColor {
	background-color: aliceblue;
}
.borderNone {
	border : none;
	width: 450px;
}
.numInput{
	width: 15px;
	border : none;
}
</style>

<div class="table-size-1300">
	<h3 style="padding-top: 30px;">강의평가 페이지</h3>
	<div>${classNo }</div>
		<form:form action="${cPath }/common/evaluation/stuInput.do" method="post" modelAttribute="EvaluationListVO">
			<table class="table table-bordered" id="qArea">
				<tr>
					<th class="num headColor" style="width: 60px;">번호</th>
					<th class="headColor">질문내용</th>
					<th class='lab headColor'>전혀그렇지않다</th>
					<th class='lab headColor'>그렇지않다</th>
					<th class='lab headColor'>보통이다</th>
					<th class='lab headColor'>그렇다</th>
					<th class='lab headColor'>매우그렇다</th>
				</tr>
				<c:forEach items="${evalContent}" var="content" varStatus="stat">
				<tr>
					<td class="num"><input class="numInput" type="text" value="${content.evaconNo }" name="evaluationList[${stat.index }].eveconNo" readonly="readonly"/></td>
					<td class="context"><input class="borderNone" type="text" name="evaluationList[${stat.index }].eveCont" value="${content.evaCont }"/></td>
					<td class='lab'><label>①<input type='radio' name='evaluationList[${stat.index }].evePoint' value='1'/></label></td>
					<td class='lab'><label>②<input type='radio' name='evaluationList[${stat.index }].evePoint' value='2'/></label></td>
					<td class='lab'><label>③<input type='radio' name='evaluationList[${stat.index }].evePoint' value='3'/></label></td>
					<td class='lab'><label>④<input type='radio' name='evaluationList[${stat.index }].evePoint' value='4'/></label></td>
					<td class='lab'><label>⑤<input type='radio' name='evaluationList[${stat.index }].evePoint' value='5' checked="checked"/></label></td>
				</tr>
				</c:forEach>
			</table>
			
			<table  class="table table-bordered" >
				<tr>
					<th>건의사항 및 기타사항 (*필수입력항목입니다.)</th>
				</tr>
				<tr>
					<td style="height: 100px;"><input style="height: 80px;width: 1270px;border: none;" type="text" placeholder="필수입력항목입니다." required="required" name="evaluationList[0].classCont"/></td>
				</tr>
			</table>			
			<input class="btn btn-outline-warning" style="float: right;" type="submit" value="제출"/>
		</form:form>
			
			
</div>		
