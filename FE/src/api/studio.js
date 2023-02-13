import { apiInstance } from "./index";

const api = apiInstance();

async function getStudioInfo(studioId, success, fail) {
    console.log("# POST : 스튜디오 정보 가져오기");
    await api.post(`/studio/info`, { studio_id: studioId }).then(success).catch(fail);
}

async function getStudioStoryScript(studioId, success, fail) {
    console.log("# POST : 스튜디오의 스토리의 스크립트");
    await api.post(`/studio/story/scripts`, { studio_id: studioId }).then(success).catch(fail);
}

async function getSceneRecordList(studioId, success, fail) {
    console.log("# POST : 스튜디오의 씬 녹화 정보 가져오기");
    await api.post(`/studio/recording`, { studio_id: studioId }).then(success).catch(fail);
}


async function getFlimList(studioId, success, fail) {
    console.log("# POST : 스튜디오의 필름 리스트 가져오기");
    await api.post(`/studio/film`, { studio_id: studioId }).then(success).catch(fail);
}

async function saveSceneRecord(record, success, fail) {
    console.log("# PUT : 씬 녹화 파일 저장하기");
    await api.post(`/studio/recording`, record).then(success).catch(fail);
}

export { getStudioInfo, getStudioStoryScript, getSceneRecordList, getFlimList, saveSceneRecord };
