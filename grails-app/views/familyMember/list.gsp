
<%@ page import="myfamilytree.FamilyMember" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'familyMember.label', default: 'FamilyMember')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-familyMember" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-familyMember" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="address" title="${message(code: 'familyMember.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="birthDate" title="${message(code: 'familyMember.birthDate.label', default: 'Birth Date')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'familyMember.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="nikname" title="${message(code: 'familyMember.nikname.label', default: 'Nikname')}" />
					
						<g:sortableColumn property="wedding" title="${message(code: 'familyMember.wedding.label', default: 'Wedding')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${familyMemberInstanceList}" status="i" var="familyMemberInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${familyMemberInstance.id}">${fieldValue(bean: familyMemberInstance, field: "address")}</g:link></td>
					
						<td><g:formatDate date="${familyMemberInstance.birthDate}" /></td>
					
						<td>${fieldValue(bean: familyMemberInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: familyMemberInstance, field: "nikname")}</td>
					
						<td>${fieldValue(bean: familyMemberInstance, field: "wedding")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${familyMemberInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
