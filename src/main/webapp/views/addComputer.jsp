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
                    <h1>Add Computer</h1>
                    <form action="AddComputerServlet" name="newComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <c:if test="${nameTest == 1}"><p>Nom requis</p></c:if>
                                <input type="text" name="computerName" class="form-control" id="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <c:if test="${introducedTest == 1}"><p>date invalide</p></c:if>
                                <c:if test="${incohérenceTest == 1}"><p>Attention la date doit être antérieur à la date d'interruption</p></c:if>
                                <input type="date" name="introduced" class="form-control" id="introduced" placeholder="yyyy-mm-dd">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <c:if test="${discontinuedTest == 1}"><p>date invalide</p></c:if>
                                <c:if test="${incohérenceTest == 1}"><p>Attention la date doit être postérieur à la date d'introduction</p></c:if>
                                <input type="date" name="discontinued" class="form-control" id="discontinued" placeholder="yyyy-mm-dd">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" name="company" id="companyId" >
                                	<option value="">---</option>
                                	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.id}">${company.name}</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" name="action" value="Add" class="btn btn-primary">
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