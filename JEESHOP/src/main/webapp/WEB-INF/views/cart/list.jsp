<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 장바구니</title>
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
		    <div class="single-slider slider-height2 d-flex align-items-center" data-background="/assets/img/hero/category.jpg">
		        <div class="container">
		            <div class="row">
		                <div class="col-xl-12">
		                    <div class="hero-cap text-center">
		                        <h1>장바구니</h1>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		  </div>
		  <!-- slider Area End-->
		
		  <!--================Cart Area =================-->
		  <section class="cart_area section_padding">
		    <div class="container">
		      <div class="cart_inner">
			    <button type="button" class="genric-btn info-border circle">
			    	선택상품 구매</button>
			    <button type="button" class="genric-btn danger-border circle">
			    	선택상품 삭제</button>
		        <div class="table-responsive">
		          <table class="table">
		            <thead>
		              <tr>
		                <th scope="col">
		                	<input type="checkbox" id="checkAll" checked="checked"/>
	                	</th>
		                <th scope="col">번호</th>
		                <th scope="col">이미지</th>
		                <th scope="col">상품명</th>
		                <th scope="col">판매가</th>
		                <th scope="col">할인가</th>
		                <th scope="col">수량</th>
		                <th scope="col"></th>
		              </tr>
		            </thead>
		            <tbody>
		            <c:forEach items="${cartProductList}" var="cartProductVO">
		              <tr>
		              	<td>
		              		<input type="checkbox" name="check" class="check" checked="checked" >
	              		</td>
		                <td><h5>${cartProductVO.pro_num}</h5></td>
		                <td>
		                <img src="/product/displayFile?fileName=${cartProductVO.pro_img}" style="width:50px; height: 50px;">
		                </td>
		                <td><h5>${cartProductVO.pro_name}</h5> </td>
		                <td><h5>￦<fmt:formatNumber value="${cartProductVO.pro_price}" pattern="###,###,###" /></h5></td>
		                <td><h5>￦<fmt:formatNumber value="${(cartProductVO.pro_price)-(cartProductVO.pro_price*(cartProductVO.pro_discount*0.01))}" pattern="###,###,###" />
		                        	(${cartProductVO.pro_discount}%)</h5></td>
		                <td>
		                    <input type="number" value="${cartProductVO.cat_amount}" style="width: 50px;">
		                </td>
		                <td></td>
		              </tr>
		              </c:forEach>
		              
		              <tr class="bottom_button">
		                <td></td>
		                <td></td>
		                <td></td>
		                <td></td>
		                <td></td>
		                <td></td>
		                <td></td>
		                <td>
		                  <div class="cupon_text float-right">
		                    <button class="btn_1">수량 변경</button>
		                  </div>
		                </td>
		              </tr>
		            </tbody>
		          </table>
		          
		          <table class="table" style="text-align: center;">
		          	<tr>
		          		<th><h5>총 판매가</h5></th>
		          		<th><h5>총 할인금액</h5></th>
		          		<th><h5>총 결제금액</h5></th>
		          	</tr>
		          	
		          	<tr>
		          		<td></td>
		          		<td></td>
		          		<td></td>
		          	</tr>
		          </table>
		          <div class="checkout_btn_inner float-right">
		            <button class="btn_1">전체상품 구매</button>
		          </div>
		        </div>
		      </div>
		  </section>	
			<!-- 장바구니 리스트 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>

</body>
</html>

