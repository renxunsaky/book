<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<div class="dataTable">
	<%@ include file="dataTable.jsp"%>
</div>

<hr class="separator" />

<div class="actionButtons">
	<select name="language" id="changeLangSelect">
		<option value="zh" ><spring:message code="lang.chinese"></spring:message></option>
		<option value="fr"><spring:message code="lang.french"></spring:message></option>
		<option value="en"><spring:message code="lang.english"></spring:message></option>
	</select>
	<input type="button" class="btn_blue btn_medium" id="create_new_cat_btn" value='<spring:message code="admin.category.create"></spring:message>'  />
</div>

<hr class="separator" />

<div class="editArea invisible">
	<%@ include file="editForm.jsp"%>
</div>

<script type="text/javascript">
	function changeLanguage(lang) {
		var url = "<c:url value='/admin/category/changeLanguage.html'/>";
		$.get(url, {lang: lang}, function(data) {
			$(".dataTable").html(data);
		});
	}
	
	function editCategory(categoryId) {
		var url = "<c:url value='/admin/category/edit.html'/>";
		var lang = $("#changeLangSelect").val();
		$.get(url, {id: categoryId, lang: lang}, function(data) {
			$(".editArea").html(data);
			$(".editArea").show();
		});
	};
	
	function deleteCategory(categoryId) {
		var url = "<c:url value='/admin/category/delete.html'/>";
		$.post(url, {id: categoryId}, function(data) {
			if (data != null && data == "OK") {
				$("#row_cat_" + categoryId).remove();
			}
		});
	};
	
	$(document).ready(function() {
		$("#create_new_cat_btn").click(function() {
			var lang = $("#changeLangSelect").val();
			$("#langId").val(lang);
			$("#editCategoryForm input[type='text']").val("");
			$(".editArea").show();
		});
	});
</script>