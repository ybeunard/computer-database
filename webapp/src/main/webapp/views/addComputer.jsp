<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.htm?search="> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                	Language : <a href="addComputer.htm?locale=en">English</a>|<a href="addComputer.htm?locale=fr">Francais</a>
                    <h1><spring:message code="add.springmvc" text="default text" /></h1>
                    <form:form action="addComputer.htm" modelAttribute="computerDto" name="newComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="name"><spring:message code="name.springmvc" text="default text" /></label>
                                <form:input path="name" type="text" name="name" class="form-control" id="name"/>
								<form:errors path="name" />
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="introduced.springmvc" text="default text" /></label>
                                <form:input path="introduced" type="date" name="introduced" class="form-control" id="introduced" placeholder="yyyy-mm-dd"/>
								<form:errors path="introduced" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="discontinued.springmvc" text="default text" /></label>
                                <form:input path="discontinued" type="date" name="discontinued" class="form-control" id="discontinued" placeholder="yyyy-mm-dd"/>
								<form:errors path="discontinued" />
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.springmvc" text="default text" /></label>
                                <form:select path="idCompany" class="form-control" name="company" id="companyId" >
                                	<option value="0">---</option>
                                	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.id}">${company.name}</option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="idCompany" />
                            </div>                  
                        </fieldset>
                        <c:if test="${not empty error}">
                        	<div class="alert alert-danger">${error}</div>
                        </c:if>
                        <div class="actions pull-right">
                            <input type="submit" name="action" class="btn btn-primary" value="<spring:message code="boutonAdd.springmvc" text="default text" />"/>
                            <spring:message code="or.springmvc" text="default text" />
                            <a href="dashboard.htm" class="btn btn-default"><spring:message code="cancel.springmvc" text="default text" /></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
   <script src="js/jquery.min.js"></script>
   <script src="js/jquery.validate.min.js"></script>
   <script src="js/addComputer.js"></script>
</body>
</html>