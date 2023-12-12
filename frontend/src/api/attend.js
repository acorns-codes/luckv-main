import http from "@/http";

const baseUrl = "attend";

// 경매 참가 목록 가져오기
export function apiGetAttendList(data) {
  const url = `/${baseUrl}/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// 경매 참가
export function apiAddAttend(data) {
  const url = `/${baseUrl}/insert`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
