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
			<th></th>
			<th>Id</th>
			<th>Name</th>
			<th>Year</th>
			<th>Running_time</th>
			<th>Country</th>
			<th>Budget</th>
			<th>Box Office</th>
		</tr>
		<form action = "mark" method = "POST">
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			
			<tr>
				<td><input type="radio" name="id" value="${movie.id}"/></td>
				<td>${movie.id}</td>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.running_time}</td>
				<td>${movie.country}</td>
				<td>${movie.budget}</td>
				<td>${movie.box_office}</td>
				<!-- <input type="hidden" name="id" value = "${movie.id}"/>-->
				<td><input type="radio" name="${movie.id}_mark" value="1" checked/>1
				<input type="radio" name="${movie.id}_mark" value="2"/>2
				<input type="radio" name="${movie.id}_mark" value="3"/>3
				<input type="radio" name="${movie.id}_mark" value="4"/>4
				<input type="radio" name="${movie.id}_mark" value="5"/>5</td>
				
			</tr>
			
			
		</c:forEach>
		<input type = "submit" name = "s" value = "calificar"/>
		</form>	
	</table>

</body>
</html>
