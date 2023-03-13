import axios from "axios";
const instance = axios.create({
  baseURL: "https://run.mocky.io/v3/bf256690-9c16-44ad-9ee0-51cb26d76405",
});

function registerUser(userData) {
  return instance.post("", userData);
}

function loginUser(userData) {
  return instance.post("", userData);
}

export { registerUser, loginUser };
