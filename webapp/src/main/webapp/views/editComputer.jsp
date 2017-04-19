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
<link href="<c:url value="/css/languages.min.css"/>" rel="stylesheet"
	media="screen" />
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html?search="> Application - Computer Database </a>
     		<div class="language">
	    	    <a class="align-middle" href="editComputer.html?locale=en"><span class="lang-lg" lang="en"></span></a>
		     	<a class="align-middle" href="editComputer.html?locale=fr"><span class="lang-lg" lang="fr"></span></a>
	       		<a href="login.html?logout"><button class="btn btn-primary"><spring:message code="logoutmessage.springmvc"/></button></a>
	       	</div>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1><spring:message code="edit.springmvc" text="default text" /></h1>

                    <form:form action="editComputer.html" modelAttribute="computerDto" method="POST">
                        <form:input path="id" type="hidden" name="computer" value="${computer.id}" id="id"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="name.springmvc" text="default text" /></label>
                                <form:input path="name" type="text" name="computerName" class="form-control" id="computerName" value="${computer.name}" onfocus="if(this.value=='${computer.name}'){this.value=''}" onblur="if(this.value==''){this.value = '${computer.name}'}"/>
								<form:errors path="name" />
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="introduced.springmvc" text="default text" /></label>
                                <form:input path="introduced" type="date" name="introduced" class="form-control" id="introduced" placeholder="yyyy-mm-dd" value="${computer.introduced}"/>
								<form:errors path="introduced" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="discontinued.springmvc" text="default text" /></label>
                                <form:input path="discontinued" type="date" name="discontinued" class="form-control" id="discontinued" placeholder="yyyy-mm-dd" value="${computer.discontinued}"/>
								<form:errors path="discontinued" />
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.springmvc" text="default text" /></label>
                                <form:select path="idCompany" class="form-control" name="company" id="companyId" >
                                    <option value="${computer.idCompany}">${computer.company}</option>
                                    <option value="0">----</option>
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
                            <input type="submit" name="action" class="btn btn-primary" value="<spring:message code="boutonEdit.springmvc" text="default text" />"/>
                            <spring:message code="or.springmvc" text="default text" />
                            <a href="dashboard.html" class="btn btn-default"><spring:message code="cancel.springmvc" text="default text" /></a>
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