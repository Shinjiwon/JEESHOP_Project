<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>JEESHOP 상품주문</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
      
	<!-- CSS here -->
	<%@include file="/WEB-INF/views/include/estorecss.jsp" %>
	
</head>

<body>
	<!-- Header -->
	   <%@include file="/WEB-INF/views/include/header.jsp" %>

		<!-- 상품구매 리스트 -->
		<!-- slider Area Start-->
		  <div class="slider-area ">
		    <!-- Mobile Menu -->
		    <div class="single-slider slider-height2 d-flex align-items-center" data-background="/assets/img/hero/category.jpg">
		        <div class="container">
		            <div class="row">
		                <div class="col-xl-12">
		                    <div class="hero-cap text-center">
		                        <h1>상품 구매</h1>
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
		      <form id="orderForm" method="post" action="/order/orderFromCart">
		      <button type="button" class="genric-btn danger-border circle" 
		      			id="btn_delete_check">
			    	선택상품 삭제</button>
		        <div class="table-responsive">
		          <br><table class="table" id="ordertbl">
		            <thead id="thead">
		              <tr>
		                <th scope="col">
		                	<input type="checkbox" id="checkAll" checked="checked">
	                	</th>
		                <th scope="col"></th>
		                <th scope="col">상품명</th>
		                <th scope="col">판매가</th>
		                <th scope="col">할인가</th>
		                <th scope="col">수량</th>
		              </tr>
		            </thead>
		            <tbody>
		            <!-- 주문할 상품이 존재하지 않을 경우 -->
		            
		            <!-- 주문할 상품이 존재할 경우 -->
		            <c:forEach items="${productList}" var="productVO" varStatus="i">
		              <tr id="productVO_${productVO.pro_num}" class="productRow">
		              	<td>
		              		<input type="checkbox" value="${productVO.pro_num}" name="check" class="check" checked="checked" >
		              		<input type="hidden" id="amount_${productVO.pro_num}" name="orderDetailList[${i.index}].ord_amount" value="${amountList[i.index]}" >
							<input type="hidden" name="orderDetailList[${i.index}].pro_num" value="${productVO.pro_num}" >
							<input type="hidden" name="orderDetailList[${i.index}].ord_price" 
							value='<fmt:formatNumber value="${((productVO.pro_price)-(productVO.pro_price*(productVO.pro_discount*0.01)))*(amountList[i.index])}" 
							pattern="0000"/>'>
							<input type="hidden" name="ord_price" 
							value='<fmt:formatNumber value="${((productVO.pro_price)-(productVO.pro_price*(productVO.pro_discount*0.01)))*(amountList[i.index])}" 
							pattern="0000"/>'>
	              		</td>
		                <td>
			                <a href="/product/read?pro_num=${productVO.pro_num}&cate_code=${cate_code}">
			                <img src="/product/displayFile?fileName=${productVO.pro_img}" style="width:70px; height: 70px;">
			                </a>
		                </td>
		                <td>
		                	<a href="/product/read?pro_num=${productVO.pro_num}&cate_code=${cate_code}">
		                	<h5>${productVO.pro_name}</h5>
		                	</a>
                		</td>
		                <td>
			                <h5>￦<fmt:formatNumber value="${productVO.pro_price}" pattern="###,###,###" /></h5>
			                <input type="hidden" name="price" value="${productVO.pro_price}" />
		                </td>
		                <td>
		                	<h5>￦<fmt:formatNumber value="${(productVO.pro_price)-(productVO.pro_price*(productVO.pro_discount*0.01))}" pattern="###,###,###" />
		                        	(${productVO.pro_discount}%)</h5>
		                    <input type="hidden" name="discount" value="${(productVO.pro_price)-(productVO.pro_price*(productVO.pro_discount*0.01))}" />    	
                       	</td>
		                <td>
		                <h5>${amountList[i.index]}</h5>
		                <input type="hidden" name="amount" value="${amountList[i.index]}">
		                </td>
		              </tr>
		              </c:forEach>
		              </tbody>
		          </table><br><br>
		        </div>
		        
		        <!-- 주문자 정보 -->
		      <div class="orderInfo" style="min-width:1000px;" > 
					<div class="userInfo" style="display:inline-block; float:left; width:60%; padding: 0% 5%;">
						<div class="container" style="width:100%;">
							<span>[주문 정보]</span>
							<div class="form-group" style="width:100%; margin-top:5px;">
								<input type="hidden" class="form-control" id="mb_id" name="mb_id" value="${user.mb_id}">
							</div>
							<div class="form-group">
								<label for="inputName">☞ 이름</label> <input type="text"
									class="form-control" id="ord_name" name="ord_name" value="${user.mb_name}">
							</div>
							<div class="form-group">
								<label for="inputMobile">☞ 휴대폰 번호</label> <input type="tel"
									class="form-control" id="ord_phone" name="ord_phone" value="${user.mb_phone}">
							</div>
							<div class="form-group">
								<label for="inputAddr">☞ 주소</label><br/>
								<input type="text" id="sample2_postcode" name="ord_postcode" class="form-control" 
									   value="${user.mb_postcode}" style="max-width: 510px; width:calc(100% - 128px); margin-right: 5px; 
									   display: inline-block;" readonly>
								<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" 
								       value="우편번호 찾기"><br>
								<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
									   value="${user.mb_addr}" style="max-width: 630px; margin:3px 0px;" readonly>
								<input type="text" id="sample2_detailAddress" name="ord_deaddr" class="form-control" 
									   value="${user.mb_deaddr}" style="max-width: 630px;">
								<input type="hidden" id="sample2_extraAddress" class="form-control" 
									   placeholder="참고항목">
							</div>
						</div>
					</div>
					
					<%-- 결제 방식 선택  및 주문 금액 확인 --%>
					<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
					<br>
						<%-- 결제 방식 --%>
						<div>
							<br><br><span>[결제 방식]</span><br>
							<div class="payment" style="margin-top:10px;">
								<br><input type="radio" name="payment" value="card" checked="checked">카드 결제<br><br>
								<input type="radio" name="payment" value="tcash">실시간 계좌이체<br><br>
								<input type="radio" name="payment" value="phone">휴대폰 결제<br><br>
								<input type="radio" name="payment" value="cash">무통장 입금<br><br>
							</div>
						</div>
					</div>
					<%-- 주문 금액 --%>
					<div class="table-responsive">
					<br><br><br><table class="table" style="text-align: center;">
			          	<tr>
			          		<th><h5>총 판매가</h5></th>
			          		<th><h5>총 결제금액(할인 포함)</h5></th>
			          	</tr>
			          	
			          	<tr>
			          		<td><h5 id="totalPrice">0</h5></td>
			          		<td><h5 id="totalCount">0</h5></td>
			          	</tr>
			          </table>
			          <div class="checkout_btn_inner" style="text-align: center;">
			            <br><button id="btn_submit" class="btn_1" type="button">결제하기</button>
			          </div>
				</div>
		      </div>
		      </form>
	      </div>
	  </section>	
	  <!-- 상품구매 리스트 -->
		
	<!-- 다음 우편번호 API -->
	<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<script>
	    // 우편번호 찾기 화면을 넣을 element
	    var element_layer = document.getElementById('layer');
	
	    function closeDaumPostcode() {
	        // iframe을 넣은 element를 안보이게 한다.
	        element_layer.style.display = 'none';
	    }
	
	    function sample2_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("sample2_extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("sample2_extraAddress").value = '';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample2_postcode').value = data.zonecode;
	                document.getElementById("sample2_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample2_detailAddress").focus();
	
	                // iframe을 넣은 element를 안보이게 한다.
	                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
	                element_layer.style.display = 'none';
	            },
	            width : '100%',
	            height : '100%',
	            maxSuggestItems : 5
	        }).embed(element_layer);
	
	        // iframe을 넣은 element를 보이게 한다.
	        element_layer.style.display = 'block';
	
	        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
	        initLayerPosition();
	    }
	
	    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
	    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
	    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
	    function initLayerPosition(){
	        var width = 300; //우편번호서비스가 들어갈 element의 width
	        var height = 400; //우편번호서비스가 들어갈 element의 height
	        var borderWidth = 5; //샘플에서 사용하는 border의 두께
	
	        // 위에서 선언한 값들을 실제 element에 넣는다.
	        element_layer.style.width = width + 'px';
	        element_layer.style.height = height + 'px';
	        element_layer.style.border = borderWidth + 'px solid';
	        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
	        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
	        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
	    }
	</script>
 
 	<!-- Footer -->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
    
    <!-- JS here -->
	<%@include file="/WEB-INF/views/include/estorejs.jsp" %>
	
	<!-- 체크박스, 상품구매 버튼 클릭 -->
	<script type="text/javascript" src="/js/order/buycart.js"></script>
	
</body>
</html>

