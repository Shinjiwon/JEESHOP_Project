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

  <title>JEESHOP 관리자 페이지</title>

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
        	<div class="row text-center">
							<div style="display: inline-block; float: left; margin-left:15px;">
							<select name="searchType" style="width:180px; height:26px;">
								<option value="null">검색조건 선택</option>
								<option value="name">상품명</option>
								<option value="detail">내용</option>
								<option value="company">제조사</option>
								<option value="name_detail">상품명+내용</option>
								<option value="name_company">상품명+제조사</option>
								<option value="all">상품명+내용+제조사</option>
							</select> 
							<input type="text" name='keyword' id="keyword" style="width:250px; height:26px;">
							<button id="btn_search" class="btn btn-primary">검색</button>
							</div>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							<div style="display: inline-block; float: right; margin-right:15px;">
							<button id="btn_edit_check"  class="btn btn-primary" >선택 상품 수정</button>
							<button id="btn_delete_check"  class="btn btn-primary" >선택 상품 삭제</button>
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
                    <tr>
                      <td><input type="checkbox" name="check" class="check"></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                    </tr>
                  </tbody>
                </table>
              </div>
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
  
</body>

</html>
