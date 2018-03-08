<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div class="container cf">
		<h1><a href="/ShivaProject/"><img src="/ShivaProject/images/logo_h1.png" alt="개발의 시발점 로고" /></a></h1>
		<nav class="cf" id="nav">
			<c:set var="userId" value="${sessionScope.id}" />
			<ul class="cf">
				<li><a href="./main.lo">IT자격증</a></li>
				<li><a href="./myResume.ro">IT이력서</a></li>
				<li><a href="./ListSaleController.uo">IT물품거래</a></li>
				<li><a href="./main.co">IT렌트카</a></li>
			</ul>
			<div class="login_id_sec">
				<span class="id_txt"><c:out value='${sessionScope.id}'/></span><a href="./memberLogout.shiva">Logout</a>				
			</div>
		</nav>
	</div>
</header>