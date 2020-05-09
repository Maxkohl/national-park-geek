<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<script src="<c:url value="/webcontent/slideshow.js" />" /></script>

<div id="surveyContainer">
	<section id="survey-details">
		<h1>What'd you think?</h1>

		<c:url var="submitSurveyUrl" value="/survey" />
		<form:form action="${surveyUrl}" method="POST" modelAttribute="survey">
			<div class="errors">
				<form:errors path="*" cssClass="error" />
			</div>

			<h3>Your Favorite National Park:</h3>
			<form:select name="selectPark" id="selectPark" path="favoriteParkCode">
				<c:forEach var="park" items="${parks}">
					<form:option value="${park.code}" label="${park.name}" />
				</c:forEach>
			</form:select>

			<h3>Your Email Address:</h3>
			<form:input path="email" name="email"/>

			<h3>Your Home State:</h3>
			<form:select path="state" name="state">
				<c:forEach var="state" items="${states}">
					<form:option value="${state}" label="${state}" />
				</c:forEach>
			</form:select>

			<div class="buttons" id="activityButtons">

				<h3>Your Activity Level:</h3>
				<form:radiobutton path="activityLevel" value="inactive"
					label="Inactive" id="inactive"/>
				<form:radiobutton path="activityLevel" value="sedentary"
					label="Sedentary" id="sedentary"/>
				<form:radiobutton path="activityLevel" value="active" label="Active" id="active"/>
				<form:radiobutton path="activityLevel" value="extremely active"
					label="Extremely Active" id="extemely active"/>
			</div>

			<input type="submit" value="Submit" id="submit" name="formSubmitButton"/>
		</form:form>
	</section>

	<div class="slideshow-container">

		<c:forEach items="${parks}" var="park">
			<div class="mySlides fade">

				<c:url var="parkImg"
					value="/img/parks/${park.code.toLowerCase()}.jpg" />
				<img src="${parkImg}" alt="Image of ${park.name}">
				<div class="text">${park.name}</div>
			</div>
		</c:forEach>

		<script src="<c:url value="/webcontent/slideshow.js" />" /></script>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />