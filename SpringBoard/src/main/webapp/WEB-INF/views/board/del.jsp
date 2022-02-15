<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<style>
	.add th { width:150px;}
	.add td { width: auto;}
	.add textarea { resize:none; height:200px;}
</style>

<!-- del.jsp -->

<form method="POST" action="/delok" enctype="multipart/form-data">

<!-- Btns -->
<div class="btns">
	<input type="button" value="돌아가기" class="btn btn-default" onclick="location.href='view?seq=${seq}';">
	<input type="submit" value="삭제하기" class="btn btn-default" >
</div>

<input type="hidden" name="seq" value="${seq}">

</form>