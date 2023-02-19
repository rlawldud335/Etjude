<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="title">스튜디오 생성하기</div>
    <div class="show-modal__body">
      <div class="team_name_container">
        <div class="title_Name">팀 이름</div>
        <input
          class="input_text"
          @input="studiodata.studio_title = $event.target.value"
          placeholder="팀명을 입력해주세요."
        />
      </div>
      <div class="member_container">
        <div class="title_Name">팀원 추가하기</div>
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
          <div class="studio-modal__email-result-dropdown" v-if="emailSearchResult?.length > 0">
            <div class="studio-modal__dropdown-list">
              <ul>
                <li
                  v-for="(user, index) in emailSearchResult"
                  :key="user.user_id"
                  :user="user"
                  :index="index"
                  @mousedown="addMember(user)"
                  :class="[
                    'studio__email-list',
                    {
                      'last-list': index === emailSearchResult.length - 1,
                      'first-list': index === 0,
                    },
                  ]"
                >
                  <div class="studio__email-profile-frame">
                    <img :src="user.picture" alt="" />
                  </div>
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
            <span class="studio-modal__member-nickname">
              {{ member.nickname }}
            </span>
            <div class="studio-modal__icon-container">
              <deleteButton class="studio-modal__delete-button" @click="deleteMember(member)" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button_container" @click="$emit('close')">
      <button @click="create">생성하기</button>
    </div>
  </vue-final-modal>
</template>
<script>
import { createStudio } from "@/api/story";
import { reactive, ref, computed } from "vue";
import { useRouter } from "vue-router";
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
    const router = useRouter();
    const userId = computed(() => store.state.user.userId);

    const emailData = reactive({
      user_id: userId.value,
      keyword: "",
    });
    const emailSearchResult = ref([]);
    const viewemail = reactive({
      keyword: [],
    });
    const selectedMember = ref([]);
    const studiodata = reactive({
      user_id: userId.value,
      studio_title: "",
      story_id: props.story_id,
      team_member_Ids: computed(() => selectedMember.value.map((member) => member.user_id)),
    });
    const searchEmail = (event) => {
      emailData.keyword = event.target.value;
      if (emailData.keyword) {
        getUserSearch(
          emailData,
          ({ data }) => {
            emailSearchResult.value = data.slice(0, 10);
          },
          (error) => {
            console.log("이메일 검색 에러:", error);
          }
        );
      }
    };
    const blurInput = () => {
      emailSearchResult.value = "";
    };
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
          router.push({ name: "studio", params: { studioId: data } });
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
  height: 440px;
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
  top: 244px;
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
        line-height: 36px;
        font-size: 12px;
      }
    }
    li:hover {
      background: $soft-bana-pink;
      font-weight: 500;
    }
    .first-list {
      border-top: 1px solid $efefe-gray;
    }
    .last-list {
      border-radius: 0px 0px 10px 10px;
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
  position: absolute;
  left: 50%;
  bottom: 8%;
  align-content: center;
  text-align: center;
  transform: translate(-50%);
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
  min-height: 36px;
  max-height: 65px;
  overflow: auto;
  background-color: white;
  padding: 6px 8px;
  box-sizing: border-box;
  color: $bana-pink;
  flex-wrap: wrap;
  margin-top: 20px;
  border-radius: 14px;
  display: flex;
  flex-direction: row;
  gap: 5px;
  align-items: center;
  border: gray solid 2px;
}
.member_list::-webkit-scrollbar {
  margin: 3px 0px;
  // height: 70%;
  width: 5px;
}
.studio__email-list {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.member_list::-webkit-scrollbar-thumb {
  background-color: $bana-pink;
}

.studio__email-profile-frame {
  height: 22px;
  width: 22px;
  border-radius: 50%;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  box-sizing: content-box;
  margin: 2px 8px;
  img {
    height: 100%;
    width: 100%;
    object-fit: cover;
  }
}
.studio-modal__member-nickname {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.studio-modal__icon-container {
  height: 15px;
  width: 15px;
}
.studio-modal__selected {
  height: 12px;
  max-width: 100px;
  border: $bana-pink solid 1px;
  border-radius: 8px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  padding: 4px 6px;
  gap: 2px;
  background: rgba(255, 87, 117, 0.4);
  span {
    font-size: 12px;
    white-space: nowrap;
  }
}
</style>
