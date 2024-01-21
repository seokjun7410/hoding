<template>
  <div>
    <form action="" @submit.prevent="submitForm">
      <div>
        <label for="userId">ID:</label>
        <input id="userId" type="text" v-model="username">
      </div>
      <div>
        <label for="password">PW:</label>
        <input type="text" v-model="password">
      </div>
    <button type="submit">login</button>
    </form>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        username: '',
        password: '',
      }
    },
    methods: {
      submitForm(){
        // event.preventDefault();
        console.log("fsdafd");
      }
    },
  }
</script>

<style scoped>

</style>