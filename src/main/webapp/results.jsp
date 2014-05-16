<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Results</title></head>
<body>
	<c:if test="${!empty results}">
		<table>
		<c:forEach var="results" items="${results}">
			<tr><td><c:out value="${results}"/></td></tr>
		</c:forEach>
		</table>
	</c:if>
</body>
</html>