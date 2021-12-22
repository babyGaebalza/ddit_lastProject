<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-4.6.0/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/commons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/templateFile/vendor/jquery-easing/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/templateFile/js/sb-admin-2.min.js"></script>

<script type="text/javascript" src="${cPath }/resources/js/paging.js" ></script>
<script type="text/javascript">	
	$("[name='searchWord']").val("${searchVO.searchWord}");
	$("[name='searchType']").val("${searchVO.searchType}")
	let searchForm = $("#searchForm").paging();
</script>