<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<spring:message code="account.connection"></spring:message>

<a href="?locale=en_US">English</a>

<form enctype="multipart/form-data" method="post" action="<%= blobstoreService.createUploadUrl("/upload.html") %>">
	<input type="file" multiple="multiple" name="imagess" />
	
	<input type="submit" value="Upload" />
</form>

<h2>blobkey is : ${imgUrl}</h2>
<img src="${imgUrl}" alt="imgUrl" />