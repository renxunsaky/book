<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<ul id="sdt_menu" class="sdt_menu">
	<c:forEach items="${ navItems }" var="item">
		<li>
			<a href='<c:url value="/images/category_${ item.id }"></c:url>'>
				<img src="${ item.imageUrl }" />
				<span class="sdt_active"></span>
				<span class="sdt_wrap">
					<span class="std_link">${ item.name }</span>
					<span class="std_descr">${ item.description }</span>
				</span>
			</a>
			<!-- 
			<div class="sdt_box">
				<c:forEach items="${ item.subLinks }" var="subLink">
					<a href="${ subLink.value }">${ subLink.key }</a>
				</c:forEach>
			</div>
			 -->
		</li>
	</c:forEach>
</ul>