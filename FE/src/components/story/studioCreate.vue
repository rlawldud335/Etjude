<!-- eslint-disable import/no-extraneous-dependencies -->
<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <!-- <div> -->
    <div class="title">스튜디오 생성하기</div>
    <div class="show-modal__body">
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
        <div class="studio-modal__email">
          <label for="studio-email" class="studio-modal__email-label"
            >이메일

            <input
              class="input_text"
              type="text"
              id="studio-email"
              @blur="blurInput"
              @focus="searchEmail"
              @input="searchEmail"
              :value="emailData.keyword"
              placeholder="이메일을 입력해주세요."
              autocomplete="off"
            />
          </label>
          <div class="studio-modal__email-result-dropdown">
            <div class="studio-modal__dropdown-list">
              <ul v-for="user in emailSearchResult" :key="user.user_id" :user="user">
                <li @mousedown="addMember(user)">
                  <span>{{ user.user_email }}</span>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="member_list">
          <div
            class="studio-modal__selected"
            v-for="(member, index) in selectedMember"
            :key="index"
            :member="member"
          >
            <span> 팀원 {{ index + 1 }} </span>
            <deleteButton class="studio-modal__delete-button" @click="deleteMember(member)" />
          </div>
        </div>
      </div>
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
import { reactive, ref, computed } from "vue";
// import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { getUserSearch } from "@/api/users";
import deleteButton from "@/assets/icons/memberdelete.svg";

export default {
  name: "studioCreate",
  components: {
    deleteButton,
  },
  props: {
    story_id: Number,
  },
  setup(props) {
    const store = useStore();
    // const router = useRouter();
    const emailData = reactive({
      keyword: "",
    });
    const emailSearchResult = ref([]);
    const viewemail = reactive({
      keyword: [],
    });
    const selectedMember = ref([]);
    const studiodata = reactive({
      user_id: store.state.user.userId,
      studio_title: "",
      story_id: props.story_id,
      team_member_Ids: computed(() => selectedMember.value.map((member) => member.user_id)),
    });
    const searchEmail = (event) => {
      emailData.keyword = event.target.value;

      getUserSearch(
        emailData,
        ({ data }) => {
          // console.log(data);
          // console.log(selectedMember.value);
          // const unSelectedData = data.filter((user) => selectedMember.value.indexOf(user) < 0);
          // console.log(emailSearchResult.value);
          emailSearchResult.value = data;
        },
        (error) => {
          console.log("이메일 검색 에러:", error);
        }
      );
    };
    const blurInput = () => {
      emailSearchResult.value = "";
    };
    // const membersNames = ref([])
    const addMember = (user) => {
      if (studiodata.team_member_Ids.indexOf(user.user_id) === -1) {
        selectedMember.value.push(user);
      }
      emailData.keyword = "";
      emailSearchResult.value = "";
    };
    const deleteMember = (member) => {
      if (selectedMember.value.indexOf(member) !== -1) {
        selectedMember.value.splice(member, 1);
      }
    };
    const create = () => {
      createStudio(
        studiodata,
        ({ data }) => {
          console.log(data, "생성 완료");
          // console.log(studiodata);
          // router.push({ name: "studio", params: { studioId: data.studio_id } });
        },
        (error) => {
          console.log("스튜디오 생성 오류:", error);
        }
      );
      emailData.keyword = "";
      studiodata.studio_title = "";
    };
    return {
      studiodata,
      emailData,
      addMember,
      searchEmail,
      blurInput,
      selectedMember,
      emailSearchResult,
      deleteMember,
      create,
      viewemail,
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
  height: 420px;
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
.show-modal__body {
  margin: 10px 20px;
}
.team_name_container {
  max-width: 100%;
  margin-bottom: 20px;
}
.title_Name {
  font-weight: 500;
  font-size: 16px;
  line-height: 27px;
  margin-bottom: 10px;
}
.input_text {
  width: 100%;
  font-size: 12px;
  box-sizing: border-box;
  background-color: white;
  padding: 6px 8px;
  border-radius: 10px;
  align-items: center;
  border: $bana-pink solid 2px;
  justify-content: space-between;
  color: #606060;
  font-size: 14px;
  position: relative;
}
.studio-modal__email-label {
  display: flex;
  flex-direction: row;
  white-space: nowrap;
  align-items: center;
  font-size: 14px;
  input {
    margin-left: 10px;
  }
}
.studio-modal__email-result-dropdown {
  position: absolute;
  background-color: white;
  left: 88.5px;
  top: 248px;
  width: calc(100% - 132.5px);
  display: flex;
  flex-direction: column;
  z-index: 1;
  border: #ff5775 2px solid;
  border-top: none;
  border-radius: 0px 0px 10px 10px;
  ul {
    li {
      height: 36px;
      span {
        padding: 6px 8px;
        line-height: 36px;
        font-size: 12px;
      }
    }
    li:hover {
      background: $soft-bana-pink;
      font-weight: 500;
    }
  }
}
.studio-modal__dropdown-list {
  margin-top: 10px;
}
.inputButton {
  margin-left: 10px;
  padding: 5px 10px;
  gap: 5px;
  background: #ff5775;
  border-radius: 4px;
  color: white;
  border: none;
  cursor: pointer;
}
.studio-modal__delete-button {
  cursor: pointer;
}
.emailData {
  padding: 3px;
  font-size: 14px;
  font-weight: 500;
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
    border-radius: 8px;
    color: white;
    border: none;
    cursor: pointer;
  }
}
.member_list {
  width: 100%;
  height: 36px;
  background-color: white;
  padding: 6px 8px;
  box-sizing: border-box;
  color: $bana-pink;
  margin-top: 20px;
  border-radius: 14px;
  display: flex;
  flex-direction: row;
  gap: 5px;
  align-items: center;
  border: gray solid 2px;
}
.studio-modal__selected {
  height: 12px;
  max-width: 50px;
  border: $bana-pink solid 1px;
  border-radius: 8px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  padding: 4px 4px;
  gap: 2px;
  background: rgba(255, 87, 117, 0.4);
  span {
    font-size: 12px;
    white-space: nowrap;
  }
}
</style>
