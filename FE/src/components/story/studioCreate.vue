<!-- eslint-disable import/no-extraneous-dependencies -->
<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <!-- <div> -->
    <div class="title">스튜디오 생성하기</div>
    <div class="team_name_container">
      <div class="title_Name">팀이름</div>
      <input
        class="input_text"
        @input="studiodata.studio_title = $event.target.value"
        placeholder="팀명을 입력해주세요."
      />
    </div>
    <div class="member_container">
      <div class="title_Name">팀원추가하기</div>
      <input class="input_text" placeholder="ID를 입력해주세요." />
      <!-- <inputDropdown></inputDropdown> -->
      <div class="member_list"></div>
    </div>
    <div class="button_container" @click="$emit('close')">
      <button @click="create">생성하기</button>
    </div>
    <!-- </div> -->
  </vue-final-modal>
</template>
<script>
// import QuitButton from "@/assets/icons/Quit Button.svg";
import { createStudio } from "@/api/story";
import { reactive } from "vue";
import { useStore } from "vuex";

export default {
  name: "studioCreate",
  components: {
    // inputDropdown,
    // QuitButton,
  },
  props: {
    story_id: Number,
  },
  setup(props) {
    const store = useStore();
    const studiodata = reactive({
      user_id: store.state.user.userId,
      studio_title: "",
      story_id: props.story_id,
      team_member_Ids: [],
    });
    const create = () => {
      createStudio(
        studiodata,
        ({ data }) => {
          console.log(data, "생성 완료");
        },
        (error) => {
          console.log(error);
          console.log(studiodata);
        }
      );
    };
    return {
      studiodata,
      create,
    };
  },
};
</script>
<style lang="scss" scoped>
::v-deep .modal-contain {
  display: flex;
  justify-content: center;
  align-items: center;
}
::v-deep .modal-cont {
  display: flex;
  position: relative;
  flex-direction: column;
  width: 450px;
  height: 402px;
  box-sizing: border-box;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  border-radius: 20px;
  background-color: white;
}
.title {
  font-weight: 700;
  font-size: 18px;
  line-height: 23px;
  text-align: center;
  margin: 20px;
}
.team_name_container {
  margin-bottom: 20px;
}
.title_Name {
  font-weight: 500;
  font-size: 16px;
  line-height: 27px;
  margin-bottom: 10px;
}
.input_text {
  background-color: white;
  padding: 6px 8px;
  border-radius: 10px;
  align-items: center;
  border: $bana-pink solid 2px;
  justify-content: space-between;
  color: #606060;
  margin-bottom: 5px;
  font-size: 14px;
}
.button_container {
  text-align: center;
  position: absolute;
  left: 50%;
  bottom: 8%;
  transform: translate(-50%);
  align-content: center;
  button {
    width: 158px;
    height: 38px;
    padding: 5px 10px;
    gap: 5px;
    background: #ff5775;
    border-radius: 4px;
    color: white;
    border: none;
    cursor: pointer;
  }
}
</style>
