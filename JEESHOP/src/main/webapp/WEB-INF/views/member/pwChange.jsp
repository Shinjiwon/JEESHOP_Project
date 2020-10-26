<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 비밀번호변경</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta name="description" content="">
  	<meta name="author" content="">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
	<!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
</head>

<body>
	<!-- Header -->
	<%@include file="/WEB-INF/views/include/header.jsp" %>

	<!-- 비밀번호 변경 Main -->
	<section class="content container-fluid">
		<div class="container" style="width: 100%; min-width: 900px; background-color: white; font-size: 20px;">
			<form id="pwchangeForm" action="/member/pwChange" method="post">
				<div class="container" style="width: 800px; padding: 10% 5%;">
				<div class="regisfont">
					<h2>비밀번호 변경</h2><br>
					</div>
					
					<input type="hidden" name="mb_id" value="${sessionScope.user.mb_id }">
					
					<div class="form-group">
						<label for="inputPassword">☞ 현재 비밀번호</label> 
						<input type="password" class="form-control" id="mb_pw"
							   placeholder="현재 비밀번호를 입력하세요." style="max-width: 630px;">
					</div>
					
					<div class="form-group">
						<label for="inputPassword">☞ 비밀번호</label> 
						<input type="password" class="form-control" id="mb_pw_change" name="mb_pw"
							   placeholder="변경할 비밀번호를 입력하세요." style="max-width: 630px;">
					</div>
					
					<div class="form-group">
						<label for="inputPasswordCheck">☞ 비밀번호 확인</label> 
						<input type="password" class="form-control" id="mb_pw_chk"
							placeholder="변경할 비밀번호를 다시 입력해 주세요." style="max-width: 630px;" >
						<p id="mbPwChk_status" style="color: red;"/>
					</div>
					
				<div class="form-group text-center">
					<button type="button" id="btn_submit" class="btn btn-primary">변경 </button>
					<button type="button" id="btn_cancle" class="btn btn-warning">취소 </button>
				</div><br><br><br><br>
				</div>
			</form>
		</div>
				
		<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
		<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		</div>
		
	</section>
	<!-- Main end -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
	<!-- 유효성 검사 -->
	<script type="text/javascript" src="/js/member/pwChange.js"></script>
</body>
</html>