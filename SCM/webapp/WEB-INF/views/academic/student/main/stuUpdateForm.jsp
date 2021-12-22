<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/includee/preScript.jsp" />

<title>수정 페이지 입니다.</title>
<style>
.table-bordered > tbody > tr > th {
    background-color: #f7f7f7;
    color: #333;
    width: 230px;
}

.table-bordered > tbody > tr > td {
    color: #333;
}

.mypage-wrap {
    width: 800px;
    margin: 0 auto;
}
.profile-box {
	min-height: 500px;
    width: 1000px;
    margin: 0 auto;
    padding: 40px 32px 38px;
    border: 1px solid #b7b7b7;
    border-radius: 8px;
    box-shadow: 0 10px 18px 0 rgb(0 75 151 / 10%);
    background: #fff;
}
.profile-wrap{
	display: flex;
}
.profile-img {
	border: 1px solid #cbccd5;
    width: 170px;
    height: 200px;
    overflow: hidden;
    border-radius: 3px;
}

.profile-img > img {
	width: 100%;
    height: 100%;
    object-fit: cover;
}

.profile-info-box {
	line-height: 48px;
    font-size: 18px;
    color: #4f565e;
}

.profile-info-box > div > span {
    color: #4f565e;
    width: 160px;
    display: inline-block;
    padding-left: 20px;
    font-weight: bold;
}

.line {
    border-bottom: 1px solid #cbccd5;
}
.h5-title {
	background: #eee;
    padding: 10px;
    text-align: center;
    color: #222;
    font-weight: bold;
    border-radius: 4px;
    margin-bottom: 18px;
}
</style> 

<form:form modelAttribute="student" method="post" enctype="multipart/form-data" id="editForm">
<input type="hidden" name="memId" value="${student.memId }">
<input type="file" name="memImage" accept="image/*" onchange="setProfile(event);" hidden="true"/>
<div class="m-top-110">
	<div class="profile-box">
		<h5 class="h5-title">수정 페이지</h5>
			<div class="profile-wrap">
				<div class="profile-img">
					<div id="image_container">
						<img src="data:image/*;base64,${student.base64Image }" style="width: 100%;" />
					</div>
				</div>
				<div class="profile-info-box m-left-20">
					<div>
						<span>학번</span>
						${student.memId }
						<form:errors path="memId" element="span" cssClass="error" />
					</div>
					<div>
						<span>이름</span>
						${student.memName }
						<form:errors path="memName" element="span" cssClass="error" />
					</div>
					<div>
						<span>전공</span>
						${student.memMajor }
						<form:errors path="memMajor" element="span" cssClass="error" />
					</div>
					<div>
						<span>학적</span>
						${student.memSemester }
						<form:errors path="memSemester" element="span" cssClass="error" />
					</div>
				</div>
			</div>
			<div style="padding: 10px 0px 0px 38px;">
				<input type="button" id="changeImage" value="사진수정" class="btn btn-secondary btn-n-sm" />
			</div>
			<div class="m-top-20"></div>
				<div class="profile-info-box">
					<div class="line" style="border-top: 1px solid #cbccd5;">
						<span>생년월일</span>
						${student.memBirth }
					</div>
					<div  class="line">
						<span>연락처</span>
						<input type="text" name="memTel" value="${student.memTel }" style="width: 150px; line-height: 30px;"/>
						<form:errors path="memTel" element="span" cssClass="error" />
					</div>
					<div class="line">
						<span>주소</span>
						<input type="text" name="memAdd1" value="${student.memAdd1 }" style="width: 500px; line-height: 30px;" />
							<form:errors path="memAdd1" element="span" cssClass="error" />
					</div>
					<div class="line">
						<span>상세 주소</span>
						<input type="text" name="memAdd2" value="${student.memAdd2 }" style="width: 500px; line-height: 30px;"/>
						<form:errors path="memAdd2" element="span" cssClass="error" />
					</div>
					<div class="line">
						<span>이메일</span>
						<input type="text" name="memMail" value="${student.memMail }" style="line-height: 30px;"/>
						<form:errors path="memMail" element="span" cssClass="error" />
					</div>
					<div class="line">
						<span>내/외국인</span>
						<c:if test="${student.memNation eq 'N'}">
							내국인
						</c:if>
						<c:if test="${student.memNation eq 'Y'}">
							외국인
						</c:if>
					</div>
					<div class="line">
						<span>졸업여부</span>
						<c:if test="${student.memGraduate eq 'N'}">
							졸업예정
						</c:if>
						<c:if test="${student.memGraduate eq 'Y'}">
							졸업
						</c:if>
					</div>
					<div>
						<span>지도교수</span>
						${student.memAdviser }
					</div>
				</div>
		</div>
			<input type="submit" value="저장" class="btn btn-secondary btn-n-sm m-right-5" />
			<input type="reset" value="취소" class="btn btn-secondary btn-n-sm" />
</div>
</form:form>

<%-- <form id="updateForm" action="${cPath }/common/member/stuMyPageUpdate.do" method="post" enctype="multipart/form-data"> --%>
<%-- 	<input type="hidden" name="memId" value="${student.memId }"> --%>
<!-- 	<input type="file" name="memImage" accept="image/*" onchange="setProfile(event);" hidden="true"/> -->
<%-- </form> --%>

<script type="text/javascript">
// 	let updateForm = $("#updateForm");

// 	$("#updateBtn").on("click", function(){
// 		let ok = $.confirm({
// 			title : '수정',
// 			content : '수정하시겠습니까?',
// 			buttons : {
// 				수정 : function(){
// 					updateForm.submit();
// 				},
// 				취소 : function(){
// 				},
// 			}
// 		});
// 	});
	
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
<jsp:include page="/includee/postScript.jsp" />
