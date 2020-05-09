<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="grid-container">
	<main class="main">
	<div class="items">
		<c:set var="detail" value="" />

		<c:forEach items="${parks}" var="park">
			<div class="polaroid">
				<c:url value="/" var="homepageDetails">
					<c:param name="parkCode" value="${park.code}" />
				</c:url>

				<c:url var="parkImg"
					value="/img/parks/${park.code.toLowerCase()}.jpg" />
				<a href="${homepageDetails}" id="${park.name}"> <img src="${parkImg}"
					alt="Image of ${park.name}">
					<p class="more-info-link">${park.name}</p>
				</a>
			</div>
			<c:if test="${param.parkCode == park.code}">
				<c:set var="detail" value="${park}" />
			</c:if>
		</c:forEach>

	</div>

	</main>
	<div class="lowerContent">
		<div id="description">
			<c:choose>
				<c:when test="${detail == '' }">
					<h3 class="parkTileHeader">Click a photo for park details!</h3>
					<p class="quote">"Thousands of tired, nerve-shaken,
						over-civilized people are beginning to find out that going to the
						mountains is going home; that wildness is a necessity; and that
						mountain parks and reservations are useful not only as fountains
						of timber and irrigating rivers, but as fountains of life.
						Awakening from the stupefying effects of the vice of over-industry
						and the deadly apathy of luxury, they are trying as best they can
						to mix and enrich their own little ongoings with those of Nature,
						and to get rid of rust and disease." -John Muir</p>
				</c:when>
				<c:otherwise>
					<c:url value="details" var="parkDetailsUrl">
						<c:param name="parkCode" value="${detail.code}" />
					</c:url>
					<h3 class="parkTileHeader">${detail.name}</h3>
					<p>${detail.description}</p>
					<a href="${parkDetailsUrl}">
						<p class="more-info-link">View Additional Park Details</p>
					</a>
				</c:otherwise>

			</c:choose>
			<h1>${parkDetail.name}</h1>

		</div>
		<div id="advertisements">
			<div class="slideshow-container">
				<c:url var="storeLink"
					value="https://www.parksproject.us/collections/all" />
				<c:url var="supportLink"
					value="https://www.nationalparks.org/support" />
				<c:url var="volunteerLink"
					value="https://www.nps.gov/getinvolved/volunteer.htm" />

				<div class="mySlides fade">
					<c:url var="adImg" value="/img/adImgs/donate1.png" />
					<a class="" target="_blank" href="${supportLink}"> <img
						src="${adImg}" style="width: 80%">
				</div>
				<div class="mySlides fade">
					<c:url var="adImg" value="/img/adImgs/hats.jpg" />
					<a class="" target="_blank" href="${storeLink}" /> <img
						src="${adImg}" style="width: 70%"> </a>
				</div>
				<div class="mySlides fade">
					<c:url var="adImg" value="/img/adImgs/volunteer.jpg" />
					<a class="" target="_blank" href="${volunteerLink}"> <img
						src="${adImg}" style="width: 90%">
				</div>
				<div class="mySlides fade">
					<c:url var="adImg" value="/img/adImgs/hoodie.png" />
					<a class="" target="_blank" href="${storeLink}"> <img
						src="${adImg}" style="width: 80%">
				</div>
				<div class="mySlides fade">
					<c:url var="adImg" value="/img/adImgs/support.png" />
					<a class="" target="_blank" href="${supportLink}"> <img
						src="${adImg}" style="width: 80%">
				</div>

				<script src="<c:url value="/webcontent/slideshow.js" />" /></script>
			</div>

		</div>

	</div>
</div>

<script data-cfasync="false"
	src="/m3-java-capstone/webcontent/jquery/dist/jquery.js"></script>
<script data-cfasync="false" src="/m3-java-capstone/webcontent/app.js"></script>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />