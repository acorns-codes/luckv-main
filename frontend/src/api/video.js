import http from "@/http";

const baseUrl = "auction";

// 경매 리스트 불러오기
export function apiGetAuctionList(data) {
  const url = `/${baseUrl}/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// 비디오 업로드
export function apiUploadVideo(data) {
  const url = "/video/upload";
  const headers = { "Content-Type": "multipart/form-data" };
  return http.post(url, data, headers).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}

// 경매 등록
export function apiAddAuction(data) {
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
// 경매 수정
export function apiEditAuction(data) {
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

//  판매 종료 후 구독으로 변경
export function apiChangeAuction(data) {
  const url = `/${baseUrl}/change`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}

// 경매 상세 정보 불러오기
export function apiGetAuctionDeatil(data) {
  const url = `/${baseUrl}/detail`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
// 마감기한 불러오기?
export function apiGetAuctionDeadline(data) {
  const url = `/${baseUrl}/deadline`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}

// 비디오 리스트 목록
export function apiGetAuctionAll(data) {
  const url = `/${baseUrl}/list`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      return response.data;
    } else {
      return response;
    }
  });
}
