

    <!-- -->
    <div class="sidebarblock">
        <h3><g:message code ="default.sidebar.popular.question"/></h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <ul class="cats">
                <g:each var="question" in="${importantQst}">
                    <li>
                        <g:link action="show" controller="question" id="${question.id}">
                            <div class="important-qst">
                                ${question.title} 
                            </div>
                            <div class="badge pull-right">
                                ${question.upvote}
                            </div>
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </div>
    </div>

    <!-- -->
    <div class="sidebarblock">
        <h3><g:message code ="default.sidebar.popular.tag"/></h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <form action="#" method="post" class="form">
                <table class="poll">
                    <tbody>
                        <g:each var="tag" in="${listTag}">
                            <tr>
                                <td>
                                    <div class="progress">
                                        <div class="progress-bar color1" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${tag.questions.size()*100/occuTag}%">
                                            ${tag.name}
                                        </div>
                                        <div class="pourcent-tag" >
                                            ${(tag.questions.size()*100/occuTag)?.intValue()}%
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </g:each>

                </tbody></table>
            </form>
        </div>
    </div>

    <!-- -->
    <div class="sidebarblock">
        <sec:ifLoggedIn>
            <h3><g:message code ="default.profile.feed"/></h3>
            <g:each var="activity" in="${currentUser.activities}">
                <div class="divline"></div>
                <div class="blocktxt">

                    <g:if test="${activity.type.toString() == 'ASK_QUESTION'}">
                        You have asked a question : 
                        <g:link action="show" controller="question" id="${activity.question.id}">
                            " ${activity?.question?.title} "
                        </g:link>
                        <div class="date-feed">
                            <g:formatDate date="${activity.dateCreated}" type="date" style="LONG"/>
                                    @                                                
                            <g:formatDate date="${activity.dateCreated}" type="time" />
                        </div>
                    </g:if>
                    <g:if test="${activity.type.toString() == 'ANSWER_QUESTION'}">
                        You have answered to a question : 
                        <g:link action="show" controller="question" id="${activity.answer.question.id}">
                            " ${activity?.answer?.body} "
                        </g:link>
                         <div class="date-feed">
                            <g:formatDate date="${activity.dateCreated}" type="date" style="LONG"/>
                                    @                                                
                            <g:formatDate date="${activity.dateCreated}" type="time" />
                        </div>
                    </g:if>

                    <!--a href="#">This Dock Turns Your iPhone Into a Bedside Lamp</a-->
                </div>
            </g:each>
        </sec:ifLoggedIn>
    </div>

