import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendStory(success, fail) {
  await api.get(`/story/recommended/popular`).then(success).catch(fail);
}

async function getStoryDetail(story, success, fail) {
  await api.post(`/story/detail`, story).then(success).catch(fail);
}

async function getDetailAccount(storyId, success, fail) {
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
