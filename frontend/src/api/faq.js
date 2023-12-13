import http from "@/http";

const basePath = "frequently";

// FAQ 목록 가져오기
export function apiGetFaqList(data) {
  const url = `/${basePath}/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// FAQ 상세 정보
export function apiGetFaqDetail(data) {
  const url = `/${basePath}/detail`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// FAQ 등록
export function apiAddFaq(data) {
  const url = `/insert/${basePath}`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// FAQ 삭제
export function apiDelteFaq(data) {
  const url = `${basePath}/delete`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// FAQ 수정
export function apiEditFaq(data) {
  const url = `/${basePath}/update`;
  return http.post(url, data).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
