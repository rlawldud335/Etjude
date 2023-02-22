import { apiInstance } from "./index";

const api = apiInstance();

async function getStudioInfo(studioId, success, fail) {
  await api.post(`/studio/info`, { studio_id: studioId }).then(success).catch(fail);
}

async function getStudioStoryScript(studioId, success, fail) {
  await api.post(`/studio/story/scripts`, { studio_id: studioId }).then(success).catch(fail);
}

async function getSceneRecordList(studioId, storyId, success, fail) {
  await api
    .post(`/studio/recording`, { studio_id: studioId, story_id: storyId })
    .then(success)
    .catch(fail);
}

async function getFlimList(studioId, success, fail) {
  await api.post(`/studio/film`, { studio_id: studioId }).then(success).catch(fail);
}

async function saveSceneRecord(record, success, fail) {
  await api.put(`/studio/recording`, record).then(success).catch(fail);
}

export { getStudioInfo, getStudioStoryScript, getSceneRecordList, getFlimList, saveSceneRecord };
