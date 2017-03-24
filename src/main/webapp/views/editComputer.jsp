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
            <a class="navbar-brand" href="dashboard.htm?resetFiltre=OK"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form:form action="editComputer.htm" modelAttribute="ordinateurDto" method="POST">
                        <form:input path="id" type="hidden" name="ordinateur" value="${computer.id}" id="id"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input path="name" type="text" name="computerName" class="form-control" id="computerName" placeholder="Computer name" value="${computer.name}" onfocus="if(this.value=='${computer.name}'){this.value=''}" onblur="if(this.value==''){this.value = '${computer.name}'}"/>
								<form:errors path="name" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="dateIntroduit" type="date" name="introduced" class="form-control" id="introduced" placeholder="yyyy-mm-dd" value="${computer.dateIntroduit}"/>
								<form:errors path="dateIntroduit" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="dateInterrompu" type="date" name="discontinued" class="form-control" id="discontinued" placeholder="yyyy-mm-dd" value="${computer.dateInterrompu}"/>
								<form:errors path="dateInterrompu" />
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <form:select path="idFactory" class="form-control" name="company" id="companyId" >
                                    <option value="${computer.idFactory}">${computer.factory}</option>
                                    <option value="0">----</option>
                                	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.id}">${company.name}</option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="idFactory" />
                            </div>            
                        </fieldset>
                        <c:if test="${not empty error}">
                        	<div class="alert alert-danger">${error}</div>
                        </c:if>
                        <div class="actions pull-right">
                            <input type="submit" name="action" value="Edit" class="btn btn-primary"/>
                            or
                            <a href="dashboard.htm" class="btn btn-default">Cancel</a>
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