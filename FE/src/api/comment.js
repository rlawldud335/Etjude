import { apiInstance } from "./index";

const api = apiInstance();

async function postComment(data, success, fail) {
  await api({
    method: "post",
    url: "/comment",
    data,
  })
    .then(success)
    .catch(fail);
}

async function putComment(data, success, fail) {
  await api({
    method: "put",
    url: "/comment",
    data,
  })
    .then(success)
    .catch(fail);
}

async function deleteComment(data, success, fail) {
  await api({
    method: "delete",
    url: "/comment",
    data,
  })
    .then(success)
    .catch(fail);
}

export { postComment, putComment, deleteComment };
