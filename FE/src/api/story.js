import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendStory(success, fail) {
  console.log("# GET : 추천 스토리 리스트");
  await api.get(`/story/recommended/popular`).then(success).catch(fail);
}

async function getStoryDetail(story, success, fail) {
  console.log("# POST : 스토리 상세 정보");
  await api.post(`/story/detail`, story).then(success).catch(fail);
}

async function getDetailAccount(storyId, success, fail) {
  console.log("# GET : 스토리 설명");
  await api({
    method: "get",
    url: `/story/detail/desc`,
    params: {
      story_id: storyId,
    },
  })
    .then(success)
    .catch(fail);
}

async function getDetailRules(storyId, success, fail) {
  console.log("# GET : 배역 설명");
  await api({
    method: "get",
    url: `/story/detail/roles`,
    params: {
      story_id: storyId,
    },
  })
    .then(success)
    .catch(fail);
}

async function getDetaileScript(storyId, success, fail) {
  console.log("# GET : 스크립트");
  await api({
    method: "get",
    url: `/story/detail/scripts`,
    params: {
      story_id: storyId,
    },
  })
    .then(success)
    .catch(fail);
}

async function createStudio(info, success, fail) {
  console.log("# POST : 스튜디오 생성");
  await api.post(`/studio/create`, info).then(success).catch(fail);
}

export {
  getRecommendStory,
  getStoryDetail,
  getDetailAccount,
  getDetailRules,
  getDetaileScript,
  createStudio,
};
