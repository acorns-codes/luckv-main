import axios from "axios";

const instance = axios.create({
  baseURL: "https://run.mocky.io/v3/bf256690-9c16-44ad-9ee0-51cb26d76405",
});

function resiseteUser(userData) {
  return instance.post("signup", userData);
}

function loginUser(userData) {
  return instance.post("login", userData);
}

export { resiseteUser, loginUser };
