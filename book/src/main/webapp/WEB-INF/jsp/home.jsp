<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<html>
	<head>
	</head>
	<body>
		<div class="rm_wrapper rectangle">
			<c:if test="${ not empty list1 }">
				<div id="rm_container_1" class="rm_container">
					<c:forEach items="${ list1 }" var="img" varStatus="status">
						<c:set value="invisible imgDiv" var="className" />
						<c:if test="${ status.count == 1 }">
							<c:set value="active imgDiv" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list1) }">
							<c:set value="imgDiv last" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list1) and 1 == fn:length(list1)}">
							<c:set value="active imgDiv last" var="className" />
						</c:if>
						<div class="${ className }" id="img_c1_${ status.count }">
							<a href='<c:url value="/images/${ img.categoryId }/${ img.id }.html#foto" />'>
								<img src="${ img.servingUrl }" width="220" height="330" alt='<spring:message code="image.description" />' />
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${ not empty list2 }">
				<div id="rm_container_2" class="rm_container">
					<c:forEach items="${ list2 }" var="img" varStatus="status">
						<c:set value="invisible imgDiv" var="className" />
						<c:if test="${ status.count == 1 }">
							<c:set value="active imgDiv" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list2) }">
							<c:set value="imgDiv last" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list2) and 1 == fn:length(list2)}">
							<c:set value="active imgDiv last" var="className" />
						</c:if>
						<div class="${ className }" id="img_c2_${ status.count }">
							<a href='<c:url value="/images/${ img.categoryId }/${ img.id }.html#foto" />'>
								<img src="${ img.servingUrl }" width="220" height="330" alt='<spring:message code="image.description" />' />
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${ not empty list3 }">
				<div id="rm_container_3" class="rm_container">
					<c:forEach items="${ list3 }" var="img" varStatus="status">
						<c:set value="invisible imgDiv" var="className" />
						<c:if test="${ status.count == 1 }">
							<c:set value="active imgDiv" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list3) }">
							<c:set value="imgDiv last" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list3) and 1 == fn:length(list3)}">
							<c:set value="active imgDiv last" var="className" />
						</c:if>
						<div class="${ className }" id="img_c3_${ status.count }">
							<a href='<c:url value="/images/${ img.categoryId }/${ img.id }.html#foto" />'>
								<img src="${ img.servingUrl }" width="220" height="330" alt='<spring:message code="image.description" />' />
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${ not empty list4 }">
				<div id="rm_container_4" class="rm_container">
					<c:forEach items="${ list4 }" var="img" varStatus="status">
						<c:set value="invisible imgDiv" var="className" />
						<c:if test="${ status.count == 1 }">
							<c:set value="active imgDiv" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list4) }">
							<c:set value="imgDiv last" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list4) and 1 == fn:length(list4)}">
							<c:set value="active imgDiv last" var="className" />
						</c:if>
						<div class="${ className }" id="img_c4_${ status.count }">
							<a href='<c:url value="/images/${ img.categoryId }/${ img.id }.html#foto" />'>
								<img src="${ img.servingUrl }" width="220" height="330" alt='<spring:message code="image.description" />' />
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${ not empty list5 }">
				<div id="rm_container_5" class="rm_container">
					<c:forEach items="${ list5 }" var="img" varStatus="status">
						<c:set value="invisible imgDiv" var="className" />
						<c:if test="${ status.count == 1 }">
							<c:set value="active imgDiv" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list5) }">
							<c:set value="imgDiv last" var="className" />
						</c:if>
						<c:if test="${ status.count == fn:length(list5) and 1 == fn:length(list5)}">
							<c:set value="active imgDiv last" var="className" />
						</c:if>
						<div class="${ className }" id="img_c5_${ status.count }">
							<a href='<c:url value="/images/${ img.categoryId }/${ img.id }.html#foto" />'>
								<img src="${ img.servingUrl }" width="220" height="330" alt='<spring:message code="image.description" />' />
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function() {
				setInterval("makeAnimation()", 5000);
			});
			
			function makeAnimation() {
				$(".rm_container").each(function() {
					var jId = $(this).attr("id");
					var id = jId.substring(jId.lastIndexOf("_") + 1);
					var jCurrentDiv = $(this).find(".active");
					if (jCurrentDiv != null && jCurrentDiv.length > 0) {
						var jImgDivId = jCurrentDiv.attr("id");
						var imgIndex = jImgDivId.substring(jImgDivId.lastIndexOf("_") + 1);
						var nextImgDivId = "img_c" + id + "_" + (parseInt(imgIndex) + 1);
						if (jCurrentDiv.hasClass("last")) {
							nextImgDivId = "img_c" + id + "_" + 1;
						}
						
						//only one element in this container
						if (jImgDivId != nextImgDivId) {
							//hide current one
							jCurrentDiv.animate({
								left: "-225px",
								opacity: 0.25
							}, 2000, function() {
								jCurrentDiv.removeClass("active");
								jCurrentDiv.addClass("invisible");
								jCurrentDiv.css("left", "225px");
							});
							
							$("#" + nextImgDivId).addClass("active");
							$("#" + nextImgDivId).removeClass("invisible");
							$("#" + nextImgDivId).animate({
								left: 0,
								opacity: 1
							}, 2000);
						}
					}
				});
			}
		</script>
	</body>
</html>