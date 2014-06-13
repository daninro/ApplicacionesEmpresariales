<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Eliminar usuario</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	   $(".delete").click(function(){
		us = $(this).attr("name");
		$.post("deleteuser",{user:us},function(result){
	});
	  });
	});
</script>
</head>
	<body>
		<h3>Eliminar Usuario </h3>
		<table>
		<c:forEach var="user" items="${users}" varStatus="status">
		<tr><td>${user.username}</td>
		<td>
		<span id = "${user.username}">
		<input type = "submit" value = "eliminar" class = "delete" name = "${user.username}">
		</span>
		</td>
		</tr>
		</c:forEach>
		</table>
	</body>
</html>