<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 로그인</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
	<!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<script>
		if("${msg}"=="LOGIN FAIL"){
			alert("로그인에 실패하였습니다.\n아이디와 비밀번호를 다시 확인하세요.");
		}
	</script>
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

	<!--================login_part Area =================-->
    <section class="login_part section_padding ">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6 col-md-6">
                    <div class="login_part_text text-center">
                        <div class="login_part_text_iner">
                            <h2>처음 오셨나요?</h2>
                            <p>처음 방문하셨다면 저희 JEESHOP에 가입하여 쇼핑을 즐겨보세요!</p>
                            <a href="/member/join" class="btn_3">회원가입</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="login_part_form">
                        <div class="login_part_form_iner">
                            <h3>어서오세요 ! <br>
                                                                로그인하기</h3><br>
                            <form id="loginForm" class="row contact_form" action="/member/login" method="post">
                                <div class="col-md-12 form-group p_star">
                                    <input type="text" class="form-control" id="mb_id" name="mb_id"
                                        placeholder="ID">
                                </div><br><br><br>
                                <div class="col-md-12 form-group p_star">
                                    <input type="password" class="form-control" id="mb_pw" name="mb_pw"
                                        placeholder="비밀번호">
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="creat_account d-flex align-items-center">
                                    </div><br><br><br><br><br><br>
                                    <button type="submit" id="btn_login" value="submit" class="btn_3">
                                        	로그인
                                    </button><br>
                                    <a class="lost_pass" href="#">비밀번호 찾기</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================login_part end =================-->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>

	<!-- 로그인 버튼 클릭 -->
	<script type="text/javascript" src="/js/member/login.js"></script>
</body>
</html>

