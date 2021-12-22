<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 a {
 	text-decoration: none;
 	color: #ffffff;
}
 
 a:hover {
	text-decoration: none;
 	color: #ffffff;
}

.table-borderless > thead > tr > th {
    border-bottom: 2px solid #dee2e6;
    height: 30px;
    vertical-align: baseline;
    position: sticky;
    top: 0px;
    padding: 9px 0px 6px 0px;
    background-color: #fff;
}
</style>
</head>

<c:set value="${scoreMap.classList }" var="classList"/>
<c:set value="${scoreMap.semesterScoreList }" var="semesterScoreList"/>
<c:set value="${scoreMap.pointByCodeMap }" var="pointByCodeMap"/>
<c:set value="${scoreMap.totalScoreMap }" var="totalScoreMap"/>

<body>
	<div class="m-top-110">
		<div class="m-top-10 m-bottom-10" style="display: flex; justify-content: space-between;">
			<div class="score-box" style="display: flex;">
				<span class="all-score">총 성적&nbsp;<i class="fas fa-chevron-right"></i></span>
				<div>이수학점 : <span>${totalScoreMap.totalPoint }</span></div>
				<div>평점평균 : <span>${totalScoreMap.totalAvgGradePoint }</span></div>
				<div>백분위평균 : <span>${totalScoreMap.totalAvgScore }</span></div>
			</div>
			<div>
				<button class="search-btn width-100" style="margin-right: 40px;">
					<a href="<c:url value='/student/score/excelDownload'/>" class="lecturePage">
						<i class="fas fa-file-excel m-right-5"></i>엑셀다운로드</a>
				</button>
			</div>
		</div>
		<div class="all-wrap">
			<div class="left-wrap">
			 <div class="table-box width-883 m-bottom-20">
			 	<div class="card-header fixed">
					<h6 class="m-0 font-weight-bold">강의별 성적</h6>
			 	</div>
			 	<div class="p-wrap" style="overflow-y: scroll; height: 650px;">
					<table class="table table-borderless txt-center">
					  <thead>
					    <tr>
					      <th scope="col">No</th>
					      <th scope="col">연도</th>
					      <th scope="col">학기</th>
					      <th scope="col">강의번호</th>
					      <th scope="col">강의명</th>
					      <th scope="col">이수구분</th>
					      <th scope="col">학점</th>
					      <th scope="col">평점</th>
					      <th scope="col">등급</th>
					      <th scope="col">백분위</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${classList }" var="eachClass" varStatus="idxOne">
					  		<tr>
						      <th scope="row">${idxOne.count }</th>
						      <td>${eachClass.classYear }</td>
						      <td>${eachClass.classSemester }학기</td>
						      <td>${eachClass.classNo }</td>
						      <td>${eachClass.classInfo.className }</td>
						      <td>${eachClass.classCodeName }</td>
						      <td>${eachClass.classInfo.classPoint }</td>
						      <td>${eachClass.classGradePoint }</td>
						      <td>${eachClass.classGrade }</td>
						      <td>${eachClass.classScore }</td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					</table>
			 	</div>
			 </div>
			</div>
			<div class="right-wrap">
			 <div class="table-box m-bottom-20">
					<div class="card-header">
						<h6 class="m-0 font-weight-bold">학기별 성적</h6>
					</div>
					<div class="p-wrap">
						<table class="table table-borderless txt-center">
						  <thead>
						    <tr>
						      <th scope="col">No</th>
						      <th scope="col">연도</th>
						      <th scope="col">학기</th>
						      <th scope="col">신청학점</th>
						      <th scope="col">취득학점</th>
						      <th scope="col">평점평균</th>
						      <th scope="col">백분위평균</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach items="${semesterScoreList }" var="semesterScore" varStatus="idxTwo">
						  		<tr>
							      <th scope="row">${idxTwo.count }</th>
							      <td>${semesterScore.year }</td>
							      <td>${semesterScore.semester }학기</td>
							      <td>${semesterScore.registerPoint }</td>
							      <td>${semesterScore.gettedPoint }</td>
							      <td>${semesterScore.avgGradePoint }</td>
							      <td>${semesterScore.avgScore }</td>
							    </tr>
						  	</c:forEach>
						  </tbody>
						</table>
					</div>
			 </div>
			  <div class="table-box m-bottom-20">
					<div class="card-header">
						<h6 class="m-0 font-weight-bold">이수구분별 학점</h6>
					</div>
					<div class="p-wrap">
						<table class="table table-borderless txt-center">
						  <thead>
						    <tr>
						      <th scope="col">No</th>
						      <th scope="col">이수구분</th>
						      <th scope="col">이수학점</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach items="${pointByCodeMap }" var="pointByCode" varStatus="idxThree">
						  		<tr>
							      <th scope="row">${idxThree.count }</th>
							      <td>${pointByCode.key }</td>
							      <td>${pointByCode.value }</td>
							    </tr>
						  	</c:forEach>
						  </tbody>
						</table>
					</div>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>