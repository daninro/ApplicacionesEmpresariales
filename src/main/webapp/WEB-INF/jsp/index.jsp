<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	</head>
	<body>
			<ul>
			<c:out value="Hola usuario ${user.username}">
			</c:out>
			<c:if test="${user.isAdmin}" >
			<li><a href = "../movie/addmovie">Agregar Películas</a></li>
			<li><a href = "../movie/addmovie">Modificar peliculas</a></li>
			<li><a href = "../user/register">Agregar Administradores</a></li>
			<li><a href = "../movie/addmovie">Eliminar Usuarios</a></li>
			</c:if>
			<li><a href = "../movie/list">listar peliculas</a></li>
			<li><a href = "../movie/mark">calificar</a></li>
			<li><a href = "../movie/search">buscar</a></li>
			<li><a href = "../movie/top20">Top 20</a></li>
			<li><a href = "../movie/last10">Ultimas agregadas</a></li>
			<li><a href = "../user/logout">Logout</a></li>
			</ul>
	</body>
</html>