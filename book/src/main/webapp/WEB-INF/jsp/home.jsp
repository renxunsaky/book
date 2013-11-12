<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<html>
	<head>
	</head>
	<body>
		<div class="scrollbar">
			<c:forEach items="${images}" var="image">
				<a href='<c:url value="/images/${image.id}"></c:url>'>
					<img src="${image.servingUrl}" />
				</a>
			</c:forEach>
		</div>
	</body>
</html>