<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.question.ask.label"/> - ZZOverflow
        </title>
        <!--asset:stylesheet src="bootstrap.css"/-->
        <asset:stylesheet src="question/create.css"/>
        <asset:stylesheet src="question/jquery-tagit.css"/>
        <asset:stylesheet src="question/tagit-ui-zendesk.css"/>
        <asset:javascript src="bootstrap.js"/>
        <asset:javascript src="question/create.js"/>
        <asset:javascript src="jquery-2.2.0.min.js"/>
        <asset:javascript src="question/jquery-ui.min.js"/>
        <asset:javascript src="question/tag-it.js"/>
        <script>
            $(function(){
                $("#myTags").tagit({
                    placeholderText : "Tags"
                });
            });
        </script>
    </head>
    <body>
        <a href="#create-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="container">
            <div class="row">
                <g:form url="[controller: 'question', action: 'save', method : POST]" role="form" id="contact-form" class="contact-form">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <g:textField class="form-control" name="title"  id="title" placeholder="${message(code: 'default.question.title.placeholder.label')}" type="text"  />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <g:hiddenField name="user.id" value="${question.user.id}" />
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <g:textArea class="form-control textarea" rows="3" name="body" id="Message" placeholder="${message(code: 'default.question.body.placeholder.label')}" /> 
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <ul id="myTags"></ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="submit" class="btn main-btn pull-right"><g:message code="default.question.post"/></button>
                                </div>
                            </div>
                </g:form>
	        </div>
            <div class="row">
                <g:hasErrors bean="${this.question}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.question}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
            </div>
        </div>
                            
    </body>
</html>