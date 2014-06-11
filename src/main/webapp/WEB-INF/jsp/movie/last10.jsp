<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Top 20</title>
</head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
	


	<h3>Top 20</h3>

	<table border="1" bgcolor="green" align="center" >
		<tr>
			<th>Name</th>
			<th>Year</th>
			<th>Running_time</th>
			<th>Country</th>
			<th>Budget</th>
			<th>Box Office</th>
			<th>avg</th>
		</tr>

		<c:forEach var="movie" items="${last10}" varStatus="status">

			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.running_time}</td>
				<td>${movie.country}</td>
				<td>${movie.budget}</td>
				<td>${movie.box_office}</td>
				<td>${movie.avg}</td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>
