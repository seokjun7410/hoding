<template>
  <app-header 
  v-bind:appTitle="message"
  v-on:change="changeMessage"
  ></app-header>
  <div>
    {{message}}
  </div>
  <button @click="showAlert">warn</button>
</template>

<script>
import AppHeader from './components/AppHeader.vue'
  export default {
    components: {
      'app-header' : AppHeader
    },
    data() {
      return {
        message: 'app header components'
      }
    },
    methods:{
      showAlert(){
        alert('heelo')
      },
      changeMessage(){
        this.message = 'changed'
      }
    }
  }
</script>

<style scoped>

</style>