<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="en">

<head>
<%@include file="head.html"%>
<title>À propos</title>
</head>

<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<h3>Centre de contrôle</h3>
			</div>

			<div class="mdl-layout--large-screen-only mdl-layout__header-row"></div>
			<div
				class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
				<a href="home" class="mdl-layout__tab ">Accueil</a> <a href="feed"
					class="mdl-layout__tab">Nourrir & Abreuver</a> <a href="media"
					class="mdl-layout__tab">Vidéo & Audio</a> <a href="about"
					class="mdl-layout__tab is-active">À propos</a>
			</div>
		</header>

		<main class="mdl-layout__content">
		<div class="mdl-layout__tab-panel is-active" id="home">
			<section
				class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div
					class="mdl-card mdl-cell mdl-cell--12-col-desktop mdl-cell--9-col-tablet mdl-cell--7-col-phone">
					<div class="mdl-card__supporting-text">
						<h4>Le projet</h4>
						<p align="justify">Dans le cadre d’un projet de session à
							l’Université de Sherbrooke, les étudiants en génie informatique
							devaient développer un produit qui utilisait l’Internet des
							objets. L’équipe Chat a donc décider d’utiliser deux Raspberry pi
							(un serveur et un client) pour faire de la domotique pour les
							chats. Les propriétaires de chats peuvent donc nourrir et
							abreuver leur chat, ainsi que les voir et leur envoyer des
							messages en temps réel. Le centre de contrôle de toutes les
							fonctionnalités s’accède via le Web, on peut donc utiliser le
							produit de n’importe où dans le monde.</p>
					</div>
				</div>
			</section>


			<section
				class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div class="mdl-card mdl-cell mdl-cell--12-col">
					<div
						class="mdl-card__supporting-text mdl-grid mdl-grid--no-spacing">
						<h4 class="mdl-cell mdl-cell--12-col">L'Équipe</h4>
						<div
							class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">

							<div class="circular circular_jul"></div>
						</div>
						<div
							class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
							<h4>Julien Larochelle</h4>
						</div>
						<div
							class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">
							<div class="circular circular_eml"></div>
						</div>
						<div
							class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
							<h4>Émile Fugulin</h4>
						</div>
						<div
							class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">
							<div class="circular circular_phil"></div>
						</div>
						<div
							class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
							<h4>Philippe Girard</h4>
						</div>
						<div
							class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">
							<div class="circular circular_do"></div>
						</div>
						<div
							class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
							<h4>Donavan Martin</h4>
						</div>
						<div
							class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">
							<div class="circular circular_sim"></div>
						</div>
						<div
							class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
							<h4>Simon Vallière</h4>
						</div>

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
