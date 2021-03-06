﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<style>
		
		
		
		</style>
	
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" href="style" type="text/css" />
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
				<h1> ${movie.name} <span><c:if test="${user.isAdmin}">
				<a href = "/ApplicacionesEmpresariales/movie/editmovie?id=${movie.id}">edit</a>
			</c:if></span></h1>
				
				<div class = "markandwish">
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
				/>5 <br/>
				</div>
		
		<img id = "image" alt="" src="${movie.image}">
		
		<div id = "general">
		<p><span>Year:</span> <a href = "/ApplicacionesEmpresariales/movie/filter?year=${movie.year}">${movie.year}</a></p>
		<p><span>Country:</span> <a href = "/ApplicacionesEmpresariales/movie/filter?country=${movie.country}">${movie.country}</a></p>
		<p><span>Director:</span> <a href = "/ApplicacionesEmpresariales/movie/filter?director=${movie.id_director}">${movie.director}</a></p>
		</div>
		<div class = "clear"></div>
				<div class = "markandwish">
				<span id = "${movie.id}">
					<c:if test="${movie.isWishlist}">
						<input type = "submit" value = "Quitar de la wishlist" class = "nowish" name = "${movie.id}">
					</c:if>
					<c:if test="${!movie.isWishlist}">
						<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
					</c:if>
				</span>
				</div>
			<div id = "actors">
			<h3>Reparto</h3>
			<c:forEach var="act" items="${actor}" varStatus="status">
				<p><a href = "/ApplicacionesEmpresariales/movie/filter?actor=${act.id_name}">${act.name}</a></p>
			</c:forEach>
			</div>
		<div class = "clear"></div>
			<div id = "genres">
			<h3>Generos</h3>
			<c:forEach var="gen" items="${genre}" varStatus="status">
				<p><a href = "/ApplicacionesEmpresariales/movie/filter?genre=${gen}">${gen}</a></p>
			</c:forEach>
			</div>
			
			
			</div>
			</div>
			<div>
			
			<div id = "footer">
				footer
			</div>
		</div>
	</body>
</html>

	
	
	
	
	