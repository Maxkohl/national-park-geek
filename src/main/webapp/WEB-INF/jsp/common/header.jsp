<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>National Park Geek</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<c:url value="/css/final.css" var="cssHref" />
	<link rel="stylesheet" href="${cssHref}">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet"/>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap" rel="stylesheet">
	<link rel="icon" href="<c:url value="/img/icon.png"/>">
</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url var="logoImg" value="/img/logo.png" />

		<a href="${homePageHref}">
		<img src="${logoImg}" alt="National Park Geek logo" />
		<h1>NATIONAL PARK GEEK</h1>
		</a>
	</header>
	<nav>
		<ul>
			<li><a href="<c:url value="/survey"/>" name="surveyLink">Survey</a></li>
			<li><a href="<c:url value="/ranking"/>">Park Ranking</a></li>
		</ul>
	</nav>

