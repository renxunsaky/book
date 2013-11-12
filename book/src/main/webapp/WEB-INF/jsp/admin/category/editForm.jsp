<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<form action="${ successPath }" enctype="multipart/form-data" method="post" id="editCategoryForm">
	<input type="hidden" name="id" value="${categoryBean.id}" />
	<input type="hidden" name="lang" value="${categoryBean.lang}" id="langId" />
	<input type="hidden" name="imageUrl" value="${categoryBean.imageUrl}" />
	<input type="hidden" name="blobKey" value="${categoryBean.blobKey}" />
	
	<table>
		<tr>
			<td>
				<spring:message code="admin.name" />
			</td>
			<td>
				<input type="text" name="name" class="btn_small" value="${categoryBean.name}" />
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="admin.description" />
			</td>
			<td>
				<input type="text" name="description" class="btn_small" value="${categoryBean.description}" />
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="admin.active" />
			</td>
			<td>
				<c:choose>
					<c:when test="${ categoryBean.active or (categoryBean.active == null) }">
						<input type="checkbox" name="active" checked="checked" />
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="active" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="admin.axe" />
			</td>
			<td>
				<input type="text" name="axe" class="btn_small" value="${categoryBean.axe}" />
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="admin.image" />
			</td>
			<td>
				<c:if test="${ not empty categoryBean.imageUrl }">
					<img src="${categoryBean.imageUrl}=s170-c" />
				</c:if>
 				<input type="file" name="image" />
			</td>
		</tr>
	</table>
	
	<input type="submit" value='<spring:message code="admin.category.save" />' class="btn_blue btn_medium" />
</form>