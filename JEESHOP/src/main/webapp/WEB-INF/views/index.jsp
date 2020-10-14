<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
	<script>
		if("${msg}"=="LOGIN SUCCESS"){
			alert("로그인되었습니다. \n즐거운 쇼핑하세요!");
			
		} else if("${msg}"=="JOIN SUCCESS"){
			alert("JEESHOP회원이 되신 걸 환영합니다. \n로그인해주세요!");
			
		} else if("${msg}"=="LOGOUT SUCCESS"){
			alert("로그아웃 되었습니다.");
			
		} else if("${msg}"=="MODIFY_SUCCESS"){
			alert("회원정보가 수정되었습니다.");
			
		} else if("${msg}"=="PWCHANGE_SUCCESS"){
			alert("비밀번호가 변경되었습니다.");
			
		} else if("${msg}"=="DELETE_SUCCESS"){
			alert("회원탈퇴 되었습니다. 이용해주셔서 감사합니다.");
		}
	</script>
</head>

<body>
	<!-- Header -->
  	<%@include file="/WEB-INF/views/include/header.jsp" %>

	<!-- Main -->
	<main>
	    <!-- slider Area Start -->
	    <div class="slider-area ">
	        
	        <!-- Mobile Menu -->
	        <div class="slider-active">
	            <div class="single-slider slider-height" data-background="assets/img/hero/h1_hero.jpg">
	                <div class="container">
	                    <div class="row d-flex align-items-center justify-content-between">
	                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 d-none d-md-block">
	                            <div class="hero__img" data-animation="bounceIn" data-delay=".4s">
	                                <img src="assets/img/hero/hero_man.png" alt="">
	                            </div>
	                        </div>
	                        <div class="col-xl-5 col-lg-5 col-md-5 col-sm-8">
	                            <div class="hero__caption">
	                                <h1 data-animation="fadeInRight" data-delay=".6s">New <br> Outer</h1>
	                                <p data-animation="fadeInRight" data-delay=".8s">Best Outer By 2020!</p>
	                                
	                                <!-- Hero-btn -->
	                                <div class="hero__btn" data-animation="fadeInRight" data-delay="1s">
	                                    <a href="industries.html" class="btn hero-btn">Shop Now</a>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="single-slider slider-height" data-background="assets/img/hero/h1_hero.jpg">
	                <div class="container">
	                    <div class="row d-flex align-items-center justify-content-between">
	                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 d-none d-md-block">
	                            <div class="hero__img" data-animation="bounceIn" data-delay=".4s">
	                                <img src="assets/img/hero/hero_man.png" alt="">
	                            </div>
	                        </div>
	                        <div class="col-xl-5 col-lg-5 col-md-5 col-sm-8">
	                            <div class="hero__caption">
	                                <span data-animation="fadeInRight" data-delay=".4s">60% Discount</span>
	                                <h1 data-animation="fadeInRight" data-delay=".6s">Winter <br> Collection</h1>
	                                <p data-animation="fadeInRight" data-delay=".8s">Best Cloth Collection By 2020!</p>
	                                
	                                <!-- Hero-btn -->
	                                <div class="hero__btn" data-animation="fadeInRight" data-delay="1s">
	                                    <a href="industries.html" class="btn hero-btn">Shop Now</a>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- slider Area End-->
	    
	    <!-- Category Area Start-->
	    <section class="category-area section-padding30">
	        <div class="container-fluid">
	            <!-- Section Tittle -->
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="section-tittle text-center mb-85">
	                        <h2>Shop by Category</h2>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-xl-4 col-lg-6">
	                    <div class="single-category mb-30">
	                        <div class="category-img">
	                            <img src="assets/img/categori/cat1.jpg" alt="">
	                            <div class="category-caption">
	                                <h2>Outer</h2>
	                                <span class="best"><a href="#">Best New Deals</a></span>
	                                <span class="collection">2020 New</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="col-xl-4 col-lg-6">
	                    <div class="single-category mb-30">
	                        <div class="category-img text-center">
	                            <img src="assets/img/categori/cat2.jpg" alt="">
	                            <div class="category-caption">
	                                <span class="collection">SALE!</span>
	                                <h2>TOP</h2>
	                                <span class="best"><a href="#">Best New Deals</a></span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-xl-4 col-lg-6">
	                    <div class="single-category mb-30">
	                        <div class="category-img">
	                            <img src="assets/img/categori/cat3.jpg" alt="">
	                            <div class="category-caption">
	                                <h2>Bottom</h2>
	                                <span class="best"><a href="#">Best New Deals</a></span>
	                                <span class="collection">New Collection</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    <!-- Category Area End-->
	    
	    <!-- Shop Method Start-->
	    <div class="shop-method-area section-padding30">
	        <div class="container">
	            <div class="row d-flex justify-content-between">
	                <div class="col-xl-3 col-lg-3 col-md-6">
	                    <div class="single-method mb-40">
	                        <i class="ti-package"></i>
	                        <h6>Free Shipping Method</h6>
	                        <p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
	                    </div>
	                </div>
	                <div class="col-xl-3 col-lg-3 col-md-6">
	                    <div class="single-method mb-40">
	                        <i class="ti-unlock"></i>
	                        <h6>Secure Payment System</h6>
	                        <p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
	                    </div>
	                </div> 
	                <div class="col-xl-3 col-lg-3 col-md-6">
	                    <div class="single-method mb-40">
	                        <i class="ti-reload"></i>
	                        <h6>Secure Payment System</h6>
	                        <p>aorem ixpsacdolor sit ameasecur adipisicing elitsf edasd.</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Shop Method End-->
	 </main>
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>

	<!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
</body>
</html>