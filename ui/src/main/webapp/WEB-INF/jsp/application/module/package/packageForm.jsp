<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="modulePath" value="/applications/${application.id}/modules/${module.id}" />
<c:set var="packagesPath" value="${modulePath}/packages" />

<c:url var="submitUrl" value="${packagesPath}" />
<c:url var="cancelUrl" value="${modulePath}">
	<c:param name="a" value="cancelled" />
</c:url>

<form:form id="packageForm" class="form-horizontal main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="control-group <form:errors path="version">error</form:errors>">
			<label class="control-label" for="version">Version:</label>
			<div class="controls">
				<form:input cssClass="span4" path="version" />
				<form:errors path="version">
					<div class="help-block"><form:errors path="version" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="hash">error</form:errors>">
			<label class="control-label" for="hash">Hash:</label>
			<div class="controls">
				<form:input cssClass="span4" path="hash" />
				<form:errors path="hash">
					<div class="help-block"><form:errors path="hash" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group">
<!-- 			<div class="control-label">&nbsp;</div> -->
			<div class="controls">
				<label class="checkbox" for="passedAutomatedAcceptanceTesting">
					<form:checkbox path="passedAutomatedAcceptanceTesting" /> Passed automated acceptance testing
				</label>
			</div>
		</div>
		<div class="control-group">
<!-- 			<div class="control-label">&nbsp;</div> -->
			<div class="controls">
				<label class="checkbox" for="passedManualAcceptanceTesting">
					<form:checkbox path="passedManualAcceptanceTesting" /> Passed manual acceptance testing
				</label>
			</div>
		</div>
		<div class="control-group">
<!-- 			<div class="control-label">&nbsp;</div> -->
			<div class="controls">
				<label class="checkbox" for="passedCapacityTesting">
					<form:checkbox path="passedCapacityTesting" /> Passed capacity testing
				</label>
			</div>
		</div>
		<div class="control-group">
<!-- 			<div class="control-label">&nbsp;</div> -->
			<div class="controls">
				<label class="checkbox" for="readyForRelease">
					<form:checkbox path="readyForRelease" /> Ready for release
				</label>
			</div>
		</div>
		<div class="control-group">
<!-- 			<div class="control-label">&nbsp;</div> -->
			<div class="controls">
				<label class="checkbox" for="released">
					<form:checkbox path="released" /> Released
				</label>
			</div>
		</div>
		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="Submit" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
