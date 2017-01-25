<html>
<head>
	<meta name="layout" content="main"/>
	<s2ui:title messageCode='spring.security.ui.register.title'/>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-offset-2 col-lg-8 col-md-offset-2 col-md-8">



				<!-- POST -->
				<div class="post">
					<form action="${postUrl ?: '/register/register'}" method="POST" id="loginForm" class="form newtopic" autocomplete="off">
						<div class="postinfotop">

							<div class="pull-center">
								<label for="note"> Create a new Account </label>
							</div>

							<div class="clearfix"></div>
						</div>
						<div class="topwrap">
							<div class="userinfo pull-left">
								<div class="avatar">
									<a href="${createLink(uri: '/')}">
										<asset:image src="question/logo.png" class="logo-size" alt="" />
									</a>
								</div>
							</div>
							<div class="posttext pull-left">

								<g:if test='${emailSent}'>								
									<g:message code='spring.security.ui.register.sent'/>
								</g:if>
								<g:else>
									<div>
										<input placeholder="Username" class="form-control" type="text" name="username" id="username" />                                
									</div>

									<div>
										<input placeholder="E-mail" class="form-control" type="text" name="email" id="username" />                                
									</div>

									<div>
										<input placeholder="Password" class="form-control" type="password" name="password" id="password" />
									</div>

									<div>
										<input placeholder="Password (again)" class="form-control" type="password" name="password2" id="password" />
									</div>

								</g:else>


							</div>
							<div class="clearfix"></div>
						</div>                              
						<div class="postinfobot">


							<div class="pull-right postreply">
								<div class="pull-left">
									<input type="submit" id="submit" class="btn btn-primary" value="Submit"/>
								</div>                                
								<div class="clearfix"></div>
							</div>


							<div class="clearfix"></div>
						</div>
					</form>
				</div><!-- POST -->



		</div>
	</div>
	<script>
	(function() {
		document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
	})();
	</script>
</body>
</html>
