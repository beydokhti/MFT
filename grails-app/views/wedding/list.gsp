
<%@ page import="myfamilytree.Wedding" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wedding.label', default: 'Wedding')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-wedding" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wedding" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="wedding.familyMember.label" default="Family Member" /></th>
					
						<th><g:message code="wedding.spouse.label" default="Spouse" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${weddingInstanceList}" status="i" var="weddingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${weddingInstance.id}">${fieldValue(bean: weddingInstance, field: "familyMember")}</g:link></td>
					
						<td>${fieldValue(bean: weddingInstance, field: "spouse")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${weddingInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
