import axios from "axios";
const instance = axios.create({
  baseURL: "http://localhost:8080",
});

function registerUser(userData) {
  return instance.post("/addMember", userData);
}

function loginUser(userData) {
  return instance.post("/login", userData);
}

export { registerUser, loginUser };
