<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Registro</title></head>
<body>
    <p><h2>Registro 
    <c:if test="${user.isAdmin}">
      de Administradores
    </c:if>
    </h2></p>
 	<form method = "POST" action="register">
			<p><label for = "keywords">Nombre</label>
			<input type = "text" id = "keywords" name = "name"/></p>

			<p><label for = "keywords">User</label>
			<input type = "text" id = "keywords" name = "username"/></p>

			<p><label for = "keywords">Password</label>
			<input type = "password" id = "keywords" name = "password"/></p>

			<p><label for = "keywords">E-Mail</label>
			<input type = "text" id = "keywords" name = "email"/></p>

			<p><label for = "keywords">Pais</label>
			<input type = "text" id = "keywords" name = "country"/></p>

			<p><label for = "keywords">Fecha de nacimiento</label>
			<input type = "text" id = "keywords" name = "date"/></p>
			<c:if test="${user.isAdmin}" >
				<input type = "hidden" id = "keywords" name = "isAdmin" value = "${user.isAdmin}"/>
			</c:if>
			<input type = "submit" value = "register" >
	</form>
				
	
</body>
</html>