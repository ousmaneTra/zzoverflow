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
                                    <img src="images/icon1.jpg" alt="">
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
                                <div class="upvote up" data-id="${question.id}">
                                    <i class="fa fa-thumbs-o-up clickable"></i>
                                    <span id="upvalue${question.id}">
                                        ${question.upvote}
                                    </span>
                                </div>
                                <div class="downvote down" data-id="${question.id}">
                                    <i class="fa fa-thumbs-o-down clickable"></i>
                                    <span id="downvalue${question.id}">
                                        ${question.downvote}
                                    </span>
                                </div>
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
                                        <!-- Solved question -->
                                        <g:if test="${question.correct}">
                                            <!-- Answer is correct -->
                                            <g:if test="${question.correct.id == answer.id}">
                                                <i class="fa fa-3x fa-check-circle solved-icon marksolved ok" data-id="${answer.id}" aria-hidden="true"></i>
                                            </g:if>                                      
                                            <g:else>
                                                <!-- Current user is question owner -->
                                                <g:if test="${currentUser?.id == question.user.id}">
                                                    <i class="fa fa-3x fa-check-circle solved-icon marksolved nok" data-id="${answer.id}" aria-hidden="true"></i>                                           
                                                </g:if>
                                            </g:else>
                                        </g:if>
                                        <g:else>
                                            <g:if test="${question.user.id == currentUser?.id}">
                                                <i class="fa fa-3x fa-check-circle solved-icon marksolved nok" data-id="${answer.id}" aria-hidden="true"></i>                                           
                                            </g:if>
                                        </g:else>
                                    </div>
                                </div>
                                <div class="posttext pull-left">
                                    <p>${answer.body}</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>                              
                            <div class="postinfobot">

                                <div class="likeblock pull-left">
                                    <div class="upvote up" data-id="${answer.id}">
                                        <i class="fa fa-thumbs-o-up clickable"></i>
                                        <span id="upvalue${answer.id}">
                                            ${answer.upvote}
                                        </span>
                                    </div>
                                    <div  class="downvote down" data-id="${answer.id}">
                                        <i class="fa fa-thumbs-o-down clickable"></i>
                                        <span id="downvalue${answer.id}">
                                            ${answer.downvote}
                                        </span>
                                    </div>
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






    
    <script type="text/javascript">

        $(document).ready(function(){
            $(".upvote").click(function(){
                var id = this.getAttribute('data-id')
                <sec:ifLoggedIn>
                       $.ajax({
                            method: "POST",
                            url: "${g.createLink(controller:'vote',action:'save')}",
                            data: {                            
                                'post.id' : id,
                                'user.id' : ${currentUser.id},
                                'vote'      : '1'
                            },
                            success: function (data) {
                                $("#upvalue"+id).text(data)
                            },
                            error: function (request, status, error) {
                                alert(error)
                            },
                            complete: function () {
                            }
                        });
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    alert("You are not logged in. Please log in and try again")
                </sec:ifNotLoggedIn>
            });
            $(".downvote").click(function(){
                var id = this.getAttribute('data-id')
                <sec:ifLoggedIn>
                       $.ajax({
                            method: "POST",
                            url: "${g.createLink(controller:'vote',action:'save')}",
                            data: {                            
                                'post.id' : id,
                                'user.id' : ${currentUser.id},
                                'vote'      : '-1'
                            },
                            success: function (data) {
                                $("#downvalue"+id).text(data)
                            },
                            error: function (request, status, error) {
                                alert(status)
                                alert(error)
                                alert(request)
                            },
                            complete: function () {
                            }
                        });
                
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    alert("You are not logged in. Please log in and try again")
                </sec:ifNotLoggedIn>
            }); 
            $(".marksolved").click(function(){
                var answer_id = this.getAttribute('data-id')
                var element   = $(this)
                <sec:ifLoggedIn>
                       $.ajax({
                            method: "POST",
                            url: "${g.createLink(controller:'question',action:'solved')}",
                            data: {   
                                'question.id' : ${question.id},                         
                                'answer.id'   : answer_id,
                                'user.id'     : ${currentUser.id},
                            },
                            success: function (data) {
                                if(data['previous']){
                                    var previous_correct = $('*[data-id=\''+data['previous']+'\']');
                                    previous_correct.removeClass('ok')
                                    previous_correct.addClass('nok')
                                }
                                element.removeClass('nok');
                                element.addClass('ok');
                            },
                            error: function (request, status, error) {
                                alert(status)
                                alert(error)
                                alert(request)
                            },
                            complete: function () {
                            }
                        });
                
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    alert("You are not logged in. Please log in and try again")
                </sec:ifNotLoggedIn>
            });            
        });
        

    </script>


    </body>
</html>