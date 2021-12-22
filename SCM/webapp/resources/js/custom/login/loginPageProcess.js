const sendCodeForm = $("#sendCodeForm");
	const codeTimerArea = $("#codeTimerArea");
	const authCode = $(":input[name='authCode']");
	// 인증만료시간 정의
	const setTime = 30;
	// 인증만료시간
	let restTime;
	// 인증만료 인터벌 저장용 변수
	let codeTimer;
	
	function timeout(){
		clearInterval(codeTimer);
		codeTimerArea.empty();
		sendCodeForm.data("send", "N")
	}
	
	let kindOfSendCode = sendCodeForm.data("what").toUpperCase();
	
	// 인증 코드 타이머/ 삭제처리 프로세스
	function timer(){
		let minute = Math.trunc(restTime / 60);
		let second = restTime % 60;
		codeTimerArea.text(minute + ":" + second);
		
		// 시간이 만료되면
		if(restTime <= 0){
			// 타이머 정지
			timeout();
			// 인증코드 삭제용 비동기요청
			$.ajax({
				url : $.getContextPath() + "/login/removeCode.do",
				data : {
					"kindOfSendCode" : kindOfSendCode
				},
				dataType : "json",
				success : function(resp) {
					
				},
				error : function(xhr, errorResp, errorMessage) {
					console.log(xhr);
					console.log(errorResp);
					console.log(errorMessage);
				}
			});
		}
		--restTime;
	}
	
	let sendCodeModal = $("#sendCodeModal");
	let modalBody = $("#modalBody");
	
	// 인증코드 보내는 프로세스
	const sendCodeUIForm = $("#sendCodeUIForm").on("click", "#sendCodeBtn", function(event) {
		event.preventDefault();
		
		// 히든폼으로 데이터 옮기기(form의 속성 중 class='user'에 지정된 css 사용하기 위함)
		sendCodeUIForm.find(":input[name]").each(function() {
			let name = this.name;
			let value = $(this).val();
			let form = sendCodeForm.get(0);
			form[name].value = value.trim();
		});
		
		// 인증코드 보내는 비동기 요청
		sendCodeForm.ajaxForm({
			data : {
				"kindOfSendCode" : kindOfSendCode
			},
			dataType : "json",
			success : function(resp) {
				if(resp.result == "OK"){
					console.log("OK")
					// 타이머 리셋
					restTime = setTime;
					codeTimer = setInterval(timer, 1000);
					modalBody.empty().text("인증코드가 발송되었습니다.");
					sendCodeForm.data("send", "Y")
				}else if(resp.result == "NOTEXIST"){
					modalBody.empty().text("일치하는 회원정보가 없습니다.");
				}else if(resp.result == "FAILED"){
					modalBody.empty().html(resp.errorMsg);
				}else{
					modalBody.empty().text("서버오류, 잠시 후 다시 시도해주세요.");
				}
				// 알림용 모달창 띄우기
				sendCodeModal.modal("show");
			},
			error : function(xhr, errorResp, errorMessage) {
				console.log(xhr);
				console.log(errorResp);
				console.log(errorMessage);
			}
		}).submit();
		
	});
	
	// 인증처리 프로세스
	let authForm = $("#authForm").on("click", "#authBtn", function() {
		modalBody.empty();
		
		if(sendCodeForm.data("send") == "N"){
			modalBody.html("인증코드가 만료되었거나 보낸 기록이 없습니다. <br>인증코드를 먼저 보내주십시오.");
		}else{
			authForm.ajaxForm({
				data : {
					"kindOfSendCode" : kindOfSendCode
				},
				dataType : "json",
				success : function(resp) {
					if(resp == "OK"){
						//알림용 모달창 띄우기
						timeout();
						
						modalBody.text("인증성공! 처리내역이 문자로 발송되었습니다.");
						sendCodeForm.data("send", "N")
					}else if(resp == "FAILED"){
						modalBody.text("인증코드가 일치하지 않습니다.");
					}else{
						modalBody.text("서버오류, 잠시 후 다시 시도해주세요.");
					}
					
				},
				error : function() {
					modalBody.text("서버오류, 잠시 후 다시 시도해주세요.");
				}
			}).submit();
		}
		
		sendCodeModal.modal("show");
		
	});