<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head><title>Modificar Datos</title></head>
	<body>
	<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
 	<form method = "POST" action="edituser">
	<table align="center" >
		<tr>
			<th>Username</th>
			<td>${user.username}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td><input type = "text" value = "${user.name}" id="name" name="name"></td>
		</tr>
		<tr>
			<th>Contraseña</th>
			<td><input type = "text" value = "${user.password}" id="password" name="password"></td>
		</tr>
		<tr>
			<th>Fecha de Nacimiento</th>
			<td><input type = "text" value = "${user.date_of_birth}" id="date" name="date"></td>
		</tr>
		<tr>
			<th>País</th>
			<td><input type = "text" value = "${user.country}" id="country" name="country"></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><input type = "text" value = "${user.email}" id="email" name="email"></td>
		</tr>
	</table>
	<p>
  				<input type = "hidden" id = "keywords" name = "user" value = "${user.username}"/>
  				<input type = "hidden" id = "keywords" name = "isAdmin" value = "${user.isAdmin}"/>
				<input type = "submit" value = "Guardar cambios">
		</p>
		</form>
</body>
</html>
