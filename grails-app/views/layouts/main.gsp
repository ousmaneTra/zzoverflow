<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>
            <g:layoutTitle default="Grails"/>
        </title>

        <asset:stylesheet src="application.css"/>

        <g:layoutHead/>

        <asset:javascript src="application.js"/>

    </head>
    <body>

        <div class="container-fluid">

            <div class="headernav">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-1 col-xs-3 col-sm-2 col-md-2 logo ">
                            <a href="${createLink(uri: '/')}">
                                <asset:image src="question/logo.png" class="logo-size" alt="" />
                            </a>
                        </div>
                        <div class="col-lg-3 col-xs-9 col-sm-5 col-md-3 selecttopic">
                            <div>
                                <a  class="logo-text" href="${createLink(uri: '/')}">ZZOverflow</a> 
                            </div>
                        </div>
                        <div class="col-lg-4 search hidden-xs hidden-sm col-md-3">
                            <div class="wrap">
                                <form action="#" method="post" class="form">
                                    <div class="pull-left txt"><input class="form-control" placeholder="${message(code: 'default.question.search.label')}" type="text"></div>
                                    <div class="pull-right"><button class="btn btn-default" type="button"><i class="fa fa-search"></i></button></div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-4 col-xs-12 col-sm-5 col-md-4 avt">
                            <div class="stnt pull-left">                            
                                    <g:link class="create" controller="question" action="create">
                                         <button class="btn btn-primary">
                                            <g:message code="default.question.ask.label"/>
                                          </button>
                                    </g:link>
                            </div>
                            <div class="env pull-left">
                                <i class="fa fa-envelope"></i>
                            </div>

                            <div class="avatar pull-left dropdown">

                                
                                    <g:if test="${currentUser}"> 
                                        <div data-toggle="dropdown" class="avatar-cursor">
                                            <g:if test="${currentUser.avatar}"> 
                                                <img id="avatar-user"  class="avatar-image" src="${createLink(controller:'user', action:'avatar_image', id:currentUser.id)}" />
                                                <b class="caret"></b>
                                                <div class="status green">&nbsp;</div>
                                            </g:if>
                                            <g:else>
                                                <asset:image id="avatar-user"  class="avatar-image" src="user/1.jpg" alt="" /></a> 
                                                <b class="caret"></b>
                                                <div class="status green">&nbsp;</div>
                                            </g:else>
                                        </div>
								    </g:if>
                                    <g:else>
                                        <div class="login-link">
                                            <a  href="${createLink(uri: '/login/auth')}" style="color: #bdc3c7;">
                                                Log in
                                            </a>
                                            /
                                            <a  href="${createLink(uri: '/register')}" style="color: #bdc3c7;">
                                                Sign up
                                            </a>

                                        </div> 
                                    </g:else>
                                
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation">
                                        <g:link role="menuitem" tabindex="-1" controller="user" action="profile">My Profile</g:link>
                                    </li>
                                    <li role="presentation">
                                        <g:link role="menuitem" tabindex="-1" controller="user" action="profile">My Acitivity feed</g:link>
                                    </li>
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-3" href="${createLink(uri: '/logoff')}">Log Out</a>
                                    </li>
                                </ul>
                            </div>
                            
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>


            <section class="content">

                <g:layoutBody/>

            </section>

            <footer>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-1 col-xs-3 col-sm-2 logo "><a href="#"><asset:image src="question/logo.png" class="logo-size" alt=""/></a></div>
                        <div class="col-lg-8 col-xs-9 col-sm-5 ">Copyrights 2017, zzoverflow.com</div>
                        <div class="col-lg-3 col-xs-12 col-sm-5 sociconcent">
                            <ul class="socialicons">
                                <li><a href="#"><i class="fa fa-facebook-square"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                <li><a href="#"><i class="fa fa-cloud"></i></a></li>
                                <li><a href="#"><i class="fa fa-rss"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </div>

        <!-- LOOK THE DOCUMENTATION FOR MORE INFORMATIONS -->
        <script type="text/javascript">
            
            var revapi;

            jQuery(document).ready(function() {
                "use strict";
                revapi = jQuery('.tp-banner').revolution(
                        {
                            delay: 15000,
                            startwidth: 1200,
                            startheight: 278,
                            hideThumbs: 10,
                            fullWidth: "on"
                        });

            });	//ready

        </script>

        <!-- END REVOLUTION SLIDER -->
    
</body></html>