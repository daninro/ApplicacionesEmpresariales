
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<style>
		
		
		
		</style>
	
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" href="../movie/style" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
		  $(".cal").click(function(){
		    cal = $(this).val();
		    id = $(this).attr("name");
		    $.post("mark",{movieId:id, mark:cal},function(result){});
		  });

			
		  $(".wish").click(function(){
			id = $(this).attr("name");
			$.post("../movie/ajaxaddwishlist",{name:id},function(result){
			$("span#"+id).html(result);
			});
		  });
		  $(".nowish").click(function(){
				id = $(this).attr("name");
				
				$.post("ajaxdeletefromwishlist",{wl:id},function(result){
				$("span#"+id).html(result);
				});
			});
		  $("#buscar").click(function(){
				id = $(this).attr("name");
				
				val = $("input[name=search]:checked").val();
				//alert(val);
				$("#keywords").attr("name", val);
			});
			 $(".delete").click(function(){
			mov = $(this).attr("name");
			
			$.post("ajaxdeletefromwishlist",{wl:mov},function(result){
				$("#" + mov).remove();
			});
			});
			
				   $(".delete2").click(function(){
		txt = $(this).attr("name");
		$.post("deletemovie",{movie:txt},function(result){
			$("#data").remove();
			
	});
	  });
		});
	</script>



		
	</head>
	<body>
		<div id = "all">
			<div id = "header">
				<div id = "logo"></div>
				<div id = "header-content">
				<form method = "GET" action="filter" name = "filter">
					<input type="radio" name="search" value="name" 
						<c:if test="${search == 'name'}">checked</c:if>
					/>Película
					<!--<input type="radio" name="search" value="actor"/>Actor
					<input type="radio" name="search" value="director"/>Director-->
					<input type="radio" name="search" value="country"
					<c:if test="${search == 'country'}">checked</c:if>
					/>País
					<input type="radio" name="search" value="year"
					<c:if test="${search == 'year'}">checked</c:if>
					/>Año
					<input type="radio" name="search" value="genre"
					<c:if test="${search == 'genre'}">checked</c:if>
					/>Género
					</br>
					<div>
					<input type = "text" id = "keywords" name = "name" value = "${name}"/>
					<input type = "submit" id = "buscar" value = "buscar"/>
					</div>
				</form>
				
				
				</div>
				<div id = "userblock">
					<h1> Hola</h1>
					<h2><c:out value="@${user.username}">
					</c:out></h2>
					<h3><a href = "/ApplicacionesEmpresariales/movie/verwishlist"> WishList </a> | <a href ="/ApplicacionesEmpresariales/user/edituser"> Perfil </a> | <a href ="/ApplicacionesEmpresariales/user/logout"> Cerrar Sesión </a></h3>
				
				</div>
			</div>			<div id = "menu">
			<ul>
				<a href ="/ApplicacionesEmpresariales/movie/list"><li>Home</li></a>

				<c:if test="${user.isAdmin}" >
				<a href = "../movie/addmovie"><li>Agregar Películas</li></a>
				<a href = "../user/deleteuser"><li>Eliminar Usuarios</li></a>
				<a href = "../user/register"><li>Agregar Administradores</li></a>
				<a href = "../movie/executealgorithm"><li>ejecutar algoritmo</li></a>
				</c:if>
			</ul>
			
			</div>
			<div id = "center">
				
				
				
				<div >	
			<h1>${movieDetails.name}</h1>
 	<form method = "POST" action="editmovie">

	
	<span id = "data">
	<input type = "button" value = "eliminar" class = "delete2" name = "${movieDetails.id}" id="${movieDetails.id}">
	<table border="1" align="center" >
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
	</span>
			
			
					</div>
			<div>
			
			<div id = "footer">
				footer
			</div>
		</div>
	</body>
</html>





