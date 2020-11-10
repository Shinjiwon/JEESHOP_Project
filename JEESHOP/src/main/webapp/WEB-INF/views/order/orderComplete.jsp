<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 상품주문완료</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

		<!-- 장바구니 리스트 -->
		<!-- slider Area Start-->
		  <div class="slider-area ">
		    <!-- Mobile Menu -->
		    <br><br><br><br><br><div class="single-slider slider-height2 d-flex align-items-center" data-background="/assets/img/hero/category.jpg">
		        <div class="container">
		            <div class="row">
		                <div class="col-xl-12">
		                    <div class="hero-cap text-center">
		                        <h1>주문이 완료되었습니다.</h1>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		  </div>
		  <!-- slider Area End-->
		
		  <!--================Cart Area =================-->
		  <section class="cart_area section_padding">
		    <div class="container" style="text-align: center;">
                    <a class="genric-btn primary radius" href="/order/list">주문내역 확인</a>
                   	<a class="genric-btn primary-border radius" href="/">쇼핑 계속하기</a>
		      </div>
		  </section>	
			<!-- 장바구니 리스트 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>

</body>
</html>

