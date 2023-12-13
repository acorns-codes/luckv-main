import http from "@/http";

const baseUrl = "question";

// qna 목록 가져오기
export function apiGetQnaList(data) {
  const url = `${baseUrl}/page`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
// qna 글 개수 가져오기
export function apiGetQnaCount(data) {
  const url = `/${baseUrl}/count`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
// qna 상세 내용 가져오기
export function apiGetQnaDetail(data) {
  const url = `/${baseUrl}/detail`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// qna 등록
export function apiAddQna(data) {
  const url = `/insert/${baseUrl}`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
// qna 수정
export function apiEditQna(data) {
  const url = `/${baseUrl}/update`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// qna 삭제
export function apiDeleteQna(data) {
  const url = `/${baseUrl}/delete`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// qna 댓글 목록
export function apiGetQnaAnswerList(data) {
  const url = `/${baseUrl}/answer/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// qna 댓글 등록
export function apiAddAnswer(data) {
  const url = `/${baseUrl}/answer`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// qna 댓글 수정
export function apiEditAnswer(data) {
  const url = `/${baseUrl}/answer/update`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
// qna 댓글 삭제
export function apiDeleteQnaAnswer(data) {
  const url = `/${baseUrl}/answer/delete`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
