
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<div class = "block">
				<input class = "cal" type = "radio" name = "${movie.id}" value = "1"/>1
				<input class = "cal" type = "radio" name = "${movie.id}" value = "2"/>2
				<input class = "cal" type = "radio" name = "${movie.id}" value = "3"/>3
				<input class = "cal" type = "radio" name = "${movie.id}" value = "4"/>4
				<input class = "cal" type = "radio" name = "${movie.id}" value = "5"/>5
				<h3> ${movie.name}</h3>
				<p>Year: ${movie.year}</p>
				<p>Country: ${movie.country}</p>
				<span id = "${movie.id}">
					<input type = "submit" value = "Agregar a Wishlist" class = "wish" name = "${movie.id}">
				</span>
			</div>
		</c:forEach>		
