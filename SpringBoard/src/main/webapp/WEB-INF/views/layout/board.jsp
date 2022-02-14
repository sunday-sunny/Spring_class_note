<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
   
   <!-- asset -->
   <tiles:insertAttribute name="asset"></tiles:insertAttribute>
   
   <style>
   		main{
   			width: 600px;
   			margin: 0px auto;
   		}
   		
   		header {
   			height: 100px;
   			font-size : 2.5em;
   			text-align: center;
   			padding-top: 30px;
   			border-bottom: 1px dashed #999;
   			font-variant: small-caps;
   		}
   		
   		section {
   			margin-top: 30px;
   		}
   </style>
</head>
<body>

   <!-- board.jsp : 템플릿 -->
  	
  	<main>
  		<tiles:insertAttribute name="header"></tiles:insertAttribute>
  		<section>
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
  		</section>
  	</main>
  	
  	
   <script>
   
   </script>
</body>
</html>





