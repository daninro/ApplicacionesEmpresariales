
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Search</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  /*$("#keywords").keyup(function(){
    txt=$("input").val();
    $.post("table_search",{name:txt},function(result){
      $("span").html(result);
    });
  });*/
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
	  
});
</script>
	<style type="text/css"> 
	.block{
		float: left;
		width: 300px;
		height: 300px;
		border-style:solid;
		border-color:#ff0000 #0000ff;
	}
	</style>
	
</head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
	
	<form method = "GET" action="search" name = "filter">
			
			<input type = "text" id = "keywords" name = "name" value = "${name}"/>
			<input type = "submit" value = "buscar" >
	</form>
	<span>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<div class = "block">
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

				<h3> ${movie.name}</h3>
				<p>Year: ${movie.year}</p>
				<p>Country: ${movie.country}</p>
				<span id = "${movie.id}">
					<c:if test="${movie.isWishlist}">
						<input type = "submit" value = "Quitar de la wishlist" class = "wish" name = "${movie.id}">
					</c:if>
					<c:if test="${!movie.isWishlist}">
						<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
					</c:if>
				</span>
			</div>
		</c:forEach>		
	</span>
	<a href = "search?name=${name}&page=${prev}">prev</a><a href = "search?name=${name}&page=${next}" onclick = "filter.submit()">next</a>
</body>
</html>
