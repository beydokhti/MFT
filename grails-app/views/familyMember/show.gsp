
<%@ page import="myfamilytree.FamilyMember" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'familyMember.label', default: 'FamilyMember')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-familyMember" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-familyMember" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list familyMember">
			
				<g:if test="${familyMemberInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="familyMember.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${familyMemberInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${familyMemberInstance?.birthDate}">
				<li class="fieldcontain">
					<span id="birthDate-label" class="property-label"><g:message code="familyMember.birthDate.label" default="Birth Date" /></span>
					
						<span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${familyMemberInstance?.birthDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${familyMemberInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="familyMember.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${familyMemberInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${familyMemberInstance?.nikname}">
				<li class="fieldcontain">
					<span id="nikname-label" class="property-label"><g:message code="familyMember.nikname.label" default="Nikname" /></span>
					
						<span class="property-value" aria-labelledby="nikname-label"><g:fieldValue bean="${familyMemberInstance}" field="nikname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${familyMemberInstance?.wedding}">
				<li class="fieldcontain">
					<span id="wedding-label" class="property-label"><g:message code="familyMember.wedding.label" default="Wedding" /></span>
					
						<span class="property-value" aria-labelledby="wedding-label"><g:fieldValue bean="${familyMemberInstance}" field="wedding"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${familyMemberInstance?.id}" />
					<g:link class="edit" action="edit" id="${familyMemberInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
