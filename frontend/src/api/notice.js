import http from "@/http";

const baseUrl = "notice";
// 공지 목록 가져오기
export function apiGetNoticeList(data) {
  const url = `/${baseUrl}/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// 공지 상세 내용 가져오기
export function apiGetNoticeDetail(data) {
  const url = `/${baseUrl}/detail`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// 공지 등록
export function apiAddNotice(data) {
  const url = `/insert/${baseUrl}`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}

// 공지 수정
export function apiEditNotice(data) {
  const url = `/${baseUrl}/update`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
