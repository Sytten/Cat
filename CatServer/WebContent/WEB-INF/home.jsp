<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="head.html"%>
	<title>Accueil</title>
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
				<a href="home" class="mdl-layout__tab is-active">Accueil</a> 
				<a href="feed" class="mdl-layout__tab">Nourrir & Abreuver</a> 
				<a href="media" class="mdl-layout__tab">Vidéo & Audio</a> 
				<a href="about" class="mdl-layout__tab ">À propos</a>
			</div>
			</header>
		<main class="mdl-layout__content">
		<div class="mdl-layout__tab-panel is-active" id="home">
			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp ">
			<div class="demo_card_1 demo-card-wide mdl-card mdl-shadow--2dp  mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--4-col-phone">
				<div class="mdl-card__title ">
					<h2 class="mdl-card__title-text txt_shw">Abreuver le chat</h2>
				</div>
				<div class="mdl-card__supporting-text">
					<span class="cite">“Chat échaudé craint l’eau froide.”</span>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<a href="feed" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
						Abreuver </a>
				</div>
			</div>
			</section>

			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp ">
			<div class="demo_card_2 demo-card-wide mdl-card mdl-shadow--2dp  mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--4-col-phone">
				<div class="mdl-card__title ">
					<h2 class="mdl-card__title-text txt_shw">Nourrir le chat</h2>
				</div>
				<div class="mdl-card__supporting-text">
					<span class="cite">“Chat gourmand rend la cuisinière avisée.”</span>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<a href="feed" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
						Nourrir </a>
				</div>
			</div>
			</section>

			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp ">
			<div class="demo_card_3 demo-card-wide mdl-card mdl-shadow--2dp  mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--4-col-phone">
				<div class="mdl-card__title ">
					<h2 class="mdl-card__title-text txt_shw">Vidéo du chat</h2>
				</div>
				<div class="mdl-card__supporting-text">
					<span class="cite">"Chat échaudé craint l’eau froide."</span>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<a href="media" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
						Regarder </a>
				</div>
			</div>
			</section>

			<section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp ">
			<div class="demo_card_4 demo-card-wide mdl-card mdl-shadow--2dp  mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--4-col-phone">
				<div class="mdl-card__title ">
					<h2 class="mdl-card__title-text txt_shw">Parler à votre chat</h2>
				</div>
				<div class="mdl-card__supporting-text">
					<span class="cite">“Il y a deux moyens d'oublier les tracas de la vie: la musique et les chats.”</span>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<a href="media" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
						Parler </a>
				</div>
			</div>
			</section>

		</div>
		<footer class="mdl-mega-footer">

		<div class="mdl-mega-footer--bottom-section">
			<div class="mdl-logo">Équipe Chat © 2016</div>
		</div>
		</footer> </main>
	</div>
</body>
</html>
