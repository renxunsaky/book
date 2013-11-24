<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<html>
	<head>
		<title><decorator:title default="Admin" /></title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/stylesheets/admin.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/stylesheets/jquery.dataTables.css" />" />
		<link rel="shortcut icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon"/>
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.easing.1.3.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js' />"></script>
		
		<decorator:head />
	</head>
	<body>
		<div class="adminGlobal">
			<div class="wrapper">
				<div class="flags right">
					<c:if test="${ hasAdminPermission }">
						<span><spring:message code="hide.admin.contorls" /></span>
						<input type="checkbox" id="toggleAdminControls" name="toggleAdminControls" <c:if test="${ not showAdminControls }">checked="checked"</c:if> onclick="javascript:toggleAdminControls(this.checked);" />
					</c:if>
					<a href="?locale=zh_CN"><img title="<spring:message code="lang.chinese" />" alt='<spring:message code="lang.chinese" />' src='<c:url value="/img/china.png" />' /></a>
					<a href="?locale=fr_FR"><img title="<spring:message code="lang.french" />" alt='<spring:message code="lang.french" />' src='<c:url value="/img/france.png" />' /></a>
					<a href="?locale=en_US"><img title="<spring:message code="lang.english" />" alt='<spring:message code="lang.english" />' src='<c:url value="/img/uk.png" />' /></a>
				</div>
				<div class="title">
					<h1><spring:message code="admin.title"/></h1>
				</div>
				<div class="right goHome">
					<a href='<c:url value="/home.html" />'><spring:message code="goto.home.page" /></a>
				</div>
				<div class="sidebar left">
					<ul>
						<li class='${ adminMenu eq "category" ? "selected" : "" }'>
							<a href='<c:url value="/admin/category.html" />'>
								<spring:message code="admin.menu.category"/>
							</a>
						</li>
						<li class='${ adminMenu eq "image" ? "selected" : "" }'>
							<a href='<c:url value="/admin/image.html" />'>
								<spring:message code="admin.image"/>
							</a>
						</li>
					</ul>
				</div>
				
				<div class="content left">
					<decorator:body />
				</div>
			</div>
		</div>
	</body>
</html>