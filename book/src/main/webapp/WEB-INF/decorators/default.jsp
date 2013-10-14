<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=600px, user-scalable=yes, minimum-scale=0.25, maximum-scale=5.0, target-densitydpi=device-dpi"/>
		<title><decorator:title default="SFR ESIM" /></title>
		<link rel="shortcut icon" href="//img.s-sfr.fr/elements/favicon.ico"></link>
		<script type="text/javascript" src="<c:url value='/js/jquery-1.7.2.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.json-2.2.min.js' />"></script>
    	<script type='text/javascript' src="<c:url value='/js/validate/jquery.validate.js'/>"></script>

		<!-- Fancy box -->
		<script type="text/javascript" src="<c:url value='/js/fancybox/jquery.fancybox-1.3.4.pack.js' />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value='/js/fancybox/jquery.fancybox-1.3.4.css' />" media="screen" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/our-fancybox.css' />" media="screen" />
		
		<!-- Css Esim -->
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/esim.css' />" media="screen" />
		<!-- début ajout spécifique au formulaire d'identification -->
		
		<script type="text/javascript">
			var __host = "<c:url value='/' />";
			if (__host.indexOf(";") != -1) {
				__host = __host.substr(0, __host.indexOf(";"));
			}
		</script>
		<!-- infos persos -->
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.selectbox.css' />" media="screen" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery.selectbox-0.2.js' />"></script>

		<script type="text/javascript" src="http://static.s-sfr.fr/resources/ist/loader.sfr.min.js"></script>
		<script type="text/javascript">
			sfrIstConfig.menuSelectedItem = 10000;
            sfrIstConfig.headerDisplaySearch = false;
            sfrIstConfig.headerDisplayUserInfo = false;
            sfrIstConfig.headerDisplayMiscLinks = false;
        </script>
        
		<decorator:head />
	</head>
	<body>
		<!-- IST HEADER PORTAIL -->
	    <script type="text/javascript">$sfr.istHeaderService();</script>
	    <!-- / IST HEADER PORTAIL -->
	
		<decorator:body />
		
	    <!-- IST FOOTER -->
	    <script type="text/javascript">$sfr.istFooterLight();</script>
	    <!-- / IST FOOTER -->   
	    
	    <script type="text/javascript">
		    function adaptContent() {
				var realWidth = 984;
				var footerWidth = jQuery("#lightFooter").width();
				var ratio = footerWidth/realWidth;
				
				if (ratio < 1) {
					changeFontSize("#lightFooter", ratio);
					changeFontSize("#lightFooter #lightFooterLinksList li", ratio);
					changeFontSize("#lightFooter #lightFooterLinksList li a", ratio);
					
					changeFontSize("#footerBottomInfo", ratio);
					
					$("#copyrightsInfo a img").css("transform", "scale("+ ratio + "," + ratio +")");
					$("#copyrightsInfo a img").css("-webkit-transform", "scale("+ ratio + "," + ratio +")");
					$("#copyrightsInfo a img").css("-o-transform", "scale("+ ratio + "," + ratio +")");
					$("#copyrightsInfo a img").css("-ms-transform", "scale("+ ratio + "," + ratio +")");
					$("#copyrightsInfo a img").css("-moz-transform", "scale("+ ratio + "," + ratio +")");
					
					$("#logoFIANET").css("transform", "scale("+ ratio + "," + ratio +")");
					$("#logoFIANET").css("-webkit-transform", "scale("+ ratio + "," + ratio +")");
					$("#logoFIANET").css("-o-transform", "scale("+ ratio + "," + ratio +")");
					$("#logoFIANET").css("-ms-transform", "scale("+ ratio + "," + ratio +")");
					$("#logoFIANET").css("-moz-transform", "scale("+ ratio + "," + ratio +")");
				}
			}
			
			function changeFontSize(id, ratio) {
				var fontSize = jQuery(id).css("font-size");
				
				fontSize = parseInt(fontSize.substr(0, fontSize.indexOf("px"))) * ratio;
				
				jQuery(id).css("font-size", fontSize + "px");
			}
			
			setTimeout(adaptContent, 1000);
		</script>
	</body>
</html>