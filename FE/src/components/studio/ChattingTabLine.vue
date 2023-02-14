<template lang="">
    <div class="chatting-container__line">
        <img class="line__profile-image"
          src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg" alt="" />
        <div class="line__chat">
          <span class="line__chat__nickname">{{line.nickname}}</span>
          <span class="line__chat__chat">{{line.content}}</span>
        </div>
        <div class="line__time">
          <span>{{ line.chatTime}}</span>
        </div>
      </div>
</template>
<script>
export default {
  props: { line: Object },
  setup(props) {
    console.log(props);
  }
}
</script>
<style lang="scss" scoped>
.chatting-container__line {
  display: flex;
  flex-direction: row;
  padding: 10px;
  justify-content: flex-start;
}

.line__profile-image {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  margin-right: 10px;
}

.line__chat {
  background-color: #EFEFEF;
  border-radius: 0px 15px 15px 15px;
  padding: 7px;
  display: flex;
  flex-direction: column;
}

.line__chat__nickname {
  font-size: 12px;
  margin-bottom: 5px;
}

.line__chat__chat {
  font-size: 14px;
  margin: 5px;
}

.line__time {
  display: flex;
  flex-direction: center;
  align-items: end;
  font-size: 12px;
  margin-left: 7px;
}
</style>