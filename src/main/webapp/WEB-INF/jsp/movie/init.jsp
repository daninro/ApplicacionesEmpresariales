<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		$.post("mark",{movieId:id, mark:cal},function(result){
			$.post("ajaxcounter", function(result){
				if(result == '0')
				window.location = "/ApplicacionesEmpresariales/movie/list";
			});
		});
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
	$.post("deleteuser",{user:us},function(result){});
	});
});
	</script>

		
	</head>
	<body>
	
		<div id = "all">

			<div id = "center">
				
				
				
			<div id = "content">	
			

			<h1>Califica 10 películas</h1>
			<div class = "pag">
				<a href = "init?page=${prev}">prev</a>...<a href = "init?page=${next}">next</a>
				</div>			
			<c:forEach var="movie" items="${movieList}" varStatus="status">
				<div class = "ficha">
					<img src="${movie.image}"/>
					<div class = "top">
						<h3> ${movie.name}</h3>
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
							
						<p>Year: ${movie.year}</p>
						<p>Country: ${movie.country}</p>
						<span id = "${movie.id}">
							<c:if test="${movie.isWishlist}">
								<input type = "submit" value = "Quitar de la wishlist" class = "nowish" name = "${movie.id}">
							</c:if>
							<c:if test="${!movie.isWishlist}">
								<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
							</c:if>
						</span>
					</div>
				</div>
				
				</c:forEach>
					<div class = "pag">
					<a href = "init?page=${prev}">prev</a>...<a href = "init?page=${next}">next</a>
				</div>	
			</div>
			<div>

		</div>
	</body>
</html>