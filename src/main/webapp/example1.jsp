<html>
<head><title>Registro</title></head>
<body>
  
      <p><h2>Registro</h2></p>
 
 <!-- <a href="<%= request.getRequestURI() %>"><h3>Vuelve a intentarlo!</h3></a> -->
	
	
	<form method = "POST" action="search">
			<p><label for = "keywords">Nombre</label>
			<input type = "text" id = "keywords" name = "nombre"/></p>

			<p><label for = "keywords">User</label>
			<input type = "text" id = "keywords" name = "user"/></p>

			<p><label for = "keywords">Password</label>
			<input type = "password" id = "keywords" name = "password"/></p>

			<p><label for = "keywords">e-mail</label>
			<input type = "text" id = "keywords" name = "email"/></p>

			<p><label for = "keywords">Pais</label>
			<input type = "text" id = "keywords" name = "pais"/></p>

			<p><label for = "keywords">Fecha de nacimiento</label>
			<input type = "text" id = "keywords" name = "fecha_de_nacimiento"/></p>
	</form>
				<input type = "submit" value = "Search">
	
</body>
</html>