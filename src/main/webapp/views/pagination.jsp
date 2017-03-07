<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE jsp>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<div class="container text-center">
            <ul class="pagination">
            	<c:if test="${page.pagePrec!=page.numPage}">
	                <li>
	                    <a href="?numPage=${page.pagePrec}" aria-label="Previous">
	                      <span aria-hidden="true">&laquo;</span>
	                  </a>
	              	</li>
              </c:if>
              <c:forEach items="${page.pagination}" var="numero" >
              	<li><a href="?numPage=${numero}">${numero}</a></li>
              </c:forEach>
              <c:if test="${page.pageSuiv!=page.numPage}">
	              	<li>
	                <a href="?numPage=${page.pageSuiv}" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
	            	</li>
            	</c:if>
        	</ul>  

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="?nbParPage=10"><button type="button" class="btn btn-default">10</button></a>
            <a href="?nbParPage=50"><button type="button" class="btn btn-default">50</button></a>
            <a href="?nbParPage=100"><button type="button" class="btn btn-default">100</button></a>
        </div>
     </div>
</body>