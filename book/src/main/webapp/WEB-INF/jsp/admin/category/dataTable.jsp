<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<c:choose>
	<c:when test="${ not empty categories }">
		<table id="category_table">
			<thead>
				<tr>
					<th><spring:message code="admin.name" /></th>
					<th><spring:message code="admin.description" /></th>
					<th><spring:message code="admin.icon" /></th>
					<th><spring:message code="admin.active" /></th>
					<th><spring:message code="admin.axe" /></th>
					<th><spring:message code="admin.action" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ categories }" var="item">
					<tr id="row_cat_${ item.id }">
						<td>${ item.name }</td>
						<td>${ item.description }</td>
						<td>
							<img alt="Icon" src="${ item.imageUrl }=s170-c" height="170px" width="170px" />
						</td>
						<td>${ item.active }</td>
						<td>${ item.axe }</td>
						<td>
							<input type="button" onclick="javascript:editCategory(${ item.id });" class="btn_small btn_blue editCategoryBtn" data-id="${ item.id }" value='<spring:message code="admin.edit"></spring:message>' />
							<input type="button" onclick="javascript:deleteCategory(${ item.id });" class="btn_small btn_blue deleteCategoryBtn" data-id="${ item.id }" value='<spring:message code="admin.delete"></spring:message>' />
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
	});
</script>