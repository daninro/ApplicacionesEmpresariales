
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
		});
	</script>
	
	</head>
	<body>
	<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
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
				
		<img alt="" src="${movie.image}">
		<h3> ${movie.name}</h3>
		<p>Year: <a href = "/ApplicacionesEmpresariales/movie/filter?year=${movie.year}">${movie.year}</a></p>
		<p>Country: <a href = "/ApplicacionesEmpresariales/movie/filter?country=${movie.country}">${movie.country}</a></p>
		<p>Director: <a href = "/ApplicacionesEmpresariales/movie/filter?director=${movie.id_director}">${movie.director}</a></p>
		
				<span id = "${movie.id}">
					<c:if test="${movie.isWishlist}">
						<input type = "submit" value = "Quitar de la wishlist" class = "nowish" name = "${movie.id}">
					</c:if>
					<c:if test="${!movie.isWishlist}">
						<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
					</c:if>
				</span>
		
			<h3>Actores</h3>
			<c:forEach var="act" items="${actor}" varStatus="status">
				<p><a href = "/ApplicacionesEmpresariales/movie/filter?actor=${act.id_name}">${act.name}</a></p>
			</c:forEach>		
			<h3>Generos</h3>
			<c:forEach var="gen" items="${genre}" varStatus="status">
				<p><a href = "/ApplicacionesEmpresariales/movie/filter?genre=${gen}">${gen}</a></p>
			</c:forEach>
			<c:if test="${user.isAdmin}">
				<a href = "/ApplicacionesEmpresariales/movie/editmovie?id=${movie.id}">edit</a>
			</c:if>
	</body>
	
	
	
	
	