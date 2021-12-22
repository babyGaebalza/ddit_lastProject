<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<c:set var="memberList" value="${pagingVO.dataList }"/>
<div class="table-size-1300">
<h3>급여테이블 및 신청현황</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="tr-style ">
				<th style="width: 80px;">
				선택
				<input type="checkbox" class="payCheckAll" name="payCheckAll" id="payCheckAll" />
				</th>
				
				<th style="width: 100px;">구분</th>
				<th style="width: 75px;">직급</th>
				<th style="width: 150px;">이름</th>
				<th style="width: 110px;">생년월일</th>
				
				<th >사번</th>
				<th style="width: 170px;">전공/소속</th>
	
				
				<th>연봉</th>
				<th style="width: 110px;">급여반영일</th>
				<th style="width: 110px;">최근신청일</th>
				<th style="width: 95px;">처리여부</th>
				<th style="width: 125px;">비고</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty memberList }">
					<c:forEach items="${memberList }" var="member">
						<tr>
							<td>
								<input type="checkbox" class="payCheck" name="payCheck" value="${member.memId }" id="checkbox"/>
								<a id="checkMessage" style="display: none"></a>
								<input type="button" id="reCheck" class="reCheck" value="" style="display: none" />
								<input type="text" id="reCheckMessage" value="${member.memId }" style="display: none">
							</td>
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
							
							<td><!-- 이름 -->
								<c:url value="/pay/payTableDetail.do" var="viewURL">
									<c:param name="who" value="${member.memId }" />
								</c:url>
								<a href="${viewURL }">${member.memName }</a>
							</td>
							
							<td>${member.memReg1 }</td><!-- 생년월일 -->
							
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
						
							<c:choose>
								<c:when test="${not empty member.payTableList }">
									<c:forEach items="${member.payTableList }" var="payTable">
										
										<td>${payTable.paytablePayDisplay }</td>	<!-- 연봉 -->
			
										<td><a id="paytableDate">${payTable.paytableDate }</a></td>	<!-- 급여반영일 -->
														
									</c:forEach>
								</c:when>
								<c:otherwise>
										<td>-</td>
										<td>-</td>
								</c:otherwise>		
							</c:choose>
							
							
							
							
							
							<c:choose>
								<c:when test="${not empty member.payList }">						
									<c:forEach items="${member.payList }" var="pay">									
												<td><a id="requestPayDate">${pay.payDate }</a></td>	<!-- 최근신청일 -->
												<td><a id="state">${pay.payState }</a></td>	<!-- 처리여부 -->
												
												<c:if test="${not empty pay.payReason }">	<!-- 비고 -->
													<td><a id="reason">${pay.payReason }</a></td>																							
												</c:if>
												<c:if test="${empty pay.payReason }">
													<td>-</td>																							
												</c:if>
									</c:forEach>
								</c:when>

							</c:choose>
							
							
							
							
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="11">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="12">
				<div class="search-align">
					<div id="searchUI">
						<select name="searchType" style="height: 34px;">
							<option value>전체 분류</option>
							<option value="ADMIN">행정</option>
							<option value="PROP">교수</option>
							<option value="ASSIS">조교</option>
							<option value="STUD">학생</option>
						</select>
						<input type="text" name="searchWord" placeholder="직원 이름" style="height: 34px;"/>					
						<input type="button" class="linkBtn btn btn-secondary" value="검색" id="searchBtn"/>
					</div>
					
					<div>
						<input type="button" class="linkBtn btn btn-secondary" value="급여입금신청" id="inputPayBtn"/>
						<input type="button" class="linkBtn btn btn-secondary" value="입금신청조회" id="inputPayListBtn"/>
					</div>
				</div>
				
					<div id="pagingArea" class="p-center m-bottom-10">
						${pagingVO.pagingHTML }
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="searchWord2" />
	<input type="hidden" name="page" />
</form>	
  <script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
  
	<script type="text/javascript">	
		window.onload = function(){
			var paytableDate = new Date();
			var requestPayDate = new Date($("#requestPayDate").text());
			var timeterm = ((paytableDate-requestPayDate)/1000/60/60/24);
			var state = $("#state").text();
 
			if(timeterm < 25 && state != "반려"){
				$("#checkbox").remove();	
				$("#checkMessage").text("불가");
				$("#checkMessage").attr('style', 'display: inline');
			}
			if(state == "반려"){
				$("#checkbox").remove();	
				$("#reCheck").val("재신청");
				$("#reCheck").attr('style', 'display: inline');
			}

			
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
		
		var checkArr = [];
		
		$("#payCheckAll").click(function(){
			checkArr=[];
			if($("#payCheckAll").prop("checked")){
				$(".payCheck").prop("checked", true);
			}
			else{
				$(".payCheck").prop("checked", false);
			}
		});
		
		$(".payCheck").click(function(){
			checkArr=[];
			if($("input[name='payCheck']:checked")){
				$("#payCheckAll").prop("checked", false);
			}
			else {
				$("#payCheckAll").prop("checked", true);
			}
			
		})
		
		$("#inputPayBtn").on("click", function(){
			$("input[name=payCheck]:checked").each(function() {
				checkArr.push($(this).val()); 
			})
			
			$.ajax({
				url : $.getContextPath() + "/pay/payInsert.do",
				data : {
					checkArr : checkArr
				},
				type : "POST",
				dataType : "json",
				success : function(resultMap) {					
					$.confirm({
						title : '처리결과',
						content :	"성공 : "+resultMap.ok  +"<br>"+
									"임금미정 : "+resultMap.no +"<br>"+
									"실패: "+resultMap.fail,
						buttons : {
								확인 : {
									btnClass : 'btn-blue',
									action : function(){
										location.reload();
									}
								}
						}
					});
				},
				error : function(xhr, errorResp, errorMessage) {
					console.log(xhr);
					console.log(errorResp);
					console.log(errorMessage);
				}
			});
			
			checkArr=[];
		});
		
		$("#inputPayListBtn").on("click", function(){
			location.href="${cPath}/pay/inputPayList.do";
		})
		
		$("#reCheck").on("click", function(){
		var reason = $("#reason").text();
		
			$.confirm({
				title : '재신청',
				content : '재신청하시겠습니까? 사유:'+reason,
				buttons : {
						확인 : {
							action : function(){
								checkArr = [];
								reCheckMessage = $("#reCheckMessage").val();
								checkArr.push(reCheckMessage);
								
								$.ajax({
									url : $.getContextPath() + "/pay/payReInsert.do",
									data : {
										checkArr : checkArr
									},
									type : "POST",
									dataType : "json",
									success : function(resultMap) {					
										$.confirm({
											title : '처리결과',
											content :	"성공 : "+resultMap.ok  +"<br>"+
														"임금미정 : "+resultMap.no +"<br>"+
														"실패: "+resultMap.fail,
											buttons : {
													확인 : {
														btnClass : 'btn-blue',
														action : function(){
															location.reload();
														}
													}
											}
										})
									},
									error : function(xhr, errorResp, errorMessage) {
										console.log(xhr);
										console.log(errorResp);
										console.log(errorMessage);
									}
								})
								
							}
						},
						돌아가기 : {
							action : function(){
								return;
							}
						}
				}
			});
			checkArr = [];
		});


	</script>
	

</html>