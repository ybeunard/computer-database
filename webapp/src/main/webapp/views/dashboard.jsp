<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.htm?resetFiltre=OK"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
        	Language : <a href="dashboard.htm?locale=en">English</a>|<a href="dashboard.htm?locale=fr">Francais</a>
            <h1 id="homeTitle">
                ${page.nbComputer} <spring:message code="found.springmvc" text="default text" />
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="dashboard.htm" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" />
                        <input type="submit" id="searchsubmit" name="action" value="<spring:message code="filterButton.springmvc" text="default text" />" class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer.htm"><spring:message code="boutonAdd.springmvc" text="default text" /></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
                    	<spring:message code="boutonEdit.springmvc" text="default text" />
                   	</a>
                   	<a class="btn btn-default" id="viewComputer" style="display: none" href="#" onclick="$.fn.toggleEditMode();">
                    	<spring:message code="boutonView.springmvc" text="default text" />
                   	</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="dashboard.htm" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall"/> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <a href="dashboard.htm?trie=name"><spring:message code="name.springmvc" text="default text" /></a>
                        </th>
                        <th>
                            <spring:message code="introduced.springmvc" text="default text" />
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="discontinued.springmvc" text="default text" />
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <a href="dashboard.htm?trie=company_name"><spring:message code="company.springmvc" text="default text" /></a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${page.contenue}" var="computer" >
	                    <tr>
	                        <td class="editMode">
	                            <input id="cb" type="checkbox" name="cb" class="cb" value="${computer.id}"/>
	                        </td>
	                        <td>
	                            <a href="editComputer.htm?ordinateur=${computer.id}" id="computerURL" onclick="">${computer.name}</a>
	                        </td>
	                        <td>${computer.dateIntroduit}</td>
	                        <td>${computer.dateInterrompu}</td>
	                        <td>${computer.factory}</td>
	
	                    </tr>
                	</c:forEach>   
                </tbody>
            </table>
        </div>
        <c:if test="${not empty error}">
       		<div class="alert alert-danger">${error}</div>
        </c:if>
    </section>

    <footer class="navbar-fixed-bottom">
        <%@include file="pagination.jsp" %>
    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>