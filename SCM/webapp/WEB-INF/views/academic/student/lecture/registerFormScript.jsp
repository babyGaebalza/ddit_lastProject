<%@page import="kr.or.ddit.enumpkg.ServiceResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//학부(과) 리스트
let majorList = $("#majorList");
let start = true;
//------------------------------------------------------------------------------------------ 단과대 변경시(->해당 단과대 학부(과) 조회) ------------------------------------------------------------------------------------------
let collegeList = $("#collegeList").on("change", function() {
	$.ajax({
		url : $.getContextPath() + "/student/lecture/collegeMajorList.do",
		data : {
			"collegeCode" : $(this).val() 
		},
		dataType : "json",
		success : function(collegeMajorList) {
			
			let majors = [];
			
			majors.push($("<option>").val("").text("전체"));
			
			$.each(collegeMajorList, function(idx, major) {
				let option = $("<option>").val(major.majorCode).text(major.majorName);
				if(major.majorCode == "${authMember.memMajor}"){
					option.attr("selected", "selected");
				}
				majors.push(option);
			});
			
			console.log(majorList);
			majorList.empty().append(majors);
			if(start){
				classSearchForm.submit();
				start = false;
			}
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});
// 페이지 접근시 기본 학과 리스트(로그인정보 기반)를 띄우기 위해 change 이벤트 발생시킴
collegeList.trigger("change");

// 로그인 정보 학년(memGrade)정보로 학년리스트 자동선택 기능
let memGrade = "${authMember.memSemester}".split("/")[1];
let gradeList = $("#gradeList").val(memGrade + "/${semester}").attr("selected", "selected");


// ------------------------------------------------------------------------------------------ 검색 기능 ------------------------------------------------------------------------------------------
let classList = $("#classList");
let classSearchForm = $("#classSearchForm").ajaxForm({
	dataType : "json",
	success : function(resultMap) {
		let trTags = [];
		$.each(resultMap.lectureList, function(idx, lecture) {
			
			let registerButton;
			switch ("${registerKind}") {
			case "1":
				registerButton = $("<input>").attr({
					"type" : "button",
					"class" : "cartInBtn"
				}).val("바구니담기");
				break;
			case "2":
				registerButton = $("<input>").attr({
					"type" : "button",
					"class" : "registerBtn"
				}).val("수강신청");
				break; 
			}
			
			if(lecture.classPerson >= lecture.classMax){
				registerButton.attr({"disabled" : "disabled"});
			}else{
				$.each(resultMap.registerList, function(idx, register) {
					console.log(idx);
					if(lecture.classNo == register.classNo){
						console.log(register);
						registerButton.attr({"disabled" : "disabled"});
						registerButton.val("신청완료");
						return false;
					}
				});
			}
			
			trTags.push(
				$("<tr>").append(
					$("<td>").text(idx+1)
					, $("<td>").append(registerButton)
					, $("<td>").text(lecture.classSemester.split("/")[0])
					, $("<td>").text(lecture.classNo).data("classNo", lecture.classNo).attr("class", "classNo")
					, $("<td>").text(lecture.classDivide)
					, $("<td>").text(lecture.className).data("className", lecture.className).attr("class", "className")
					, $("<td>").text(lecture.classCodeName)
					, $("<td>").text(lecture.classPoint)
					, $("<td>").text(lecture.classProName)
					, $("<td>").text(lecture.classTime)
					, $("<td>").text(lecture.classPerson + "/" + lecture.classMax)
					, $("<td>").text("강의계획서")
					).attr("class", "lecture")
				);
		});
		
		classList.empty().append(trTags);
	},
	error : function(xhr, errorResp, errorMessage) {
		console.log(xhr);
		console.log(errorResp);
		console.log(errorMessage);
	}
}).submit();


let cartList = $("#cartList");
// ------------------------------------------------------------------------------------------ 강의바구니 조회 함수 ------------------------------------------------------------------------------------------
function  cartClassList() {
	$.ajax({
		url : $.getContextPath() + "/student/lecture/cartList.do",
		dataType : "json",
		success : function(resultMap) {
			
			
			let trTags = [];
			$.each(resultMap.lectureList, function(idx, lecture) {
				
				// 수강신청 버튼
				let registerButton = $("<input>").attr({
					"type" : "button",
					"class" : "registerBtn"
				}).val("수강신청");
				if(lecture.classPerson >= lecture.classMax || "${registerKind}" == "1"){
					// (제한인원이 다 찼음 OR 담기기간)일 경우 비활성화 
					registerButton.attr("disabled", "disabled");
				}
				
				$.each(resultMap.registerList, function(idx, register) {
					console.log(idx);
					if(lecture.classNo == register.classNo){
						console.log(register);
						registerButton.attr({"disabled" : "disabled"});
						registerButton.val("신청완료");
						return false;
					}
				});
				
				// 담기취소 버튼
				let cartOutButton = $("<input>").attr({
					"type" : "button",
					"class" : "cartOutBtn"
				}).val("담기취소");
				if("${registerKind}" != "1"){
					//담기기간이 아닐 경우
					cartOutButton.attr("disabled", "disabled");
				}
				
				trTags.push(
					$("<tr>").append(
						$("<td>").text(idx+1)
						, $("<td>").append(registerButton)
						, $("<td>").text(lecture.classSemester.split("/")[0])
						, $("<td>").text(lecture.classNo).data("classNo", lecture.classNo).attr("class", "classNo")
						, $("<td>").text(lecture.classDivide)
						, $("<td>").text(lecture.className).data("className", lecture.className).attr("class", "className")
						, $("<td>").text(lecture.classCodeName)
						, $("<td>").text(lecture.classPoint)
						, $("<td>").text(lecture.classProName)
						, $("<td>").text(lecture.classTime)
						, $("<td>").text(lecture.classPerson + "/" + lecture.classMax)
						, $("<td>").text("강의계획서")
						, $("<td>").append(cartOutButton)
						).attr("class", "lecture")
					);
			});
			
			cartList.empty().append(trTags);
			
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}
	
	});
}

cartClassList();
let cartMax = "<%=ServiceResult.CARTMAX.getValue()%>";
let cartRegisterResultModal = $("#cartRegisterResultModal");
let cartRegisterResultModalBody = cartRegisterResultModal.find("#modalBody");

//------------------------------------------------------------------------------------------ 바구니 강의 담기 ------------------------------------------------------------------------------------------
let cartInBtn =  $(document).on("click", ".cartInBtn", function() {
	let lecture = $(this).closest(".lecture");
	let classNo = lecture.find(".classNo").data("classNo");
	let className = lecture.find(".className").data("className");
	
	$.ajax({
		url : $.getContextPath() + "/student/lecture/cartRegister.do",
		data : {
			"classNo" : classNo
		},
		dataType : "json",
		success : function(resultMap) {
			let cartCnt = resultMap.cartClassCount;
			switch (resultMap.result) {
			case "OK":
				cartRegisterResultModalBody.empty().html("(강의명:" + className + ") 담기 성공<br>현재 바구니에 담긴 강의 수 : " + cartCnt + "<br>바구니에 담을수 있는 강의 수 : " + cartMax);
				// 성공시 강의 바구니 재조회
				cartClassList();
				break;
			case "PKDUPLICATED":
				cartRegisterResultModalBody.empty().html("(강의명:" + className + ") 는 이미 바구니에 담겨있습니다.");
				break;
			case "CARTMAX":
				cartRegisterResultModalBody.empty().html("강의바구니가 이미 가득찼습니다.<br>현재 바구니에 담긴 강의 수 : " + cartCnt + "<br>바구니에 담을수 있는 강의 수 : " + cartMax);
				break;
			case "NOTPERIOD":
				cartRegisterResultModalBody.empty().html("강의 담기 기간이 아닙니다.");
				break;
			case "FAILED":
				cartRegisterResultModalBody.empty().html("서버오류, 잠시 후 다시 시도해주세요.");
				break;
			}
			cartRegisterResultModal.modal("show");
			
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});

//------------------------------------------------------------------------------------------ 바구니 강의 삭제 ------------------------------------------------------------------------------------------
let cartOutBtn =  $(document).on("click", ".cartOutBtn", function() {
	let lecture = $(this).closest(".lecture");
	let classNo = lecture.find(".classNo").data("classNo");
	let className = lecture.find(".className").data("className");
	
	$.ajax({
		url : $.getContextPath() + "/student/lecture/cartRemove.do",
		data : {
			"classNo" : classNo
		},
		dataType : "json",
		success : function(resultMap) {
			let cartCnt = resultMap.cartClassCount;
			switch (resultMap.result) {
			case "OK":
				cartRegisterResultModalBody.empty().html("강의(강의명:" + className + ")<br> 담기 취소됨<br>현재 바구니에 담긴 강의 수 : " + cartCnt + "<br>바구니에 담을수 있는 강의 수 : " + cartMax);
				// 삭제시 강의 바구니 재조회
				cartClassList();
				break;
			case "NOTEXIST":
				cartRegisterResultModalBody.empty().html("바구니에 강의(강의명:" + className + ")<br> 가 담겨있지않아 취소할 수 없습니다.");
				break;
			case "NOTPERIOD":
				cartRegisterResultModalBody.empty().html("강의 담기 기간이 아닙니다.");
				break;
			case "FAILED":
				cartRegisterResultModalBody.empty().html("서버오류, 잠시 후 다시 시도해주세요.");
				break;
			}
			cartRegisterResultModal.modal("show");
			
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});

// ------------------------------------------------------------------------------------------ 수강신청 리스트/시간표 조회 ------------------------------------------------------------------------------------------
let registerList = $("#registerList");
let timeTable = $("#timeTable");
let pointInfo = $("#pointInfo");

function registerClassListAndTimeTable() {
	$.ajax({
		url : $.getContextPath() + "/student/lecture/registerList.do",
		dataType : "json",
		success : function(resultMap) {
			// 수강신청 리스트
			let trTags = [];
			let totalPoint = 0;
			$.each(resultMap.classRegisterList, function(idx, lecture) {
				totalPoint += lecture.classPoint;
				let cancelButton = $("<input>").attr({
					"type" : "button",
					"class" : "cancelBtn"
				}).val("수강취소");
				
				trTags.push(
					$("<tr>").append(
						$("<td>").text(idx+1)
						, $("<td>").text(lecture.classNo).data("classNo", lecture.classNo).attr("class", "classNo")
						, $("<td>").text(lecture.classDivide)
						, $("<td>").text(lecture.className).data("className", lecture.className).attr("class", "className")
						, $("<td>").text(lecture.classCodeName)
						, $("<td>").text(lecture.classPoint)
						, $("<td>").text(lecture.classProName)
						, $("<td>").text(lecture.classTime)
						, $("<td>").text(lecture.classPerson + "/" + lecture.classMax)
						, $("<td>").text("강의계획서")
						, $("<td>").append(cancelButton)
						).attr("class", "lecture")
					);
			});
			
			
			
			registerList.empty().append(trTags);
			
			// 시간표
			trTags = [];
			let timeTableMap = resultMap.stuTimeTableMap;
			
			for (var i = 0; i < 9; i++) {
				trTags.push($("<tr>").append($("<td>").text(i+1)));
			}
			
			let weekdayList = ["월", "화", "수", "목", "금"];
			
			$.each(weekdayList, function(idx, weekday) {
				let dayTime = timeTableMap[weekday];
				$.each(trTags, function(idx, time) {
					let content = dayTime["stutime" + (idx + 1)];
					time.append(
						$("<td>").html(content)	
					);
				});
			})  
			
			
			pointInfo.empty().append(
				$("<div>").append(
					$("<span>").text("제한학점: ${authMember.memRegisterlimit }")
					, $("<span>").text("신청학점: " + totalPoint)
				).addClass("pointArea")	
			);
			timeTable.empty().append(trTags);
			
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
}

registerClassListAndTimeTable();

// ------------------------------------------------------------------------------------------ 수강신청 ------------------------------------------------------------------------------------------
// 수강신청 버튼 이벤트 핸들링(가능여부 검사)
$(document).on("click", ".registerBtn", function() {
	let lecture = $(this).closest(".lecture");
	let classNo = lecture.find(".classNo").data("classNo");
	let className = lecture.find(".className").data("className");
	
	$.ajax({
		url : $.getContextPath() + "/student/lecture/registerCheck.do",
		data : {
			"classNo" : classNo
		},
		dataType : "json",
		success : function(resultMap) {
			
			switch (resultMap.result) {
			case "NOTPERIOD":
				$.alert({
				    title: '알림',
				    content: '현재 수강신청 기간이 아닙니다.',
				});
				break;
			case "NOTMAJOR":
				$.alert({
				    title: '알림',
				    content: '타전공 강의는 변경기간에 별도로 신청이 가능합니다.',
				});
				break;
			case "PKDUPLICATED":
				$.alert({
				    title: '알림',
				    content: '이미 수강신청된 강의입니다.',
				});
				break;
			case "LIMITEXCEED":
				$.alert({
				    title: '알림',
				    content: '수강제한 학점을 넘어서는 수강신청은 불가능 합니다.',
				});
				break;
			case "TIMEDUPLICATED":
				$.alert({
				    title: '알림',
				    content: '현재 수강신청한 강의와 시간표가 겹칩니다.',
				});
				break;
			default:
				//  수강신청/재수강 가능한 경우
				let ajaxData = {
						url : $.getContextPath() + "/student/lecture/register.do",
						dataType : "json",
						success : function(ServiceResult) {
							let alertData = {
							    title: '알림'
							}
							switch (ServiceResult) {
							case "OK":
								alertData.content = "수강신청 성공";
								
								break;
							case "LIMITEXCEED":
								alertData.content = "수강인원이 꽉 찬 강의는 신청이 불가능합니다.";
								break;
							case "FAILED":
								alertData.content = "서버오류, 잠시 후 다시시도해주세요.";
								break;
								
							}
							// 수강신청 리스트/시간표 재조회
							registerClassListAndTimeTable();
							// 강의검색 재조회
							classSearchForm.submit();
							// 강의 바구니 재조회
							cartClassList();+6
						
							$.alert(alertData);
							
						},
						error : function(xhr, errorResp, errorMessage) {
							console.log(xhr);
							console.log(errorResp);
							console.log(errorMessage);
						}

					}
				if(resultMap.result == "OK"){
					data = {
						"classNo" : classNo	
					}
					ajaxData.data = data;
					$.ajax(ajaxData);
				}else if(resultMap.result == "RETAKE"){
					$.confirm({
					    title: '재수강 대상 알림',
					    content: '신청하려는 강의(강의명:' + className + ')는 ' + '이미 수강하신 강의(강의명:' + resultMap.retakeClass.className + ')의 재수강 대상입니다. 신청하시겠습니까?',
					    buttons: {
					    	재수강신청: function () {
					        	data = {
										"classNo" : classNo,
										"classRetake" : resultMap.retakeClassNo
									}
					        	ajaxData.data = data;
								$.ajax(ajaxData);
				        	},
			  			      취소: function () {
						    }
				        }
					});
					
				}else{
					"서버오류alert처리";
					break;
				}
				break;
			}
		},
		error : function(xhr, errorResp, errorMessage) {
			console.log(xhr);
			console.log(errorResp);
			console.log(errorMessage);
		}

	});
});
// ------------------------------------------------------------------------------------------ 수강신청 취소 ------------------------------------------------------------------------------------------
$(document).on("click", ".cancelBtn", function() {
	let lecture = $(this).closest(".lecture");
	let classNo = lecture.find(".classNo").data("classNo");
	let className = lecture.find(".className").data("className");
	
	$.confirm({
	    title: '수강신청',
	    content: '수강신청 강의(강의명:' + className + ')를 <br>취소하시겠습니까?',
	    buttons: {
	        신청취소: function () {
	        	$.ajax({
	    			url : $.getContextPath() + "/student/lecture/registerRemove.do",
	    			data : {
	    				"classNo" : classNo
	    			},
	    			dataType : "json",
	    			success : function(serviceResult) {
	    				let alertData = {
	    					    title: '알림'
	    					}
	    				switch (serviceResult) {
	    				case "OK":
	    					alertData.content = "수강신청 강의(강의명:" + className + ")가 정상적으로 취소되었습니다.";
	    					
	    					break;
	    				case "FAILED":
	    					alertData.content = "서버오류, 잠시 후 다시 시도해주세요.";
	    					break;
	    				}
	    				// 수강신청 리스트/시간표 재조회
						registerClassListAndTimeTable();
						// 강의검색 재조회
						classSearchForm.submit();
						// 강의 바구니 재조회
						cartClassList();
						
	    				$.alert(alertData);
	    			},
	    			error : function(xhr, errorResp, errorMessage) {
	    				console.log(xhr);
	    				console.log(errorResp);
	    				console.log(errorMessage);
	    			}

	    		});
	        },
	        아니오: function () {
	        }
	    }
	});
	
	
});
//------------------------------------------------------------------------------------------ 기간변경 ------------------------------------------------------------------------------------------
$("#cartChangeBtn").on("click", function() {
	location.href = $.getContextPath() + "/student/lecture/cartChange.do";
});

$("#regiChangeBtn").on("click", function() {
	location.href = $.getContextPath() + "/student/lecture/regiChange.do";
});
</script>