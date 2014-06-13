<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>List of movies</title>
</head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
	


	<h3>List of movies</h3>

	<table border="1" bgcolor="cyan" align="center" >
		<tr>
			<th>Name</th>
			<th>Year</th>
			<th>Country</th>
		</tr>

		<c:forEach var="movie" items="${movieList}" varStatus="status">

			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.country}</td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>
