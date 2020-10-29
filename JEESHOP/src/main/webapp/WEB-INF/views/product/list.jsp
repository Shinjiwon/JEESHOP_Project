<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 상품리스트</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

			<!-- 상품 리스트 -->
					<div class="single-slider slider-height2 d-flex align-items-center" data-background="/assets/img/hero/category.jpg">
		            <div class="container">
		                <div class="row">
		                    <div class="col-xl-12">
		                        <div class="hero-cap text-center">
		                            <h1>${cate_name}</h1>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        
				 <section class="product_list section_padding">
			        <div class="container">
			            <div class="row">
			                <div class="col-md-12">
			                    <div class="product_list">
			                        <div class="row">
			                        	<!-- 상품이 존재 하지 않을 경우 -->
			                        	<c:if test="${empty proList}">
			                        	<span style="text-align: center;">등록된 상품이 존재하지 않습니다.</span>
			                        	</c:if>
			                        	
			                        	<!-- 상품이 존재 할 경우 -->
			                        	<c:forEach items="${proList}" var="productVO">
			                            <div class="col-lg-3 col-sm-3">
			                                <div class="single_product_item">
			                                    <img src="/product/displayFile?fileName=${productVO.pro_img}" width="200px" height="250px">
			                                    <h3><a href="/product/read${pm.makeQuery(pm.cri.page)}&pro_num=${productVO.pro_num}&cate_code=${cate_code}">
			                                    		${productVO.pro_name}
	                                    		</a></h3>
			                                    <span>판매가: <fmt:formatNumber value="${productVO.pro_price}" pattern="###,###,###" />원<br></span>
			                                    <span style="color: blue;">
			                                    	할인가: <fmt:formatNumber value="${(productVO.pro_price)-(productVO.pro_price*(productVO.pro_discount*0.01))}" pattern="###,###,###" />원
		                                    		(${productVO.pro_discount}%)
		                                    	</span>
			                                </div>
			                            </div>
			                            </c:forEach>
			                        </div>
			                        	<!-- 페이징 -->
				                        <nav class="blog-pagination justify-content-center d-flex">
				                            <ul class="pagination">
				                            	<c:if test="${pm.prev}">
				                                <li class="page-item">
				                                    <a href="list${pm.makeQuery(pm.startPage-1)}&cate_code=${cate_code}" class="page-link" aria-label="Previous">
				                                        <i class="ti-angle-left"></i>
				                                    </a>
				                                </li>
				                                </c:if>
				                                
				                                <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="idx">
				                                <li class="page-item" <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
				                                    <a href="list${pm.makeQuery(idx)}&cate_code=${cate_code}" class="page-link">${idx}</a>
				                                </li>
				                                </c:forEach>
				                                
				                                <c:if test="${pm.next && pm.endPage > 0}">
				                                <li class="page-item">
				                                    <a href="list${pm.makeQuery(pm.endPage +1)}&cate_code=${cate_code}" class="page-link" aria-label="Next">
				                                        <i class="ti-angle-right"></i>
				                                    </a>
				                                </li>
				                                </c:if>
				                            </ul>
				                        </nav>
									</div>
								</div>
		                    </div>
		                </div>
			    </section> 
			<!-- 상품 리스트 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>

</body>
</html>

