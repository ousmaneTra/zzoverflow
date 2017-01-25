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
                    <div class="row post-content">
                        <div class="col-lg-8 col-md-8">
                            <g:each in="${list}" var="post" status="i">
                            <!-- POST -->
                                <div class="post">                    
                                    <div class="wrap-ut pull-left">
                                        <div class="userinfo pull-left">
                                            <div class="avatar">
                                                <g:if test="${post.user.avatar}">
                                                    <img class="avatar avatar-image" src="${createLink(controller:'user', action:'avatar_image', id:post.user.id)}" />
                                                </g:if>
                                                <g:else>
                                                    <asset:image class="avatar-image" src="user/1.jpg" alt="" />
                                                </g:else>
                                                
                                                <div class="status green">&nbsp;</div>
                                                <div class="avatar-username">${post.user.username}</div>
                                            </div>

                                            <div class="icons">
                                                <div class="up">
                                                    <i class="fa fa-thumbs-o-up"></i>
                                                    ${post.upvote}
                                                </div>
                                                <div class="down">
                                                    <i class="fa fa-thumbs-o-down"></i>
                                                    ${post.downvote}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="posttext pull-left">
                                            <h2>
                                                <g:link action="show" id="${post.id}">
                                                    <%
                                                        if(post.title.size() > 55)
                                                            print post.title.substring(0,55) + " ..."
                                                        else
                                                            print post.title
                                                    %>
                                                </g:link>
                                            </h2>
                                            <p class="post-body">
                                                <%
                                                    if(post.body.size() > 140)
                                                        print post.body.substring(0,140) + " ..."
                                                    else
                                                        print post.body
                                                %>
                                            </p>
                                            <div class="tag-container">
                                                <g:each in="${post.tags}" var="tag" status="j">
                                                    <span class="badge pull-left tag">${tag}</span>
                                                </g:each>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="postinfo pull-left">
                                        <div class="comments">
                                            <g:if test="${post.solved}">
                                                <div class="commentbg-solved">
                                            </g:if>
                                            <g:else>
                                                <div class="commentbg">
                                            </g:else>
                                                ${comments.get(post.id)}
                                                <div class="mark"></div>
                                            </div>

                                        </div>
                                        <div class="views"><i class="fa fa-eye"></i> ${post.nbView} </div>
                                        <div class="time">
                                            <i class="fa fa-clock-o"></i>
                                                ${duration.get(post.id)}
                                        </div>                                    
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            <!-- POST -->
                            </g:each>



                        </div>
                        <div class="col-lg-4 col-md-4">

                            <g:applyLayout name="sidebar">
                            </g:applyLayout>


                        </div>
                        

                    </div>
                </div>



                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-xs-12">
                            <div class="pull-left"><a href="#" class="prevnext"><i class="fa fa-angle-left"></i></a></div>
                            <div class="pull-left">
                                <ul class="paginationforum">
                                    <li class="hidden-xs"><a href="#">1</a></li>
                                    <li class="hidden-xs"><a href="#">2</a></li>
                                    <li class="hidden-xs"><a href="#">3</a></li>
                                    <li class="hidden-xs"><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">6</a></li>
                                    <li><a href="#" class="active">7</a></li>
                                    <li><a href="#">8</a></li>
                                    <li class="hidden-xs"><a href="#">9</a></li>
                                    <li class="hidden-xs"><a href="#">10</a></li>
                                    <li class="hidden-xs hidden-md"><a href="#">11</a></li>
                                    <li class="hidden-xs hidden-md"><a href="#">12</a></li>
                                    <li class="hidden-xs hidden-sm hidden-md"><a href="#">13</a></li>
                                    <li><a href="#">1586</a></li>
                                </ul>
                            </div>
                            <div class="pull-left"><a href="#" class="prevnext last"><i class="fa fa-angle-right"></i></a></div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
    
    </body>
</html>