import axios from "axios";
const instance = axios.create({
  baseURL: "http://localhost:80",
});

function registerUser(userData) {
  return instance.post("/addMember", userData);
}

function loginUser(userData) {
  return instance.post("/login", userData);
}

export { registerUser, loginUser };
