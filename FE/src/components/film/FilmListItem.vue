<template>
  <div class="film__card">
    <div class="film__card-image">
      <img src="@/assets/images/더글로리.jpg" alt="thumbnail" />
    </div>
    <div class="film__card-info">
      <div class="film__card-author-profile">
        <div class="film__profile-frame">
          <img class="film__profile-image" src="@/assets/images/profile.jpg" alt="" />
        </div>
      </div>
      <div class="film__card-body">
        <span class="film__card-title">송혜교다</span>
        <span class="film__card-author">중기</span>
        <span class="film__card-history">조회수 14K ▪ 14일전</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "FilmListItem",
};
</script>

<style scoped lang="scss">
.film__card {
  width: 240px;
  min-width: 240px;
  img {
    width: 100%;
  }
}

.film__card-info {
  display: flex;
  align-items: center;
}

.film__card-author-profile {
  margin: 8px;
  img {
    width: 40px;
    height: auto;
    width: 100%;
    height: 100%;
  }
}

.film__profile-frame {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  display: flex;
}

.film__card-body {
  display: flex;
  flex-direction: column;
  span {
    margin: 1px 0px;
  }
}
.film__card-title {
  font-size: 16px;
  font-weight: 600;
  justify-content: space-between;
}
.film__card-author {
  font-size: 14px;
}
.film__card-history {
  font-size: 10px;
}
</style>
