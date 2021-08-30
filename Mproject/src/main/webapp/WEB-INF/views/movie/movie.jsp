<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>





<!DOCTYPE html>
<html>
<!-- 헤드는 본 문이 아닌 태그다  -->
<head>


<meta charset="UTF-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<div>
<form > 

	
	영화 : <input type = "text" class ="form-control" id="keyword" placeholder="영화 제목">
	<button type = "button" value="전송" id="btn-movies-find">검색</button>




</form>
</div>

</body>





<script>






$(function() {

	$("#btn-movies-find").on("click",()=>{
		var keyword = $('#keyword').val();

		$.ajax({ 
			type: "GET",
			url: "/api/v1/movies/"+keyword,
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
			}).done(function(res) { 
				alert(JSON.stringify(res)); 
			}).fail(function (error) { 
				alert("왜안돼?"); 
			});


		
	});

	
	

});


</script>




</html>