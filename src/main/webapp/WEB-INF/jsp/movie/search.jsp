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
  $(".cal").click(function(){
	    cal = $(this).val();
	    id = $(this).attr("name");
	    $.post("mark",{movieId:id, mark:cal},function(result){});
	  });

	  $(".wish").click(function(){
		id = $(this).attr("name");
		$.post("../movie/moviedetails",{name:id},function(result){
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
	
	<form method = "POST" action="search">
			
			<input type = "text" id = "keywords" name = "name"/></p>
			<input type = "submit" value = "buscar" >
	</form>
	<span>
	
	
		</span>
</body>
</html>
