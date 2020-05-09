<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section class="park-grid-container">
	<div id="parkDetailsImage">
		<c:url var="parkImg" value="/img/parks/${park.code.toLowerCase()}.jpg" />
		<img src="${parkImg}" alt="Image of ${park.name}">
	</div>
	<div id="parkInfoTile">
		<h3 class="parkTileHeader">${park.name}</h3>
		<p class="quote"><c:out value='"${park.inspoQuote}" - ${park.inspoQuoteSrc}'/></p>
		<p><c:out value="${park.description}"/></p>
	</div>

	<c:set var="todaysWeather" value="" />

	<div class="forecast">
		<c:set var="i" value="0" />
		<p id="scroll-tip">Scroll for more Weather!</p>
		<div class="forecastList">
			<c:forEach items="${forecasts}" var="forecast">
				<c:if test="${i == 0}">
					<c:set var="todaysWeather" value="${forecast}" />
				</c:if>
				<c:if test="${i != 0}">
					<div class="dayForecast">
						<p class="date">
							<fmt:parseDate type="date" pattern="yyyy-MM-dd"
								value="${forecast.getForecastDate(i)}" var="date" />
							<fmt:formatDate value="${date}" />
						</p>
						<c:url var="forecastImg" value="/img/weather/${forecast.imgPath}" />
						<img src="${forecastImg}"
							alt="Image of ${forecast.weatherType} Weather">

						<c:set var="high" value="" />
						<c:set var="low" value="" />
						<c:set var="suffix" value="" />
						<c:choose>
							<c:when test="${isFarenheit == true}">
								<c:set var="high" value="${forecast.fHigh}" />
								<c:set var="low" value="${forecast.fLow}" />
								<c:set var="suffix" value="°F" />
							</c:when>
							<c:when test="${isFarenheit == false}">
								<c:set var="high" value="${forecast.cHigh}" />
								<c:set var="low" value="${forecast.cLow}" />
								<c:set var="suffix" value="°C" />

							</c:when>
						</c:choose>
						<p>
							<c:out value="High: ${high} ${suffix}" />
						</p>
						<p>
							<c:out value="Low: ${low} ${suffix}" />
						</p>
					</div>
				</c:if>

				<c:set var="i" value="${i + 1}" />
			</c:forEach>
		</div>
		<div id="converter">
			<form action="<c:url value="/details" />" method="POST">
				<c:set var="tempUnit" value="true" />

				<c:url var="farenURL" value="/img/weather/farenheit.png" />
				<img src="${farenURL}" alt="Farenheit img" />
				<c:url var="celURL" value="/img/weather/celsius.png" />
				<img src="${celURL}" alt="Celsius img" /> <br /> <input
					type="radio" value="${tempUnit}" name="tempUnit" checked="checked"><input type="radio"
					value="${!tempUnit}" name="tempUnit" />
					<br/>
					<input
					type="submit" value="Change Unit" />
			</form>
		</div>
	</div>

	<div id="todaysForecast">
		<h3 class="date">Today</h3>
		<c:url var="forecastImg" value="/img/weather/${todaysWeather.imgPath}" />
		<img src="${forecastImg}"
			alt="Image of ${todaysWeather.weatherType} Weather">

		<c:set var="high" value="" />
		<c:set var="low" value="" />
		<c:set var="suffix" value="" />
		<c:choose>
			<c:when test="${isFarenheit == true}">
				<c:set var="high" value="${todaysWeather.fHigh}" />
				<c:set var="low" value="${todaysWeather.fLow}" />
				<c:set var="suffix" value="°F" />
			</c:when>
			<c:when test="${isFarenheit == false}">
				<c:set var="high" value="${todaysWeather.cHigh}" />
				<c:set var="low" value="${todaysWeather.cLow}" />
				<c:set var="suffix" value="°C" />

			</c:when>
		</c:choose>
		<p>
			<fmt:parseDate type="date" pattern="yyyy-MM-dd"
				value="${todaysWeather.getForecastDate(0)}" var="date" />
			<fmt:formatDate value="${date}" />
		</p>
		<p>
			<c:out value="High: ${high} ${suffix}" />
		</p>
		<p>
			<c:out value="Low: ${low} ${suffix}" />
		</p>
		<div class="weatherAdvisory">
			<c:if test="${todaysWeather.weatherType == 'snow'}">
				<p>Pack snowshoes!</p>
			</c:if>
			<c:if test="${todaysWeather.weatherType == 'rain'}">
				<p>Pack rain gear and wear water-proof shoes!</p>
			</c:if>
			<c:if test="${todaysWeather.weatherType == 'sunny'}">
				<p>Bring sunblock!</p>
			</c:if>
			<c:if test="${todaysWeather.weatherType == 'thunderstorms'}">
				<p>Seek shelter and avoid hiking on exposed ridges!</p>
			</c:if>
			<c:if test="${todaysWeather.fHigh > 75}">
				<p>Bring an extra gallon of water!</p>
			</c:if>
			<c:if test="${todaysWeather.fHigh - forecast.fLow > 20}">
				<p>Wear breathable layers!</p>
			</c:if>
			<c:if test="${todaysWeather.fLow < 20}">
				<p>Warning: Will Be Exposed to Frigid Temperatures!</p>
			</c:if>
		</div>
	</div>

	<div id="moreInfo">
		<button class="collapsible">Additional Park Info</button>
		<div class="content">
			<p><c:out value="${park.name} has a ${park.climate.toLowerCase()} climate." /></p>
			<p><c:out value="${park.name} spans ${park.acres} acres of land." /></p>
			<p><c:out value="Every year about ${park.annualVisitorCount} visitors come from
				all over the world to see ${park.name}!" /></p>
			<p><c:out value="Our park was founded in ${park.yearFounded} and continues to
				astound visitors to this day!" /></p>
			<p><c:out value="Entry Fee: $${park.entryFee}" /></p>
		</div>
		<button class="collapsible">Campsite Information</button>
		<div class="content">
			<p><c:out value="${park.name} sits at an elevation of ${park.elevation} feet above
				sea level." /></p>
			<p><c:out value="We have over ${park.campsiteCount} campsites that are available during
				our open seasons!" /></p>
		</div>
		<button class="collapsible">Wildlife and Hiking</button>
		<div class="content">
			<p><c:out value="${park.name} hosts over ${park.animalSpeciesCount} different
				species of wildlife." /></p>
			<p><c:out value="Are you an avid hiked? ${park.name} has over
				${park.milesOfTrail } miles of trail for you to traverse and explore!" /></p>
		</div>
		<script src="<c:url value="/webcontent/collapseMenu.js" />" /></script>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />