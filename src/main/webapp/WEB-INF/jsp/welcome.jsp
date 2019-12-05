<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Happy Hours</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
    <header>
	</header>
	<main >
	    <h1>Hi ${user.name}! Waddap?</h1>
	    <h2>Statistics</h2>
	    <p>Time goal: ${statistics.timeGoal}</p>
	    <p>Time elapsed today: ${statistics.totalToday}</p>
	    <p>Today to goal difference: ${statistics.todayToGoalDifference}</p>
	    <p>Month to goal difference: ${statistics.monthToGoalDifference}</p>
	    <p>Total in month: ${statistics.totalMonth}</p>
	    <p>Total in year: ${statistics.totalYear}</p>
	    <p>Average in month: ${statistics.averageMonth}</p>
	    <p>Average in year: ${statistics.averageYear}</p>
	    <p>${statistics.percentile}${statistics.percentileSuffix} in month: ${statistics.percentileMonth}</p>
	    <p>${statistics.percentile}${statistics.percentileSuffix} in year: ${statistics.percentileYear}</p>

	</main>
</body>

</html>