asas

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Ejemplo 2</title></head>
<body>
	<c:if test="${!empty movies}">
		<table>
		<c:forEach var="movie" items="${movies}">
			<tr><td><c:out value="${movie}"/></td></tr>
		</c:forEach>
		</table>
	</c:if>
</body>
</html>