<html lang="en">
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.list.label" args="[entityName]" /> - ZZOverflow
        </title>
    </head>
    <body>

            <div class="container">
                <div class="row">
                    <div class="col-lg-offset-2 col-lg-8 col-md-offset-2 col-md-8">



                        <!-- POST -->
                        <div class="post">
                            <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="form newtopic" autocomplete="off">
                                <div class="postinfotop">

                                    <div class="pull-center">
                                        <label for="note"> Please Log In </label>
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                <div class="topwrap">
                                    <div class="userinfo pull-left">
                                        <div class="avatar">
                                            <a href="${createLink(uri: '/')}">
                                                <asset:image src="question/logo1.jpg" class="logo-size" alt="" />
                                            </a>
                                        </div>
                                    </div>
                                    <div class="posttext pull-left">

                                        <g:if test='${flash.message}'>
			<div class="login_message">${flash.message}</div>
		</g:if>

                                        <div>
                                            <input placeholder="Enter your username" class="form-control" type="text" name="${usernameParameter ?: 'username'}" id="username" />                                
                                        </div>

                                        <div>
                                            <input placeholder="Enter your password" class="form-control" type="password" name="${passwordParameter ?: 'password'}" id="password" />
                                        </div>


                                    </div>
                                    <div class="clearfix"></div>
                                </div>                              
                                <div class="postinfobot">

                                    <div class="notechbox pull-left">
                                        <input name="note" id="note" class="form-control" type="checkbox">
                                    </div>

                                    <div class="pull-left">
                                        <label for="note"> Remember Me</label>
                                    </div>

                                    <div class="pull-right postreply">
                                        <div class="pull-left">
                                            <input type="submit" id="submit" class="btn btn-primary" value="Login"/>
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