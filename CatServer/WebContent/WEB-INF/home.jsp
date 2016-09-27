<%@page import="communication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">


<link rel="stylesheet" type="text/css" href="css/chat-base.css">

<!-- http://purecss.io/start/ -->
<title>Domotique pour chat</title>
</head>

<body>
	
	<h1 class="cat_h1 text_effect">Domotique pour chat</h1>
	
	<div class="row cat-panel">
				
		<div class="col-md-5 cat-left-panel">
			<div>
				<img src="img/food.png" width=150px class="cat-img" />
			</div>
			<div class="cat-button-wrap">
				<button type="button" class="peter-river-flat-button" id="feed_btn">Donner
					de la nourriture au chat</button>
			</div>
			<div>
				<img src="img/water.png" width=100px class="cat-img" />
			</div>
			<div class="cat-button-wrap">
				<button type="button" class="peter-river-flat-button" id="water_btn">Donner
					de l'eau au chat</button>
			</div>
		</div>
		<div class="col-md-7 cat-right-panel">
			<h2 class="cat-h2">Votre chat en direct</h2>
			<img src="img/tmp_stream.jpg" width=100%/>
			<!-- <div class="video-box embed-responsive embed-responsive-16by9">
				<iframe width="420" height="315"
					src="https://www.youtube.com/embed/H4aBEE8iDDU" frameborder="0"
					allowfullscreen></iframe>
			</div>
			
			-->
		</div>
		
	</div>
	<footer>Produit par l'équipe 1 © ∫ <img src="img/hellogiphy.gif" width=30px /></footer>	
	
	<strong id="ip-box"></strong>
	<strong id="msg_box"></strong>

	<script type="text/javascript" src="js/jquery2.2.4.js">
		
	</script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>


	<script>
		var q_url = "http://192.168.1.115:8080/CatServer/home";
		$("#ip-box").text(q_url);

		$("#feed_btn").click(function() {
			console.log("Food");
			$("#feed_btn").attr("disabled", true);
			$("#water_btn").attr("disabled", true);
			$("#msg_box").text("Food");
			$.ajax({
				type : "POST",
				url : q_url,
				data : 'cat=FOOD',
				success : function(response) {
					$("#msg_box").text("Food = sucess");
					$("#feed_btn").attr("disabled", false);
					$("#water_btn").attr("disabled", false);
				}
			});

		});

		$("#water_btn").click(function() {
			console.log("Water");
			$("#water_btn").attr("disabled", true);
			$("#feed_btn").attr("disabled", true);
			$("#msg_box").text("Water");
			$.ajax({
				type : "POST",
				url : q_url,
				data : 'cat=WATER',
				success : function(response) {
					$("#msg_box").text("Water = sucess");
					$("#water_btn").attr("disabled", false);
					$("#feed_btn").attr("disabled", false);
				}
			});
		});
	</script>
</body>
</html>