<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<c:choose>
	<c:when test="${ not empty images }">
		<a id="foto" name="foto"></a>
		<div class="fotorama rectangle" data-nav="thumbs" data-thumbheight="60" 
			data-maxwidth="100%" data-width="100%" data-startIndex="${ startIndex }"
			data-allowfullscreen="native" data-hash="true" data-loop="true"
			data-keyboard="true" data-swipe="true" data-trackpad="true">
			<c:forEach items="${ images }" var="image">
				<img src="${ image.servingUrl }=s1600" data-imgid="${ image.id }" />
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<div class="emptyResultDiv rectangle">
			<spring:message code="images.no.result" arguments="${ categoryBean.name }"></spring:message>
		</div>
	</c:otherwise>
</c:choose>

<c:forEach items="${ images }" var="image">
	<div class="commentDiv invisible" id="image_comment_div_${ image.id }">
		<div class="socialLinks"></div>
		<div class="ratings"></div>
		<div class="comments"></div>
	</div>
		
	<c:if test="${ hasAdminPermission }">
		<div class="showInFront invisible" id="show_in_front_div_${ image.id }">
			<span><spring:message code="images.show.in.front"></spring:message></span>
			<input type="checkbox" data-imgId="${ image.id }" class="showInFrontCheck" name="showInFront" <c:if test='${ image.showInFront }'>checked="checked"</c:if> />
			<form action='<c:url value="/images/delete/${ image.id }.html" />' method="post">
				<input type="hidden" name="categoryId" value="${ categoryBean.id }" />
				<input type="submit" value='<spring:message code="images.delete.image" />' />
			</form>
		</div>
	</c:if>
</c:forEach>

<c:if test="${ hasAdminPermission }">
	<div class="adminDiv rectangle">
		<span><spring:message code="images.upload.images"></spring:message></span>
		<form action="${ successPath }" enctype="multipart/form-data" method="post" id="uploadImagesForm">
			<input type="hidden" name="categoryId" value="${ categoryBean.id }" />
			<input type="file" multiple="multiple" name="uploadedImages" />
			<input type="submit" value='<spring:message code="images.upload.button" />'  class="btn_small btn_blue" />
		</form>
	</div>
</c:if>

<script type="text/javascript">
	function toggleComments(imgId) {
		//hide other comments while show the correspanding one
		$(".commentDiv").hide();
		$("#image_comment_div_" + imgId).show();
		
		$(".showInFront").hide();
		if (!$("#toggleAdminControls").is(":checked")) {
			$("#show_in_front_div_" + imgId).show();
		}
	}
	
	$(document).ready(function() {
		var containerHeight = $(window).height() - 66;
		if (containerHeight < 400) {
			containerHeight = 400;
		}
		
		var isMobile = false;
		if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
			isMobile = true;
		}
		
		$('.fotorama')
			.on('fotorama:show', function (e, fotorama) {
				var activeFrame = fotorama.activeFrame;
				var imgId = activeFrame.imgid;
				toggleComments(imgId);
		}).fotorama();
	
		$(".fotorama").fotorama({
			nav: isMobile ? 'dots' : 'thumbs',
			height: containerHeight
		});
		
		var checked = $("#toggleAdminControls").is(":checked");
		if (checked) {
			$(".adminDiv").hide();
		} else {
			$(".adminDiv").show();
		}
		
		$(".showInFrontCheck").click(function() {
			var showInFront = $(this).is(":checked");
			var imageId = $(this).attr("data-imgId");
			var url = "<c:url value='/images/showInFront/" + imageId + ".html'/>";
			$.post(url, {showInFront: showInFront});
		});
	});
</script>