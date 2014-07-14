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
		});
		 $("#buscar").click(function(){
				id = $(this).attr("name");
				
				val = $("input[name=search]:checked").val();
				//alert(val);
				$("#keywords").attr("name", val);
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
			</div>			
			<div id = "menu">
			<ul>
				<a href ="/ApplicacionesEmpresariales/movie/list"><li>Películas</li></a>
				<a href ="/ApplicacionesEmpresariales/movie/last10"><li>Ultimas Agregadas</li></a>
				<a href ="/ApplicacionesEmpresariales/movie/top20"><li>Ver Recomendaciones</li></a>
				<a href ="/ApplicacionesEmpresariales/user/edituser"><li>Perfil de Usuario</li></a>
				<c:if test="${user.isAdmin}" >
				<a href = "../user/admin"><li>Administrar</li></a>
				</c:if>
			</ul>
			
			</div>
			<div id = "center">
				
				
				
		<div class = "content">	
			
				
						<h1>${user.username}</h1>
					</tr>
			<form method = "POST" action="edituser">
				<table align="center" >
					
					<tr>
						<th>Nombre</th>
						<td><input type = "text" value = "${user.name}" id="name" name="name"></td>
					</tr>
					<tr>
						<th>Contraseña</th>
						<td><input type = "text" value = "${user.password}" id="password" name="password"></td>
					</tr>
					<tr>
						<th>Fecha de Nacimiento</th>
						<td><input type = "text" value = "${user.date_of_birth}" id="date" name="date"></td>
					</tr>
					<tr>
						<th>País</th>
						<td><input type = "text" value = "${user.country}" id="country" name="country"></td>
					</tr>
					<tr>
						<th>Email</th>
						<td><input type = "text" value = "${user.email}" id="email" name="email"></td>
					</tr>
				</table>
				<p>
						<input type = "hidden" id = "keywords" name = "user" value = "${user.username}"/>
						<input type = "hidden" id = "keywords" name = "isAdmin" value = "${user.isAdmin}"/>
						<input type = "submit" value = "Guardar cambios">
				</p>
				</form>
				
				
				</div>
			<div>
				<div id = "footer">
					footer
				</div>
			</div>
	</body>
</html>












