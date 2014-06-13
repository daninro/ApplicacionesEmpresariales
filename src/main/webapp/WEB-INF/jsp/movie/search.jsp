<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Search</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#keywords").keyup(function(){
    txt=$("input").val();
    $.post("table_search",{name:txt},function(result){
      $("span").html(result);
    });
  });
});
</script>


</head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
	
	<form method = "POST" action="search">
			
			<input type = "text" id = "keywords" name = "name"/></p>
			<input type = "submit" value = "buscar" >
	</form>
	<span>
	<table border="1" bgcolor="cyan" align="center" >
		<tr>
			<th>Name</th>
			<th>Year</th>
			<th>Country</th>
		</tr>

		<c:forEach var="movie" items="${movieList}" varStatus="status">

			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>${movie.country}</td>
			</tr>

		</c:forEach>

	</table>
	</span>
</body>
</html>
