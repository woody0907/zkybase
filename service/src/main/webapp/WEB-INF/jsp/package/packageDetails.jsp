<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="span12">
		<div class="section first">
			<div class="grid">
				<div class="gridBody">
					<div class="row">
						<div class="span3 gridLabel">Group ID:</div>
						<div class="span8">
							<c:out value="${package.groupId}" />
						</div>
					</div>
					<div class="row">
						<div class="span3 gridLabel">Package ID:</div>
						<div class="span8">
							<c:out value="${package.packageId}" />
						</div>
					</div>
					<div class="row">
						<div class="span3 gridLabel">Version:</div>
						<div class="span8">
							<c:out value="${package.version}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
