<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/includee/preScript.jsp" />
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
<title>마이페이지 입니다.</title>

<div class="m-top-110">
	<div class="profile-box">
		<h5 class="h5-title">마이 페이지</h5>
			<div class="profile-wrap">
				<div class="profile-img">
					<img src="data:image/*;base64,${assistant.base64Image }"/>
				</div>
				<div class="profile-info-box m-left-20">
					<div>
						<span>사번</span>
						${assistant.memId }
					</div>
					<div>
						<span>이름</span>
						${assistant.memName }
					</div>
					<div>
						<span>생년월일</span>
						${assistant.memBirth }
					</div>
					<div>
						<span>연락처</span>
						${assistant.memTel }
					</div>
			</div>
		</div>
		
		<div class="m-top-20">
		</div>
			<div class="profile-info-box">
				<div class="line" style="border-top: 1px solid #cbccd5;">
					<span>주소</span>
					${assistant.memAdd1 }
				</div>
				<div class="line">
					<span>상세 주소</span>
					${assistant.memAdd2 }
				</div>
				<div class="line">
					<span>이메일</span>
					${assistant.memMail }
				</div>
				<div class="line">
					<span>내/외국인</span>
					<c:if test="${assistant.memNation eq 'N'}">
						내국인
					</c:if>
					<c:if test="${assistant.memNation eq 'Y'}">
						외국인
					</c:if>
				</div>
				<div class="line">
					<span>직급</span>
					${assistant.rankCode }
				</div>
				<div class="line">
					<span>소속단과대</span>
					${assistant.memCollege }
				</div>
				<div class="line">
					<span>입사일</span>
					${assistant.memJoindate }
				</div>
				<div>
					<span>4대보험가입</span>
					${assistant.memInsurance }
				</div>
			</div>
	</div>
	<div class="flex-center m-top-20">
		<input type="button" value="정보수정" id="updateBtn" class="btn btn-secondary m-right-5"/> 
		<input type="button" value="메인으로 이동" id="mainBtn" class="btn btn-secondary"/> 
	</div>
</div>

<form id="updateForm" action="${cPath }/common/member/assiMyPageUpdate.do" method="get" >
	<input type="hidden" name="memId" value="${assistant.memId}" >
</form>

<form id="mainForm" action="${cPath }/" method="post" >
	<input type="hidden" name="memId" value="${assistant.memId}" >
</form>

<script type="text/javascript">
	let updateForm = $("#updateForm");
	let mainForm = $("#mainForm");
	
	$("#updateBtn").on("click", function(){
		let ok = $.confirm({
			title : '정보 수정',
			content : '해당 정보를 수정하시겠습니까',
			buttons : {
				수정 : function(){
					updateForm.submit();
				},
				취소 : function(){
				},
			}
		});
	});
	
	$("#mainBtn").on("click", function(){
		let ok = $.confirm({
			title : '메인화면으로 이동',
			content : '메인화면으로 이동하시겠습니까?',
			buttons : {
				이동 : function(){
					mainForm.submit();
					$.alert('메인으로 이동합니다.');
				},
				취소 : function(){
				},
			}
		});
	});
	
	
	function setThumbnail(event){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute("class", "col-lg-6");
			document.querySelector("div#image_container").appendChild(img);
		};
		
		reader.readAsDataURL(event.target.files[0]);
	}
</script>

<jsp:include page="/includee/postScript.jsp" />
