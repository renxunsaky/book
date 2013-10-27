<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title><decorator:title default="Book" /></title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="Slide Down Box Menu with jQuery and CSS3" />
        <meta name="keywords" content="jquery, css3, sliding, box, menu, cube, navigation, portfolio, thumbnails"/>
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/stylesheets/main.css"></c:url>" />
		<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
		
		<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"></c:url>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.easing.1.3.js"></c:url>"></script>
		
		<decorator:head />
	</head>
	<body>
		<decorator:body />
	</body>
</html>