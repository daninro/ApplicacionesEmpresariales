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
	  
	  
	  $(".delete3").click(function(){
		us = $(this).attr("name");
		$.post("deleteuser",{user:us},function(result){
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
			
 <h1>Añadir película</h1>
    
 	<form method = "POST" action="addmovie">
	<h2>Datos Generales</h2>
 		<table>
 			<tr>
				<td><label for = "keywords">Nombre</label></td>
				<td><input type = "text" id = "keywords" name = "name"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Año</label></td>
				<td><input type = "text" id = "keywords" name = "year"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Pais</label></td>
				<td><input type = "text" id = "keywords" name = "country"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Imagen</label></td>
				<td><input type = "text" id = "keywords" name = "image"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Director</label></td>
				<td><input type = "text" id = "keywords" name = "director_name"/></td>
			</tr>
			
			<tr>
				<td><label for = "keywords">Director_id</label></td>
				<td><input type = "text" id = "keywords" name = "director_id"/></td>
			</tr>
			
			
		</table>
		<h2>Géneros</h2>
		<table>
			<tr>
				<td><input type="checkbox" name="genre" value="IMAX">IMAX</td>
				<td><input type="checkbox" name="genre" value="Comedy"> Comedy</td>
				<td><input type="checkbox" name="genre" value="Fantasy"> Fantasy</td>
				<td><input type="checkbox" name="genre" value="Drama"> Drama</td>
				<td><input type="checkbox" name="genre" value="Horror"> Horror</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="genre" value="Thriller"> Thriller</td>
				<td><input type="checkbox" name="genre" value="Film-Noir"> Film-Noir</td>
				<td><input type="checkbox" name="genre" value="Crime"> Crime</td>
				<td><input type="checkbox" name="genre" value="Western"> Western</td>
				<td><input type="checkbox" name="genre" value="War"> War</td>
				
			</tr>
			<tr>
				<td><input type="checkbox" name="genre" value="Musical"> Musical</td> 
				<td><input type="checkbox" name="genre" value="Short"> Short</td>
				<td><input type="checkbox" name="genre" value="Children"> Children
				<td><input type="checkbox" name="genre" value="Adventure"> Adventure</td>
				<td><input type="checkbox" name="genre" value="Sci-Fi"> Sci-Fi</td>
			</tr>
			<tr>
				
				<td><input type="checkbox" name="genre" value="Action"> Action</td>
				<td><input type="checkbox" name="genre" value="Documentary"> Documentary</td>
				<td><input type="checkbox" name="genre" value="Romance"> Romance</td>
				<td><input type="checkbox" name="genre" value="Animation"> Animation</td>
				<td><input type="checkbox" name="genre" value="Mystery"> Mystery</td>
				
			</tr>
			<tr>
			<td>
				<input type = "submit" value = "agregar" >
			</td>
			</tr>
		</table>
	</form>
			
			
					</div>
			<div>
			
			<div id = "footer">
				footer
			</div>
		</div>
	</body>
</html>