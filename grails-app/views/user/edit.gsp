<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.profile.update.label"/></title>
    </head>
    <body>
        <a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="edit-user" class="content scaffold-edit" role="main">
            <h1><g:message code="default.profile.update.label"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.user}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            
            <g:form class="container" resource="${this.user}" method="PUT">
                <g:hiddenField name="version" value="${this.user?.version}" />
                <fieldset class="form">
                        <f:field bean="user" property="name"/>
                        <div class="fieldcontain required">
                            <label for="aboutMe"> <g:message code="default.aboutMe.label" default="About Me"/> </label>
                            <g:textArea  name="aboutMe" value="${user.aboutMe}" />
                        </div>
                    <div class="fieldcontain required">
                        <button class="btn main-btn" type="submit"><g:message code="default.button.update.label" default="Update"/></button>
                     </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
