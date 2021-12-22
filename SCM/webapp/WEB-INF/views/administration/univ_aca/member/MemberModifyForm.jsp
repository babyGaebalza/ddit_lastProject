<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>

.user-table-width > .user-tr > td {
	border-right: 1px solid #b6bccd;
}

.user-table {
	border: 1px solid #b6bccd;
}

.user-tr > td {
	padding: 0px 0px 0px 14px;
}

.td-boder > td {
	border: 1px solid #b6bccd;
}

.input-margin {
	margin-bottom : 10px;
}
</style>

<c:set var="who" value="${member.userCode }" />
	<c:if test="${who eq '행정직원'}">
		<c:set var="who" value="u1" /> 
	</c:if>

	<c:if test="${who eq '교수'}"> 
		<c:set var="who" value="u2" /> 				
	</c:if>
	
	<c:if test="${who eq '조교'}"> 
		<c:set var="who" value="u3" /> 							
	</c:if>
	
	<c:if test="${who eq '학생'}"> 
		<c:set var="who" value="u4" /> 							
	</c:if>





<div class="container">
	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<div class="row">
				<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
				<div class="col-lg-7">
					<div class="p-5">
											
						<div class="text-center">
							<c:if test="${who eq 'u1'}"> 
								<h1 class="h4 text-gray-900 mb-4">행정직원 수정</h1>
							</c:if>
							<c:if test="${who eq 'u2'}"> 
								<h1 class="h4 text-gray-900 mb-4">교수 수정</h1>
							</c:if>
							<c:if test="${who eq 'u3'}"> 
								<h1 class="h4 text-gray-900 mb-4">조교 수정</h1>
							</c:if>
							<c:if test="${who eq 'u4'}"> 
								<h1 class="h4 text-gray-900 mb-4">학생 수정</h1>
							</c:if>
						</div>
													
					<form:form class="user" action="${cPath }/member/memberModify.do" method="post" name="member" id="memberForm">

							<input type="text" value="${member.memId }" name="memId" hidden="">
						
							<div class="form-group" hidden>
							코드
									<c:if test="${who eq 'u1'}">
									<input type="text" class="form-control form-control-user" name="userCode" value="US01" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u2'}">
									<input type="text" class="form-control form-control-user" name="userCode" value="US02" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u3'}">
									<input type="text" class="form-control form-control-user" name="userCode" value="US03" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u4'}">
									<input type="text" class="form-control form-control-user" name="userCode" value="US04" readonly="readonly">
									</c:if>
							</div>



							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="text" class="form-control form-control-user" value="${member.memName}" name="memName" id="memName" placeholder="이름" required="required">
								</div>

								<div class="col-sm-6">
									<input type="text" class="form-control form-control-user" value="${member.memEname}" name="memEname" id="memEname" placeholder="영문 이름">
								</div>
							</div>



							<div class="input-group input-margin">
								<div class="input-group-prepend">
									<span class="input-group-text">주민등록번호</span>
								</div>
								<input type="text"   class="form-control"   name="memReg1" id="memReg1" placeholder="주민번호 앞" value="${member.memReg1 }" style="background-color: #f7f7f7;" readonly="readonly">
								<input type="text" class="form-control" name="memReg2" id="memReg2" placeholder="주민번호 뒤" value="${member.memReg2 }" style="background-color: #f7f7f7;" readonly="readonly">
							</div>

							<div style="display: inline-flex;width: 540px;justify-content: space-between;">
								<div class="input-group mb-3" style="width: 240px;">
									<div class="input-group-prepend">
									   <label class="input-group-text" for="inputGroupSelect01">성별</label>
									</div>
									<select class="custom-select" id="memGender" name="memGender" required="required">
										<option value>성별</option>
											<c:if test="${member.memGender eq '남' }">
												<option value="남"  selected="selected">남</option>
												<option value="여" >여</option>
											</c:if>
											<c:if test="${member.memGender eq '여' }">
												<option value="남" >남</option>
												<option value="여"  selected="selected">여</option>
											</c:if>
											
									</select>
								</div>
								
								<div class="input-group mb-3" style="width: 240px;">
									<div class="input-group-prepend">
									   <label class="input-group-text" for="inputGroupSelect01">내/외국인</label>
									</div>
									<select class="custom-select" id="memNation" name="memNation" required="required">
										<option value selected>내국인유무</option>
										<c:if test="${member.memNation eq 'Y' }">
											<option value="Y" selected="selected">내국인</option>
											<option value="N">외국인</option>
										</c:if>
										<c:if test="${member.memNation eq 'N' }">
											<option value="Y">내국인</option>
											<option value="N" selected="selected">외국인</option>
										</c:if>
									</select>
								</div>
							</div>
								
								<div class="input-group flex-nowrap input-margin">
									<span class="input-group-text" id="addon-wrapping">생년월일</span>
									<input type="date" class="form-control" id="memBirth" name="memBirth" placeholder="생년월일" value="${member.memBirth }">
								</div>
													
								<div class="input-group flex-nowrap input-margin">
									<span class="input-group-text" id="addon-wrapping">메일</span>
									<input type="email" class="form-control" id="memMail" name="memMail" placeholder="이메일" value="${member.memMail }">
								</div>
								<div class="input-group flex-nowrap input-margin">
									<span class="input-group-text" id="addon-wrapping">연락처</span>
									<input type="tel" class="form-control phoneNumber" name="memTel" placeholder="전화번호" value="${member.memTel }" required="required">
								</div>
							
							<div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<input class="btn btn-outline-secondary" type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
									</div>
									<input type="text" class="form-control"  id="sample6_address" placeholder="주소" name="memAdd1" value="${member.memAdd1 }" required="required" readonly="readonly" style="background-color: #f7f7f7;" ><br>
								</div>
								<div class="input-group flex-nowrap input-margin">
									<span class="input-group-text" id="addon-wrapping">상세주소</span>
									<input type="text" class="form-control" id="sample6_detailAddress" placeholder="상세주소" name="memAdd2" value="${member.memAdd2 }" required="required">
								</div>	
							</div>
							
								<input type="text" id="sample6_extraAddress" placeholder="참고항목" hidden>	
								<input type="text"id="sample6_postcode" placeholder="우편번호" hidden>
							
							<div style="text-align-last: justify;">
								<c:if test="${who eq 'u1' }">
									<div class="input-group mb-3" style="width:  240px;display: inline-flex;">
										<div class="input-group-prepend">
											<label class="input-group-text" for="inputGroupSelect01">부서</label>
										</div>
										<select class="custom-select" id="deptCode" name="deptCode">
											<option value>부서</option>
											<c:forEach items="${depts }" var="deptCode">
												<c:if test="${member.deptCode eq  deptCode.cateCode}">
													<option selected="selected" value="${deptCode.cateCode }">${deptCode.cateName1 }</option>
												</c:if>
												<option value="${deptCode.cateCode }">${deptCode.cateName1 }</option>
											</c:forEach>
										</select>
									</div>
								</c:if>
								
								<c:if test="${who eq 'u1' or who eq 'u2' or who eq 'u3'}">
								
									<div class="input-group mb-3" style="width:  240px;display: inline-flex;">
										<div class="input-group-prepend">
											<label class="input-group-text" for="inputGroupSelect01">직급</label>
										</div>
										<select  class="custom-select"  id="rankCode" name="rankCode">
												<option value>직급</option>
											<c:forEach items="${ranks }" var="rankAdmin">
												<c:if test="${member.rankCode eq rankAdmin.cateCode}">
													<option selected="selected" value="${rankAdmin.cateCode }">${rankAdmin.cateName1 }</option>
												</c:if>
												<option value="${rankAdmin.cateCode }">${rankAdmin.cateName1 }</option>
											</c:forEach>
										</select>
									</div>
	
									<div class="input-group flex-nowrap">
										<span class="input-group-text input-margin" id="addon-wrapping">입사일</span>
										<input type="date" class="form-control" name="memJoindate" placeholder="입사일" value="${member.memJoindate }" readonly="readonly" style="background-color: #f7f7f7;">
									</div>
									
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<label class="input-group-text" for="inputGroupSelect01">보험적용</label>
										</div>
	
										<select class="custom-select" name="memInsurance">
											<option value>4대보험</option>
											<c:if test="${member.memInsurance eq 'Y' }">
												<option value="Y" selected="selected">적용</option>
												<option value="N">비적용</option>
											</c:if>
											<c:if test="${member.memInsurance eq 'N' }">
												<option value="Y">적용</option>
												<option value="N" selected="selected">비적용</option>
											</c:if>
	
										</select>
									</div>
									
								</c:if>
							</div>
							
							
							
							<c:if test="${who eq 'u4'}">
							
								<div class="input-group flex-nowrap">
									<span class="input-group-text input-margin" id="addon-wrapping">입학일</span>
									<input type="date" class="form-control" id="memJoindate" name="memEntdate" placeholder="입학일" readonly="readonly" style="background-color: #f7f7f7;" >
								</div>
								
								<div class="input-group flex-nowrap">
									<span class="input-group-text input-margin" id="addon-wrapping">비상연락처</span>
									<input type="tel" class="form-control"  id="memSubtel" name="memSubtel" placeholder="보조연락처">
								</div>
							<div style="display: inline-flex;width: 540px;justify-content: space-between;">
								<div class="input-group mb-3" style="width: 240px;">
									<div class="input-group-prepend">
									   <label class="input-group-text" for="inputGroupSelect01">입학전형</label>
									</div>
									<select id="admissionCode"  class="custom-select"  name="admissionCode">
										<option value>입학전형</option>
										<c:forEach items="${admissions }" var="admissionsCode">
										
											<c:if test="${admissionsCode.cateName1 eq member.admissionCode }">
												<option class="admission" value="${admissionsCode.cateCode }" selected="selected">${admissionsCode.cateName1 }</option>
											</c:if>
										
											<option class="admission" value="${admissionsCode.cateCode }">${admissionsCode.cateName1 }</option>
											
										</c:forEach>
									</select>
								</div>

							</c:if>
							
							<c:if test="${who eq 'u3' or who eq 'u2' or who eq 'u4'}">
							
								<div class="input-group mb-3" style="width: 240px;">
									<div class="input-group-prepend">
									   <label class="input-group-text" for="inputGroupSelect01">전공</label>
									</div>
									<select id="memMajor" class="custom-select"  name="memMajor">
										<option value>전공</option>
										<c:forEach items="${major }" var="majorCode">
										
											<c:if test="${majorCode.majorName eq member.memMajor }">
												<option class="major" value="${majorCode.majorCode }" selected="selected">${majorCode.majorName }</option>
											</c:if>
											
												<option class="major" value="${majorCode.majorCode }">${majorCode.majorName }</option>
										</c:forEach>
									</select>
								</div>
								
							</div>
							</c:if>
							
							<c:if test="${order eq 'update' }">
								<input type="submit" class="btn btn-primary btn-user btn-block" value="수정완료" />
								<input type="hidden" value="${page }" name="page" />
								<input type="hidden" value="${searchType2 }" name="searchType2" />
								<input type="hidden" value="${searchWord2 }" name="searchWord2" />
							</c:if>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


	<script type="text/javascript" src="${cPath }/resources/js/memberModiForm.js" />


</html>