<html lang="en">
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.show.label" args="[entityName]" /> - ZZOverflow</title>
        </title>
    </head>
    <body>
        <div class="container">
            <div class="row post-content">
                <div class="col-lg-8 col-md-8">

                    <!-- POST -->
                    <div class="post beforepagination">
                        <div class="topwrap">
                            <div class="userinfo pull-left">
                                <div class="avatar">
                                    <g:if test="${question.user.avatar}">
                                            <img class="avatar avatar-image" src="${createLink(controller:'user', action:'avatar_image', id:question.user.id)}" />
                                    </g:if>
                                    <g:else>
                                        <asset:image class="avatar-image" src="user/1.jpg" alt="" />
                                    </g:else>
                                    <div class="status green">&nbsp;</div>
                                    <div class="avatar-username">${question.user.username}</div>
                                </div>

                                <div class="icons">
                                    <img src="images/icon1.jpg" alt=""><img src="images/icon4.jpg" alt=""><img src="images/icon5.jpg" alt=""><img src="images/icon6.jpg" alt="">
                                </div>
                            </div>
                            <div class="posttext pull-left">
                                <h2>${question.title}</h2>
                                <p>${question.body}</p>
                            </div>
                            <div class="clearfix"></div>
                        </div>                              
                        <div class="postinfobot">

                            <div class="likeblock pull-left">
                                <a href="#" class="up">
                                    <i class="fa fa-thumbs-o-up"></i>
                                    ${question.upvote}
                                </a>
                                <a href="#" class="down">
                                    <i class="fa fa-thumbs-o-down"></i>
                                    ${question.downvote}
                                </a>
                            </div>

                            <div class="prev pull-left">                                        
                                <a href="#">
                                    <i class="fa fa-reply"></i>
                                </a>
                            </div>

                            <div class="posted pull-left">
                                <i class="fa fa-clock-o"></i>
                                    Posted on :
                                        <g:formatDate date="${question.dateCreated}" type="date" style="LONG"/>
                                    @                                                
                                        <g:formatDate date="${question.dateCreated}" type="time" />
                            </div>

                            <div class="next pull-right">                                        
                                <a href="#"><i class="fa fa-share"></i></a>

                                <a href="#"><i class="fa fa-flag"></i></a>
                            </div>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <!-- POST -->

                    <!-- ANSWERS -->
                    <g:each in="${ question.answers.sort{a,b-> a.dateCreated.compareTo(b.dateCreated)} }" var="answer" status="i">
                        <div class="post">
                            <div class="topwrap">
                                <div class="userinfo pull-left">
                                    <div class="avatar">
                                        <g:if test="${answer.user.avatar}">
                                            <img class="avatar avatar-image" src="${createLink(controller:'user', action:'avatar_image', id:answer.user.id)}" />
                                        </g:if>
                                        <g:else>
                                            <asset:image class="avatar-image" src="user/1.jpg" alt="" />
                                        </g:else>
                                        <div class="status red">&nbsp;</div>
                                        <div class="avatar-username">${answer.user.username}</div>
                                    </div>

                                    <div class="icons">
                                        <img src="images/icon3.jpg" alt="">
                                        <img src="images/icon4.jpg" alt="">
                                        <img src="images/icon5.jpg" alt="">
                                        <img src="images/icon6.jpg" alt="">
                                    </div>
                                </div>
                                <div class="posttext pull-left">
                                    <p>${answer.body}</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>                              
                            <div class="postinfobot">

                                <div class="likeblock pull-left">
                                    <a href="#" class="up">
                                        <i class="fa fa-thumbs-o-up"></i>
                                        ${answer.upvote}
                                    </a>
                                    <a href="#" class="down">
                                        <i class="fa fa-thumbs-o-down"></i>
                                        ${answer.downvote}
                                    </a>
                                </div>

                                <div class="prev pull-left">                                        
                                    <a href="#" class="reply-padding">
                                        <i class="fa fa-reply"></i>
                                    </a>
                                </div>

                                <div class="posted pull-left">
                                    <i class="fa fa-clock-o"></i> 
                                        Posted on : 
                                        <g:formatDate date="${answer.dateCreated}" type="date" style="LONG"/>
                                        @                                                
                                        <g:formatDate date="${answer.dateCreated}" type="time" />
                                    </div>

                                <div class="next pull-right">                                        
                                    <a href="#"><i class="fa fa-share"></i></a>

                                    <a href="#"><i class="fa fa-flag"></i></a>
                                </div>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </g:each>
                    
                    <!-- /ANSWERS -->

                    <!-- NEW ANSWER -->
                    <div class="post">
                        <g:form url="[controller: 'answer', action: 'process', method : POST]" class="form">            
                            <div class="topwrap">
                                <div class="userinfo pull-left">
                                    <div class="avatar">
                                        <sec:ifLoggedIn>
                                            <g:if test="${currentUser.avatar}">
                                                <img class="avatar avatar-image" src="${createLink(controller:'user', action:'avatar_image', id:currentUser.id)}" />
                                            </g:if>
                                            <g:else>
                                                <asset:image class="avatar-image" src="user/1.jpg" alt="" />
                                            </g:else>
                                        </sec:ifLoggedIn>
                                        <sec:ifNotLoggedIn>
                                            <asset:image class="avatar-image" src="user/1.jpg" alt="" />
                                        </sec:ifNotLoggedIn>
                                        <div class="status red">&nbsp;</div>
                                        <sec:ifLoggedIn>
                                            <div class="avatar-username">${currentUser.username}</div>
                                        </sec:ifLoggedIn>
                                    </div>

                                    <div class="icons">
                                        <img src="images/icon3.jpg" alt=""><img src="images/icon4.jpg" alt=""><img src="images/icon5.jpg" alt=""><img src="images/icon6.jpg" alt="">
                                    </div>
                                </div>
                                <div class="posttext pull-left">
                                    <div class="textwraper">
                                        <div class="postreply">Post a Reply</div>
                                        <textarea name="body" id="reply" placeholder="Type your message here"></textarea>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div> 
                            <div class="row">
                                <sec:ifLoggedIn>
                                    <g:hiddenField name="user.id" value="${currentUser.id}" />
                                </sec:ifLoggedIn>
                                <g:hiddenField name="question.id" value="${question.id}" />
                            </div>                             
                            <div class="postinfobot">

                                <div class="notechbox pull-left">
                                    <input name="note" id="note" class="form-control" type="checkbox">
                                </div>

                                <div class="pull-left">
                                    <label for="note"> Email me when some one post a reply</label>
                                </div>

                                <div class="pull-right postreply">
                                    <div class="pull-left smile"><a href="#"><i class="fa fa-smile-o"></i></a></div>
                                    <div class="pull-left"><button type="submit" class="btn btn-primary">Post Reply</button></div>
                                    <div class="clearfix"></div>
                                </div>


                                <div class="clearfix"></div>
                            </div>
                        </g:form>
                    </div>
                    <!-- NEW ANSWER -->


                </div>
                <div class="col-lg-4 col-md-4">

                    <g:applyLayout name="sidebar">
                    </g:applyLayout>


                </div>
            </div>
        </div>



        <div class="container">
            <div class="row">
                <div class="col-lg-8">
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