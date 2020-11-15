<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		      <form method="post" action="/order/buyFromCart" >
			    <button type="submit" class="genric-btn info-border circle" id="btn_buy_check">
			    	선택상품 구매</button>
			    <button type="button" class="genric-btn danger-border circle" id="btn_delete_check">
			    	선택상품 삭제</button>
		        <div class="table-responsive">
		          <table class="table">
		            <thead>
		              <tr>
		                <th scope="col">
		                	<input type="checkbox" id="checkAll" checked="checked"/>
	                	</th>
		                <th scope="col">번호</th>
		                <th scope="col"></th>
		                <th scope="col">상품명</th>
		                <th scope="col">판매가</th>
		                <th scope="col">할인가</th>
		                <th scope="col"></th>
		                <th scope="col"></th>
		                <th scope="col">수량</th>
		              </tr>
		            </thead>
		            <tbody>
		            <!-- 장바구니에 상품이 존재하지 않을 경우 -->
		            <c:if test="${empty cartProductList}">
		            <tr>
		            	<td colspan="9">
		            		<p style="padding:50px 0px; text-align: center;">장바구니에 담긴 상품이 없습니다.</p>
		            	</td>
		            </tr>
		            </c:if>
		            
		            <!-- 장바구니에 상품이 존재할 경우 -->
		            <c:set var="i" value="${fn:length(cartProductList)}" />
		            <c:forEach items="${cartProductList}" var="cartProductVO">
		              <tr>
		              	<td>
		              		<input type="checkbox" value="${cartProductVO.cat_code}" name="check" class="check" checked="checked" >
		              		<input type="hidden" id="pro_num_${cartProductVO.cat_code}" name="pro_num" value="${cartProductVO.pro_num}" >
							<input type="hidden" name="cat_amount" value="${cartProductVO.cat_amount}" >
							<input type="hidden" name="cat_code" value="${cartProductVO.cat_code}" >
	              		</td>
		                <td><h5>${i}</h5></td>
		                <td>
			                <a href="/product/read?pro_num=${cartProductVO.pro_num}&cate_code=${cate_code}">
			                <img src="/product/displayFile?fileName=${cartProductVO.pro_img}" style="width:70px; height: 70px;">
			                </a>
		                </td>
		                <td>
		                	<a href="/product/read?pro_num=${cartProductVO.pro_num}&cate_code=${cate_code}">
		                	<h5>${cartProductVO.pro_name}</h5>
		                	</a>
                		</td>
		                <td>
			                <h5>￦<fmt:formatNumber value="${cartProductVO.pro_price}" pattern="###,###,###" /></h5>
			                <input type="hidden" name="price_${cartProductVO.cat_code}" value="${cartProductVO.pro_price}" />
		                </td>
		                <td>
		                	<h5>￦<fmt:formatNumber value="${(cartProductVO.pro_price)-(cartProductVO.pro_price*(cartProductVO.pro_discount*0.01))}" pattern="###,###,###" />
		                        	(${cartProductVO.pro_discount}%)</h5>
		                    <input type="hidden" name="discount_${cartProductVO.cat_code}" value="${(cartProductVO.pro_price)-(cartProductVO.pro_price*(cartProductVO.pro_discount*0.01))}" />    	
                       	</td>
		                <td colspan="3" style="text-align: center;">
		                    <input type="number" name="cat_amount_${cartProductVO.cat_code}"
		                    	value="${cartProductVO.cat_amount}" style="width: 50px;">
		                    <button type="button" name="btn_modify" class="genric-btn primary-border small"
		                    	value="${cartProductVO.cat_code}">
		                    	변경
	                    	</button>
		                </td>
		                <c:set var="i" value="${i-1}" />
		              </tr>
		              </c:forEach>
		              </tbody>
		          </table><br><br>
		          </form>
		          <table class="table" style="text-align: center;">
		          	<tr>
		          		<th><h5>총 판매가</h5></th>
		          		<th><h5>총 결제금액(할인 포함)</h5></th>
		          	</tr>
		          	
		          	<tr>
		          		<td><h5 id="totalPrice">0</h5></td>
		          		<td><h5 id="totalCount">0</h5></td>
		          	</tr>
		          </table>
		          <div class="checkout_btn_inner float-right">
		            <button class="btn_1">상품 구매</button>
		          </div>
		        </div>
		      </div>
		  </section>	
			<!-- 장바구니 리스트 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<!-- 버튼 클릭 이벤트 -->
	<script type="text/javascript" src="/js/cart/cartlist.js"></script>

</body>
</html>

