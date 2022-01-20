<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">계정잠금 해제</h1>
                        </div>
                        <form class="user" id="sendCodeUIForm">
							<div class="form-group">
                                <input type="text" class="form-control form-control-user" name="memId"
                                    placeholder="학번/사번 입력" required>
                            </div>
							<div class="form-group">
                                <input type="text" class="form-control form-control-user" name="memName"
                                    placeholder="이름 입력" required>
                            </div>
							<div class="form-group">
                                <input type="text" class="form-control form-control-user" name="memTel"
                                    placeholder="휴대전화번호 입력(ex. 010-1234-5678)" required>
                            </div>
                            
							<hr>
                            <input type="button" class="btn btn-primary btn-user btn-block " value="인증번호 받기" id="sendCodeBtn">
							<hr>
						</form>
						<form:form class="user" action="${pageContext.request.contextPath }/login/auth.do" method="post" id="authForm">
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="text" class="form-control form-control-user" name="authCode"
									placeholder="인증번호 6자리 입력" maxlength="6" required>
							</div>
							<div class="col-sm-6 mb-3 mb-sm-0" id="codeTimerArea">
							</div>
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="button" class="btn btn-primary btn-user btn-block " value="인증" id="authBtn" required/>
							</div>
                        </form:form>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="sendCodeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">알림</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="modalBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<form:form id="sendCodeForm" action="${pageContext.request.contextPath }/login/unlockAccount/sendCode.do" method="post" data-what="unlockAccount" data-send="N">
	<input type="hidden" name="memId"/>
	<input type="hidden" name="memName"/>
	<input type="hidden" name="memTel"/>
</form:form>

<script type="text/javascript" src="${cPath }/resources/js/custom/login/loginPageProcess.js"></script>
