import err_code from "@/utils/err_kr";

export const errorHandler = (err) => {
  if (err) {
    return alert(err_code[err]);
  } else {
    return console.error(err);
  }
};
