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
	
	<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<tr>
			<td>{{rew_num}}</td>
			<td>{{mb_id}}</td>
			<td>{{prettifyDate rew_date}}</td>
			<td><input type="hidden" value={{rew_score}} name="rew_score">{{checkRating rew_score}}</td>
			<td>{{rew_content}}</td>
			<td>{{eqReplyer mb_id rew_num}}</td>
		</tr>
	{{/each}}
	</script>
	
	<style>
		#star_grade a{
			font-size:22px;
	        text-decoration: none;
	        color: lightgray;
		}
		
		#star_grade a.on{
	        color: orange;
		}
		
		#star_grade_modal a{
			font-size:22px;
	        text-decoration: none;
	        color: lightgray;
		}
		
		#star_grade_modal a.on{
	        color: orange;
		}
		
		td{
			text-align: center;
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
				                	<form method="get" action="/order/buy">
				                    <p style="text-align: center;">수량</p>
				                    <div class="product_count d-inline-block">
				                        <span class="product_count_item inumber-decrement"> <i class="ti-minus"></i></span>
				                        <input id="ord_amount" name="ord_amount" class="product_count_item input-number" 
				                        		type="text" value="1">
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
						<h3 style="text-align: center; color: grey;">Review</h3><br>
						<div class="rating">
							<p id="star_grade">
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
						        <a href="#">★</a>
							</p>
						</div>
						<textarea id="reviewContent" rows="3" style="border-color: grey; width:100%; color: black;"></textarea><br>
						</div>
						
						<!-- 상품후기 테이블 -->
				        <div class="table-responsive">
				        <br><button class="genric-btn info-border circle" id="btn_write_review" type="button">상품후기쓰기</button>
						<button class="genric-btn info-border circle arrow" id="btn_see_review" type="button">상품후기보기</button>
						<small id='replycntSmall' style="color: grey;"> [ ${totalReview} ] </small><br>
						
						<!-- 상품리뷰 데이터가 없을 때 -->
				          <br><table class="table" id="noReview" style="display: none;">
				            <thead>
				              <tr>
				                <th scope="col" style="text-align: center;">댓글번호</th>
				                <th scope="col" style="text-align: center;">ID</th>
				                <th scope="col" style="text-align: center;">등록일</th>
				                <th scope="col" style="text-align: center;">평점</th>
				                <th scope="col" style="text-align: center;">내용</th>
				              </tr>
				            </thead>
				            <tbody>
				              <td colspan="5" style="text-align: center;">
				              	등록된 상품후기가 존재하지 않습니다.
			              	  </td>
				            </tbody>
				          </table>
						 </div>
						 
						 <!-- 상품리뷰가 있을 때 -->
						 <div class="table-responsive" id="reviewListPage" style="display: none;">
				          <br><table class="table" id="reviewList" >
				            <thead>
				              <tr>
				              	<th scope="col" style="text-align: center;">댓글번호</th>
				                <th scope="col" style="text-align: center;">ID</th>
				                <th scope="col" style="text-align: center;">등록일</th>
				                <th scope="col" style="text-align: center;">평점</th>
				                <th scope="col" style="text-align: center;">내용</th>
				                <th scope="col" style="text-align: center;"></th>
				              </tr>
				            </thead>
				            <tbody>
				            <!-- 리뷰 추가 위치 -->
				            </tbody>
				          </table>
				          <!-- 리뷰테이블 페이징 -->
				          <nav class="blog-pagination justify-content-center d-flex">
				          <ul class="pagination">
					          
                          </ul>
                          </nav>
				          </div>
				          
				          <!-- 리뷰 수정 팝업 -->
				          <div id="modifyModal" class="modal-message" role="dialog" style="display: none;">
							  <div class="modal-dialog">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header" >
							        <div class="modal-title">
							        <button type="button" class="close" onclick="modalreview()">&times;</button>
										<p id="star_grade_modal">
									        <a href="#">★</a>
									        <a href="#">★</a>
									        <a href="#">★</a>
									        <a href="#">★</a>
									        <a href="#">★</a>
										</p>
							        </div>
							      </div>
							      <div class="modal-body" data-rew_num>
							        <p><input type="text" id="replytext" class="form-control" style="height: 80px;"></p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="genric-btn info-border circle" id="btn_modal_modify">수정</button>
							        <button type="button" class="genric-btn primary-border circle" onclick="modalreview()">닫기</button>
							      </div>
							    </div>
							  </div>
							</div>
				        </div>
	    		</section>
			<!-- 상품 상세 -->
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<!-- 사용자 정의 헬퍼 -->
	<script>
	$(document).ready(function(){

	    /* 사용자 정의헬퍼 */
	    // 메게변수로 받은 timeValue를 원하는 날짜 형태로 바꿔준다.
	    Handlebars.registerHelper("prettifyDate", function(timeValue){
	        var dateObj = new Date(timeValue);
	        var year = dateObj.getFullYear();
	        var month = dateObj.getMonth() + 1;
	        var date = dateObj.getDate();
	        return year + "." + month + "." + date;
	    });

	    // 메게변수로 받은 별점을 출력
	    Handlebars.registerHelper("checkRating", function(rating){

	        var stars = "";

	        switch(rating){
	            case 1:
	                 stars="★☆☆☆☆";
	                 break;

	            case 2:
	                 stars="★★☆☆☆";
	                 break;

	            case 3:
	                 stars="★★★☆☆";
	                 break;

	            case 4:
	                 stars="★★★★☆";
	                 break;

	            case 5:
	                 stars="★★★★★";
	                 break;

	            default:
	                stars="☆☆☆☆☆";
	        }
	        return stars;
	    });
	     
	    /* 로그인 한 아이디와 리뷰의 아이디 확인 후, 수정/삭제 버튼 활성화 */
	    Handlebars.registerHelper("eqReplyer", function(replyer, rew_num){
	         var btnHtml = "";
	         var mb_id = "${sessionScope.user.mb_id}";

	         if(replyer=="${user.mb_id}"){
	              btnHtml = "<button class='genric-btn info-border circle replyLi' data-target='#modifyModal'>"
	                         +"수정" + "</button>&nbsp;"
	                         +"<button class='genric-btn danger-border circle'"
	                         + "onclick='deleteReview("+rew_num+");'"
	                         +"type='button'>삭제</button>";
	         }
	         return new Handlebars.SafeString(btnHtml);
	    });
	});
	
	function modalreview(){
		
		$("#modifyModal").hide();
	}
	</script>
	
	<!-- 유효성 검사 -->
	<script type="text/javascript" src="/js/product/proread.js"></script>
</body>
</html>

