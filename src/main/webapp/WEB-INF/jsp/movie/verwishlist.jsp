<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Wishlist</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	   $(".delete").click(function(){
		mov = $(this).attr("name");
		
		$.post("ajaxdeletefromwishlist",{wl:mov},function(result){
			$("#" + mov).remove();
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

		<c:forEach var="movie" items="${movies}" varStatus="status">
		
			<div id = "${movie.id}">
			<a href = "/ApplicacionesEmpresariales/user/index">${movie.name}</a> 
			<input type = "submit" value = "eliminar" class = "delete" name = "${movie.id}"/>
			</div>	
		
		</c:forEach>
	</body>
</html>