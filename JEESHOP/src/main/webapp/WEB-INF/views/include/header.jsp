<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<div class="header-area">
		<div class="main-header ">
		    <div class="header-top top-bg d-none d-lg-block">
		       <div class="container-fluid">
		           <div class="col-xl-12">
		                <div class="row d-flex justify-content-between align-items-center">
		                    <div class="header-info-left d-flex">
		                    </div>
		                    <div class="header-info-right">
		                       <ul> 
		                       	   <!-- 로그인 안 한 상태 -->
		                       	   <c:if test="${sessionScope.user == null}">
		                       	   <li><a href="/member/join">회원가입</a></li>
		                       	   </c:if>
		                       	      
		                       	   <!-- 로그인 한 상태 -->
		                       	   <c:if test="${sessionScope.user != null}">                                      
					               <li><p>${sessionScope.user.mb_id}님</p></li>
		                           <li><a href="/member/pwChk?url=modify">회원정보 수정 </a></li>
		                           <li><a href="/member/pwChk?url=pwChange">비밀번호 변경 </a></li>
		                           <li><a href="#">장바구니</a></li>
		                           <li><a href="#">주문조회</a></li>
		                           <li><a href="#">위시리스트</a></li>
		                           </c:if>
		                       </ul>
		                    </div>
		                </div>
		           </div>
		       </div>
		    </div>
		   <div class="header-bottom  header-sticky">
		        <div class="container-fluid">
		            <div class="row align-items-center">
		                <!-- Logo -->
		                <div class="col-xl-1 col-lg-1 col-md-1 col-sm-3">
		                    <div class="logo">
		                      <a href="/"><h1>JEESHOP</h1></a>
		                    </div>
		                </div>
		                <div class="col-xl-6 col-lg-8 col-md-7 col-sm-5">
		                    <!-- Main-menu -->
		                    <div class="main-menu f-right d-none d-lg-block">
		                        <nav>                                                
		                            <ul id="navigation">                                                                                                                                     
		                                <li><a href="/">Home</a></li>
		                                <li class="hot"><a href="#">아우터</a>
		                                    <ul class="submenu">
		                                        <li><a href="product_list.html">가딘건</a></li>
		                                        <li><a href="single-product.html">자켓</a></li>
		                                        <li><a href="single-product.html">점퍼</a></li>
		                                    </ul>
		                                </li>
		                                <li><a href="blog.html">상의</a>
		                                    <ul class="submenu">
		                                        <li><a href="blog.html">기본티</a></li>
		                                        <li><a href="single-blog.html">맨투맨</a></li>
		                                        <li><a href="single-blog.html">후드티/집업</a></li>
		                                    </ul>
		                                </li>
		                                <li><a href="#">하의</a>
		                                    <ul class="submenu">
		                                        <li><a href="login.html">슬랙스</a></li>
		                                        <li><a href="cart.html">청바지</a></li>
		                                        <li><a href="elements.html">면바지</a></li>
		                                    </ul>
		                                </li>
		                                <li><a href="contact.html">COMMUNITY</a>
		                                	<ul class="submenu">
		                                        <li><a href="#">공지사항</a></li>
		                                        <li><a href="#">질문하기</a></li>
		                                    </ul>
		                                </li>
		                            </ul>
		                        </nav>
		                    </div>
		                </div> 
		                <div class="col-xl-5 col-lg-3 col-md-3 col-sm-3 fix-card">
		                    <ul class="header-right f-right d-none d-lg-block d-flex justify-content-between">
		                        <li class="d-none d-xl-block">
		                            <div class="form-box f-right ">
		                                <input type="text" name="Search" placeholder="상품검색">
		                                <div class="search-icon">
		                                    <i class="fas fa-search special-tag"></i>
		                                </div>
		                            </div>
		                         </li>
		                        <li>
		                            <div class="shopping-card">
		                                <a href="cart.html"><i class="fas fa-shopping-cart"></i></a>
		                            </div>
		                        </li>
		                        <!-- 로그인 안 한 상태 -->
		                        <c:if test="${sessionScope.user == null}">
		                       <li class="d-none d-lg-block"> <a href="/member/login" class="btn header-btn">로그인</a></li>
		                       </c:if>
		                       
		                       <!-- 로그인 한 상태 -->
		                        <c:if test="${sessionScope.user != null}">
		                       <li class="d-none d-lg-block"> <a href="/member/logout" class="btn header-btn">로그아웃</a></li>
		                       </c:if>
		                    </ul>
		                </div>
		                <!-- Mobile Menu -->
		                <div class="col-12">
		                    <div class="mobile_menu d-block d-lg-none"></div>
		                </div>
		            </div>
		        </div>
		   </div>
		</div>
	</div>
</head>