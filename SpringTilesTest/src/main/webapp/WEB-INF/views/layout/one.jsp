<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	
	<!-- asset 세팅 -->
	<tiles:insertAttribute name="asset"></tiles:insertAttribute>
	
	<style>
	
	</style>
</head>
<body>

	<!-- one.jsp (2단 구성, main.do) -->

	<%--  *** tiles 사용시 유의사항 *** 
   		- taglib 기재				: <%@ taglib prefix="tiles" uri="..."> 
 		- include 사용 X			: <%@include file="..."> (같은 기능 혼용 사용 X)
 		- 등록된 조각페이지 사용 	: <tiles:insertAttribute name="...">
    --%>


	<main>
		<!-- 상단바, 메인메뉴 -->
		<tiles:insertAttribute name="mainmenu"></tiles:insertAttribute>
		<section>
			<!-- 내용 -->
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</section>

	</main>


	<script>
   
   </script>
</body>
</html>





