<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<html>
	<head>
		<title><decorator:title default="Book" /></title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="keywords" content="book,gallary,makeup,maquillage,coiffure,hairdressing,marriage,mariage,wedding,mengdie,gao"/>
        <meta name="description" content='<spring:message code="site.description" />' />
        <meta name="author" content="sakyrenxun@gmail.com" />
        <meta name="copyright" content="Copyright 2013, Ren Xun. All Rights Reserved." />
        <meta name="application-name" content="Book" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        
        <link rel="canonical" href="http://www.gmd221.com" />
		<link rel="home" href="http://www.gmd221.com" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/stylesheets/main.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/stylesheets/fotorama.css" />" />
		<link rel="shortcut icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon"/>
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.easing.1.3.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/fotorama.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/book.js' />"></script>
		
		<script type="text/javascript">
			function toggleAdminControls(checked) {
				var url = "<c:url value='/toggleAdminControls.html' />";
				$.post(url, {showAdminControls: !checked}, function(data) {
					if (data != null && data == "OK") {
						if (checked) {
							$(".adminDiv").hide();
							toggleComments();
						} else {
							$(".adminDiv").show();
							toggleComments();
						}
					}
				});
			}
		</script>
		
		<decorator:head />
	</head>
	<body>
		<div class="global">
			<div class="wrapper">
				<div class="head">
					<div class="flags right">
						<c:if test="${ hasAdminPermission }">
							<span><spring:message code="hide.admin.contorls" /></span>
							<input type="checkbox" id="toggleAdminControls" name="toggleAdminControls" <c:if test="${ not showAdminControls }">checked="checked"</c:if> onclick="javascript:toggleAdminControls(this.checked);" />
						</c:if>
						<a href="?locale=zh_CN"><img title="<spring:message code="lang.chinese" />" alt='<spring:message code="lang.chinese" />' src='<c:url value="/img/china.png" />' /></a>
						<a href="?locale=fr_FR"><img title="<spring:message code="lang.french" />" alt='<spring:message code="lang.french" />' src='<c:url value="/img/france.png" />' /></a>
						<a href="?locale=en_US"><img title="<spring:message code="lang.english" />" alt='<spring:message code="lang.english" />' src='<c:url value="/img/uk.png" />' /></a>
					</div>
					<div class="clear"></div>
					<div class="logo">
						<a href='<c:url value="/home.html" />'>
							<img alt="Makeup-Mengdie" src='<c:url value="/img/logo.png" />' />
						</a>
					</div>
					
					<div class="navigation">
						<%@ include file="/WEB-INF/jsp/common/navigation.jsp"%>
					</div>
				</div>
				<div class="clear"></div>
				<div class="content">
					<decorator:body />
				</div>
			</div>
		</div>
	</body>
</html>