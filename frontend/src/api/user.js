import http from "@/http";

//로그인
export function apiSignIn(data) {
  const url = "/login";
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
//로그인
export function apiSignUp(data) {
  const url = "/addMember";
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
  const url = "/addMember";
  return http.post(url, data).then((response) => {
    if (response) {
      console.log(response.data);
      return response.data;
    } else {
      return response;
    }
  });
}
