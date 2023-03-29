export default function getRemainingTime(lastday) {
  // 마감날짜
  const lastDayMs = new Date(lastday).getTime();
  // console.log(lastDayMs);
  // 오늘 날짜
  const today = new Date().getTime();
  // console.log(today);
  // console.log(lastDayMs, today);

  // dday 산출 값
  const time = lastDayMs - today;
  // console.log(time, "dday의 ms");

  // dday 산출을 위해 필요한 값
  const oneDay = 24 * 60 * 60 * 1000;
  const oneHour = 60 * 60 * 1000;
  const oneMinute = 60 * 1000;
  let days = time / oneDay;
  days = Math.floor(days);
  let hours = Math.floor((time % oneDay) / oneHour);
  let minutes = Math.floor((time % oneHour) / oneMinute);
  let seconds = Math.floor((time % oneMinute) / 1000);

  // console.log(seconds);
  const values = [days, hours, minutes, seconds];
  // console.log(values);
  // this.ddayList = values;

  console.log(values);
  return values;
}
