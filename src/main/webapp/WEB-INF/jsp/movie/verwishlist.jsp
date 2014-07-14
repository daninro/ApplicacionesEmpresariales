<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
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
		});
	</script>
	
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" href="style" type="text/css" />
		


		
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
			</div>		<div id = "menu">
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
				
				
				
			<div id = "content">	
			

			<h1>Lista de Deseos</h1>
			
			<c:forEach var="movie" items="${movies}" varStatus="status">
				<div class = "ficha" id = "${movie.id}">
					<img src="${movie.image}"/>
					<div class = "top">
						<a href = "/ApplicacionesEmpresariales/movie/movie?movie=${movie.id}" ><h3> ${movie.name}</h3></a>
					</div>
					
					<div class = "bottom">
						<input class = "cal" type = "radio" name = "${movie.id}" value = "1"
							<c:if test="${movie.avg == 1}">checked</c:if>
						/>1
						<input class = "cal" type = "radio" name = "${movie.id}" value = "2"
						<c:if test="${movie.avg == 2}">checked</c:if>
						/>2
						<input class = "cal" type = "radio" name = "${movie.id}" value = "3"
						<c:if test="${movie.avg == 3}">checked</c:if>
						/>3
						<input class = "cal" type = "radio" name = "${movie.id}" value = "4"
						<c:if test="${movie.avg == 4}">checked</c:if>
						/>4
						<input class = "cal" type = "radio" name = "${movie.id}" value = "5"
						<c:if test="${movie.avg == 5}">checked</c:if>
						/>5
						<br/>	
							
						<p>Year: <a href = "/ApplicacionesEmpresariales/movie/filter?year=${movie.year}">${movie.year}</a></p>
						<p>Country: <a href = "/ApplicacionesEmpresariales/movie/filter?country=${movie.country}">${movie.country}</a></p>
						<span id = "${movie.id}">
							
								<input type = "submit" value = "Quitar de la wishlist" class = "nowish delete" name = "${movie.id}" >
							
						</span>
					</div>
				</div>
				
				</c:forEach>
					
			</div>
			<div>
			
			<div id = "footer">
				footer
			</div>
		</div>
	</body>
</html>

<!--
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
			<a href = "/ApplicacionesEmpresariales/movie/movie?movie=${movie.id}">${movie.name}</a> 
			<input type = "submit" value = "eliminar" class = "delete" name = "${movie.id}"/>
			</div>	
		
		</c:forEach>
	</body>
</html>-->