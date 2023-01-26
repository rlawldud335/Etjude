<template>
  <h2>로그인</h2>
  <button @click="login">
    google로 로그인하기
  </button>
</template>

<script>
import axios from 'axios'

const ARI_URL = ''

export default {
  name: "LoginView",
  data() {
    return {};
  },
  computed: {
    login() {
      axios({
        method: 'get',
        url: '{API_URL}',
				params: {},
				data: {},
      });
				.then((response) => {
          console.log(response)
        })
				.catch((error) => {
					console.log(error)
				})
    },
  },
};
</script>
<style lang="scss" scoped></style>
