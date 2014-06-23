<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Eliminar Peliculas</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	   $(".delete").click(function(){
		txt = $(this).attr("name");
		$.post("deletemovie",{movie:txt},function(result){
	});
	  });
	});
</script>
</head>
	<body>
	<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
</ul>

		<h3>Eliminar Peliculas </h3>
		<table>
		<c:forEach var="movie" items="${movies}" varStatus="status">
		<tr><td>${movie.name}</td>
		<td>
		<span id = "${movie.name}">
		<input type = "submit" value = "eliminar" class = "delete" name = "${movie.id}" id="${movie.id}">
		</span>
		</td>
		</tr>
		</c:forEach>
		</table>
	</body>
</html>