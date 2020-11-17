<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>JEESHOP 회원목록</title>

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
        	
						
			<div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">회원 목록</h6>
            </div>
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr style="text-align: center;">
                      <th>이름</th>
                      <th>아이디</th>
                      <th>닉네임</th>
                      <th>이메일</th>
                      <th>휴대폰</th>
                      <th>가입일</th>
                      <th>수신동의 여부</th>
                    </tr>
                  </thead>
                  
                  <tbody>
                     <c:if test="${empty userList}">
                     <tr>
							<p style="padding:50px 0px; text-align: center;"><td colspan="7"> 
							<p style="padding:50px 0px; text-align: center;">회원이 존재하지 않습니다.</p>
					</tr>	
                     </c:if>
                     
                     <c:forEach items="${userList}" var="list">
                     <tr style="text-align: center;">
                     <td>${list.mb_name}</td>
                     <td>${list.mb_id}</td>
                     <td>${list.mb_nick}</td>
                     <td>${list.mb_email}</td>
                     <td>${list.mb_phone}</td>
                     <td><fmt:formatDate value="${list.mb_join}" pattern="yyyy.MM.dd" /></td>
                     <td>${list.mb_accept}</td>
                     </tr>
                     </c:forEach>
                  </tbody>
                </table>
              </div>
              
           
              <!-- 페이징 기능 -->
					<ul class="pagination">
						<!-- 이전표시 -->
						<c:if test="${pm.prev}">
						<li>
						<a href="userList${pm.makeQuery(pm.startPage-1)}">&laquo;</a>
						</li>&nbsp;
						</c:if>
						
						<!-- 페이지 번호: 1 2 3 4 5 -->
						<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="idx">
						<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
						<a href="userList${pm.makeQuery(idx)}">${idx}</a>
						</li>&nbsp;
						</c:forEach>
						
						<!-- 다음 표시 -->
						<c:if test="${pm.next && pm.endPage > 0}">
						<li>
						<a href="userList${pm.makeQuery(pm.endPage +1)}">&raquo;</a>
						</li>
						</c:if>
					</ul>
            
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
  
</body>
</html>