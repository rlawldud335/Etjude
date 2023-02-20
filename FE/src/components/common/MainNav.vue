<template lang="">
  <div>
    <nav class="header">
      <div class="header__logo-nav">
        <router-link :to="{ name: 'main' }" class="header__logo">
          <headerLogo />
        </router-link>
        <div class="header__nav">
          <router-link :to="{ name: 'search' }" class="header__nav-item">검색</router-link>
          <a
            class="header__nav-item header__dropdown-button"
            @mouseover="categoryHover"
            @focus="categoryHover"
            @mouseout="categoryUnhover"
            @focusout="categoryUnhover"
          >
            카테고리
            <ul v-show="category.isHovered" class="header__dropdown">
              <li @click="goCategory('1')">
                <span>드라마</span>
              </li>
              <li @click="goCategory('2')">
                <span>뮤지컬</span>
              </li>
              <li @click="goCategory('3')">
                <span>연극</span>
              </li>
              <li @click="goCategory('4')">
                <span>영화</span>
              </li>
            </ul>
          </a>
          <router-link :to="{ name: 'film' }" class="header__nav-item">필름 공유</router-link>
        </div>
      </div>
      <div v-if="!user">
        <router-link :to="{ name: 'login', query: { next: lastPath } }">
          <button class="header__login-button">Login</button>
        </router-link>
      </div>
      <div class="header__profile--login" v-else-if="user">
        <div class="header__profile-frame">
          <img
            class="header__profile_image"
            :src="user?.myPageSimpleResponse.userPhotoUrl"
            alt=""
          />
        </div>
        <ul class="header__profile-dropdown">
          <div class="header__profile-dropdown-title">
            <div class="header__profile-dropdown-profile-frame">
              <img :src="user?.myPageSimpleResponse.userPhotoUrl" alt="" />
            </div>
            <span>{{ user.myPageSimpleResponse.userNickName }}</span>
          </div>
          <li @click="clickProfile">
            <div class="header__dropdown-icon-section">
              <profileIcon />
            </div>
            <span>내 프로필</span>
          </li>
          <li @click="handleSignOut">
            <div class="header__dropdown-icon-section">
              <logoutIcon />
            </div>
            <span>로그아웃</span>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>
<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter, useRoute } from "vue-router";
import { handleSignOut } from "@/api/login";
import logoutIcon from "@/assets/icons/logout.svg";
import profileIcon from "@/assets/icons/profile.svg";
import headerLogo from "@/assets/icons/logo_textrow_header.svg";

export default {
  components: {
    logoutIcon,
    profileIcon,
    headerLogo,
  },
  setup() {
    const router = useRouter();
    const route = useRoute();
    const store = useStore();
    const user = computed(() => store.state.user);
    const category = reactive({ isHovered: false });
    const categoryHover = () => {
      category.isHovered = true;
    };
    const categoryUnhover = () => {
      category.isHovered = false;
    };
    const goCategory = (categoryId) => {
      router.push({ name: "search-group-page", params: { categoryId, menuId: "1", page: "1" } });
    };
    const lastPath = computed(() => {
      let path;
      if (route.query.next) {
        path = route.query.next;
      } else {
        path = route.path;
      }
      return path;
    });
    const clickProfile = () => {
      router.push({
        name: "profile",
        params: { userId: user.value.userId },
      });
    };
    return {
      category,
      clickProfile,
      categoryHover,
      categoryUnhover,
      goCategory,
      lastPath,
      user,
    };
  },
  methods: {
    handleSignOut,
  },
};
</script>
<style lang="scss">
nav {
  // padding: 16px;

  a {
    font-weight: bold;
    color: #2c3e50;
    text-decoration: none;

    &.router-link-exact-active {
      font-weight: 900;
    }
  }
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 24px;
  padding: 16px;
  text-align: center;
}

.header__logo-nav {
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.header__logo {
  color: $bana-pink;
  display: flex;
  align-items: center;
  font-weight: 700;
  font-size: 24px;
}

.header__nav {
  display: flex;
  margin-left: 5rem;
  align-items: center;
}

.header__nav-item {
  font-size: 18px;
  margin-right: 3rem;
  min-width: 80px;
}

.header__dropdown-button {
  position: relative;
  display: inline-block;
}

.header__dropdown {
  text-align: left;
  float: right;
  position: absolute;
  min-width: 140px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  z-index: 1;

  li {
    background-color: $bana-pink;
    padding: 11px 30px;
    cursor: pointer;
    span {
      font-style: normal;
      font-weight: 500;
      font-size: 16px;
      color: $aha-gray;
    }
  }
  li:nth-child(1) {
    margin-top: 5px;
    border-radius: 5px 5px 0px 0px;
  }
  li:nth-last-child(1) {
    border-radius: 0px 0px 5px 5px;
  }
  li:hover {
    background-color: #ff7b93;
    span {
      font-weight: 800;
    }
  }
}

.header__login-button {
  border: none;
  color: white;
  background-color: $bana-pink;
  width: 87px;
  height: 32px;
  border-radius: 4px;
  margin-right: 20px;
  cursor: pointer;
}
.header__profile--login {
  margin-right: 2%;
}
.header__profile-frame {
  height: 35px;
  width: 35px;
  border-radius: 50%;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  img {
    height: 100%;
    width: 100%;
    object-fit: cover;
  }
  position: relative;
}
.header__profile-dropdown {
  width: 190px;
  background: #ffffff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  border-radius: 20px;
  right: calc(2% + 10px);
  position: absolute;
  z-index: 1;
  display: flex;
  padding-bottom: 15px;
  flex-direction: column;
  display: none;
  li {
    display: flex;
    cursor: pointer;

    align-items: center;
    float: left;
    padding: 10px 20px;
    span {
      margin-left: 10px;
    }
  }
  li:hover {
    background: $efefe-gray;
    font-weight: 500;
  }
}
.header__profile-dropdown-title {
  display: flex;
  align-items: center;
  padding: 15px 20px 10px 20px;
  span {
    max-width: 100px;
    margin-left: 15px;
    font-size: 18px;
    font-weight: 500;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}
.header__profile-dropdown-profile-frame {
  height: 35px;
  width: 35px;
  min-height: 35px;
  min-width: 35px;
  border-radius: 50%;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  img {
    height: 100%;
    width: 100%;
    object-fit: cover;
  }
  position: relative;
}

.header__dropdown-icon-section {
  width: 35px;
  display: flex;
  justify-content: center;
}
.header__profile--login:hover .header__profile-dropdown {
  display: flex;
}
</style>
