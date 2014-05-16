<html>
<head><title>Ejemplo 1</title></head>
<body>
  <%
    double num = Math.random();
    if (num > 0.95) {
  %>
      <h2>Has tenido suerte!</h2><p>(<%= num %>)</p>
  <%
    } else {
  %>
      <h2>Bueno, la vida sigue... </h2><p>(<%= num %>)</p>
  <%
    }
  %>
  <a href="<%= request.getRequestURI() %>"><h3>Vuelve a intentarlo!</h3></a>
</body>
</html>