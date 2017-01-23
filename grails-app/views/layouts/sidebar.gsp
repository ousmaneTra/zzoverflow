

    <!-- -->
    <div class="sidebarblock">
        <h3>Categories</h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <ul class="cats">
                <li><a href="#">Trading for Money <span class="badge pull-right">20</span></a></li>
                <li><a href="#">Vault Keys Giveway <span class="badge pull-right">10</span></a></li>
                <li><a href="#">Misc Guns Locations <span class="badge pull-right">50</span></a></li>
                <li><a href="#">Looking for Players <span class="badge pull-right">36</span></a></li>
                <li><a href="#">Stupid Bugs &amp; Solves <span class="badge pull-right">41</span></a></li>
                <li><a href="#">Video &amp; Audio Drivers <span class="badge pull-right">11</span></a></li>
                <li><a href="#">2K Official Forums <span class="badge pull-right">5</span></a></li>
            </ul>
        </div>
    </div>

    <!-- -->
    <div class="sidebarblock">
        <h3>Poll of the Week</h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <p>Which game you are playing this week?</p>
            <form action="#" method="post" class="form">
                <table class="poll">
                    <tbody><tr>
                        <td>
                            <div class="progress">
                                <div class="progress-bar color1" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 90%">
                                    Call of Duty Ghosts
                                </div>
                            </div>
                        </td>
                        <td class="chbox">
                            <input id="opt1" name="opt" value="1" type="radio">  
                            <label for="opt1"></label>  
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="progress">
                                <div class="progress-bar color2" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 63%">
                                    Titanfall
                                </div>
                            </div>
                        </td>
                        <td class="chbox">
                            <input id="opt2" name="opt" value="2" checked="" type="radio">  
                            <label for="opt2"></label>  
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="progress">
                                <div class="progress-bar color3" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 75%">
                                    Battlefield 4
                                </div>
                            </div>
                        </td>
                        <td class="chbox">
                            <input id="opt3" name="opt" value="3" type="radio">  
                            <label for="opt3"></label>  
                        </td>
                    </tr>
                </tbody></table>
            </form>
            <p class="smal">Voting ends on 19th of October</p>
        </div>
    </div>

    <!-- -->
    <div class="sidebarblock">
        <sec:ifLoggedIn>
            <h3>My Activity feed</h3>
            <g:each var="activity" in="${currentUser.activities}">
                <div class="divline"></div>
                <div class="blocktxt">

                    <g:if test="${activity.type.toString() == 'ASK_QUESTION'}">
                        You have asked a <g:link action="show" controller="question" id="${activity.question.id}">question</g:link>
                         : 
                        ${activity?.question?.title}
                    </g:if>
                    <g:if test="${activity.type.toString() == 'ANSWER_QUESTION'}">
                        You have answered a <g:link action="show" controller="question" id="${activity.answer.question.id}">question</g:link>
                         : 
                        ${activity?.answer?.body}
                    </g:if>

                    <!--a href="#">This Dock Turns Your iPhone Into a Bedside Lamp</a-->
                </div>
            </g:each>
        </sec:ifLoggedIn>
    </div>

