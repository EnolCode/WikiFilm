<template>
    <section>
        <div class="social-media">
            <a href="#" class="social-media__link">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#" class="social-media__link">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="#" class="social-media__link">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="#" class="social-media__link">
                <i class="fab fa-youtube"></i>
            </a>
        </div>
    </section>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

    .social-media{
		@include m.flex(flex, row, auto, end, center);
		@include m.font(default, 1.2em, map-get(c.$colors, "white"));
        width: 100%;
        font-size: 1.2em;
        padding-right: 1.3em;
        padding-bottom: 1em;

        &__link{
            margin: 0 0.5em;
            &:hover{
                @include m.pointer-opacity();
            }
        }
    }
</style>