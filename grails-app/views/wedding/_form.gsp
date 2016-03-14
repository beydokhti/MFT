<%@ page import="myfamilytree.Wedding" %>



<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'familyMember', 'error')} required">
	<label for="familyMember">
		<g:message code="wedding.familyMember.label" default="Family Member" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="familyMember" name="familyMember.id" from="${myfamilytree.FamilyMember.list()}" optionKey="id" required="" value="${weddingInstance?.familyMember?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'spouse', 'error')} required">
	<label for="spouse">
		<g:message code="wedding.spouse.label" default="Spouse" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="spouse" name="spouse.id" from="${myfamilytree.FamilyMember.list()}" optionKey="id" required="" value="${weddingInstance?.spouse?.id}" class="many-to-one"/>
</div>

