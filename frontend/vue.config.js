// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: true,

//   pluginOptions: {
//     vuetify: {
//       // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
//     },
//   },
//   css: {
//     loaderOptions: {
//       scss: {
//         data: `
//               @import "@/styles/sass/variables.scss";
//             `,
//       },
//     },
//   },
// });

// // module.exports = {
// //   css: {
// //     loaderOptions: {
// //       scss: {
// //         prependData: `
// //           @import "@/assets/scss/variables.scss";
// //         `,
// //       },
// //     },
// //   },
// // };

module.exports = {
  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    },
  },
  css: {
    loaderOptions: {
      sass: {
        prependData: `
        @import "@/styles/sass/variables.scss";
        `,
      },
    },
  },
};
