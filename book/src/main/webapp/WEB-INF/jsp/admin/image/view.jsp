<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<select name="category" onchange="javascript:loadImages(this.value)">
	<c:forEach items="${ categories }" var="cat">
		<option value="${ cat.id }" title="${ cat.description }">${ cat.name }</option>
	</c:forEach>
</select>

<div id="imagePool">
	<c:forEach items="${ images }" var="image">
		<div class="imageArea">
			<img src="${ image.servingUrl }" />
		</div>
	</c:forEach>
</div>

<script type="text/javascript">
	function loadImages(categoryId) {
		var url = "<c:url value='/admin/images/" + categoryId + ".html'/>";
		$.get(url, function(data) {
			$("#imagePool").html(data);
		});
	}
</script>