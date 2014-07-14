
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
	  $(".nowish").click(function(){
			id = $(this).attr("name");
			
			$.post("ajaxdeletefromwishlist",{wl:id},function(result){
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
	
	<form method = "GET" action="filter" name = "filter">
			
			<input type = "text" id = "keywords" name = "name" value = "${name}"/>
			<input type = "submit" value = "buscar" >

			<select name="genre">
			<option selected disabled hidden value=''></option>
			<option value="IMAX">IMAX</option>
   			<option value="IMAX">IMAX</option>
			<option value="Comedy">Comedy</option>
			<option value="Fantasy">Fantasy</option>
			<option value="Drama">Drama</option>
			<option value="Horror">Horror</option>
			<option value="Thriller">Thriller</option>
			<option value="Film-Noir">Film-Noir</option>
			<option value="Crime">Crime</option>
			<option value="Western">Western</option>
			<option value="War">War</option>
			<option value="Musical">Musical</option>
			<option value="Short">Short</option>
			<option value="Children">Children</option>
			<option value="Adventure">Adventure</option>
			<option value="Sci-Fi">Sci-Fi</option>
			<option value="Action">Action</option>
			<option value="Documentary">Documentary</option>
			<option value="Romance">Romance</option>
			<option value="Animation">Animation</option>
			<option value="Mystery">Mystery</option> 
			</select>
			
			
			
			
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
				<a href = "/ApplicacionesEmpresariales/movie/movie?movie=${movie.id}" ><h3> ${movie.name}</h3></a>
				<p>Year: <a href = "/ApplicacionesEmpresariales/movie/filter?year=${movie.year}">${movie.year}</a></p>
				<p>Country: <a href = "/ApplicacionesEmpresariales/movie/filter?country=${movie.country}">${movie.country}</a></p>
				<img style = "float:right;" width = "90px" alt="" src="${movie.image}"/>				
				<span id = "${movie.id}">
					<c:if test="${movie.isWishlist}">
						<input type = "submit" value = "Quitar de la wishlist" class = "nowish" name = "${movie.id}">
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
