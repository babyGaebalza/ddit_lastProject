<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.table-width-410{
	width : 410px;
}

.table-both{
	display: flex;
	height: 160px;
	font-size: 16px;
	justify-content: space-between;
	margin-bottom: 30px;
}

.user-table-width {
    height: 94px;
    text-align: center;
    border: 1px solid #b6bccd;
    margin-bottom: 30px;
}

.user-tr-width {
    border: 1px solid #b6bccd;
    color: #000;
    text-align: center;
    background-color: #e7e8ef;
}

.user-tr-width > th {
	border: 1px solid #b6bccd;
}

.td-boder > td {
	border: 1px solid #b6bccd;
}


.user-table-width > .user-tr > td {
	border-right: 1px solid #b6bccd;
}

.user-tr > th {
    border-right: 1px solid #b6bccd;
    color: #000;
    text-align: center;
    background-color: #e7e8ef;
}

.user-tr > td {
	padding: 0px 0px 0px 14px;
}

.user-table {
	border: 1px solid #b6bccd;
}

.user-tr {
	border: 1px solid #b6bccd;
}

.height-94 {
	height: 94px;
}
</style>

<div class="table-size-1300" style="padding-top: 15px;">
	<div class="board-title">
	<h5>사용자 세부정보</h5>
	</div>
		<div class="table-both">
			<table class="user-table table-width-410">
					<tr class="user-tr">
						<th>아이디(잠김)</th>
						<td>
							${member.memId }
						</td>
					</tr>
					<tr class="user-tr">
						<th>이름</th>
						<td>${member.memName }</td>
					</tr>
					<tr class="user-tr">
						<th>영문이름</th>
						<td>${member.memEname }</td>
					</tr>
					
			</table>
			
			<table class="user-table table-width-410">
					<tr class="user-tr">
						<th>사용자 분류</th>
						<td>${member.userCode }</td>
					</tr>
					<tr class="user-tr">
						<th>계정상태</th>
						<c:if test="${member.memLoginfailcnt < 3 }">
							<td>(정상)</td>
						</c:if>
						<c:if test="${member.memLoginfailcnt >= 3}">
							<td>(잠김)</td>
						</c:if>
					</tr>
					<tr class="user-tr">
						<th >주민번호</th>
						<td>${member.memReg1 }-${member.memReg2 }</td>
					</tr>


			</table>


			<table class="user-table table-width-410">
					<tr class="user-tr">
						<th>생일</th>
						<td>${member.memBirth }</td>
					</tr>

					<tr class="user-tr">
						<th>성별</th>
						<td>${member.memGender }</td>
					</tr>
					<tr class="user-tr">
						<th>연락처</th>
						<td>${member.memTel }</td>
					</tr>

			</table>
		</div>
		
		
		
			<table class="user-table-width table-size-1300">
				<tr class="user-tr-width">
					<th>주소</th>
					<th>상세주소</th>
					<th>메일</th>
					<th>국적</th>
					<th>사이트탈퇴여부</th>
				<tr>
				<tr class="td-boder">
					<td>${member.memAdd1 }</td>
					<td>${member.memAdd2 }</td>
					<td>${member.memMail }</td>
					<td>${member.memNation }</td>
					<td>${member.memDelete }</td>
				<tr>
			</table>
		
			
			
			<c:if test="${member.userCode eq '행정직원' or member.userCode eq '교수' or member.userCode eq '조교' }">
				<table class="user-table-width table-size-1300">
					<tr class="user-tr-width">
							<th>사번</th>
							<th>직급</th>
							<th>입사일</th>
							<th>퇴사일</th>
							<th>근속기간</th>
							
							<c:if test="${member.userCode eq '행정직원' }">
								<th>부서</th>
							</c:if>
							<th>4대보험</th>	
												
							<c:if test="${member.userCode eq '교수' or member.userCode eq '조교'}" >
								<th>전공</th>
							</c:if>
						
					</tr>
					<tr class="td-boder">
							<td>${member.memEmpno }</td>
							<td>${member.rankCode }</td>
							<td>${member.memJoindate }</td>
							<td>
								<c:if test="${empty member.memResdate}">
									-
								</c:if> 
								<c:if test="${not empty member.memResdate}">
									${member.memResdate }
								</c:if> 
							</td>
							
							<td>
								<c:if test="${empty member.memPeriod}">
									-
								</c:if> 
								<c:if test="${not empty member.memPeriod}">
									${member.memPeriod } 
								</c:if>
							</td>
							
							<c:if test="${member.userCode eq '행정직원' }">
								<td>${member.deptCode }</td>
							</c:if>
	
							<td>${member.memInsurance }</td>
						
							<c:if test="${member.userCode eq '교수' or member.userCode eq '조교'}" >
								<td>
									<c:if test="${empty member.memMajor}">
										-
									</c:if>
									<c:if test="${not empty member.memMajor}">
										${member.memMajor }
									</c:if>
									
								</td>
							</c:if>
						
					</tr>
				</table>
			</c:if>
			
			
			<c:if test="${member.userCode eq '학생' }">
				<table class="user-table-width table-size-1300">
						<tr class="user-tr-width">					
							<th>학번</th>
							<th>입학일</th>
							<th>졸업예정일</th>
							<th>보호자연락처</th>
						</tr>
						<tr class="td-boder">
							<td>${member.memStuno }</td>
							<td>${member.memEntdate }</td>
							<td>${member.memGradate }</td>
							<td>${member.memSubtel }</td>			
						</tr>
				</table>
				
				<table class="user-table-width table-size-1300">
						<tr class="user-tr-width">

							<th>졸업트랙1</th>
							<th>복수전공</th>
							<th>졸업트랙2</th>
							<th>부전공</th>
							<th>졸업트랙3</th>
						</tr>
						<tr class="td-boder">
							<td>${member.memTrack }</td>
							<td>${member.majorDouble }</td>
							<td>${member.memTrack2 }</td>
							<td>${member.majorMinor }</td>
							<td>${member.memTrack3 }</td>
						</tr>
				</table>
						
				<table class="user-table-width table-size-1300">	
						<tr class="user-tr-width">

							<th>졸업여부</th>
							<th>입학전형정보</th>
							<th>지도교수정보</th>
							<th>지도교수2</th>
						</tr>
						<tr class="td-boder">
							<td>${member.memGraduate }</td>
							<td>
								<c:if test="${empty member.admissionCode}">
									-
								</c:if>
								<c:if test="${not empty member.admissionCode}">
									${member.admissionCode }
								</c:if>
							</td>
							<td>
								<c:if test="${empty member.memAdviser}">
									-
								</c:if> 
								<c:if test="${not empty member.memAdviser}">
									${member.memAdviser }
								</c:if> 
							</td>
							<td>
								<c:if test="${empty member.memAdviser2}">
									-
								</c:if> 
								<c:if test="${not empty member.memAdviser2}">
									${member.memAdviser2 }
								</c:if>
							</td>
						</tr>
				</table>
					
				<table class="user-table-width table-size-1300">
						<tr class="user-tr-width">
							<th>학기</th>
							<th>통학여부</th>							
						</tr>
						<tr class="td-boder">							
							<td>
								<c:if test="${empty member.memSemester}">
									-
								</c:if> 
								<c:if test="${not empty member.memSemester}">
									${member.memSemester }
								</c:if> 
							</td>
							<td>
								<c:if test="${empty member.memCommute}">
									-
								</c:if> 
								<c:if test="${not empty member.memCommute}">
									${member.memCommute }
								</c:if>
							</td>							
						</tr>
				</table>
			</c:if>
			
			
				<div class="btn-center">
					<form action="${cPath }/member/modifyForm.do" method="post" id="modifyBtnForm" class="m-right-5">
						<input type="hidden" value="${member.memId }"  name="who">	
						<input type="hidden" value="${page}" name="page" />
						<input type="hidden" value="${searchType2 }" name="searchType2" />
						<input type="hidden" value="${searchWord2 }" name="searchWord2" />				
						<input type="submit" value="정보수정" id="modifyBtn" class="btn btn-secondary"/>
					</form>
					
					<form:form action="${cPath }/member/memberRemove.do" method="post" id="deleteBtnForm">
						<input type="hidden" value="${member.memId }"  name="who">
						<input type="hidden" value="${page}" name="page" />
						<input type="hidden" value="${searchType2 }" name="searchType2" />
						<input type="hidden" value="${searchWord2 }" name="searchWord2" />
						<input type="submit" value="등록해제" id="deleteBtn" class="btn btn-secondary"/>
					</form:form>
				</div>	
	
</div>					

	<script type="text/javascript" src="${cPath }/resources/js/MemberDetail.js" />


