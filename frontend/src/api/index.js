import axios from "axios";
const instance = axios.create({
  baseURL: "http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80",
});

function registerUser(userData) {
  return instance.post("/addMember", userData);
}

function loginUser(userData) {
  return instance.post("/login", userData);
}

export { registerUser, loginUser };
