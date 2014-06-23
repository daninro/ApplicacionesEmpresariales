<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	</head>
	<body>
			<ul>
			<c:out value="Hola @${user.username}">
			</c:out>
			<li><a href = "../movie/list">listar peliculas OK</a></li>
			<li><a href = "../movie/search">buscar OK</a></li>
			<li><a href = "../movie/top20">Top n OK</a></li>
			<li><a href = "../movie/last10">Ultimas agregadas OK</a></li>
			<li><a href = "../user/edituser">Modificar datos OK</a></li>
			<li><a href = "../movie/verwishlist">Ver wishlist</a></li>
			<li><a href = "../movie/last10">ver recomendaciones</a></li>
			
			<c:if test="${user.isAdmin}" >
			<li><a href = "../movie/addmovie">Agregar Películas</a></li>
			<li><a href = "../movie/editmovie">Modificar peliculas OK</a></li>
			<li><a href = "../movie/addmovie">Eliminar peliculas</a></li>
			<li><a href = "../user/deleteuser">Eliminar Usuarios OK</a></li>
			<li><a href = "../user/register">Agregar Administradores OK</a></li>
			<li><a href = "../movie/editmovie">Eliminar mi cuenta</a></li>
			</c:if>
			<li><a href = "../user/logout">Logout</a></li>
			</ul>
	</body>
</html>