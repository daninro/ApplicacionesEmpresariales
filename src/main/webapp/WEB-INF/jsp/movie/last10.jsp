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
			<th>Country</th>
			<th>avg</th>
		</tr>

		<c:forEach var="movie" items="${last10}" varStatus="status">

			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.country}</td>
				<td>${movie.avg}</td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>
