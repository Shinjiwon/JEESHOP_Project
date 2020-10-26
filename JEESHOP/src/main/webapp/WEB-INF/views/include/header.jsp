<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

	<!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<!-- Handlebars -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<!-- Handlebar Template -->
	<script id="subCateListTemplate" type="text/x-handlebars-template">
	{{#each .}}
		<li><a href="/product/list?cate_code={{cate_code}}">{{cate_name}}</a></li>
	{{/each}}
	</script>
	
	<!-- 2차 카테고리 템플릿 적용 -->
	<script>
		$(document).ready(function(){
	
		    $(".mainCategory").hover(function(){
		        var mainCateCode = $(this).val();
		        var url = "/product/subCateList/" + mainCateCode;
	
		        // REST방식 전송
		        $.getJSON(url, function(data){
		             // 전송 받은 데이터로 subCategory 템플릿에 적용
		             subCateList(data, $(".subCategory"), $("#subCateListTemplate"));
		        });
		    });
		});
	
		var subCateList = function(subCateStr, target, templateObject){
		    var template = Handlebars.compile(templateObject.html());
		    var lis = template(subCateStr);
		    
		    target.empty();
		    target.append(lis);
		}
		
	</script>
	
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
		                                
		                                <!-- 카테고리 출력 -->
		                                <c:forEach items="${userCateList}" var="list">
		                                <li class="mainCategory" value="${list.cate_code}">
		                                <a href="/product/list?cate_code=${list.cate_code}">
		                                ${list.cate_name}
		                                </a>
		                                  <ul class="submenu subCategory"></ul>  
		                                </li>
		                                </c:forEach>
		                                
		                                <li><a href="#">COMMUNITY</a>
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