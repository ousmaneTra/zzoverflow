<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Avatar</title>
    </head>
    <body>
        <a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="edit-user" class="content scaffold-edit" role="main">
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
            <g:if test="${user.avatar}">
                <img class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:user.ident())}" />
            </g:if>
            <fieldset>
            <legend>Avatar Upload</legend>
            <g:uploadForm action="upload_avatar">
                <label for="avatar">Avatar (16K)</label>
                <input type="file" name="avatar" id="avatar" />
                <div style="font-size:0.8em; margin: 1.0em;">
                For best results, your avatar should have a width-to-height ratio of 4:5.
                For example, if your image is 80 pixels wide, it should be 100 pixels high.
                </div>
                <input type="submit" class="buttons" value="Upload" />
            </g:uploadForm>
            </fieldset>
        </div>
    </body>
</html>
