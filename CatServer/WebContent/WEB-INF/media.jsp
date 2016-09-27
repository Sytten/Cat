<%@page import="communication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="head.html"%>
	<script src="js/record.js"></script>
	<title>Vidéo et Audio</title>
</head>

<body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row"> </div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<h3>Centre de contrôle</h3>
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row"> </div>
			<div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
				<a href="home" class="mdl-layout__tab ">Accueil</a> 
				<a href="feed" class="mdl-layout__tab">Nourrir & Abreuver</a> 
				<a href="media" class="mdl-layout__tab is-active">Vidéo & Audio</a> 
				<a href="about" class="mdl-layout__tab ">À propos</a>
			</div>
		</header>
		
		<main class="mdl-layout__content">
		<div class="mdl-layout__tab-panel is-active" id="video">
			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp ">
				<div class="mdl-card mdl-cell mdl-cell--12-col topCustomCard">
					<div class="customCard">
						<h5 class="customH5">Parlez à votre chat</h5>
						<button id="record" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" style="margin-right: 20px;" disabled>
							Enregistrer l'audio</button>
						<button id="stop" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" disabled>
							Envoyer</button>
					</div>
				</div>
			</section>

			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
			<div class="mdl-card mdl-cell mdl-cell--12-col">
				<div class="mdl-card__supporting-text mdl-grid mdl-grid--no-spacing">
					<iframe width="1700" height="600" name="iframe-video"
						src="http://192.168.2.3:8081/"></iframe>
				</div>
			</div>
			</section>
		</div>
		
		<footer class="mdl-mega-footer">
			<div class="mdl-mega-footer--bottom-section">
				<div class="mdl-logo">Équipe Chat © 2016</div>
			</div>
		</footer> 
		</main>
	</div>
</body>
</html>