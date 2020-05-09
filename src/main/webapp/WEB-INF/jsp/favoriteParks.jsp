<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="parkTiles">

	<c:forEach items="${favorites}" var="entry">

		<c:url value="details" var="parkDetailsUrl">
			<c:param name="parkCode" value="${entry.key.code}" />
		</c:url>

		<div class="parkTile">
			<c:url var="parkImg" value="/img/parks/${entry.key.code.toLowerCase()}.jpg" />
			<a class="park-image" href="${parkDetailsUrl}">
				<img src="${parkImg}" alt="Image of ${entry.key.name}">
			</a>
			
			<c:set var="suffix" value=""/>
			
			<c:choose>
				<c:when test="${entry.value > 1}">
					<c:set var="suffix" value="people!"/>
				</c:when>
				<c:otherwise>
					<c:set var="suffix" value="person!"/>
				</c:otherwise>
			</c:choose>
			<div>
				<h2><c:out value="${entry.key.name} - Loved by ${entry.value} ${suffix}"/></h2>
				<div><c:out value="${entry.key.description}"/>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />