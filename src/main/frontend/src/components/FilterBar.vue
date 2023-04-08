<script setup>
	import { ref, watch } from "vue";
	import { defineProps, defineEmits } from "vue";

	const props = defineProps({
		modelValue: String,
	});

	const emit = defineEmits(["update:modelValue"]);

	const searchTitle = ref(props.modelValue);
	watch(searchTitle, newValue => {
		emit("update:modelValue", newValue);
	});
</script>

<template>
	<div class="filter">
		<i
			class="fa-solid fa-magnifying-glass header__search"
			style="color: #ffffff"
		></i>
		<input
			type="text"
			name="filter"
			class="filter__input"
			placeholder="Búsqueda por título"
			v-model="searchTitle"
		/>
	</div>
</template>

<style lang="scss">
	.filter {
		display: flex;
		align-items: center;
		justify-content: space-between;
		width: 10%;

		&__input {
			color: white;
			padding: 0.1em;
			outline: none;

			&:focus {
				border-bottom: 1px solid white;
			}
		}
	}
</style>
