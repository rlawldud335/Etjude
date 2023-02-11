import { apiInstance } from "@/api/index";

const api = apiInstance();

async function getWorkInfo(workId, success, fail) {
  await api({
    method: "get",
    url: "/work/detail",
    params: {
      work_id: workId,
    },
  })
    .then(success)
    .catch(fail);
}

async function getStoriesOnWork(workId, success, fail) {
  await api({
    method: "get",
    url: "/work/detail/stories",
    params: {
      work_id: workId,
    },
  })
    .then(success)
    .catch(fail);
}

export { getWorkInfo, getStoriesOnWork };
