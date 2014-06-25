
<html>
<head><title>Registro</title></head>
<body>
<ul>
		<li><a href = "/ApplicacionesEmpresariales/user/index">Principal</a></li>
	</ul>
    <h1>Añadir película</h1>
    <h2>Datos Generales</h2>
 	<form method = "POST" action="addmovie">
 		<table>
 			<tr>
				<td><label for = "keywords">Nombre</label></td>
				<td><input type = "text" id = "keywords" name = "name"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Año</label></td>
				<td><input type = "text" id = "keywords" name = "year"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Pais</label></td>
				<td><input type = "text" id = "keywords" name = "country"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Imagen</label></td>
				<td><input type = "text" id = "keywords" name = "image"/></td>
			</tr>
			<tr>
				<td><label for = "keywords">Director</label></td>
				<td><input type = "text" id = "keywords" name = "director_name"/></td>
			</tr>
			
			<tr>
				<td><label for = "keywords">Director_id</label></td>
				<td><input type = "text" id = "keywords" name = "director_id"/></td>
			</tr>
			
			
		</table>
		<h2>Géneros</h2>
		<table>
			<tr>
				<td><input type="checkbox" name="genre" value="IMAX">IMAX</td>
				<td><input type="checkbox" name="genre" value="Comedy"> Comedy</td>
				<td><input type="checkbox" name="genre" value="Fantasy"> Fantasy</td>
				<td><input type="checkbox" name="genre" value="Drama"> Drama</td>
				<td><input type="checkbox" name="genre" value="Horror"> Horror</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="genre" value="Thriller"> Thriller</td>
				<td><input type="checkbox" name="genre" value="Film-Noir"> Film-Noir</td>
				<td><input type="checkbox" name="genre" value="Crime"> Crime</td>
				<td><input type="checkbox" name="genre" value="Western"> Western</td>
				<td><input type="checkbox" name="genre" value="War"> War</td>
				
			</tr>
			<tr>
				<td><input type="checkbox" name="genre" value="Musical"> Musical</td> 
				<td><input type="checkbox" name="genre" value="Short"> Short</td>
				<td><input type="checkbox" name="genre" value="Children"> Children
				<td><input type="checkbox" name="genre" value="Adventure"> Adventure</td>
				<td><input type="checkbox" name="genre" value="Sci-Fi"> Sci-Fi</td>
			</tr>
			<tr>
				
				<td><input type="checkbox" name="genre" value="Action"> Action</td>
				<td><input type="checkbox" name="genre" value="Documentary"> Documentary</td>
				<td><input type="checkbox" name="genre" value="Romance"> Romance</td>
				<td><input type="checkbox" name="genre" value="Animation"> Animation</td>
				<td><input type="checkbox" name="genre" value="Mystery"> Mystery</td>
				
			</tr>
			<tr>
			<td>
				<input type = "submit" value = "agregar" >
			</td>
			</tr>
		</table>
	</form>
<form>


				
	
</body>
</html>