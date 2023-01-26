<template lang="">
  <div>
    <ul class="card-wrapper">
      <li class="card" v-for="(item, index) in testdatas" :key="index">
        <img :src="require(`@/images/profile.jpg`)" alt="" />
        <h3>
          <a href="">{{ item.comment1 }}</a>
        </h3>
        <p>{{ item.comment2 }}</p>
        <p>{{ item.img }}</p>
      </li>
    </ul>
  </div>
</template>
<script>
import testdata from "./testdata.json";

export default {
  data() {
    return {
      testdatas: testdata,
    };
  },
};
</script>
<style lang="scss">
/* // Full tutorial coming soon! */

.card {
  // --card-gradient: rgba(0, 0, 0, 0.8);
  // --card-gradient: #5e9ad9, #e271ad;
  // --card-blend-mode: overlay;

  background-color: #fff;
  border-radius: 0.5rem;
  box-shadow: 0.05rem 0.1rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45);
  padding: 2rem;
  padding-bottom: 1rem;
  background-image: linear-gradient(var(--card-gradient), white max(9.5rem, 27vh));
  overflow: hidden;

  img {
    border-radius: 0.5rem 0.5rem 0 0;
    width: 100%;
    object-fit: cover;
    /* // height: max(10rem, 25vh); */
    max-height: max(10rem, 30vh);
    aspect-ratio: 4/3;
    mix-blend-mode: var(--card-blend-mode);
    /* // filter: grayscale(100); */

    ~ * {
      margin-left: 1rem;
      margin-right: 1rem;
    }
  }

  > :last-child {
    margin-bottom: 0;
  }
}

/* Additional demo display styles */
* {
  box-sizing: border-box;
}

body {
  display: grid;
  place-content: center;
  justify-items: center;
  min-height: 100vh;
  margin: 0;
  padding: 1rem;
  line-height: 1.5;
  color: #444;
  background-color: #e1faf1;
}

.card h3 {
  margin-top: 1rem;
  font-size: 1.25rem;
}

.card a {
  color: inherit;
}

.card-wrapper {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(25%, 1fr));
  max-width: 100vw;
  width: 200ch;
  padding-left: 1rem;
  padding-right: 1rem;
}
</style>
