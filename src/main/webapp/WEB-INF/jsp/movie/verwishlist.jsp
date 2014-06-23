<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Wishlist</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	   $(".delete").click(function(){
		mov = $(this).attr("name");
		$.post("verwishlist",{wl:mov},function(result){
	});
	  });
	});
</script>
</head>
	<body>
	<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
</ul>

		<h3>Wishlist </h3>
		<table>
		<c:forEach var="movie" items="${movies}" varStatus="status">
		<tr><td><a href = "/ApplicacionesEmpresariales/user/index">${movie.name}</a></td> <!--  Falta llevar a info de pelicula -->
		<td>
		<span id = "${movie.id}">
		<input type = "submit" value = "eliminar" class = "delete" name = "${movie.id}">
		</span>
		</td>
		</tr>
		</c:forEach>
		</table>
	</body>
</html>