<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <a class="navbar-brand" href="DashboardServlet?resetFiltre=OK"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: 0
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="EditComputerServlet" method="POST">
                        <input type="hidden" name="ordinateur" value="${computer.id}" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <c:if test="${nameTest == 1}"><p>Nom requis</p></c:if>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" value="${computer.name}" onfocus="if(this.value=='${computer.name}'){this.value=''}" onblur="if(this.value==''){this.value = '${computer.name}'}"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <c:if test="${introducedTest == 1}"><p>date invalide</p></c:if>
                                <c:if test="${incohérenceTest == 1}"><p>Attention la date doit être antérieur à la date d'interruption</p></c:if>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" value="${computer.dateIntroduit}"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <c:if test="${discontinuedTest == 1}"><p>date invalide</p></c:if>
                                <c:if test="${incohérenceTest == 1}"><p>Attention la date doit être postérieur à la date d'introduction</p></c:if>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${computer.dateInterrompu}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" name="company" id="companyId" >
                                    <option value="${idCompany}">${computer.factory}</option>
                                    <option value="0">----</option>
                                	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.id}">${company.name}</option>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="DashboardServlet" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/addComputer.js"></script>
</body>
</html>