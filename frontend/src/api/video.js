import http from "@/http";

// 메인 비디오 검색
export function apiGetVideo(type) {
  const url = `/${type}`;
  return http.get(url).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
