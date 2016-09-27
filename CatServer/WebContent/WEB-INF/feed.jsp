<%@page import="communication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<%@include file="head.html"%>
	<title>Nourrir et Abreuver</title>
	</head>

<body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row"></div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<h3>Centre de contrôle</h3>
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row"></div>
			<div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
				<a href="home"  class="mdl-layout__tab ">Accueil</a> 
				<a href="feed"  class="mdl-layout__tab is-active">Nourrir & Abreuver</a> 
				<a href="media" class="mdl-layout__tab">Vidéo & Audio</a> 
			    <a href="about" class="mdl-layout__tab ">À propos</a>
			</div>
		</header>

		<main class="mdl-layout__content">
		<div class="mdl-layout__tab-panel is-active" id="feed">
			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div class="mdl-card mdl-cell mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--7-col-phone">
					<div class="mdl-card__supporting-text">
						<h4>Nourrir le chat</h4>
						Cliquez sur la bouton qui correspond à la quantité que vous avez
						besoin.

						<div>
							<button id="feed_btn1" qty="1" btnType="FOOD" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1/3 tasse</button>
							<button id="feed_btn2" qty="2" btnType="FOOD" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1/2 tasse</button>
							<button id="feed_btn3" qty="3" btnType="FOOD" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								3/4 tasse</button>
							<button id="feed_btn4" qty="4" btnType="FOOD" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1 tasse</button>
						</div>
					</div>
				</div>
			</section>

			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div class="mdl-card mdl-cell mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--7-col-phone">
					<div class="mdl-card__supporting-text">
						<h4>Abrever le chat</h4>
						Cliquez sur la bouton qui correspond à la quantité que vous avez
						besoin.

						<div>
							<button id="water_btn1" qty="1" btnType="WATER" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1/3 tasse</button>
							<button id="water_btn2" qty="2" btnType="WATER" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1/2 tasse</button>
							<button id="water_btn3" qty="3" btnType="WATER" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								3/4 tasse</button>
							<button id="water_btn4" qty="4" btnType="WATER" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent monBouton">
								1 tasse</button>
						</div>
					</div>
				</div>
			</section>
		</div>
		</main>
		<footer class="mdl-mega-footer">
			<div class="mdl-mega-footer--bottom-section">
				<div class="mdl-logo">Équipe Chat © 2016</div>
			</div>
		</footer>
	</div>
	<%@include file="buttonScript.html"%>
</body>
</html>