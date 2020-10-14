<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	
	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
	  <i class="fa fa-bars"></i>
	</button>
	
	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
		<!-- 로그인 안 한 상태 -->
		<c:if test="${sessionScope.admin == null}">
		<li class="nav-item dropdown no-arrow">
		   <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		     <span class="mr-2 d-none d-lg-inline text-gray-600 small">로그인하세요.</span>
		   </a>
		</li>
		</c:if>
		
		<!-- 로그인 한 상태 -->
		<c:if test="${sessionScope.admin != null}">
		<li class="nav-item dropdown no-arrow">
		   <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		     <span class="mr-2 d-none d-lg-inline text-gray-600 small">
		     	최근 접속 시간: <fmt:formatDate value="${admin.admin_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		     </span>
		   </a>
		</li>
		
		 <!-- 사용자 이름 -->
		<li class="nav-item dropdown no-arrow">
		  <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${admin.admin_id }님</span>
		    <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
		  </a>
		  <!-- Dropdown - User Information -->
		      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
		        <a class="dropdown-item" href="#">
		          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
		          Profile
		        </a>
		        <a class="dropdown-item" href="#">
		          <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
		          Settings
		        </a>
		        <div class="dropdown-divider"></div>
		        <a class="dropdown-item" href="/admin/logout" onclick="return confirm('로그아웃하시겠습니까?');"
		        	data-target="#logoutModal">
		          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
		          Logout
		        </a>
		      </div>
		    </li>
		    
		    <li class="nav-item dropdown no-arrow">
		   <a class="nav-link dropdown-toggle" href="/admin/logout" onclick="return confirm('로그아웃하시겠습니까?');" 
		   		id="userDropdown" aria-haspopup="true" aria-expanded="false">
		     <span class="mr-2 d-none d-lg-inline text-gray-600 small">로그아웃</span>
		   </a>
		</li>
		    </c:if>
	  </ul>
	</nav>