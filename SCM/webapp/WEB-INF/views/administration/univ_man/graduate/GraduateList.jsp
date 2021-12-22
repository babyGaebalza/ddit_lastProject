<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />

<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<c:set var="graduateList" value="${pagingVO.dataList }" />

<div class="table-size-1300">
	<h3>졸업자 / 졸업예정자 현황</h3>
	
	<label><input type="radio" name="formType" class="form_1" value="1" checked="checked" />전체</label>
	<label><input type="radio" name="formType" class="form_2" value="2" />졸업예정자</label>
	<label><input type="radio" name="formType" class="form_3" value="3" />졸업자</label>

<div id="form_1" class="form_1">
  <hi>전체 리스트 입니다.</hi>
  <form action="proc.php">
			<table class="table table-bordered tb-txt">
				<thead>
					<tr class="tr-style">
						<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학번</th>
						<th style="border-right: 1px solid #b6bccd;">이름</th>
						<th style="border-right: 1px solid #b6bccd;">생년월일</th>
						<th style="border-right: 1px solid #b6bccd;">성별</th>
						<th style="border-right: 1px solid #b6bccd;">연락처</th>
						<th style="border-right: 1px solid #b6bccd;">주전공</th>
						<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
						<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${not empty graduateList }">
							<c:forEach items="${graduateList }" var="graduate">
								<tr>
									<td>
										<c:url value="/univ_man/graduateView.do" var="viewURL">
											<c:param name="memId" value="${graduate.memId }" />
										</c:url>
										<a href="${viewURL }">${graduate.memId }</a>
									</td>
									<td>${graduate.memName }</td>
									<td>${graduate.memBirth }</td>
									<td>${graduate.memGender }</td>
									<td>${graduate.memTel }</td>
									<td>${graduate.memMajor }</td>
									<td>${graduate.memGradate }</td>
									<td>${graduate.memGraduate }</td>
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
					<td colspan="9">
						<div class="search-align">
							<div id="searchUI" class="search-align">
								<select name="searchType" class="search-align m-right-5">
									<option value>전체</option>
									<option value="id">학번</option>
									<option value="name">이름</option>
								</select> 
								<input type="text" name="searchWord" onkeyup="enterkey();"/> 
								<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
							</div>
						</div>
						<div id="pagingArea" class="p-center m-bottom-10">
							${pagingVO.pagingHTML }
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>


<div id="form_2" class="form_2">
	<hi>졸업 예정자 리스트 입니다.</hi>
	<form action="proc.php">
		<table class="table table-bordered tb-txt">
				<thead>
					<tr class="tr-style">
						<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학번</th>
						<th style="border-right: 1px solid #b6bccd;">이름</th>
						<th style="border-right: 1px solid #b6bccd;">생년월일</th>
						<th style="border-right: 1px solid #b6bccd;">성별</th>
						<th style="border-right: 1px solid #b6bccd;">연락처</th>
						<th style="border-right: 1px solid #b6bccd;">주전공</th>
						<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
						<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${not empty graduateList }">
							<c:forEach items="${graduateList }" var="graduate">
								<c:if test="${graduate.memGraduate eq 'N' }">
								<tr>
									<td>
										<c:url value="/univ_man/graduateView.do" var="viewURL">
											<c:param name="memId" value="${graduate.memId }" />
										</c:url>
										<a href="${viewURL }">${graduate.memId }</a>
									</td>
									<td>${graduate.memName }</td>
									<td>${graduate.memBirth }</td>
									<td>${graduate.memGender }</td>
									<td>${graduate.memTel }</td>
									<td>${graduate.memMajor }</td>
									<td>${graduate.memGradate }</td>
									<td>${graduate.memGraduate }</td>
								</tr>
								</c:if>
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
					<td colspan="9">
						<div class="search-align">
							<div id="searchUI" class="search-align">
								<select name="searchType" class="search-align m-right-5">
									<option value>전체</option>
									<option value="id">학번</option>
									<option value="name">이름</option>
								</select> 
								<input type="text" name="searchWord" onkeyup="enterkey();"/> 
								<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
							</div>
						</div>
						<div id="pagingArea" class="p-center m-bottom-10">
							${pagingVO.pagingHTML }
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>


<div id="form_3" class="form_3">
	<hi>졸업자 리스트 입니다.</hi>
		<form action="proc.php">
			<table class="table table-bordered tb-txt">
				<thead>
					<tr class="tr-style">
						<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학번</th>
						<th style="border-right: 1px solid #b6bccd;">이름</th>
						<th style="border-right: 1px solid #b6bccd;">생년월일</th>
						<th style="border-right: 1px solid #b6bccd;">성별</th>
						<th style="border-right: 1px solid #b6bccd;">연락처</th>
						<th style="border-right: 1px solid #b6bccd;">주전공</th>
						<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
						<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${not empty graduateList }">
							<c:forEach items="${graduateList }" var="graduate">
								<c:if test="${graduate.memGraduate eq 'Y' }">
								<tr>
									<td>
										<c:url value="/univ_man/graduateView.do" var="viewURL">
											<c:param name="memId" value="${graduate.memId }" />
										</c:url>
										<a href="${viewURL }">${graduate.memId }</a>
									</td>
									<td>${graduate.memName }</td>
									<td>${graduate.memBirth }</td>
									<td>${graduate.memGender }</td>
									<td>${graduate.memTel }</td>
									<td>${graduate.memMajor }</td>
									<td>${graduate.memGradate }</td>
									<td>${graduate.memGraduate }</td>
								</tr>
								</c:if>
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
					<td colspan="9">
						<div class="search-align">
							<div id="searchUI" class="search-align">
								<select name="searchType" class="search-align m-right-5">
									<option value>전체</option>
									<option value="id">학번</option>
									<option value="name">이름</option>
								</select> 
								<input type="text" name="searchWord" onkeyup="enterkey();"/> 
								<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
							</div>
						</div>
						<div id="pagingArea" class="p-center m-bottom-10">
							${pagingVO.pagingHTML }
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>


	<form id="searchForm">
		<input type="hidden" name="searchType" />
		<input type="hidden" name="searchWord" />
		<input type="hidden" name="page" />
		<input type="hidden" name="formType" />
	</form>
	
	
	<script type="text/javascript">	
	
	
	$(".form_2").hide();
	$(".form_3").hide();
		$("[name='searchWord']").val("${searchVO.searchWord}");
		$("[name='searchType']").val("${searchVO.searchType}");
// 		$("[name='formType']").val("${searchVO.formType}");
		
		let searchForm = $("#searchForm").paging();
		
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
		
		$(function(){
			$(".form_1").click(function(){
				if($("input[type='radio']").is(':checked')){
					$(".form_1").show();
						$(".form_1").val();
					
					$(".form_2").hide();
					
					$(".form_3").hide();
				}
			});
			
			$(".form_2").click(function(){
				if($("input[type='radio']").is(':checked')){
					$(".form_1").hide();
					
					$(".form_2").show();
						$(".form_2").val("N");
					
					$(".form_3").hide();
				}
			});
			
			$(".form_3").click(function(){
				if($("input[type='radio']").is(':checked')){
					$(".form_1").hide();
					
					$(".form_2").hide();
					
					$(".form_3").show();
						$(".form_3").val("Y");
				}
			});
		})
		
		$(document).ready(function() { 
			
			if($("input[type='radio']").is(':checked')){
				$(".form_1").show();
					$(".form_1").val();
				
				$(".form_2").hide();
				
				$(".form_3").hide();
			}
			
		});
		
	</script>
	
	<jsp:include page="/includee/postScript.jsp" />
