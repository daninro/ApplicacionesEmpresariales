<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>List of movies</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#wish").click(function(){
    txt=${movieDetails.id};
    $.post("ajaxdelete",{name:txt},function(result){
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

	<h3>${movieDetails.name}</h3>

	<table border="1" bgcolor="cyan" align="center" >
		<tr>
			<th>Name</th>
			<th>Year</th>
			<th>Country</th>
		</tr>
		<tr>
			<td>${movieDetails.name}</td>
			<td>${movieDetails.year}</td>
			<td>${movieDetails.country}</td>
		</tr>
	</table>
	<span>
		<p>
<!--  				<input type = "hidden" value = "${movieDetails.id}" id="nameid"> -->
				<input type = "submit" value = "Agregar a Wishlist" id="wish">
		</p>
	</span>
</body>
</html>
