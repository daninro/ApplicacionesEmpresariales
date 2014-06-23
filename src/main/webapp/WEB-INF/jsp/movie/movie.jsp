
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
			$.post("../movie/moviedetails",{name:id},function(result){
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
		<input class = "cal" type = "radio" name = "${movie.id}" value = "1"/>1
		<input class = "cal" type = "radio" name = "${movie.id}" value = "2"/>2
		<input class = "cal" type = "radio" name = "${movie.id}" value = "3"/>3
		<input class = "cal" type = "radio" name = "${movie.id}" value = "4"/>4
		<input class = "cal" type = "radio" name = "${movie.id}" value = "5"/>5
		
		<img alt="" src="${movie.image}">
		<h3> ${movie.name}</h3>
		<p>Year: ${movie.year}</p>
		<p>Country: ${movie.country}</p>
		<p>Director: ${movie.director}</p>
		<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
	
		
			<div class = "block">
				
				<h3> ${movie.name}</h3>
				<p>Year: ${movie.year}</p>
				<p>Country: ${movie.country}</p>
				<p>Director: ${movie.country}</p>
			</div>
			<h3>Actores</h3>
			<c:forEach var="actor" items="${actors}" varStatus="status">
				<p>${actor.name}</p>
			</c:forEach>		
	</body>
	
	
	
	
	