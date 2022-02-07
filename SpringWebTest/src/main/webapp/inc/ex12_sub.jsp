<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ex12_sub.jsp -->   
<h4>조각 페이지</h4>


<%-- <p>1. 지역변수 : <%= num1 %></p> --%>
<p>2. pageContext : <%= pageContext.getAttribute("num2") %></p>
<p>3. request : <%= request.getAttribute("num3") %></p>
<p>4. session : <%= session.getAttribute("num4") %></p>
