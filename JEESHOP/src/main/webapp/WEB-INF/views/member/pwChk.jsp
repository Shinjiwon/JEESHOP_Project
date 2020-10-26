<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 비밀번호확인</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
	<!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div style="background-color: white; width:70%; padding: 5% 5%;">
					<h4>비밀번호 확인</h4>
					<form id="checkPwForm" method="post" action="pwChk">
						<input type="hidden" name="url" value="${url}">
						<div class="form-group">
							<!-- 1)회원정보 수정 url=modify,  2)비밀번호 변경 url=pwChange  -->
							<input type="password" class="form-control" id="mb_pw" name="mb_pw" class="form-control"
								placeholder="비밀번호를 입력해주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<input type="button" id="btn_submit" value="확인">
						</div>
					</form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>

	<script>
		if("${msg}" == "PW_CHECK_FAIL"){
			alert("비밀번호가 다릅니다.");
		}
	</script>
	
	<script>
		$(document).ready(function(){
		    $("#btn_submit").click(function(){
		        if($("#mb_pw").val()=="" || $("#mb_pw").val()==null){
		            alert("비밀번호를 입력하세요.");
		            $("#mb_pw").focus();
	
		        } else {
		            $("#checkPwForm").submit();
		        }
		    });
		});
	</script>
	
</body>
</html>

