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

  <title>JEESHOP 상품등록</title>

  <!-- CSS -->
  <%@include file="/WEB-INF/views/include/admincss.jsp" %>
  
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
								<h3 class="box-title">상품 등록하기</h3>
							</div>
							
							<!-- /.box-header -->
							<form id='insertForm' role="form" action="/admin/product/insert" method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<select class="form-control" id="mainCategory" name="cate_prtcode" style="width:30%; margin-right:10px; display: inline-block;" >
										  <option value="default">1차 카테고리 선택</option>
										  <c:forEach items="${cateList}" var="vo">
										  <option value="${vo.cate_code}">${vo.cate_name}</option>
										  </c:forEach>
										</select>
										
										<select class="form-control" id="subCategory" name="cate_code" style="width: 30%; display: inline-block;">
										 	<option value="default">2차 카테고리 선택</option>
										</select>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">상품명</label> <input
											type="text" id="pdt_name" name="pdt_name" class="form-control"
											placeholder="상품명을 입력하세요.">
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">제조사</label> <input
											type="text" id="pdt_company" name="pdt_company" class="form-control"
											placeholder="제조사를 입력하세요.">
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">상품 가격</label> 
										<label for="exampleInputEmail1" style="width:40%;">할인</label> 
										<input style="width:40%; margin-right:10px; display: inline-block;"
											type="text" id="pdt_price" name="pdt_price" class="form-control" 
											placeholder="상품가격을 입력하세요." />
										<input style="width:40%; display: inline-block;"
											type="text" id="pdt_discount" name="pdt_discount" class="form-control "
											placeholder="할인률을 입력하세요." />
									</div>
									
									<div class="form-group">
										<label for="exampleInputPassword1">상품 상세</label>
										<textarea class="form-control" id="pdt_detail" name="pdt_detail" rows="8"
											placeholder="상품상세 입력 ..."></textarea>
									</div>

									<div class="form-group">
										<label for="exampleInputEmail1">Thumbnail Image</label> <input
											type="file" id="file1" name="file1" class="form-control" />
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">상품 수량</label> 
										<label for="exampleInputEmail1" style="width:15%;">구매 가능여부</label><br /> 
										<input style="width:30%; margin-right:10px; display: inline-block;"
											type="text" id="pdt_amount" name='pdt_amount' class="form-control" 
											placeholder="상품 수량을 입력하세요." />
										<select class="form-control" id="pdt_buy" name="pdt_buy" style="width: 15%; display: inline-block;">
										  <option>Y</option>
										  <option>N</option>
										</select>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<div>
										<hr>
									</div>

									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>

									<button id="btn_submit" type="button" class="btn btn-primary">상품등록</button>
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
</body>

</html>
