<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<style>
form.user .form-control-user {
    font-size: .8rem;
    border-radius: 10rem;
    padding: 1.5rem 1rem;
    padding-block: 5px;
}
</style>

<c:set var="who" value="${user }" />
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
			<!-- Nested Row within Card Body -->
			<div class="row">
				<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
				<div class="col-lg-7">
					<div class="p-5">
						
						<div class="text-center">
							<c:if test="${who eq 'u1'}"> 
								<h1 class="h4 text-gray-900 mb-4">행정직원 등록</h1>
							</c:if>
							<c:if test="${who eq 'u2'}"> 
								<h1 class="h4 text-gray-900 mb-4">교수 등록</h1>
							</c:if>
							<c:if test="${who eq 'u3'}"> 
								<h1 class="h4 text-gray-900 mb-4">조교 등록</h1>
							</c:if>
							<c:if test="${who eq 'u4'}"> 
								<h1 class="h4 text-gray-900 mb-4">학생 등록</h1>
								<input type="button" value="정보입력" id="InfoBtn"/>
							</c:if>
						</div>
						
						<c:if test="${order eq 'insert' }">
							<c:set var="act" value="${cPath }/member/memberInsert.do"/>
						</c:if>
						<c:if test="${order eq 'update' }">
							<c:set var="act" value="${cPath }/member/memberModify.do"/>
						</c:if>
							
							<form:form class="user" action="${act }" method="post" name="member" id="memberForm">
							<input type="hidden" name="user" value="${user }" />
						
							<div class="form-group">
									<c:if test="${who eq 'u1'}">
									<input type="hidden" class="form-control form-control-user" name="userCode" value="US01" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u2'}">
									<input type="hidden" class="form-control form-control-user" name="userCode" value="US02" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u3'}">
									<input type="hidden" class="form-control form-control-user" name="userCode" value="US03" readonly="readonly">
									</c:if>
									<c:if test="${who eq 'u4'}">
									<input type="hidden" class="form-control form-control-user" name="userCode" value="US04" readonly="readonly">
									</c:if>
							</div>
								
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="text" class="form-control form-control-user" name="memName" id="memName" placeholder="이름" required="required">
								</div>
								
								<div class="col-sm-6">
									<input type="text" class="form-control form-control-user" name="memEname" id="memEname" placeholder="영문 이름">
								</div>
							</div>
													
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="number" class="form-control form-control-user regNo" name="memReg1" id="memReg1" placeholder="주민번호 앞" required="required" maxlength="6" oninput="numberMaxLength(this);" />
									<form:errors path="memReg1" element="span" cssClass="error"/>
								</div>
								
								<div class="col-sm-6">
									<input type="number" class="form-control form-control-user regNo" name="memReg2" id="memReg2" placeholder="주민번호 뒤" required="required" maxlength="7" oninput="numberMaxLength(this);" />
									<form:errors path="memReg2" element="span" cssClass="error"/>
								</div>								
							</div>
							
							<!-- class="form-control form-control-user" -->
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<div  class="form-control form-control-user">
										<select  id="memGender" name="memGender" required="required" style="border: none;width: 220px;">
											<option value selected>성별</option>
											<option value="남" >남</option>
											<option value="여" >여</option>
										</select>
									</div>
								</div>
								
								<div class="col-sm-6 mb-3 mb-sm-0">
									<div  class="form-control form-control-user" >
										<select  id="memNation" name="memNation" required="required" style="border: none;width: 220px;">
											<option value selected>내국인유무</option>
											<option value="Y">내국인</option>
											<option value="N">외국인</option>
										</select>
									</div>
								</div>
							</div>
								
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">	
									<div class="form-group">
										<div class="form-control form-control-user" >생년월일<input type="date" id="memBirth" name="memBirth" required="required" placeholder="생년월일" style="border:  none;padding-left: 30px;" ></div>
									</div>
								</div>

								<div class="col-sm-6 mb-3 mb-sm-0">	
									<div class="form-group">
										<input type="email" class="form-control form-control-user" id="memMail" name="memMail" required="required" placeholder="이메일">
									</div>
								</div>
							</div>
									
							<div class="form-group">
								<input type="tel" class="form-control form-control-user phoneNumber" id="memTel" name="memTel" placeholder="전화번호" required="required">
							</div>
							
							
							<div class="form-group">
							<input type="text" id="sample6_postcode" placeholder="우편번호" hidden>
							<div class="form-control form-control-user" style="margin-bottom: 15px;">
								<input type="text"  id="sample6_address" placeholder="주소" name="memAdd1" required="required" style="border:  none;width: 360px;">
								<input type="button" class="btn btn-light btn-icon-split" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="height: 29px;margin-top: -5px;">
							</div>
							<input type="text"  class="form-control form-control-user" id="sample6_detailAddress" placeholder="상세주소" name="memAdd2" required="required" >
							<input type="text" id="sample6_extraAddress" placeholder="참고항목" hidden>	
							</div>
							
							
							<c:if test="${who eq 'u1' }">
								<div class="form-group">
									<div class="form-control form-control-user">
										<select id="deptCode" name="deptCode" required="required" style="border: none;width: 220px;">
											<option value>부서</option>
											<c:forEach items="${depts }" var="deptCode">
												<option value="${deptCode.cateCode }">${deptCode.cateName1 }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</c:if>
							
							
							
							<c:if test="${who eq 'u1' or who eq 'u2' or who eq 'u3'}">
							
								<div class="form-group">
									<div class="form-control form-control-user">
										<select  id="rankCode" name="rankCode" required="required" style="border: none;width: 220px;">
												<option value>직급</option>
											<c:forEach items="${ranks }" var="rankAdmin">
												<option value="${rankAdmin.cateCode }">${rankAdmin.cateName1 }</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group">
									<div class="form-control form-control-user" >입사일<input type="date" id="memJoindate" name="memJoindate" placeholder="입사일" required="required" style="border:  none;padding-left: 35px;"></div>
								</div>
								
								<div class="form-group">
									<div class="form-control form-control-user">
										<select name="memInsurance" style="border: none;width: 220px;">
											<option value>4대보험</option>
											<option value="Y">적용</option>
											<option value="N">비적용</option>
										</select>
									</div>
								</div>
																
							</c:if>
							
							
							
							
							<c:if test="${who eq 'u4'}">
							
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<div class="form-group">
										<div class="form-control form-control-user">입학일<input type="date"  id="memJoindate" name="memEntdate" placeholder="입학일" required="required" style="border:  none;padding-left: 35px;"></div>
									</div>
								</div>
								
								<div class="col-sm-6 mb-3 mb-sm-0">
									<div class="form-group">
										<input type="tel" class="form-control form-control-user phoneNumber" id="memSubtel" name="memSubtel" placeholder="보조연락처">
									</div>
								</div>
							</div>
								
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<div class="form-group">
											<div class="form-control form-control-user">
												<select id="admissionCode" name="admissionCode" required="required" style="border: none;width: 220px;">
													<option value>입학전형</option>
													<c:forEach items="${admissions }" var="admissionsCode">
														<option value="${admissionsCode.cateCode }">${admissionsCode.cateName1 }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-sm-6 mb-3 mb-sm-0">
										<div class="form-group">
											<div class="form-control form-control-user">
												<select id="majorCode" name="majorCode"
													style="border: none; width: 220px;">
													<option value>전공</option>
													<c:forEach items="${major }" var="majorCode">
														<option value="${majorCode.majorCode }">${majorCode.majorName }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>

							</c:if>
							
							<c:if test="${who eq 'u3' or who eq 'u2'}">
							
								<div class="form-group">
									<div class="form-control form-control-user">
										<select id="majorCode" name="majorCode" style="border: none;width: 220px;">
											<option value>전공</option>
											<c:forEach items="${major }" var="majorCode">
												<option value="${majorCode.majorCode }">${majorCode.majorName }</option>
											</c:forEach>
										</select>
									</div>
								</div>

							</c:if>
							
							
							<div class="form-group row">
							
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="password" class="form-control form-control-user" name="memPass" id="memPass" placeholder="비밀번호" required="required">
									<form:errors path="memPass" element="span" cssClass="error"/>
								</div>
								
								<div class="col-sm-6">
									<input type="password" class="form-control form-control-user" name="memPassRe" id="memPassRe" placeholder="비밀번호 확인" required="required">
								</div>
								
							</div>
							
							<c:if test="${order eq 'insert' }">
								<input type="submit" class="btn btn-primary btn-user btn-block" value="등록완료" />
							</c:if> 
							<c:if test="${order eq 'update' }">
								<input type="submit" class="btn btn-primary btn-user btn-block" value="수정완료" />
							</c:if>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


	<script type="text/javascript" src="${cPath }/resources/js/memberForm.js" />
<<<<<<< .mine
	<script type="text/javascript">
		function setProfile(event){
			var reader = new FileReader();
			
			reader.onload = function(event){
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				$(document.querySelector("div#image_container")).html(img);
			};
			
			reader.readAsDataURL(event.target.files[0]);
		}
		
		let changeImage = $("#changeImage").on("click", function() {
			let memImageTag = $(":input[name='memImage']").trigger("click");
		})
	</script>
||||||| .r205288
=======
<script>
>>>>>>> .r205540

</script>

</html>