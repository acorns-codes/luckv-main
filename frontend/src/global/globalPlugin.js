// [설정 수행 실시]
///*

export default {
  install(Vue) {
    Vue.config.globalProperties.$globalFuc = function money(price) {
      let money = new Number(price);
      return money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };
    Vue.config.globalProperties.$totalPage = function totalpage(cnt) {
      if (cnt == 0) {
        // 현재 게시판 글 갯수가 0개일때 총 페이지가 0이 되는거 방지
        return 1;
      } else {
        return Math.ceil(cnt / 10);
        // (글 갯수/10)한 후 올림 연산을 통해 총 페이지 계산
      }
    };
  },
};

//*/
