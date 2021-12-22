<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>

<script async type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<div class="table-size-1300">
	<c:set var="mem" value="${member}" />
	<input type="hidden" name="tuiMem" value="${mem.memId }">
	
	<h3>등록 납부</h3>
	
	<table class="table table-bordered">  
		<thead>
			<tr class="tr-style">
				<th>번호</th>
				<th>학번</th>
				<th>이름</th>
				<th>학과</th>
				<th>등록금 납부 여부</th>
			</tr>
		</thead>
		
		<c:set var="enrollList" value="${pagingVO.dataList }" />
	
		<tbody>
			<c:choose>
				<c:when test="${not empty enrollList }">
					<c:forEach items="${enrollList }" var="enroll">
						<tr>
							<td>${enroll.tuiNo }</td>
							<td>${enroll.member1.memId }</td>
							<td>${enroll.member1.memName }</td>
							<td>${enroll.major1.majorName }</td>
							
							<c:choose>
								<c:when test="${enroll.tuiPayment eq 'Y'}">
									<td><span class='badge badge-info'>납부완료</span></td>
								</c:when>
								<c:otherwise>
									<td><span class='badge badge-danger'>미납</span></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<td colspan="6"><jsp:include page="/enrollmentPDF.jsp" flush="true"/></td>
		</tbody>
		
		
		<tfoot>
				<tr>
					<td colspan="6">
						<div class="search-align">
							<div id="searchUI" class="search-align">
								<select name="searchType">
									<option value>전체</option>
									<option value="writer">학번</option>
									<option value="title">전공</option>
									<option value="title">납부여부</option>
								</select> 
								<input type="text" name="searchWord" /> 
								<input type="button" value="검색" id="searchBtn" class="btn btn-secondary btn-sm"/> 
							</div>

							<div>
								 <button id="check_module" class="btn-secondary btn-sm" data-success="0">등록금 납부</button>
							</div>
						</div>
						
						
						<div id="pagingArea" class="p-center m-bottom-10">
							${pagingVO.pagingHTML }
						</div>
					</td>
				</tr>
			</tfoot>
	</table>
</div>

<script>
		let input =  document.querySelector('#check_module');
		console.log(input.dataset);
		
		if(input ==  1){
			$("#check_module").hide();
		}
		
    $("#check_module").click(function () {
        var IMP = window.IMP; // 생략가능
        IMP.init('imp74099144');
        IMP.request_pay({
            pg: 'inicis', // version 1.1.0부터 지원.
            pay_method: 'vbank',
            merchant_uid: '1',
            name: 'SCM 등록금',
            amount: 3000000,
            buyer_email: 'iamport@siot.do',
            buyer_name: '구매자이름',
            buyer_tel: '010-6626-3591',
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'humanentec.iptime.org'
        }, function (rsp) {
            if (rsp.success) {
            	 console.log("성공!")
            	 jQuery.ajax({
                    url: '<%=request.getContextPath()%>/student/enrollment/enrollmentInsert.do',
                    type : "POST",
        			dataType : "json",
                    data: {
	                      'merchant_uid': rsp.merchant_uid,
	                      'vbank_name' : rsp.vbank_name,
	                      'vbank_num' : rsp.vbank_num,
	                      'buyer_name' : rsp.buyer_name
                    }
            	}).done(function (data) {
            		 var msg = '결제가 완료되었습니다.';
                     alert(msg);
                     
                     location.reload();
                     
                     let input = document.querySelector('#check_module');
                     input.dataset.success = '1';
            	})
           	} else {
                 alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
            }
    });
});

// var Iamport = require('iamport');
// var iamport = new Iamport({
//   impKey: '8483634781502586',
//   impSecret: 'rsFrtB8l1YIElamdjWhzx5bgvdcsjEVL6AcAclWdjFSFPfS9c9JJfUZL7cerR9cXKpyWKva4a2J0hBat'
// });

// app.get('/payments/status/all',(req,res)=>{
//     iamport.payment.getByStatus({
//       payment_status: 'paid' 
//     }).then(function(result){
//         res.render('payments_list',{list:result.list});
//     }).catch(function(error){
//         console.log(error);
//         red.send(error);
//     })
// });
</script>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>

<jsp:include page="/includee/postScript.jsp" />