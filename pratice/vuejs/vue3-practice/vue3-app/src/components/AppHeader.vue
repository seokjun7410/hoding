<template>
  <div>
    <h1>{{ appTitle }}</h1>
    <button @click="changeTitle">change</button>
  </div>
</template>

<script>
export default {
  props: ["appTitle"],
  methods: {
    changeTitle() {
      this.$emit("change");
    },
  },
};
</script>

<style scoped></style>
