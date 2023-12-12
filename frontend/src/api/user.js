import http from "@/http";

const baseUrl = "user";

//로그인
export function apiSignIn(data) {
  const url = `/${baseUrl}/login`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
// 회원가입
export function apiSignUp(data) {
  const url = `/${baseUrl}/join`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
// 아이디 중복체크
export function apiIdCheck(data) {
  const url = `/${baseUrl}/Idcheck`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}

// 회원정보 가져오기
export function apiGetInfoMember(data) {
  const url = `/${baseUrl}/info`;
  return http.get(url, { params: data }).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
// 회원 정보 수정
export function apiEditInfoMember(data) {
  const url = `/${baseUrl}/edit`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}

// 구독 신청
export function apiPostVideo(data) {
  const url = `/${baseUrl}/video/sub`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
// 구독 해지
export function apiDeleteVideo(data) {
  const url = `/${baseUrl}/video/subnot`;
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
