<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'currentUser.label', default: 'User')}" />
        <title><g:message code="default.profile.label"/></title>
    
    	<asset:stylesheet src="profile/profile.css"/>
	</head>
    <body>
<div id="user-profile-2" class="user-profile">
	<g:if test="${flash.message}">
    	<div class="alert alert-info" role="status">${flash.message}</div>
    </g:if>
		<div class="tabbable">
			<ul class="nav nav-tabs padding-18">
				<li class="active">
					<a data-toggle="tab" href="#home">
						<i class="green ace-icon fa fa-user bigger-120"></i>
						Profile
					</a>
				</li>

				<li>
					<a data-toggle="tab" href="#feed">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						Activity Feed
					</a>
				</li>
			</ul>

			<div class="tab-content no-border padding-24">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-12 col-sm-3 center">
							<span class="profile-picture">
								<g:if test="${currentUser.avatar}">
									<img class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:currentUser.id)}" />
								</g:if>
								<g:else>
									 <asset:image class="editable img-responsive" alt=" Avatar" id="avatar2" src="user/1.jpg" alt="" />
								</g:else>
							</span>
							<div class="space space-4"></div>
							<g:link action="changeAvatar"><g:message code="default.avatar.change.label" default="Change your avatar"/></g:link>

						</div><!-- /.col -->

						<div class="col-xs-12 col-sm-9">
							<h4 class="blue">
								<span class="middle">${currentUser.name}</span>

								<span class="label label-purple arrowed-in-right">
									<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
									online
								</span>
							</h4>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> <g:message code="default.username.label"/> </div>

									<div class="profile-info-value">
										<span>${currentUser.username}</span>
									</div>
								</div>
								
								
								<div class="profile-info-row">
									<div class="profile-info-name"> <g:message code="default.reputation.label"/> </div>

									<div class="profile-info-value">
										<span>${currentUser.reputation}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Joined </div>

									<div class="profile-info-value">
										<span><g:formatDate date="${currentUser.dateCreated}" type="date" style="LONG" formatName="default.date.format"/></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Badges </div>

									<div class="profile-info-value">
										<g:each var="item" in="${currentUser.badges}">
											<span class="badge">${item.name}</span>
										</g:each>
									</div>
								</div>
							</div>

							<div class="hr hr-8 dotted"></div>

								<div class="profile-info-row">
									<div class="profile-info-name"><g:message code="default.aboutMe.label" default="About Me"/></div>

									<div class="profile-info-value">
										<g:if test = "${currentUser.aboutMe}">
											${currentUser.aboutMe}
										</g:if>
									</div>
								</div>
							<div class="space space-4"></div>
							<g:link class="btn btn-sm btn-block btn-success" action="edit">
								<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
								<span class="bigger-110"><g:message code="default.button.edit.label" default="Edit" /></span>
							</g:link>
						</div><!-- /.col -->
					</div><!-- /.row -->
					
				</div><!-- /#home -->

				<div id="feed" class="tab-pane">
					<div class="profile-feed row">
						<div class="col-sm-6">
							<g:each var="activity" in="${currentUser.activities}">
								<div class="profile-activity clearfix">
									<div>
										<img class="pull-left" alt="${currentUser.name + ' Avatar' }" src="${createLink(controller:'user', action:'avatar_image', id:currentUser.id)}" />
										 ${currentUser.name}
										<g:if test="${activity.type.toString() == 'ASK_QUESTION'}">
											asked a <g:link action="show" controller="question" id="${activity.question.id}">question</g:link>
											<br/>
											${activity?.question?.title}
										</g:if>
										<g:if test="${activity.type.toString() == 'ANSWER_QUESTION'}">
											answered a <g:link action="show" controller="question" id="${activity.answer.question.id}">question</g:link>
											<br/>
											${activity?.answer?.body}
										</g:if>
										
										<div class="time">
											<i class="ace-icon fa fa-clock-o bigger-110"></i>
											<g:formatDate date="${activity.dateCreated}" type="datetime" style="SHORT" formatName="default.date.format"/>
										</div>
									</div>
									<div class="tools action-buttons">
										<a href="#" class="blue">
											<i class="ace-icon fa fa-pencil bigger-125"></i>
										</a>

										<a href="#" class="red">
											<i class="ace-icon fa fa-times bigger-125"></i>
										</a>
									</div>
								</div>
							</g:each>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /#feed -->
			</div>
		</div>
	</div>
    </body>
</html>
