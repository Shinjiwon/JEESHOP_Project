<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>JEESHOP 상품목록</title>

  <!-- CSS -->
  <%@include file="/WEB-INF/views/include/admincss.jsp" %>
  
  <script>
  	if("${msg}"=="PROINSERT_SUCCESS"){
  		alert("상품등록이 완료되었습니다.");
  		
  	} else if("${msg}"=="DELETE_SUCCESS"){
  		alert("상품삭제가 완료되었습니다.");
  		
  	}
  </script>
  
</head>

<body>
  <!-- 전체 페이지 -->
  <div id="wrapper">

    <!-- 사이드 메뉴 -->
    <%@include file="/WEB-INF/views/include/left_admin.jsp" %>

    <!-- 메인 페이지 -->
    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">

        <!-- Header-->
        <%@include file="/WEB-INF/views/include/header_admin.jsp" %>

        <!-- 주요 내용 -->
        <div class="container-fluid">
        	<div class="row text-center">
							<div style="display: inline-block; float: left; margin-left:15px;">
							<select name="searchType" style="width:180px; height:26px;">
								<option value="null"
									<c:out value="${cri.searchType == null?'selected':''}" />>검색조건 선택</option>
								<option value="name"
									<c:out value="${cri.searchType eq 'name'?'selected':''}" />>상품명</option>
								<option value="detail"
									<c:out value="${cri.searchType eq 'detail'?'selected':''}" />>내용</option>
								<option value="company"
									<c:out value="${cri.searchType eq 'company'?'selected':''}" />>제조사</option>
								<option value="name_detail"
									<c:out value="${cri.searchType eq 'name_detail'?'selected':''}" />>상품명+내용</option>
								<option value="name_company"
									<c:out value="${cri.searchType eq 'name_company'?'selected':''}" />>상품명+제조사</option>
								<option value="all"
									<c:out value="${cri.searchType eq 'all'?'selected':''}" />>상품명+내용+제조사</option>
							</select> 
							<input type="text" name='keyword' id="keyword" style="width:250px; height:26px;" value='${cri.keyword}'>
							<button id="btn_search" class="btn btn-primary">검색</button>
							</div>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							
							<div style="display: inline-block; float: right; margin-right:15px;">
							<button id="btn_edit_check"  class="btn btn-success" >선택 상품 수정</button>
							<button id="btn_delete_check"  class="btn btn-danger" >선택 상품 삭제</button>
							<button class="btn btn-primary"
								onClick="location.href='/admin/product/insert'">상품 등록</button>
							</div>	
						</div><br>
						
			<div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">상품 목록</h6>
            </div>
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th><input type="checkbox" id="checkAll" /></th>
                      <th>상품번호</th>
                      <th>상품이미지</th>
                      <th>상품명</th>
                      <th>판매가</th>
                      <th>할인률</th>
                      <th>제조사</th>
                      <th>수량</th>
                      <th>판매여부</th>
                      <th>수정/삭제</th>
                    </tr>
                  </thead>
                  
                  <tbody>
                     <c:if test="${empty productList }">
                     <tr>
							<p style="padding:50px 0px; text-align: center;"><td colspan="10"> 
							<p style="padding:50px 0px; text-align: center;">등록된 상품이 존재하지 않습니다.</p>
					</tr>	
                     </c:if>
                     
                     <c:forEach items="${productList}" var="productVO">
                     <tr>
                     <td><input type="checkbox" name="check" class="check" value="${productVO.pro_num}"></td>
                     <td>${productVO.pro_num}</td>
                     <td>
                     <img src="/admin/product/displayFile?fileName=${productVO.pro_img }" 
                     		style="width: 80px; height: 80px;">
                   	 <input type="hidden" name="img_${productVO.pro_num}" value="${productVO.pro_img}" />
                     </td>
                     <td>
                     <a href="/admin/product/read${pm.makeSearch(pm.cri.page)}&pdt_num=${productVO.pro_num}">
                     ${productVO.pro_name}
                     </a>
                     </td>
                     <td>${productVO.pro_price}원</td>
                     <td>${productVO.pro_discount}%</td>
                     <td>${productVO.pro_dev}</td>
                     <td>
                     	<input type="number" name="amount_${productVO.pro_num}" value="${productVO.pro_amount}" style="width: 55px;">
                     </td>
                     <td>
                     	<select class="form-control" name="buy_${productVO.pro_num}" style="width: 60px; display: inline-block;">
						  <option <c:out value="${productVO.pro_buy == 'Y'?'selected':''}"/>>Y</option>
						  <option <c:out value="${productVO.pro_buy == 'N'?'selected':''}"/>>N</option>
						</select>
                     </td>
                     <td>
                     	<form class="deleteForm" method="post"
								action="/admin/product/delete${pm.makeSearch(pm.cri.page)}">
							<!-- 상품 코드 -->
							<input type="hidden" name="pro_num"
								value="${productVO.pro_num}">
							<!-- 파일 이미지명 -->
							<input type="hidden" name="pro_img"
								value="${productVO.pro_img}">
							<!-- 수정기능 -->
							<button type="button" name="btn_edit" class="btn btn-success">수정</button>
							
							<!-- 삭제기능 -->
							<button type="button" name="btn_delete" class="btn btn-danger">삭제</button>
						</form>
                     </td>
                     </tr>
                     </c:forEach>
                  </tbody>
                </table>
              </div>
              
              <!-- 페이징 기능 -->
              <div>
					<ul class="pagination">
						<!-- 이전표시 -->
						<c:if test="${pm.prev}">
						<li>
						<a href="list${pm.makeSearch(pm.startPage-1)}">&laquo;</a>
						</li>&nbsp;
						</c:if>
						
						<!-- 페이지 번호: 1 2 3 4 5 -->
						<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="idx">
						<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
						<a href="list${pm.makeSearch(idx)}">${idx}</a>
						</li>&nbsp;
						</c:forEach>
						
						<!-- 다음 표시 -->
						<c:if test="${pm.next && pm.endPage > 0}">
						<li>
						<a href="list${pm.makeSearch(pm.endPage +1)}">&raquo;</a>
						</li>
						</c:if>
					</ul>
            </div>
          </div>
        </div>
        <!-- 주요 내용  -->
      </div>

      <!-- Footer -->
      <%@include file="/WEB-INF/views/include/footer_admin.jsp" %>

    </div>
    <!-- 메인 페이지 -->
  </div>
  <!-- 전체 페이지 -->

  <!-- JS -->
  <%@include file="/WEB-INF/views/include/adminjs.jsp" %>
  
  <!-- 유효성 검사 -->
  <script>
  $(document).ready(function(){

	    /* 검색버튼 클릭 */
	    $("#btn_search").click(function(){

	        self.location = "list"
	                        + '${pm.makeQuery(1)}'
	                        + "&searchType=" + $("select option:selected").val()
	                        + "&keyword=" + $("#keyword").val();
	    });

	    /* 전체 체크박스 선택 */
	    $("#checkAll").click(function(){

	        $(".check").prop('checked', this.checked);        
	    });

	    /* 선택 안된 체크박스 있을 경우 전체선택 해제 */
	    $(".check").click(function(){

	        $("#checkAll").prop('checked', false);
	    });

	    /* 체크 된 상품 삭제 */
	    $("#btn_delete_check").click(function(){

	        // 체크 여부 확인
	        if($("input[name='check']:checked").length==0){
	            alert("삭제할 상품을 선택하세요.");
	            return;
	        }

	        // 체크 된 상품이 존재
	        var result = confirm("선택한 상품을 삭제하시겠습니까?");

	        if(result){

	            var checkArr = [];
	            var imgArr = [];

	            // 체크된 상품의 값(pro_num) 가져오기
	            $("input[name='check']:checked").each(function(){

	                var pro_num = $(this).val();
	                var pdt_img = $("input[name='img_"+pro_num+"']").val();

	                checkArr.push(pro_num);
	                imgArr.push(pdt_img);
	            });

	            $.ajax({
	                url:'/admin/product/deleteCheck',
	                type: 'post',
	                dataType: 'text',
	                data: {
	                    checkArr: checkArr,
	                    imgArr: imgArr
	                },
	                success: function(data){
	                    alert("삭제가 완료되었습니다.");
	                    location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
	                }
	                
	            });
	        } else{}
	    });

	    /* 상품 리스트 테이블에 포함된 삭제버튼 클릭 */
	    $("button[name=btn_delete]").click(function(){

	        var result = confirm("상품을 삭제하시겠습니까?");

	        if(result){

	            $(this).parent().submit()
	        } else{}
	    });

	    /* 체크 된 상품 수정 */
	    $("#btn_edit_check").click(function(){

	        // 체크 여부 확인
	        if($("input[name='check']:checked").length==0){
	            alert("수정할 상품을 선택하세요.");
	            return;

	        }

	        var checkArr = [];
	        var amountArr = [];
	        var buyArr = [];

	        // 체크 된 상품의 값을 가져오기
	        $("input[name='check']:checked").each(function(){

	            var pro_num = $(this).val();
	            var pro_amount = $("input[name='amount_"+pro_num+"']").val();
	            var pro_buy = $("select[name='buy_"+pro_num+"']").val();
					
	            checkArr.push(pro_num);
	            amountArr.push(pro_amount);
	            buyArr.push(pro_buy);
	        });

	        $.ajax({
	            url: '/admin/product/editCheck',
	            type: 'post',
	            dataType: 'text',
	            data: {
	                checkArr: checkArr,
	                amountArr: amountArr,
	                buyArr: buyArr
	            },
	            success: function(data){
	                alert("수정이 완료되었습니다.");
	                location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
	            }
	        });
	    });
	});
  </script>
</body>
</html>