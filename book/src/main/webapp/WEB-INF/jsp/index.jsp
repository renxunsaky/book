<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


Account : <spring:message code="account.connection"></spring:message>

<form enctype="multipart/form-data" method="post" action="<%= blobstoreService.createUploadUrl("/upload.html") %>">
	<input type="file" multiple="multiple" name="images" />
	
	<input type="submit" value="Upload" />
</form>