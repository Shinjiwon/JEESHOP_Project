<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<title>JEESHOP 상품수정</title>
	
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
								<h3 class="box-title">상품 수정하기</h3>
							</div><br>
							
							<!-- /.box-header -->
							<form id='editForm' role="form" action="/admin/product/edit" method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<input type="hidden" name="page" value="${cri.page}" />
										<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
										<input type="hidden" name="searchType" value="${cri.searchType}" />
										<input type="hidden" name="keyword" value="${cri.keyword}" />
									</div>
										
									<div class="form-group">
										<input type="hidden" name="pro_num" value="${vo.pro_num}">
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<select class="form-control" id="mainCategory" name="cate_prtcode" style="width:30%; margin-right:10px; display: inline-block;" >
										  <option value="default">1차 카테고리 선택</option>
										  <c:forEach items="${cateList}" var="list">
										  <option value="${list.cate_code}" <c:out value="${vo.cate_prtcode == list.cate_code?'selected':''}"/>>
										  	${list.cate_name}
										  </option>
										  </c:forEach>
										</select>
										
										<select class="form-control" id="subCategory" name="cate_code" style="width: 30%; display: inline-block;">
										 	<option value="default">2차 카테고리 선택</option>
										 	<c:forEach items="${subCateList}" var="subList">
										 		<option value="${subList.cate_code}" <c:out value="${vo.cate_code == subList.cate_code?'selected':''}"/>>
										 			${subList.cate_name}
										 		</option>
										 	</c:forEach>
										</select>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">상품명</label> <input
											type="text" id="pro_name" name="pro_name" class="form-control"
											value="${vo.pro_name}">
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1">제조사</label> <input
											type="text" id="pro_dev" name="pro_dev" class="form-control"
											value="${vo.pro_dev}">
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">상품 가격</label> 
										<label for="exampleInputEmail1" style="width:40%;">할인</label> 
										<input style="width:40%; margin-right:10px; display: inline-block;"
											type="text" id="pro_price" name="pro_price" class="form-control" 
											value="${vo.pro_price}" />
										<input style="width:40%; display: inline-block;"
											type="text" id="pro_discount" name="pro_discount" class="form-control "
											value="${vo.pro_discount}" />
									</div>
									
									<div class="form-group">
										<label for="exampleInputPassword1">상품 상세</label>
										<textarea class="form-control" id="pro_detail" name="pro_detail" rows="8">
											${vo.pro_detail}
										</textarea>
									</div>

									<div class="form-group">
									<input type="hidden" name="pro_img" value="${vo.pro_img}">
										<label for="exampleInputEmail1">Thumbnail Image</label>
										<span id="fileName">현재 파일:<c:out value="${originFile}" /></span> 
										<input onchange="fileChange(this)" type="file" id="file1" name="file1" class="form-control" >
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">상품 수량</label> 
										<label for="exampleInputEmail1" style="width:15%;">구매 가능여부</label><br /> 
										<input style="width:30%; margin-right:10px; display: inline-block;"
											type="text" id="pro_amount" name='pro_amount' class="form-control" 
											value="${vo.pro_amount}" />
										<select class="form-control" id="pro_buy" name="pro_buy" style="width: 15%; display: inline-block;">
										  <option <c:out value="${vo.pro_buy == 'Y'?'selected':'' }" />>Y</option>
										  <option <c:out value="${vo.pro_buy == 'N'?'selected':'' }" />>N</option>
										</select>
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

									<button id="btn_submit" type="button" class="btn btn-primary">상품수정</button>
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
  
  <!-- 유효성 검사 -->
  <!-- CkEditor, 2차카테고리 -->
	<script>
		$(document).ready(function(){
			
			/* CkEditor 설정 */
			// config.js 외 개별설정. JSON문법 스타일 사용한 설정 구문
			var ckeditor_config = {
				resize_enable: false,
				enterMode: CKEDITOR.ENTER_BR,
				shiftEnterMode: CKEDITOR.ENTER_P,
				toolbarCanCollapse: true,
				removePlugins : "elementspath", 
				/* 파일 업로드 기능
				   CkEditor를 이용해 업로드 할 때 아래 주소에 업로드	
				*/
				filebrowserUploadUrl: '/admin/product/imgUpload'
				
			};
			CKEDITOR.replace("pro_detail", ckeditor_config);
		
			/* 1차 카테고리에 해당하는 2차 카테고리 출력 */
		    $("#mainCategory").change(function(){
		
		        // 선택한 1차 카테고리 값
		        var mainCateCode = $(this).val();
		        // url매핑주소를 경로형태로 사용 @PathVarialbe
		        var url = "/admin/product/subCateList/" + mainCateCode;
		
		        $.getJSON(url, function(data) {
		            // data: 2차 카테고리 데이터 정보
		            // 데이터를 subCategory에 적용
		        subCateList(data, $("#subCategory"), $("#subCateListTemplate"))
		        });
		    });
			
		    /* 상품 목록 버튼 클릭 */
		    $("#btn_list").click(function(){

		        var result = confirm("수정하지 않고 상품목록으로 돌아가시겠습니까?");

		        if(result){
		            location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
		        
		        } else{} 
		    });
		
		});
	</script>
	
	<!-- Handlebars템플릿에 2차카테고리 데이터 적용 -->
	<script>
		var subCateList = function(subCateStr, target, templateObject){
		    
		    var template = Handlebars.compile(templateObject.html());
		    var options = template(subCateStr);
	
		    $("#subCategory option").remove();
		    target.append(options);
		}
	</script>
	
	<!-- 파일 변경 시 메세지 이벤트 -->
	<script>
		var fileChange = function(fis){
			
			var str = fis.value;
			$("#fileName").html("파일 변경됨");
		}
	</script>
	
	<!-- 상품수정 버튼 클릭 -->
	<script type="text/javascript" src="/js/admin/proedit.js"></script>
</body>

</html>
