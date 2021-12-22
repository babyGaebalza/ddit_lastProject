<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.hColor {
	background-color: whitesmoke
}
</style>

<c:set var="totalCount" value="${eveTotalCount }"/>
<c:set var="pointlist" value="${pointList }" />

<input type="hidden" value="${totalCount }" id="count" />

<div class="table-size-1300">
	<div>
		<h3 style="padding-top: 30px;">강의평가 결과 조회화면</h3>
		<table >
			<tr>
				<th style="width: 60px;">강의명  : </th>
				<td><input type="text" value="${className }" readonly="readonly" style="border: none;width: 300px;" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<th style="width: 115px;">평가 참여인원 : </th>
				<td style="width: 100px;"><div style="width: 40px;" >${totalCount }명</div></td>
			</tr>
		</table>
		<table class="table table-bordered" id="qArea">
				<tr>
					<th class="hColor" style="width: 60px;">번호</th>
					<th class="ti hColor">질문내용</th>
					<th class="ans hColor">평균점수</th>
				</tr>
				<c:forEach items="${evaResultList }" var="evaresultList" varStatus="stat">
				<tr>
					<td>${evaresultList.eveconNo }</td>
					<td>${evaresultList.eveCont }</td>
					<td>${pointlist[stat.index] }</td>
				</tr>
				</c:forEach>
			</table>
			<table class="table table-bordered" id="qArea">
				<tr>
					<th class="hColor">평가내용 리스트</th>
				</tr>
				<c:if test="${empty evaContList}">
					<tr>
						<td>아직 평가한 학생이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty evaContList}">
					<c:forEach items="${evaContList}" var="evaContlist">
						<tr>
							<td>${evaContlist }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
	</div>
</div>

<script>
	var Count = document.getElementById("count").value;
	
	window.onload = function(){
		if(Count == 0){
			$.alert({
				title: "확인",
				content : "아직 평가한 학생이 없습니다."
			});
		}		
	};
	

</script>