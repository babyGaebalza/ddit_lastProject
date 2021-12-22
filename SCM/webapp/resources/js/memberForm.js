
$("#InfoBtn").on("click", function(){
	$.alert({
		title: '정보 자동입력',
		content : '시연용 정보가 자동으로 입력됩니다.'
	});
	document.getElementById("memName").value ="이지은";
	document.getElementById("memEname").value ="IU";
	document.getElementById("memReg1").value ="930516";
	document.getElementById("memReg2").value ="2440001";
	
	document.getElementById("memGender").getElementsByTagName('option')[2].selected = 'selected';
	document.getElementById("memNation").getElementsByTagName('option')[1].selected = 'selected';

	document.getElementById("memBirth").value = "1993-05-16";
	document.getElementById("memMail").value ="dlwlrma@naver.com";
	
	document.getElementById("memTel").value ="02-6956-1003";
	document.getElementById("sample6_detailAddress").value ="301호";

	document.getElementById("memJoindate").value = "2021-12-21";
	document.getElementById("memSubtel").value = "042-222-8202";
	
	document.getElementById("admissionCode").getElementsByTagName('option')[1].selected = 'selected';
	document.getElementById("majorCode").getElementsByTagName('option')[70].selected = 'selected';
	
	document.getElementById("memPass").value = "java";
	document.getElementById("memPassRe").value = "java";
	
});

let pass = true;

$("#memBirth").on("click", function(){
	var today = new Date();
	$("#memBirth").attr("max", today);
	
});


let memberForm = $("#memberForm").on("submit", function(event){	

	
	let memReg1 = $("#memReg1").val();
	let memReg1Exp = /[0-9]{6}/;
	
	if(!memReg1Exp.test(memReg1)){
		$.confirm({
			title : '주민번호 입력 오류',
			content : '주민번호 앞자리 형식을 확인해주세요',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	if(memReg1 == null || memReg1 == ""){
		$.confirm({
			title : '주민번호 입력 오류',
			content : '주민번호를 입력해주세요',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	
	
	let memReg2 = $("#memReg2").val();
	let memReg2Exp = /[0-9]{7}/;
	if(!memReg2Exp.test(memReg2)){
		$.confirm({
			title : '주민번호 입력 오류',
			content : '주민번호 뒷자리 형식을 확인해주세요',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	
	
/*	let memTel = $("#memTel").val();
	let memTelExp = /^([0|1|6|7|8|9]{2,4})-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!memTelExp.test(memTel)){
		$.confirm({
			title : '전화번호 입력 오류',
			content : '전화번호 형식을 확인해주세요',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	*/
	
	let memAdd1 = $("#sample6_address").val();
	if(memAdd1 == null || memAdd1 == ""){
		$.confirm({
			title : '주소 입력 오류',
			content : '주소를 입력해주세요',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	
	
	let memPass = $("#memPass").val();
	let memPassExp = /[a-z0-9_-]{4,15}/;
	if(!memPassExp.test(memPass)){
		$.confirm({
			title : '비밀번호 입력 오류',
			content : '비밀번호 규칙은 4-15자(숫자,영문자)입니다.',
			buttons : {
				확인 : function(){
					
				}
			}
		});
		return false;
	}
	
	
	let password = $('#memPass').val();
	let passwordRe = $('#memPassRe').val();

	if(password != passwordRe){
		alert("비밀번호 불일치! 비밀번호를 확인해주세요");
		return false;
	}
	
	
	exampleInputEmail
	let reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
})

$(document).on("keyup", ".phoneNumber", function() { $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); });

$(document).on("keyup", "#memReg1", function(){
	let pass = $("#memReg1").val();
	$("#memPass").val(pass);
	$("#memPassRe").val(pass);
	
});

function numberMaxLength(e){
	if(e.value.length > e.maxLength){
		e.value = e.value.slice(0, e.maxLength);
	}
}

$(document).on("keyup", ".regNo", function(){
	let pass = $("#memReg1").val();
	let pass2 = $("#memReg2").val();
	
	if(pass.length == 6 && pass2.length == 7){
		var year = pass.substring(0,2);
		var month = pass.substring(2,4);
		var day = pass.substring(4,6);
		var old = pass2.substring(0,1);
		var frontyear = 19;
		if(old >= 3){
			frontyear = 20;
		}
		if(old == 1 || old == 3){
			$("#memGender").val("남").prop("selected", true);		
		}
		else{
			$("#memGender").val("여").prop("selected", true);
		}
		var totalyear = frontyear+year+"-"+month+"-"+day;
		$("#memBirth").val(totalyear);
	}
	
});





$("#postbutton").on("click", function(){
	sample4_execDaumPostcode();
})

function sample4_execDaumPostcode() {
	var width = 500; //팝업의 너비
	var height = 600; //팝업의 높이
    new daum.Postcode({
        oncomplete: function(data) {
        	popupTitle: 'SCM 우편번호 검색 팝업입니다.'
        		// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
    }).open({
        left: (window.screen.width / 2) - (width / 2),
        top: (window.screen.height / 2) - (height / 2)
    });
}
