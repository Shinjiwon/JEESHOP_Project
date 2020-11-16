<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 주문상세</title>
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
		                        <h1>주문 상세</h1>
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
		        <div class="table-responsive">
		          <table class="table">
		            <tbody>
		            <!-- 상품이 존재하지 않을 경우 -->
		            <c:if test="${empty orderList}">
		            <tr>
		            	<td colspan="9">
		            		<p style="padding:50px 0px; text-align: center;">구매하신 상품이 존재하지 않습니다.</p>
		            	</td>
		            </tr>
		            </c:if>
		            
		            <!-- 상품이 존재할 경우 -->
		              <tr>
		              	<td colspan="4" style="text-align: left;">
		              		<h4>
		              		주문날짜: <fmt:formatDate value="${order.ord_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
		              		［주문번호: ${order.ord_num}］
		              		</h4>
		              	</td>
		              	<td>
		              	
		              </tr>
		              <tr style="text-align: center;">
						<td><h5>IMAGE</h5></td>
						<td><h5>상품명</h5></td>
						<td><h5>수량</h5></td>
						<td><h5>결제금액</h5></td>
						<td><h5>리뷰</h5></td>
					 </tr>
		            <c:forEach items="${orderList}" var="orderVO" varStatus="status">
		              <tr style="text-align: center;">
		                <td>
			                <a href="/product/read?pro_num=${orderVO.pro_num}">
			                <img src="/product/displayFile?fileName=${orderVO.pro_img}" style="width:120px; height: 130px;">
			                </a>
		                </td>
		                <td>
		                	<a href="/product/read?pro_num=${orderVO.pro_num}">
		                	<h5>${orderVO.pro_name}</h5>
		                	</a>
                		</td>
		                <td>
		                	<h5>${orderVO.ord_amount}</h5>	
                       	</td>
		                <td style="text-align: center;">
		                    <h5>￦<fmt:formatNumber value="${orderVO.ord_price}" pattern="###,###,###" /></h5>
		                </td>
		                <td>
		                	<button class="genric-btn success-border circle" type="button"
	                				onclick="location.href='/product/read?pro_num=${orderVO.pro_num}';" value="${orderVO.pro_num}">
		                		상품후기 작성</button>
		                </td>
		              </tr>
		              </c:forEach>
		              </tbody>
		          </table><br><br>

	        <%-- 주문 정보 --%>
			<div class="orderInfo" style="width:100%;" > 
				<div class="userInfo" style="display:inline-block; float:left; width:60%; padding: 0% 5%;">
					<div class="container" style="width:100%;">
						<span>[주문 정보]</span>
						<div class="form-group">
							<label for="inputName">☞ 이름</label> <input type="text"
								class="form-control" value="${order.ord_name}" readonly>
						</div>
						<div class="form-group">
							<label for="inputMobile">☞ 휴대폰 번호</label> <input type="tel"
								class="form-control" value="${order.ord_phone}" readonly>
						</div>
						<div class="form-group">
							<label for="inputAddr">☞ 주소</label> <br />
							<input type="text" id="sample2_postcode" name="ord_postcode" class="form-control" 
								value = "${order.ord_postcode}" 
								style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
							<br>
							<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
								value = "${order.ord_addr}" 
								placeholder="주소" style=" margin:3px 0px;" readonly>
							<input type="text" id="sample2_detailAddress" name="ord_detaddr" class="form-control" 
								value = "${order.ord_deaddr}"
								readonly >
							<input type="hidden" id="sample2_extraAddress" class="form-control" 
								placeholder="참고항목">
						</div>
					</div>
					<%-- 주문 금액 --%>
				<div class="table-responsive">
				<table class="table" style="text-align: center;">
		          	<tr>
		          		<th><h5>총 결제금액(할인 포함)</h5></th>
		          	</tr>
		          	
		          	<tr>
		          		<td><h5>￦<fmt:formatNumber value="${order.ord_price}" pattern="###,###,###" /></h5></td>
		          	</tr>
		          </table>
				</div>
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
	

</body>
</html>

