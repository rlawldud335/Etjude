<template>
  <div class="film__card">
    <div class="film__card-image">
      <img src="@/assets/images/더글로리.jpg" alt="thumbnail" />
    </div>
    <div class="film__card-info">
      <div class="film__card-author-profile">
        <img src="@/assets/images/profile.jpg" alt="" />
      </div>
      <div class="film__card-body">
        <div class="film__card-title">송혜교다</div>
        <div class="film__card-author">중기</div>
        <div class="film__card-history">조회수 14K ▪ 14일전</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "FilmListItem",
};
</script>

<style lang="scss">
.film__card {
  width: 250px;
  min-width: 250px;
  margin: 20px;
  img {
    width: 100%;
  }
}

.film__card-info {
  display: flex;
  align-items: center;
}

.film__card-author-profile {
  margin: 10px;
  img {
    width: 40px;
    height: auto;
  }
}
</style>
