<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<title>JEESHOP 상품상세</title>
	
	  <!-- CSS -->
	<%@include file="/WEB-INF/views/include/admincss.jsp" %>
  
  	<!-- CkEditor, Handlebars -->
  	<script src="/ckeditor/ckeditor.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<!-- Handlebar Template -->
	<script id="subCateListTemplate" type="text/x-handlebars-template">
		<option value="default">2차 카테고리 선택</option>
		{{#each .}}
		<option value="{{cate_code}}">{{cate_name}}</option>
		{{/each}}
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
			<section class="content container-fluid">

				<!-- 상품등록 폼 -->
				<div class="row">
					<!-- left column -->
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">상품 상세정보</h3>
							</div><br>
							
							<!-- /.box-header -->
							<form id='editForm' role="form" action="/admin/product/edit" method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">${vo.cate_prtcode}</span>
									<span class="form-control" style="width:40%;  display:inline-block;">${vo.cate_code}</span>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">상품명</label>
										<span class="form-control">${vo.pro_name}</span>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">제조사</label>
										<span class="form-control">${vo.pro_dev}</span>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">상품 가격</label> 
										<label for="exampleInputEmail1" style="width:40%;">할인</label> 
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">${vo.pro_price}</span>
									<span class="form-control" style="width:40%; display:inline-block;">${vo.pro_discount}</span>
									</div>
									
									<div class="form-group">
										<label for="exampleInputPassword1">상품 상세</label>
										<div contenteditable="false" style="border: 1px solid #d2d2d2; padding: 20px;">
											${vo.pro_detail}
										</div>
									</div>

									<div class="form-group">
										<label for="exampleInputEmail1">Thumbnail Image</label>
										<span class="form-control"><c:out value="${vo.pro_img}" /></span>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">상품 수량</label> 
										<label for="exampleInputEmail1" style="width:15%;">구매 가능여부</label><br>
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">${vo.pro_amount}</span>
									<span class="form-control" style="width:40%; display:inline-block;">${vo.pro_buy}</span> 
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">등록 날짜</label> 
										<label for="exampleInputEmail1" style="width:40%;">업데이트 날짜</label> <br>
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">
											<fmt:formatDate value="${vo.pro_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</span>
										<span class="form-control" style="width:40%; display: inline-block;">
											<fmt:formatDate value="${vo.pro_update}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</span>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<div>
										<hr>
									</div>

									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>

									<button id="btn_list" type="button" class="btn btn-secondary">상품목록</button>
								</div>
							</form>
						</div>
						<!-- /.box -->	
					</div>
					<!--/.col (left) -->
				</div>
			</section>
        </div>
        <!-- 주요 내용  -->

      </div><br>

      <!-- Footer -->
      <%@include file="/WEB-INF/views/include/footer_admin.jsp" %>

    </div>
    <!-- 메인 페이지 -->
  </div>
  <!-- 전체 페이지 -->

  <!-- JS -->
  <%@include file="/WEB-INF/views/include/adminjs.jsp" %>
  
  <!-- 상품 목록 버튼 클릭 -->
  <script>
	  $(document).ready(function(){
	
		    /* 상품 목록 버튼 클릭 */
		    $("#btn_list").click(function(){
	
		        location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
		    });
		});
  </script>
</body>

</html>
