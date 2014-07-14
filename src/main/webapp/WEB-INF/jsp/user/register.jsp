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
				<c:choose>
				
				<c:when test="${user.isAdmin}" >
					<h1> Hola</h1>
					
					<h2><c:out value="@${user.username}">
					</c:out></h2>
					<h3><a href = "/ApplicacionesEmpresariales/movie/verwishlist"> WishList </a> | <a href ="/ApplicacionesEmpresariales/user/edituser"> Perfil </a> | <a href ="/ApplicacionesEmpresariales/user/logout"> Cerrar Sesión </a></h3>
				</c:when>
				<c:when test="${!user.isAdmin}" >
				 <h2>Iniciar Sesión</h2>
	
		<form method = "POST" action="login">
			<p>
			<input type = "text" id = "keywords" name = "username"/></p>
		
			<p>
			<input type = "password" id = "keywords" name = "password"/></p>
			
			<input type = "submit" value = "Iniciar Sesión" >
		</form>
				
				</c:when>
				</c:choose>
				</div>
			</div>			<div id = "menu">
			<ul>
			<c:if test="${user.isAdmin}" >
				<a href ="/ApplicacionesEmpresariales/movie/list"><li>Home</li></a>

				
				<a href = "../movie/addmovie"><li>Agregar Películas</li></a>
				<a href = "../user/deleteuser"><li>Eliminar Usuarios</li></a>
				<a href = "../user/register"><li>Agregar Administradores</li></a>
				<a href = "../movie/executealgorithm"><li>ejecutar algoritmo</li></a>
				
				
			</ul>
			</c:if>
			</div>
			<div id = "center">
				
				
				
				<div >	
			    <p><h1>Registro 
    <c:if test="${user.isAdmin}">
      de Administradores
    </c:if>
    </h1></p>
 	<form method = "POST" action="register">
	<table>
			<tr><td><label for = "keywords">Nombre</label></td>
			<td><input type = "text" id = "keywords" name = "name"/></td></tr>

			<tr><td><label for = "keywords">User</label></td>
			<td><input type = "text" id = "keywords" name = "username"/></td></tr>

			<tr><td><label for = "keywords">Password</label></td>
			<td><input type = "password" id = "keywords" name = "password"/></td></tr>

			<tr><td><label for = "keywords">E-Mail</label></td>
			<td><input type = "text" id = "keywords" name = "email"/></td></tr>

			<tr><td><label for = "keywords">Pais</label></td>
			<td><input type = "text" id = "keywords" name = "country"/></td></tr>

			<tr><td><label for = "keywords">Fecha de nacimiento</label></td>
			<td><input type = "text" id = "keywords" name = "date"/></td></tr>
			<c:if test="${user.isAdmin}" >
				<input type = "hidden" id = "keywords" name = "isAdmin" value = "${user.isAdmin}"/>
			</c:if>
			<tr><td><input type = "submit" value = "Registrarse" ><td></tr>
			</table>
	</form>

			<h1><c:out value="${message}"/></h1>
			
					</div>
			<div>
			
			<div id = "footer">
				footer
			</div>
		</div>
	</body>
</html>