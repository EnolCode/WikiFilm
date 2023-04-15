<script setup>
	import SocialMedia from "@/components/SocialMedia.vue";
</script>

<template>
	<footer class="footer">
		<div class="footer__newsletter">
			<h2 class="footer__newsletter__title">
				Unete a nuestra Newsletter para recibir las ultimas noticias
				del mundo audiovisual.
			</h2>
			<form class="footer__newsletter__form">
				<input
					type="email"
					class="footer__newsletter__input"
					placeholder="Your email"
				/>
				<button class="footer__newsletter__button">Subscribe</button>
			</form>
		</div>

        <nav class="footer__nav">
            <ul class="footer__nav__list">
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">HOME</a>
                </li>
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">ABOUT</a>
                </li>
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">CONTACT</a>
                </li>
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">BLOG</a>
                </li>
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">TERMS</a>
                </li>
                <li class="footer__nav__list__item">
                    <a href="#" class="footer__nav__list__item__link">PRIVACY</a>
                </li>
            </ul>

            <SocialMedia />
        </nav>

            
        
	</footer>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.footer {
		width: 100%;
		background: map-get(c.$colors, "red");
		margin-top: 3em;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

		&__newsletter {
			display: flex;
			flex-direction: column;
			justify-content: center;

			&__title {
				color: map-get(c.$colors, "white");
				font-size: 1.1em;
			}

			&__form {
				display: flex;
				flex-direction: row;
				margin-top: 1em;
			}
			&__input {
				background: map-get(c.$colors, "white");
			}

			&__button {
				width: 10em;
				height: 2em;
				border-radius: 0.5em;
				border: none;
				background: map-get(c.$colors, "white");
				color: map-get(c.$colors, "red");
				font-weight: bold;
				margin-left: 1em;
			}
		}

        &__nav{
            display: flex;
            flex-direction: row;
            font-size: .8em;
            justify-content: space-between;
            align-items: center;
            background: map-get(c.$colors, "black");
            width: 100%;
            padding: 1em;

            &__list{
                display: flex;
                gap: 1em;

                &__item{
                    &__link{
                        color: map-get(c.$colors, "white");
                    }
                }
            }
        }
	}
</style>
