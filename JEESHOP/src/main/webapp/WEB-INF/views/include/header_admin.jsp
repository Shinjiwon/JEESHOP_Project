<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	
	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
	  <i class="fa fa-bars"></i>
	</button>
	
	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
		<!-- 로그인 -->
		<li class="nav-item dropdown no-arrow">
		   <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		     <span class="mr-2 d-none d-lg-inline text-gray-600 small">로그인</span>
		   </a>
		</li>
		
		<li class="nav-item dropdown no-arrow">
		   <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		     <span class="mr-2 d-none d-lg-inline text-gray-600 small">로그아웃</span>
		   </a>
		</li>
		
		 <!-- 사용자 이름 -->
		<li class="nav-item dropdown no-arrow">
		  <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
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
		        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
		          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
		          Logout
		        </a>
		      </div>
		    </li>
	  </ul>
	</nav>