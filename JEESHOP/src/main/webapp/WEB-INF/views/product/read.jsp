<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 상품상세</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
	<!-- Handlebars -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<style>
		#star_grade a{
			
			font-size:22px;
	        text-decoration: none;
	        color: lightgray;
		}
		
		#star_grade a.on{
	        
	        color: orange;
		}
	</style>
	
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

			<!-- 상품 상세 -->
				<section class="product-details spad">
		        	<div class="container">
		            	<div class="row">
		                	<div class="col-lg-6"><br><br>
			                    <img src="/product/displayFile?fileName=${vo.pro_img}">
		                	</div>
		                	
		                	<div class="col-lg-6"><br><br>
		                	<div class="card_area">
		                        <h2 style="text-align: center;">${vo.pro_name}</h2>
		                        </div>
		                        
		                        <div class="card_area" style="text-align: center;">
		                        <h4>판매가: ￦<fmt:formatNumber value="${vo.pro_price}" pattern="###,###,###" /> </h4><br>
		                        <h4 style="color: blue;">
		                        	할인가: ￦<fmt:formatNumber value="${(vo.pro_price)-(vo.pro_price*(vo.pro_discount*0.01))}" pattern="###,###,###" />
		                        	(${vo.pro_discount}%)
		                        	</h4>
	                        	</div>
		                        
	                            <div class="card_area">
				                <div class="product_count_area">
				                	<form method="get" action="/order/buy" >
				                    <p style="text-align: center;">Quantity</p>
				                    <div class="product_count d-inline-block">
				                        <span class="product_count_item inumber-decrement"> <i class="ti-minus"></i></span>
				                        <input class="product_count_item input-number" type="text" value="1" min="0" max="10">
				                        <span class="product_count_item number-increment"> <i class="ti-plus"></i></span>
				                    </div>
				                </div><br><br>
				                
				                  <input type="hidden" id="pro_num" name="pro_num" value="${vo.pro_num}" />
				                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="btn_buy" class="btn btn-default">구매하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
				                  <button type="button" id="btn_cart" class="btn_3">장바구니</button>&nbsp;&nbsp;&nbsp;&nbsp;
				                  <button type="button" id="btn_wish" class="btn_3">위시리스트</button>&nbsp;&nbsp;&nbsp;&nbsp;
		                    	  </form>
		                    	</div><br>
		        		</div><br>
		        		
		        		<!-- 상품 상세 -->
		        		<div class="col-lg-12"><br><br><br><br>
			        		<h2 style="text-align: center;">Detail</h2><br>
								<div contenteditable="false" style="border: none; padding: 20px; text-align: center;">
									${vo.pro_detail}
								</div>
						</div><br>
						
						<!-- 상품 후기 -->
						<div class="col-lg-12"><br><br>
						<h3 style="text-align: center; color: steelblue">Review</h3><br>
						<div class="rating">
							<p id="star_grade">
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
							</p>
						</div>
						<textarea id="reviewContent" rows="3" style="border-color: steelblue; width:100%; color: black;"></textarea><br>
						<br><button class="genric-btn info-border circle" id="btn_write_review" type="button">상품후기쓰기</button>
						<button class="genric-btn info-border circle arrow" id="btn_see_review" type="button">상품후기보기</button>
						</div>
						
						<!-- 상품후기 테이블 -->
						
				        <div class="table-responsive">
				          <br><table class="table">
				            <thead>
				              <tr>
				                <th scope="col" style="text-align: center;">ID</th>
				                <th scope="col" style="text-align: center;">등록일</th>
				                <th scope="col" style="text-align: center;">평점</th>
				                <th scope="col" style="text-align: center;">내용</th>
				              </tr>
				            </thead>
				            
				            <tbody>
				              <td colspan="4" style="text-align: center;">
				              	등록된 상품후기가 존재하지 않습니다.
			              	  </td>
				            </tbody>
				          </table>
				        </div>
	    		</section>
			<!-- 상품 상세 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<!-- 유효성 검사 -->
	<script type="text/javascript" src="/js/product/proread.js"></script>
</body>
</html>

