<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<table border="1" bgcolor="cyan" align="center" >
		<tr>
			<th>Name</th>
			<th>Year</th>
			<th>Running_time</th>
			<th>Country</th>
			<th>Budget</th>
			<th>Box Office</th>
		</tr>

		<c:forEach var="movie" items="${movieList}" varStatus="status">

			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.running_time}</td>
				<td>${movie.country}</td>
				<td>${movie.budget}</td>
				<td>${movie.box_office}</td>
			</tr>

		</c:forEach>

	</table>