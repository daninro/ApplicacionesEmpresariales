<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>List of movies</title>
</head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
</ul>
 	<form method = "POST" action="editmovie">

	<h3>${movieDetails.name}</h3>

	<table border="1" bgcolor="cyan" align="center" >
		<tr>
			<th>Name</th>
			<td><input type = "text" value = "${movieDetails.name}" id="name" name="name"></td>
		</tr>
		<tr>
			<th>Year</th>
			<td><input type = "text" value = "${movieDetails.year}" id="year" name="year"></td>
		</tr>
		<tr>
			<th>Country</th>
			<td><input type = "text" value = "${movieDetails.country}" id="country" name="country"></td>

		</tr>
		<tr>
			<th>Image</th>
			<td><input type = "text" value = "${movieDetails.image}" id="imagen" name="imagen"></td>
		</tr>

	</table>
	
		<p>
  				<input type = "hidden" value = "${movieDetails.id}" id="movieid" name="movieid">
				<input type = "submit" value = "Guardar cambios" id="wish">
		</p>
		</form>
</body>
</html>
