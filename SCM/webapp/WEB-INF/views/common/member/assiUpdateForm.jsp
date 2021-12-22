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

<div class="m-top-110">
	<div class="profile-box">
		<h5 class="h5-title">마이 페이지</h5>
			<div class="profile-wrap">
				<div class="profile-img">
					<img src="data:image/*;base64,${assistant.base64Image }"/>
					<input type="file" name="memImage" accept="image/*" onchange="setProfile(event);" />
					<div id="image_container"></div>
				</div>
				<div class="profile-info-box m-left-20">
					<div>
						<span>학번</span>
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
						<input type="text" name="memTel" value="${assistant.memTel }" style="width: 150px;"/>
						<form:errors path="memTel" element="span" cssClass="error" />
					</div>
			</div>
		</div>
		
		<div class="m-top-20">
		</div>
			<div class="profile-info-box">
				<div class="line" style="border-top: 1px solid #cbccd5;">
					<span>주소</span>
					<input type="text" name="memAdd1" value="${assistant.memAdd1 }" style="width: 500px;" />
						<form:errors path="memAdd1" element="span" cssClass="error" />
				</div>
				<div class="line">
					<span>상세 주소</span>
					<input type="text" name="memAdd2" value="${assistant.memAdd2 }" style="width: 500px;"/>
					<form:errors path="memAdd2" element="span" cssClass="error" />
				</div>
				<div class="line">
					<span>이메일</span>
					<input type="text" name="memMail" value="${assistant.memMail }" />
					<form:errors path="memMail" element="span" cssClass="error" />
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
					<span>전공</span>
					${assistant.memMajor }
				</div>
				<div class="line">
					<span>졸업여부</span>
					<c:if test="${assistant.memGraduate eq 'N'}">
						졸업예정
					</c:if>
					<c:if test="${assistant.memGraduate eq 'Y'}">
						졸업
					</c:if>
				</div>
				<div>
					<span>지도교수</span>
					${assistant.memAdviser }
				</div>
			</div>
	</div>
</div>

<script>
  function setProfile(event){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			document.querySelector("div#image_container").appendChild(img);
		};
		
		reader.readAsDataURL(event.target.files[0]);
	}
  
  
 </script>

<jsp:include page="/includee/postScript.jsp" />
