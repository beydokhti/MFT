<%@ page import="myfamilytree.FamilyMember" %>



<div class="fieldcontain ${hasErrors(bean: familyMemberInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="familyMember.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${familyMemberInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: familyMemberInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="familyMember.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${familyMemberInstance?.birthDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: familyMemberInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="familyMember.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${familyMemberInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: familyMemberInstance, field: 'nikname', 'error')} ">
	<label for="nikname">
		<g:message code="familyMember.nikname.label" default="Nikname" />
		
	</label>
	<g:textField name="nikname" value="${familyMemberInstance?.nikname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: familyMemberInstance, field: 'wedding', 'error')} ">
	<label for="wedding">
		<g:message code="familyMember.wedding.label" default="Wedding" />
		
	</label>
	<g:textField name="wedding" value="${familyMemberInstance?.wedding}"/>
</div>

