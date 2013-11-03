<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<c:choose>
	<c:when test="${ not empty categories }">
		<table id="category_table">
			<thead>
				<tr>
					<th><spring:message code="admin.category.name" /></th>
					<th><spring:message code="admin.category.description" /></th>
					<th><spring:message code="admin.category.icon" /></th>
					<th><spring:message code="admin.category.active" /></th>
					<th><spring:message code="admin.category.axe" /></th>
					<th><spring:message code="admin.category.action" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ categories }" var="item">
					<tr>
						<td>${ item.name }</td>
						<td>${ item.description }</td>
						<td>
							<img alt="Icon" src="${ item.imageUrl }" height="170px" width="170px" />
						</td>
						<td>${ item.active }</td>
						<td>${ item.axe }</td>
						<td>
							<input type="button" class="btn_small btn_blue editCategoryBtn" data-id="${ item.id }" value='<spring:message code="admin.category.edit"></spring:message>' />
							<input type="button" class="btn_small btn_blue deleteCategoryBtn" data-id="${ item.id }" value='<spring:message code="admin.category.delete"></spring:message>' />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<p class="emptyResult"><spring:message code="admin.empty.result" /></p>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	$(document).ready(function() {
		$("#category_table").dataTable();
		
		$(".editCategoryBtn").click(function() {
			var url = "<c:url value='/admin/category/edit.html'/>";
			var id = $(this).attr("data-id");
			var lang = $("#changeLangSelect").val();
			$.get(url, {id: id, lang: lang}, function(data) {
				$(".editArea").html(data);
				$(".editArea").show();
			});
		});
		
		$(".deleteCategoryBtn").click(function() {
			var id = $(this).attr("data-id");
			var url = "<c:url value='/admin/category/delete.html'/>";
			$.post(url, {id: id}, function(data) {
				if (data != null && data == "OK") {
					$(this).parent().parent().remove();
				}
			});
		});
	});
</script>