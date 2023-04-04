// [설정 수행 실시]
///*

export default {
  install(Vue) {
    Vue.config.globalProperties.$globalFuc = function money(price) {
      let money = new Number(price);
      return money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };
  },
};

//*/
