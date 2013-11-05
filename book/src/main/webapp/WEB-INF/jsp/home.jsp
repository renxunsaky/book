<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<html>
	<head>
	</head>
	<body>
		<div class="global">
			<div class="wrapper">
				<div class="head">
					<div class="flags right">
						<a href="?locale=zh_CN"><img title="<spring:message code="lang.chinese" />" alt='<spring:message code="lang.chinese" />' src='<c:url value="/img/china.png" />'></a>
						<a href="?locale=fr_FR"><img title="<spring:message code="lang.french" />" alt='<spring:message code="lang.french" />' src='<c:url value="/img/france.png" />'></a>
						<a href="?locale=en_US"><img title="<spring:message code="lang.english" />" alt='<spring:message code="lang.english" />' src='<c:url value="/img/uk.png" />'></a>
					</div>
					<div class="logo">
						<img alt="Makeup-Mengdie" src='<c:url value="/img/logo.png" />' />
					</div>
					
					<div class="navigation">
						<%@ include file="/WEB-INF/jsp/common/navigation.jsp"%>
					</div>
				</div>
				<div class="content">
					<div class="scrollbar">
						<c:forEach items="${images}" var="image">
							<a href='<c:url value="/images/${image.id}"></c:url>'>
								<img src="${imgage.servingUrl}" />
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>