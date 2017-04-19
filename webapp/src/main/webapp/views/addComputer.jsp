<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	   	    	<c:if test="${not empty username}"><button disabled>${username}</button></c:if>
	    	    <a class="align-middle" href="addComputer.html?locale=en"><span class="lang-lg" lang="en"></span></a>
		     	<a class="align-middle" href="addComputer.html?locale=fr"><span class="lang-lg" lang="fr"></span></a>
	       		<c:if test="${not empty username}"><a id="Logout" href="login.html?logout"><button>
						<spring:message code="logoutmessage.springmvc" />
					</button></a></c:if>
	       	</div>
        </div>
    </header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="add.springmvc" text="default text" />
					</h1>
					<form:form action="addComputer.html" modelAttribute="computerDto"
						name="newComputer" method="POST">
						<fieldset>
							<spring:bind path="name">
								<div class="form-group">
									<label for="name"><spring:message code="name.springmvc"
											text="default text" /></label>
									<form:input path="name" type="text" name="name"
										class="form-control" id="name" />
								</div>
								<c:if test="${status.error}">
									<div class="alert alert-danger fade in">
										<form:errors path="name" />
									</div>
								</c:if>
							</spring:bind>

							<spring:bind path="introduced">

								<div class="form-group">
									<label for="introduced"><spring:message
											code="introduced.springmvc" text="default text" /></label>
									<form:input path="introduced" type="date" name="introduced"
										class="form-control" id="introduced" placeholder="yyyy-mm-dd" />
								</div>
								<c:if test="${status.error}">
									<div class="alert alert-danger fade in">
										<form:errors path="introduced" />
									</div>
								</c:if>
							</spring:bind>


							<spring:bind path="discontinued">
								<div class="form-group">
									<label for="discontinued"><spring:message
											code="discontinued.springmvc" text="default text" /></label>
									<form:input path="discontinued" type="date" name="discontinued"
										class="form-control" id="discontinued"
										placeholder="yyyy-mm-dd" />
								</div>
								<c:if test="${status.error}">
									<div class="alert alert-danger fade in">
										<form:errors path="discontinued" />
									</div>
								</c:if>
							</spring:bind>

							<div class="form-group">
								<label for="companyId"><spring:message
										code="company.springmvc" text="default text" /></label> <input
									onclick="onFocusDropDown()" id="companySearch"
									class="form-control" type="search" onkeyup="deleteCompanies()"
									placeholder="Search..." />
								<div id="dropdown" class="dropdown close">
									<ul id="companiesForEach" class="dropdown-menu scrollable-menu">
										<li><a onclick="setSpring(this)" name="--"
											style="display: block" id="0"><span>--</span></a></li>
										<c:forEach items="${companies}" var="company">
											<li><a onclick="setSpring(this)" name="${company.name}"
												style="display: none" id="${company.id}"><span>${company.name}</span></a></li>

										</c:forEach>
									</ul>
								</div>
								<form:select style="display:none" path="idCompany"
									class="form-control" name="company" id="companyId">
								</form:select>

								<form:errors path="idCompany" />
							</div>
						</fieldset>
						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>
						<div class="actions pull-right">
							<input type="submit" name="action" class="btn btn-primary"
								value="<spring:message code="boutonAdd.springmvc" text="default text" />" />
							<spring:message code="or.springmvc" text="default text" />
							<a href="dashboard.html" class="btn btn-default"><spring:message
									code="cancel.springmvc" text="default text" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap-list-filter.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/addComputer.js"></script>
	<script src="js/dynamic_companies.js"></script>
</body>
</html>