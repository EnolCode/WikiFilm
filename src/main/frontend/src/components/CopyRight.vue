<script setup></script>

<template>
	<div class="copyright">
		<img
			class="copyright__logo"
			src="@/assets/images/logo-W.png"
			alt="logo wikifilm"
		/>
		<div class="copyright__container-text">
			<p class="copyright__text">
				© 2010-2023 Wikifilm, inc. All rights reserved.
			</p>
			<p class="copyright__text">Hecha a mano en Gijón, Asturias.</p>
		</div>
	</div>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;
	.copyright {
		display: flex;
		color: map-get(c.$colors, "white");
		background: map-get(c.$colors, "black");
        font-size: .6em;

		&__logo {
			width: 3em;
            margin-right: 1.1em;
            margin-bottom: 1em;
		}
	}
</style>
