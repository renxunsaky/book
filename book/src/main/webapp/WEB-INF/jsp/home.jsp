<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<html>
	<head>
	</head>
	<body class="homeBody">
		<div class="wrapper">
			<div class="head">
				<div class="logo">
					<img alt="Makeup-Mengdie" src='<c:url value="/img/logo.png"></c:url>' />
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
	</body>
</html>