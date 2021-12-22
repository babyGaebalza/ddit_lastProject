<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />

<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<c:set var="graduateList" value="${pagingVO.dataList }" />

<div class="table-size-1300">
	<h3>졸업자 / 졸업예정자 현황</h3>
	<!-- 
	<div class="float-right m-bottom-10">
		<input type="radio" name="test" value="1" id="total" checked onclick="div_OnOff(this.value,'total');" > 전체
		<input type="radio" name="test" value="2" id="graduating" onclick="div_OnOff(this.value,'graduating');" > 졸업예정자
		<input type="radio" name="test" value="3" id="graduated" onclick="div_OnOff(this.value,'graduated');" > 졸업자
	</div>
	-->
	
<!-- 	<table class="table table-bordered tb-txt"> -->
<!-- 		<thead> -->
<!-- 			<tr class="tr-style"> -->
<!-- 				<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학번</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">이름</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">영어이름</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">생년월일</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">성별</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">연락처</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">주전공</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">지도교수</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">졸업예정일</th> -->
<!-- 				<th style="border-right: 1px solid #b6bccd;">졸업여부</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<!-- 		<div id="grad_total" style="display:none" > -->
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${not empty graduateList }"> --%>
<%-- 					<c:forEach items="${graduateList }" var="graduate"> --%>
<!-- 						<tr> -->
<!-- 							<td> -->
<%-- 								<c:url value="/univ_man/graduateView.do" var="viewURL"> --%>
<%-- 									<c:param name="memId" value="${graduate.memId }" /> --%>
<%-- 								</c:url> --%>
<%-- 								<a href="${viewURL }">${graduate.memId }</a> --%>
<!-- 							</td> -->
<%-- 							<td>${graduate.memName }</td> --%>
<%-- 							<td>${graduate.memEname }</td> --%>
<%-- 							<td>${graduate.memBirth }</td> --%>
<%-- 							<td>${graduate.memGender }</td> --%>
<%-- 							<td>${graduate.memTel }</td> --%>
<%-- 							<td>${graduate.memMajor }</td> --%>
<%-- 							<td>${graduate.memAdviser }</td> --%>
<%-- 							<td>${graduate.memGradate }</td> --%>
<%-- 							<td>${graduate.memGraduate }</td> --%>
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 					<tr> -->
<!-- 						<td colspan="10">검색 조건에 맞는 게시글이 없음.</td> -->
<!-- 					</tr> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
<!-- 		</div> -->
<!-- 		<div id="grad_graduating" style="display:none" > -->
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${not empty graduateList }"> --%>
<%-- 					<c:forEach items="${graduateList }" var="graduate"> --%>
<!-- 						<tr> -->
<!-- 							<td> -->
<%-- 								<c:url value="/univ_man/graduateView.do" var="viewURL"> --%>
<%-- 									<c:param name="memId" value="${graduate.memId }" /> --%>
<%-- 								</c:url> --%>
<%-- 								<a href="${viewURL }">${graduate.memId }</a> --%>
<!-- 							</td> -->
<%-- 							<td>${graduate.memName }</td> --%>
<%-- 							<td>${graduate.memEname }</td> --%>
<%-- 							<td>${graduate.memBirth }</td> --%>
<%-- 							<td>${graduate.memGender }</td> --%>
<%-- 							<td>${graduate.memTel }</td> --%>
<%-- 							<td>${graduate.memMajor }</td> --%>
<%-- 							<td>${graduate.memAdviser }</td> --%>
<%-- 							<td>${graduate.memGradate }</td> --%>
<%-- 							<td>${graduate.memGraduate }</td> --%>
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 					<tr> -->
<!-- 						<td colspan="10">검색 조건에 맞는 게시글이 없음.</td> -->
<!-- 					</tr> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
<!-- 		</div> -->
<!-- 		<div id="grad_graduated" style="display:none" > -->
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${not empty graduateList }"> --%>
<%-- 					<c:forEach items="${graduateList }" var="graduate"> --%>
<!-- 						<tr> -->
<!-- 							<td> -->
<%-- 								<c:url value="/univ_man/graduateView.do" var="viewURL"> --%>
<%-- 									<c:param name="memId" value="${graduate.memId }" /> --%>
<%-- 								</c:url> --%>
<%-- 								<a href="${viewURL }">${graduate.memId }</a> --%>
<!-- 							</td> -->
<%-- 							<td>${graduate.memName }</td> --%>
<%-- 							<td>${graduate.memEname }</td> --%>
<%-- 							<td>${graduate.memBirth }</td> --%>
<%-- 							<td>${graduate.memGender }</td> --%>
<%-- 							<td>${graduate.memTel }</td> --%>
<%-- 							<td>${graduate.memMajor }</td> --%>
<%-- 							<td>${graduate.memAdviser }</td> --%>
<%-- 							<td>${graduate.memGradate }</td> --%>
<%-- 							<td>${graduate.memGraduate }</td> --%>
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 					<tr> -->
<!-- 						<td colspan="10">검색 조건에 맞는 게시글이 없음.</td> -->
<!-- 					</tr> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
<!-- 		</div> -->
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- 	<div class="search-align"> -->
<!-- 						<div id="searchUI" class="search-align"> -->
<!-- 							<select name="searchType" class="search-align m-right-5"> -->
<!-- 								<option value>전체</option> -->
<!-- 								<option value="id">학번</option> -->
<!-- 								<option value="name">이름</option> -->
<!-- 								<option value="graduate">졸업여부</option> -->
<!-- 							</select>  -->
<!-- 							<input type="text" name="searchWord" onkeyup="enterkey();"/>  -->
<!-- 							<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" />  -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 					<div id="pagingArea" class="p-center m-bottom-10"> -->
<%-- 						${pagingVO.pagingHTML } --%>
<!-- 					</div> -->
<!-- 	<form id="searchForm"> -->
<!-- 		<input type="hidden" name="searchType" /> -->
<!-- 		<input type="hidden" name="searchWord" /> -->
<!-- 		<input type="hidden" name="page" /> -->
<!-- 	</form> -->
<!-- 	<script type="text/javascript">	 -->
<%--  		$("[name='searchWord']").val("${searchVO.searchWord}"); --%>
<%--  		$("[name='searchType']").val("${searchVO.searchType}") --%>
<!-- 		let searchForm = $("#searchForm").paging(); -->
<!-- 	</script> -->
<%-- 	<jsp:include page="/includee/postScript.jsp" /> --%>
	
	<!-- 
	<script>
		function div_OnOff(List){
		 // 라디오 버튼 value 값 조건 비교
			var total = document.getElementById("grad_total");
			var graduating = document.getElementById("grad_graduating");
			var graduated = document.getElementById("grad_graduated");
			
			if(List == "1"){
				total.style.display = "block";
				graduating.style.display = "none";
				graduated.style.display = "none";
			} else if(List == "2"){
				total.style.display = "none";
				graduating.style.display = "block";
				graduated.style.display = "none";
			} else if(List == "3"){
				total.style.display = "none";
				graduating.style.display = "none";
				graduated.style.display = "block";
			} else {
				total.style.display = "none";
				graduating.style.display = "none";
				graduated.style.display = "none";
			}
		}
		
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	-->
	
	<script type="text/javascript">
		function enterkey(){
	         if(window.event.keyCode == 13){
	            document.getElementById("searchBtn").click();
	         }
	      }
	</script>
	
	<script>
		function chooseForm(radioName) {
		  var radios = document.getElementsByName(radioName);
		  for (var i = 0, length = radios.length; i < length; i++) {
		    document.getElementById('form_' + radios[i].value).style.display = 'none';
		    if (radios[i].checked) {
		      document.getElementById('form_' + radios[i].value).style.display = 'block';
		    }
		  }
		}
	</script>

<label><input type="radio" name="formType" value="1" checked onclick="chooseForm(this.name)" />전체</label>
<label><input type="radio" name="formType" value="2" onclick="chooseForm(this.name)" />졸업예정자</label>
<label><input type="radio" name="formType" value="3" onclick="chooseForm(this.name)" />졸업자</label>

<div id="form_1">
  <hi>Form 1</hi>
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
						<th style="border-right: 1px solid #b6bccd;">지도교수</th>
						<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
						<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
					</tr>
				</thead>
				<tbody>
				<div id="grad_total" >
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
									<td>${graduate.memAdviser }</td>
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
				</div>
			</tbody>
		</table>
		<div class="search-align">
			<div id="searchUI" class="search-align">
				<select name="searchType" class="search-align m-right-5">
					<option value>전체</option>
					<option value="id">학번</option>
					<option value="name">이름</option>
					<option value="graduate">졸업여부</option>
				</select> 
				<input type="text" name="searchWord" onkeyup="enterkey();"/> 
				<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
			</div>
		</div>
		
		<div id="pagingArea" class="p-center m-bottom-10">
			${pagingVO.pagingHTML }
		</div>
			<form id="searchForm">
				<input type="hidden" name="searchType" />
				<input type="hidden" name="searchWord" />
				<input type="hidden" name="page" />
			</form>
			<script type="text/javascript">	
				$("[name='searchWord']").val("${searchVO.searchWord}");
				$("[name='searchType']").val("${searchVO.searchType}")
				let searchForm = $("#searchForm").paging();
			</script>
			<jsp:include page="/includee/postScript.jsp" />
		</form>
		</div>


<div id="form_2">
	<hi>Form 2asd</hi>
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
					<th style="border-right: 1px solid #b6bccd;">지도교수</th>
					<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
					<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
				</tr>
			</thead>
			<tbody>
			<div id="grad_total">
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
								<td>${graduate.memAdviser }</td>
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
			</div>
			</tbody>
		</table>
		<div class="search-align">
			<div id="searchUI" class="search-align">
				<select name="searchType" class="search-align m-right-5">
					<option value>전체</option>
					<option value="id">학번</option>
					<option value="name">이름</option>
					<option value="graduate">졸업여부</option>
				</select> 
				<input type="text" name="searchWord" onkeyup="enterkey();"/> 
				<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
			</div>
		</div>
		
		<div id="pagingArea" class="p-center m-bottom-10">
			${pagingVO.pagingHTML }
		</div>
		<form id="searchForm">
			<input type="hidden" name="searchType" />
			<input type="hidden" name="searchWord" />
			<input type="hidden" name="page" />
		</form>
		<script type="text/javascript">	
			$("[name='searchWord']").val("${searchVO.searchWord}");
			$("[name='searchType']").val("${searchVO.searchType}")
			let searchForm = $("#searchForm").paging();
		</script>
	<jsp:include page="/includee/postScript.jsp" />
	</form>
</div>


<div id="form_3">
	<hi>Form 3</hi>
		<form action="proc.php">
			<table class="table table-bordered tb-txt">
				<thead>
					<tr class="tr-style">
						<th style="border-right: 1px solid #b6bccd; border-left: 1px solid #b6bccd;">학번</th>
						<th style="border-right: 1px solid #b6bccd;">이름</th>
						<th style="border-right: 1px solid #b6bccd;">영어이름</th>
						<th style="border-right: 1px solid #b6bccd;">생년월일</th>
						<th style="border-right: 1px solid #b6bccd;">성별</th>
						<th style="border-right: 1px solid #b6bccd;">연락처</th>
						<th style="border-right: 1px solid #b6bccd;">주전공</th>
						<th style="border-right: 1px solid #b6bccd;">지도교수</th>
						<th style="border-right: 1px solid #b6bccd;">졸업예정일</th>
						<th style="border-right: 1px solid #b6bccd;">졸업여부</th>
					</tr>
				</thead>
				<tbody>
					<div id="grad_total">
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
									<td>${graduate.memEname }</td>
									<td>${graduate.memBirth }</td>
									<td>${graduate.memGender }</td>
									<td>${graduate.memTel }</td>
									<td>${graduate.memMajor }</td>
									<td>${graduate.memAdviser }</td>
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
				</div>
			</tbody>
		</table>
		<div class="search-align">
			<div id="searchUI" class="search-align">
				<select name="searchType" class="search-align m-right-5">
					<option value>전체</option>
					<option value="id">학번</option>
					<option value="name">이름</option>
					<option value="graduate">졸업여부</option>
				</select> 
				<input type="text" name="searchWord" onkeyup="enterkey();"/> 
				<input type="button" value="검색" id="searchBtn" class="linkBtn btn btn-secondary btn-sm m-left-5" /> 
			</div>
		</div>
		
		<div id="pagingArea" class="p-center m-bottom-10">
			${pagingVO.pagingHTML }
		</div>
		<form id="searchForm">
			<input type="hidden" name="searchType" />
			<input type="hidden" name="searchWord" />
			<input type="hidden" name="page" />
		</form>
		<script type="text/javascript">	
			$("[name='searchWord']").val("${searchVO.searchWord}");
			$("[name='searchType']").val("${searchVO.searchType}")
			let searchForm = $("#searchForm").paging();
		</script>
		<jsp:include page="/includee/postScript.jsp" />
	</form>
</div>

