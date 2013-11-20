<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<ul id="sdt_menu" class="sdt_menu">
	<c:if test="${ not empty navItems }">
		<c:forEach items="${ navItems }" var="item">
			<li>
				<a href='<c:url value="/images/${ item.id }.html#foto"></c:url>'>
					<img src="${ item.imageUrl }=s170-c" />
					<span class="sdt_active"></span>
					<span class="sdt_wrap">
						<span class="sdt_link">${ item.name }</span>
						<span class="sdt_descr">${ item.description }</span>
					</span>
				</a>
			</li>
		</c:forEach>
	</c:if>
</ul>