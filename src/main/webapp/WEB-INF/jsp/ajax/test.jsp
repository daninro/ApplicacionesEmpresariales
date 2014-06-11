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
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<div class = "block">
				<input class = "cal" type = "radio" name = "${movie.id}" value = "1"/>1
				<input class = "cal" type = "radio" name = "${movie.id}" value = "2"/>2
				<input class = "cal" type = "radio" name = "${movie.id}" value = "3"/>3
				<input class = "cal" type = "radio" name = "${movie.id}" value = "4"/>4
				<input class = "cal" type = "radio" name = "${movie.id}" value = "5"/>5
				<h1> ${movie.name}</h1>
				<p>Year: ${movie.year}</p>
				<p>Country: ${movie.country}</p>
			</div>
		</c:forEach>
		
	</body>
</html>
