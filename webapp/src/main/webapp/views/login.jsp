<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link href="signin.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	media="screen">

</head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
	$(function() {

		if (localStorage.chkbx && localStorage.chkbx != '') {
			$('#remember_me').attr('checked', 'checked');
			$('#username').val(localStorage.usrname);
			$('#pass').val(localStorage.pass);
		} else {
			$('#remember_me').removeAttr('checked');
			$('#username').val('');
			$('#pass').val('');
		}

		$('#remember_me').click(function() {

			if ($('#remember_me').is(':checked')) {
				// save username and password
				localStorage.usrname = $('#username').val();
				localStorage.pass = $('#pass').val();
				localStorage.chkbx = $('#remember_me').val();
			} else {
				localStorage.usrname = '';
				localStorage.pass = '';
				localStorage.chkbx = '';
			}
		});
	});
	$(function() {
		$("input").prop('required', true);
		$('#remember_me').removeAttr('required');
	});
</script>
<body onload='document.loginForm.username.focus();'>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html?search=">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">

				<div id="login-box">
					<h1>Security Login Form</h1>
					<h3>Login with Username and Password</h3>


					<form class="form-signin" name='loginForm'
						action="<c:url value='/login.html' />" method='POST'
						style="align: center">
						<h2 class="form-signin-heading">Please sign in</h2>
						<fieldset>
							<div class="form-group">


								<label><h3>User:</h3></label> <input type='text' name='username'
								class="form-control" placeholder="UserName" id="username" />

							</div>
							<div class="form-group">
								<label><h3>Password :</h3></label> <input type='password'
									name='password' class="form-control" placeholder="Password"
									id="pass" /> <label class="checkbox"> <input
									type="checkbox" value="remember-me" id="remember_me"
									required="false"> Remember me
								</label>
							</div>
						</fieldset>

						<c:if test="${not empty error}">
							<div class="error">
								<b style="color: red">${error}</b> <br /> <br />
							</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg">
								<b style="color: red">${msg}</b> <br /> <br />
							</div>
						</c:if>

						<div class="form-group">

							<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
								in</button>

						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<a id="Logout">Logout</a>
					
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript">$("#Logout").click(function() {
			$form = $("<form>").attr({
				"action" : "${pageContext.request.contextPath}" + "/login.html?logout",
				"method" : "post"
			})
				.append($("<input>").attr({
					"type" : "hidden",
					"name" : "${_csrf.parameterName}",
					"value" : "${_csrf.token}"
				}))
			$("#Logout").append($form);
			$form.submit();
		});
	</script>

	<script src="<c:url value="/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/js/dashboard.js"/>"></script>
	<script src="<c:url value="/js/addComputer.js"/>"></script>
</body>
</html>