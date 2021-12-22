<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>

<c:set var="memberList" value="${pagingVO.dataList }"/>
<table class="table-size-1300">
	<thead>
		<th>구분</th>
		<th>직급</th>
		<th>이름</th>
		<th>생년월일</th>
		<th>사번</th>
		<th>전공/소속</th>
		<th>퇴사여부</th>
		
		<th>급여</th>
		<th>반영일</th>

	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty memberList }">
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td>
							<c:if test="${member.userCode eq 'US01'}">
							행정직원
							</c:if>
							<c:if test="${member.userCode eq 'US02'}">
							교수
							</c:if>
							<c:if test="${member.userCode eq 'US03'}">
							조교
							</c:if>					
						</td>

						<td>
							<c:if test="${member.rankCode eq 'RANK01'}">
							처장
							</c:if>
							<c:if test="${member.rankCode eq 'RANK02'}">
							부장
							</c:if>
							<c:if test="${member.rankCode eq 'RANK03'}">
							사원
							</c:if>
							<c:if test="${member.rankCode eq 'RANK04'}">
							학과장
							</c:if>
							<c:if test="${member.rankCode eq 'RANK05'}">
							교수
							</c:if>
							<c:if test="${member.rankCode eq 'RANK06'}">
							조교
							</c:if>
							<c:if test="${empty member.rankCode}">
							-
							</c:if>
						</td>
						
						<td>
							<c:url value="/pay/payTableDetail.do" var="viewURL">
								<c:param name="who" value="${member.memId }" />
							</c:url>
							<a href="${viewURL }">${member.memName }</a>
						</td>
						
						<td>${member.memReg1 }</td>
						
						<td>
							<c:if test="${empty member.memEmpno}">
							-
							</c:if>
							<c:if test="${not empty member.memEmpno}">
							${member.memEmpno }
							</c:if>
						</td>
						
						<td>
							<c:if test="${not empty member.memMajor}">
							${member.memMajor }
							</c:if>
							<c:if test="${not empty member.deptCode}">
							${member.deptCode }
							</c:if>
						</td>
						
						<td>${member.memDelete }</td>
						
						
						<c:choose>
							<c:when test="${not empty member.payTableList }">
								<c:forEach items="${member.payTableList }" var="payTable">
		
												<td>${payTable.paytablePayDisplay }</td>
		
												<td>${payTable.paytableDate }</td>
	
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td>-</td>
								<td>-</td>
							</c:otherwise>							
						</c:choose>
						
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="10">검색 조건에 맞는 게시글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="10">
				<div id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
				<div id="searchUI">
					<div>
					퇴사자포함검색<input type="checkbox" name="searchWord2" id="Word2" />
					</div>
					<select name="searchType">
						<option value>전체 분류</option>
						<option value="ADMIN">행정</option>
						<option value="PROP">교수</option>
						<option value="ASSIS">조교</option>
						<option value="STUD">학생</option>
					</select>
					<input type="text" name="searchWord" placeholder="직원 이름"/>					
					<input type="button" value="검색" id="searchBtn"/>
					<%-- <input type="button" value="새글쓰기" class="linkBtn" data-gopage="${cPath }/board/boardInsert.do"/> --%>
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />
</form>	
  <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
  
	<script type="text/javascript">	
		window.onload = function(){
			if($("#Word2").is(":checked")){
				$("#Word2").val("Y");
			}
			else{
				$("#Word2").val('');
			}
		}
	
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}");
	 	$("[name='searchWord2']").val("${searchVO.searchWord2}");

		var check = false;
		
		$("#Word2").change(function(){
			if($("#Word2").is(":checked")){
				$("#Word2").val("Y");
			}
			else{
				$("#Word2").val('');
			}
		});
		
		let searchForm = $("#searchForm").paging();
	</script>
	

</html>